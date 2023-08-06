package linkedlists

func Map[E comparable, N comparable](
	srcList LinkedList[E],
	createElementFunc func(element E) N,
) LinkedList[N] {
	list := NewLinkedList[N]()
	srcList.ForEach(func(element E) (doBreak bool) {
		newElement := createElementFunc(element)
		list.Add(newElement)
		return false
	})
	return list
}
