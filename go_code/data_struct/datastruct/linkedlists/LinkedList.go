package linkedlists

import (
	"datastructutil/datastruct/basicerrors"
	"fmt"
	"reflect"
	"sort"
	"strings"
	"sync"
)

type LinkedList[E any] interface {
	// **Public**

	// get
	GetAt(index int) (element E)
	GetFirst() (element E)
	GetLast() (element E)
	// GetSlice(startIndex int, sliceLen int) (elementArr []E)
	// IndexOf(element E) (index int)

	// add
	AddAt(index int, elementArr ...E)
	AddFirst(elementArr ...E)
	AddLast(elementArr ...E)

	// remove
	RemoveAt(index int) (element E)
	RemoveFirst() (element E)
	RemoveLast() (element E)
	Clear()

	// operation
	Equal(linkedList LinkedList[E]) bool

	ForEach(callback func(index int, element E))
	SortBy(less func(a E, b E) bool)

	Len() (length int)
	IsEmpty() (isEmpty bool)

	ToArray() (elementArr []E)
	ToString() (str string)

	// **Private**
	toLinkedListNodes(elementArr ...E) (headNode *linkedListNode[E], tailNode *linkedListNode[E])
	getNodeAt(index int) (node *linkedListNode[E])
	getHeadNode() (node *linkedListNode[E])
	getTailNode() (node *linkedListNode[E])
	linkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E])
	unlinkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E])
}

type linkedListNode[E any] struct {
	pre     *linkedListNode[E]
	next    *linkedListNode[E]
	element *E
}

type linkedList[E any] struct {
	LinkedList[E]
	mutex    sync.Mutex
	headNode *linkedListNode[E]
	tailNode *linkedListNode[E]
	length   int
}

func NewLinkedList[E any](elementArr ...E) LinkedList[E] {
	list := &linkedList[E]{length: 0}
	list.AddLast(elementArr...)
	return list
}

func (l *linkedList[E]) GetAt(index int) (element E) {
	if index < 0 || index >= l.length {
		err := basicerrors.NewIndexOutOfBoundsError(index, l.length)
		panic(err)
	}

	return *(l.getNodeAt(index).element)
}

func (l *linkedList[E]) GetFirst() (element E) {
	if l.length == 0 {
		err := basicerrors.NewIsEmptyError()
		panic(err)
	}
	return l.GetAt(0)
}

func (l *linkedList[E]) GetLast() (element E) {
	if l.length == 0 {
		err := basicerrors.NewIsEmptyError()
		panic(err)
	}
	return l.GetAt(l.length - 1)
}

func (l *linkedList[E]) AddAt(index int, elementArr ...E) {
	if index < 0 || index > l.length {
		err := basicerrors.NewIndexOutOfBoundsError(index, l.length)
		panic(err)
	}

	elementArrLen := len(elementArr)
	if elementArrLen == 0 {
		return
	}

	// 執行緒安全處理
	l.mutex.Lock()
	defer func() {
		l.length += elementArrLen
		l.mutex.Unlock()
	}()

	subListHeadNode, subListTailNode := l.toLinkedListNodes(elementArr...)

	if l.length == 0 { // 若此條件滿足 index 必等於 0
		l.headNode = subListHeadNode
		l.tailNode = subListTailNode
		return
	}

	// 單向連接處理
	if index == 0 {
		l.linkNode(subListTailNode, l.headNode)
		l.headNode = subListHeadNode
		return
	}
	if index == l.length {
		l.linkNode(l.tailNode, subListHeadNode)
		l.tailNode = subListTailNode
		return
	}

	// 雙向連接處理
	oldNode := l.getNodeAt(index)
	tempPreNode := oldNode.pre
	l.linkNode(tempPreNode, subListHeadNode)
	l.linkNode(subListTailNode, oldNode)
}

func (l *linkedList[E]) AddFirst(elementArr ...E) {
	l.AddAt(0, elementArr...)
}

func (l *linkedList[E]) AddLast(elementArr ...E) {
	l.AddAt(l.length, elementArr...)
}

func (l *linkedList[E]) RemoveAt(index int) (element E) {
	if index < 0 || index >= l.length {
		err := basicerrors.NewIndexOutOfBoundsError(index, l.length)
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
	if l.length == 0 {
		err := basicerrors.NewIsEmptyError()
		panic(err)
	}
	return l.RemoveAt(0)
}

func (l *linkedList[E]) RemoveLast() (element E) {
	if l.length == 0 {
		err := basicerrors.NewIsEmptyError()
		panic(err)
	}
	return l.RemoveAt(l.length - 1)
}

func (l *linkedList[E]) Clear() {
	l.headNode = nil
	l.tailNode = nil
	l.length = 0
}

func (l *linkedList[E]) Equal(linkedList LinkedList[E]) bool {
	return reflect.DeepEqual(l, linkedList)
}

func (l *linkedList[E]) ForEach(callback func(index int, element E)) {
	if l.length > 0 {
		var tempNode = l.headNode
		var i = 0
		callback(i, *(tempNode.element))
		for tempNode.next != nil {
			tempNode = tempNode.next
			i++
			callback(i, *(tempNode.element))
		}
	}
}

func (l *linkedList[E]) ToArray() (elementArr []E) {
	elementArr = make([]E, l.length)
	l.ForEach(func(i int, element E) {
		elementArr[i] = element
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

func (l *linkedList[E]) SortBy(less func(a E, b E) bool) {
	// do sort
	elementArr := l.ToArray()
	sort.Slice(elementArr, func(i int, j int) bool {
		return less(elementArr[i], elementArr[j])
	})

	// update linked list
	l.Clear()
	for _, element := range elementArr {
		l.AddLast(element)
	}
}

func (l *linkedList[E]) Len() (length int) {
	return l.length
}

func (l *linkedList[E]) IsEmpty() (isEmpty bool) {
	return l.length == 0
}

func (l *linkedList[E]) toLinkedListNodes(
	elementArr ...E,
) (
	headNode *linkedListNode[E], tailNode *linkedListNode[E],
) {
	elementArrLen := len(elementArr)
	if elementArrLen == 0 {
		err := basicerrors.NewIsEmptyError()
		panic(err)
	}

	headElement := elementArr[0]
	headNode = &linkedListNode[E]{element: &headElement}
	if elementArrLen == 1 {
		return headNode, headNode
	}

	var tempNode = headNode
	for i := 1; i < elementArrLen; i++ {
		element := elementArr[i]
		tailNode = &linkedListNode[E]{element: &element}
		l.linkNode(tempNode, tailNode)
		tempNode = tailNode
	}

	return headNode, tailNode
}

func (l *linkedList[E]) getNodeAt(index int) (node *linkedListNode[E]) {
	if index < 0 || index >= l.length {
		err := basicerrors.NewIndexOutOfBoundsError(index, l.length)
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

func (l *linkedList[E]) getHeadNode() (node *linkedListNode[E]) {
	return l.headNode
}

func (l *linkedList[E]) getTailNode() (node *linkedListNode[E]) {
	return l.tailNode
}

func (l *linkedList[E]) linkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E]) {
	preNode.next = nextNode
	nextNode.pre = preNode
}

func (l *linkedList[E]) unlinkNode(preNode *linkedListNode[E], nextNode *linkedListNode[E]) {
	preNode.next = nil
	nextNode.pre = nil
}
