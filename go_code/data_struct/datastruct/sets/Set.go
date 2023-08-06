package sets

import (
	"datastructutil/datastruct/datastructinterfaces"
	"reflect"
	"sync"
)

type Set[E any] interface {
	datastructinterfaces.Collection[E]
	ForEach(callback func(element E) (doBreak bool))
}

type set[E comparable] struct {
	// Set[E]
	mutex *sync.Mutex
	Map   map[E]interface{}
}

func NewSet[T comparable](elementArr ...T) Set[T] {
	set := &set[T]{
		mutex: new(sync.Mutex),
		Map:   map[T]interface{}{},
	}
	for _, element := range elementArr {
		set.Map[element] = nil
	}
	return set
}

func (s *set[E]) Equal(obj any) (isEqual bool) {
	return reflect.DeepEqual(s, obj)
}

func (s *set[E]) Add(elementArr ...E) {
	// 執行緒安全處理
	s.mutex.Lock()
	defer func() {
		s.mutex.Unlock()
	}()

	for _, element := range elementArr {
		s.Map[element] = nil
	}
}

func (s *set[E]) Remove(elementArr ...E) {
	// 執行緒安全處理
	s.mutex.Lock()
	defer func() {
		s.mutex.Unlock()
	}()

	for _, element := range elementArr {
		delete(s.Map, element)
	}
}

func (s *set[E]) Contains(elementArr ...E) (isContains bool) {
	for _, element := range elementArr {
		if _, isExist := s.Map[element]; !isExist {
			return false
		}
	}
	return true
}

func (s *set[E]) Clear() {
	s.Map = map[E]interface{}{}
}

func (s *set[E]) Len() (length int) {
	return len(s.Map)
}

func (s *set[E]) IsEmpty() (isEmpty bool) {
	return s.Len() == 0
}

func (s *set[E]) ForEach(callback func(element E) (doBreak bool)) {
	for element := range s.Map {
		if callback(element) {
			break
		}
	}
}

func (s *set[E]) ToArray() (elementArr []E) {
	elementArr = make([]E, s.Len())
	var i = 0
	for element := range s.Map {
		elementArr[i] = element
		i++
	}
	return elementArr
}
