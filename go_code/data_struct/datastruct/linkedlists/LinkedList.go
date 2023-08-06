package linkedlists

import (
	"datastructutil/datastruct/datastructerrors"
	"datastructutil/datastruct/datastructinterfaces"
	"datastructutil/datastruct/sets"
	"datastructutil/utils/arrayutils"
	"fmt"
	"runtime"
	"sort"
	"strings"
	"sync"
)

type LinkedList[E any] interface {
	datastructinterfaces.Collection[E]
	// ** Public **
	// get
	GetAt(index int) (element E)
	GetFirst() (element E)
	Peek() (element E)

	// get index
	IndexOf(target E, startIndex int, reverse bool) (targetIndex int)

	// add
	AddAt(index int, elementArr ...E)
	AddFirst(elementArr ...E)

	// set
	SetAt(index int, newElement E) (oldElement E)

	// remove
	RemoveAt(index int) (element E)
	RemoveFirst() (element E)
	Pop() (element E)

	// operation
	Filter(filterFunc func(element E) (doKeep bool)) (newList LinkedList[E])
	SortBy(lessFunc func(a E, b E) (isLess bool))

	ToString() (str string)

	// ** Private **
	createLinkedListNode(elementArr ...E) (length int, headNode *linkedListNode[E], tailNode *linkedListNode[E])
	getNodeAt(index int) (node *linkedListNode[E])
	linkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E])
	unlinkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E])
	clearNode(node *linkedListNode[E])
	forEachNode(startIndex int, reverse bool, callback func(node *linkedListNode[E]) (doBreak bool))
	removeNode(node *linkedListNode[E])
}

type linkedListNode[E any] struct {
	pre     *linkedListNode[E]
	next    *linkedListNode[E]
	element *E
}

type linkedList[E comparable] struct {
	// LinkedList[E]
	mutex    *sync.Mutex
	headNode *linkedListNode[E]
	tailNode *linkedListNode[E]
	length   int
}

func NewLinkedList[E comparable](elementArr ...E) LinkedList[E] {
	list := &linkedList[E]{
		mutex:  new(sync.Mutex),
		length: 0,
	}
	runtime.SetFinalizer(list, func(l *linkedList[E]) {
		l.Clear()
	})

	list.Add(elementArr...)
	return list
}

func (l *linkedList[E]) GetAt(index int) (element E) {
	return *(l.getNodeAt(index).element)
}

func (l *linkedList[E]) GetFirst() (element E) {
	if l.IsEmpty() {
		err := datastructerrors.NewIsEmptyError()
		panic(err)
	}
	return l.GetAt(0)
}

func (l *linkedList[E]) Peek() (element E) {
	if l.IsEmpty() {
		err := datastructerrors.NewIsEmptyError()
		panic(err)
	}
	return l.GetAt(l.length - 1)
}

func (l *linkedList[E]) IndexOf(target E, startIndex int, reverse bool) (targetIndex int) {
	targetIndex = -1

	var tempIndex = startIndex
	l.forEachNode(startIndex, reverse, func(node *linkedListNode[E]) (doBreak bool) {
		if target == *(node.element) {
			targetIndex = tempIndex
			return true
		}
		if !reverse {
			tempIndex++
		} else {
			tempIndex--
		}
		return false
	})
	return targetIndex
}

func (l *linkedList[E]) AddAt(index int, elementArr ...E) {
	// add 可接受 index == l.length
	if index < 0 || index > l.length {
		err := datastructerrors.NewIndexOutOfBoundsError(index, l.length)
		panic(err)
	}

	var newElementLen int

	// 執行緒安全處理
	l.mutex.Lock()
	defer func() {
		l.length += newElementLen
		l.mutex.Unlock()
	}()

	newElementLen, newElementHeadNode, newElementTailNode := l.createLinkedListNode(elementArr...)
	if newElementLen == 0 {
		return
	}

	if l.IsEmpty() { // 若此條件滿足 index 必等於 0
		l.headNode = newElementHeadNode
		l.tailNode = newElementTailNode
		return
	}

	// 單向連接處理
	if index == 0 {
		l.linkNode(newElementTailNode, l.headNode)
		l.headNode = newElementHeadNode
		return
	}
	if index == l.length {
		l.linkNode(l.tailNode, newElementHeadNode)
		l.tailNode = newElementTailNode
		return
	}

	// 雙向連接處理
	oldNode := l.getNodeAt(index)
	tempPreNode := oldNode.pre
	l.linkNode(tempPreNode, newElementHeadNode)
	l.linkNode(newElementTailNode, oldNode)
}

func (l *linkedList[E]) AddFirst(elementArr ...E) {
	l.AddAt(0, elementArr...)
}

func (l *linkedList[E]) Add(elementArr ...E) {
	l.AddAt(l.length, elementArr...)
}

func (l *linkedList[E]) SetAt(index int, newElement E) (oldElement E) {
	node := l.getNodeAt(index)
	tempElement := *node.element
	node.element = &newElement
	return tempElement
}

func (l *linkedList[E]) RemoveAt(index int) (element E) {
	// 執行緒安全處理
	l.mutex.Lock()
	defer func() {
		l.length--
		l.mutex.Unlock()
	}()

	// 雙向連接處理
	node := l.getNodeAt(index)
	element = *(node.element)
	l.removeNode(node)
	return element
}

func (l *linkedList[E]) RemoveFirst() (element E) {
	if l.IsEmpty() {
		err := datastructerrors.NewIsEmptyError()
		panic(err)
	}
	return l.RemoveAt(0)
}

func (l *linkedList[E]) Remove(elementArr ...E) {
	var removeElementLen int

	// 執行緒安全處理
	l.mutex.Lock()
	defer func() {
		l.length -= removeElementLen
		l.mutex.Unlock()
	}()

	set := sets.NewSet(elementArr...)

	listOfNodesToBeDeleted := NewLinkedList[interface{}]()
	l.forEachNode(0, false, func(node *linkedListNode[E]) (doBreak bool) {
		if set.Contains(*node.element) {
			listOfNodesToBeDeleted.Add(node)
		}
		return false
	})

	removeElementLen = listOfNodesToBeDeleted.Len()
	listOfNodesToBeDeleted.ForEach(func(element interface{}) (doBreak bool) {
		node := element.(*linkedListNode[E])
		l.removeNode(node)
		return false
	})
}

func (l *linkedList[E]) Pop() (element E) {
	if l.IsEmpty() {
		err := datastructerrors.NewIsEmptyError()
		panic(err)
	}
	return l.RemoveAt(l.length - 1)
}

func (l *linkedList[E]) Contains(elementArr ...E) (isContains bool) {
	set := sets.NewSet[E]()
	l.ForEach(func(element E) (doBreak bool) {
		set.Add(element)
		return false
	})
	return set.Contains(elementArr...)
}

func (l *linkedList[E]) Equal(obj any) (isEqual bool) {
	t, ok := obj.(*linkedList[E])
	if !ok {
		return false
	}

	if l.Len() != t.Len() {
		return false
	}

	if !arrayutils.Equal(l.ToArray(), t.ToArray()) {
		return false
	}

	return true
}

func (l *linkedList[E]) Len() (length int) {
	return l.length
}

func (l *linkedList[E]) IsEmpty() (isEmpty bool) {
	return l.length == 0
}

func (l *linkedList[E]) Clear() {
	// 斷開雙向鏈結列表
	nodeArr := make([]*linkedListNode[E], l.length)
	var i = 0
	l.forEachNode(0, false, func(node *linkedListNode[E]) (doBreak bool) {
		nodeArr[i] = node
		i++
		return false
	})
	for _, node := range nodeArr {
		l.clearNode(node)
	}

	l.headNode = nil
	l.tailNode = nil
	l.length = 0
}

func (l *linkedList[E]) ForEach(callback func(element E) (doBreak bool)) {
	l.forEachNode(0, false, func(node *linkedListNode[E]) (doBreak bool) {
		return callback(*(node.element))
	})
}

func (l *linkedList[E]) Filter(
	filterFunc func(element E) (doKeep bool),
) (
	newList LinkedList[E],
) {
	newList = NewLinkedList[E]()
	l.ForEach(func(element E) (doBreak bool) {
		if filterFunc(element) {
			newList.Add(element)
		}
		return false
	})
	return newList
}

func (l *linkedList[E]) SortBy(lessFunc func(a E, b E) (isLess bool)) {
	// do sort
	elementArr := l.ToArray()
	sort.Slice(elementArr, func(i int, j int) bool {
		return lessFunc(elementArr[i], elementArr[j])
	})

	// update linked list
	l.Clear()
	l.Add(elementArr...)
}

func (l *linkedList[E]) ToArray() (elementArr []E) {
	elementArr = make([]E, l.length)
	var i = 0
	l.ForEach(func(element E) (doBreak bool) {
		elementArr[i] = element
		i++
		return false
	})
	return elementArr
}

func (l *linkedList[E]) ToString() (str string) {
	elementArr := l.ToArray()
	strList := make([]string, len(elementArr))
	for i, element := range elementArr {
		strList[i] = fmt.Sprintf("%v", element)
	}
	return fmt.Sprintf("[%v]", strings.Join(strList, ", "))
}

func (l *linkedList[E]) getNodeAt(index int) (node *linkedListNode[E]) {
	if index < 0 || index >= l.length {
		err := datastructerrors.NewIndexOutOfBoundsError(index, l.length)
		panic(err)
	}

	if index < (l.length / 2) {
		node = l.headNode
		for i := 0; i < index; i++ {
			node = node.next
		}

	} else {
		node = l.tailNode
		for i := (l.length - 1); i > index; i-- {
			node = node.pre
		}
	}
	return node
}

func (l *linkedList[E]) createLinkedListNode(
	elementArr ...E,
) (
	length int, headNode *linkedListNode[E], tailNode *linkedListNode[E],
) {
	elementArrLen := len(elementArr)
	if elementArrLen == 0 {
		return elementArrLen, nil, nil
	}

	headElement := elementArr[0]
	headNode = &linkedListNode[E]{element: &headElement}
	if elementArrLen == 1 {
		return elementArrLen, headNode, headNode
	}

	var preNode = headNode
	var i = 1
	for i < elementArrLen {
		element := elementArr[i]
		tailNode = &linkedListNode[E]{element: &element}
		l.linkNode(preNode, tailNode)

		// afterthought
		preNode = tailNode
		i++
	}

	return elementArrLen, headNode, tailNode
}

func (l *linkedList[E]) linkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E]) {
	preNode.next = nextNode
	nextNode.pre = preNode
}

func (l *linkedList[E]) unlinkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E]) {
	preNode.next = nil
	nextNode.pre = nil
}

func (l *linkedList[E]) clearNode(node *linkedListNode[E]) {
	node.pre = nil
	node.next = nil
	node.element = nil
	node = nil
}

func (l *linkedList[E]) forEachNode(
	startIndex int, reverse bool, callback func(node *linkedListNode[E]) (doBreak bool),
) {
	if l.IsEmpty() {
		return
	}

	// initialization
	var node = l.getNodeAt(startIndex)
	if !reverse { // 正向
		for ; node != nil; node = node.next {
			if callback(node) {
				break
			}
		}
	} else { // 反向
		for ; node != nil; node = node.pre {
			if callback(node) {
				break
			}
		}
	}
}

func (l *linkedList[E]) removeNode(node *linkedListNode[E]) {

	defer func() { l.clearNode(node) }()

	if node.pre == nil &&
		node.next == nil {
		l.clearNode(node)
		return
	}

	if node.pre != nil &&
		node.next != nil {
		preNode := node.pre
		nextNode := node.next
		l.unlinkNode(preNode, node)
		l.unlinkNode(node, nextNode)
		l.linkNode(preNode, nextNode)
		l.clearNode(node)
		return
	}

	if node.pre == nil &&
		node.next != nil {
		nextNode := node.next
		l.unlinkNode(node, nextNode)
		if node == l.headNode {
			l.headNode = nextNode
		}
		return
	}

	if node.pre != nil &&
		node.next == nil {
		preNode := node.pre
		l.unlinkNode(preNode, node)
		node = preNode
		// if node == l.tailNode {
		// 	l.tailNode = preNode
		// }
		return
	}
}
