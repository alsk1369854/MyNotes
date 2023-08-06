package linkedlists

func Map[E comparable, N comparable](
	srcList LinkedList[E],
	createElementFunc func(index int, element E) N,
) LinkedList[N] {
	list := NewLinkedList[N]()
	srcList.ForEach(func(index int, element E) (doBreak bool) {
		newElement := createElementFunc(index, element)
		list.Add(newElement)
		return false
	})
	return list
}
