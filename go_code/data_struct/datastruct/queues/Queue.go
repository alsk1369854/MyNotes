package queues

import "datastructutil/datastruct/linkedlists"

type Queue[E any] interface {
	Enqueue(elementArr ...E)
	Dequeue() (element E)
	Peek() (element E)
	Len() (Len int)
	IsEmpty() (isEmpty bool)
}

type queue[E any] struct {
	list linkedlists.LinkedList[E]
}

func NewQueue[E any](elementArr ...E) Queue[E] {
	queue := &queue[E]{
		list: linkedlists.NewLinkedList(elementArr...),
	}
	return queue
}

func (q *queue[E]) Enqueue(elementArr ...E) {
	q.list.AddLast(elementArr...)
}

func (q *queue[E]) Dequeue() (element E) {
	return q.list.RemoveFirst()
}

func (q *queue[E]) Peek() (element E) {
	return q.list.GetFirst()
}

func (q *queue[E]) Len() (Len int) {
	return q.list.Len()
}

func (q *queue[E]) IsEmpty() (isEmpty bool) {
	return q.list.IsEmpty()
}
