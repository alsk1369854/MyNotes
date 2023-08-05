package linkedlists

func Map[E any, N any](
	linkedlist LinkedList[E],
	callback func(index int, element E) N,
) LinkedList[N] {
	list := NewLinkedList[N]()
	linkedlist.ForEach(func(index int, element E) {
		newElement := callback(index, element)
		list.AddLast(newElement)
	})
	return list
}
