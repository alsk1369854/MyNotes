package linkedlists

import (
	"datastructutil/datastruct/customerrors"
	"fmt"
	"reflect"
	"sort"
	"strings"
	"sync"
)

type LinkedList[E comparable] interface {
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
	Add(elementArr ...E)

	// set
	SetAt(index int, newElement E) (oldElement E)

	// remove
	RemoveAt(index int) (element E)
	RemoveFirst() (element E)
	Pop() (element E)

	// operation
	Equal(linkedList LinkedList[E]) (isEqual bool)
	Len() (length int)
	IsEmpty() (isEmpty bool)

	Clear()

	ForEach(callback func(index int, element E) (doBreak bool))
	Filter(filterFunc func(index int, element E) (doKeep bool)) (newList LinkedList[E])
	SortBy(lessFunc func(a E, b E) (isLess bool))

	ToArray() (elementArr []E)
	ToString() (str string)

	// ** Private **
	createLinkedListNode(elementArr ...E) (length int, headNode *linkedListNode[E], tailNode *linkedListNode[E])
	getNodeAt(index int) (node *linkedListNode[E])
	linkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E])
	unlinkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E])
	clearNode(node *linkedListNode[E])
	forEachNode(startIndex int, reverse bool, callback func(index int, node *linkedListNode[E]) (doBreak bool))
}

type linkedListNode[E comparable] struct {
	pre     *linkedListNode[E]
	next    *linkedListNode[E]
	element *E
}

type linkedList[E comparable] struct {
	LinkedList[E]
	mutex    sync.Mutex
	headNode *linkedListNode[E]
	tailNode *linkedListNode[E]
	length   int
}

func NewLinkedList[E comparable](elementArr ...E) LinkedList[E] {
	list := &linkedList[E]{length: 0}
	list.Add(elementArr...)
	return list
}

func (l *linkedList[E]) GetAt(index int) (element E) {
	return *(l.getNodeAt(index).element)
}

func (l *linkedList[E]) GetFirst() (element E) {
	if l.IsEmpty() {
		err := customerrors.NewIsEmptyError()
		panic(err)
	}
	return l.GetAt(0)
}

func (l *linkedList[E]) Peek() (element E) {
	if l.IsEmpty() {
		err := customerrors.NewIsEmptyError()
		panic(err)
	}
	return l.GetAt(l.length - 1)
}

func (l *linkedList[E]) IndexOf(target E, startIndex int, reverse bool) (targetIndex int) {
	targetIndex = -1
	l.forEachNode(startIndex, reverse, func(i int, node *linkedListNode[E]) (doBreak bool) {
		if target == *(node.element) {
			targetIndex = i
			return true
		}
		return false
	})
	return targetIndex
}

func (l *linkedList[E]) AddAt(index int, elementArr ...E) {
	// add 可接受 index == l.length
	if index < 0 || index > l.length {
		err := customerrors.NewIndexOutOfBoundsError(index, l.length)
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
	if index < 0 || index >= l.length {
		err := customerrors.NewIndexOutOfBoundsError(index, l.length)
		panic(err)
	}

	// 執行緒安全處理
	l.mutex.Lock()
	defer func() {
		l.length--
		l.mutex.Unlock()
	}()

	if l.length == 1 { // 若此條件滿足 index 必等於 0
		tempNode := l.headNode
		l.headNode = nil
		l.tailNode = nil
		return *(tempNode.element)
	}

	// 單向連接處理
	if index == 0 {
		tempNode := l.headNode
		l.headNode = l.headNode.next
		l.unlinkNode(tempNode, l.headNode)
		return *(tempNode.element)
	}
	if index == (l.length - 1) {
		tempNode := l.tailNode
		l.tailNode = l.tailNode.pre
		l.unlinkNode(l.tailNode, tempNode)
		return *(tempNode.element)
	}

	// 雙向連接處理
	tempNode := l.getNodeAt(index)
	tempPreNode := tempNode.pre
	tempNextNode := tempNode.next
	l.unlinkNode(tempPreNode, tempNode)
	l.unlinkNode(tempNode, tempNextNode)
	l.linkNode(tempPreNode, tempNextNode)
	return *(tempNode.element)
}

func (l *linkedList[E]) RemoveFirst() (element E) {
	if l.IsEmpty() {
		err := customerrors.NewIsEmptyError()
		panic(err)
	}
	return l.RemoveAt(0)
}

func (l *linkedList[E]) Pop() (element E) {
	if l.IsEmpty() {
		err := customerrors.NewIsEmptyError()
		panic(err)
	}
	return l.RemoveAt(l.length - 1)
}

func (l *linkedList[E]) Equal(linkedList LinkedList[E]) (isEqual bool) {
	return reflect.DeepEqual(l, linkedList)
}

func (l *linkedList[E]) Len() (length int) {
	return l.length
}

func (l *linkedList[E]) IsEmpty() (isEmpty bool) {
	return l.length == 0
}

func (l *linkedList[E]) Clear() {
	l.forEachNode(0, false, func(index int, node *linkedListNode[E]) (doBreak bool) {
		l.clearNode(node)
		return false
	})
	l.headNode = nil
	l.tailNode = nil
	l.length = 0
}

func (l *linkedList[E]) ForEach(callback func(index int, element E) (doBreak bool)) {
	l.forEachNode(0, false, func(index int, node *linkedListNode[E]) (doBreak bool) {
		return callback(index, *(node.element))
	})
}

func (l *linkedList[E]) Filter(
	filterFunc func(index int, element E) (doKeep bool),
) (
	newList LinkedList[E],
) {
	newList = NewLinkedList[E]()
	l.ForEach(func(index int, element E) (doBreak bool) {
		if filterFunc(index, element) {
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
	l.ForEach(func(i int, element E) (doBreak bool) {
		elementArr[i] = element
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
		err := customerrors.NewIndexOutOfBoundsError(index, l.length)
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
}

func (l *linkedList[E]) forEachNode(
	startIndex int, reverse bool, callback func(index int, node *linkedListNode[E]) (doBreak bool),
) {
	if l.IsEmpty() {
		return
	}

	// initialization
	var node = l.getNodeAt(startIndex)
	var i = startIndex

	// create afterhought func
	afterthought := func() {
		node = node.next
		i++
	}
	if reverse {
		afterthought = func() {
			node = node.pre
			i--
		}
	}

	// for loop
	for ; node != nil; afterthought() {
		if callback(i, node) {
			break
		}
	}
}
