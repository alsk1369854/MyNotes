package datastructinterfaces

type Collection[E any] interface {
	Add(elementArr ...E)
	Remove(elementArr ...E)
	Contains(elementArr ...E) (isContains bool)
	Clear()
	Len() int
	IsEmpty() bool
	ToArray() []E
	Equal(obj any) (isEqual bool)
	ForEach(callback func(element E) (doBreak bool))
}
