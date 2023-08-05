package linkedlists

import (
	"reflect"
	"testing"
)

func TestNewLinkedList(t *testing.T) {
	type args struct {
		elementArr []int
	}
	tests := []struct {
		name     string
		args     args
		wantList LinkedList[int]
	}{
		{
			name:     "Create with empty args",
			args:     args{elementArr: []int{}},
			wantList: NewLinkedList[int](),
		},
		{
			name:     "Create with multi args",
			args:     args{elementArr: []int{}},
			wantList: NewLinkedList[int](),
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			wantList := tt.wantList
			list := NewLinkedList(tt.args.elementArr...)
			if list.Len() != wantList.Len() {
				t.Error("Len error")
			}

			wantList.ForEach(func(i int, elemnt int) {
				if list.GetAt(i) != elemnt {
					t.Error("GetAt error")
				}
			})
		})
	}
}

func TestLen(t *testing.T) {
	tests := []struct {
		name       string
		linkedList LinkedList[int]
		wantLen    int
	}{
		{
			name:       "test 1",
			linkedList: NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			wantLen:    10,
		},
		{
			name:       "test 2",
			linkedList: NewLinkedList(0, 1, 2, 3, 4),
			wantLen:    5,
		},
		{
			name:       "test 3",
			linkedList: NewLinkedList[int](),
			wantLen:    0,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.linkedList
			wantLen := tt.wantLen
			if list.Len() != wantLen {
				t.Error("wround result")
			}
		})
	}
}

func TestGetAt(t *testing.T) {
	type args struct {
		index int
	}
	tests := []struct {
		name        string
		list        LinkedList[int]
		args        args
		wantElement int
	}{
		{
			name:        "test 1",
			list:        NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:        args{index: 3},
			wantElement: 3,
		},
		{
			name:        "test 2",
			list:        NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:        args{index: 7},
			wantElement: 7,
		},
		{
			name:        "test 3",
			list:        NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:        args{index: 0},
			wantElement: 0,
		},
		{
			name:        "test 4",
			list:        NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:        args{index: 9},
			wantElement: 9,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			args := tt.args
			wantElement := tt.wantElement
			if list.GetAt(args.index) != wantElement {
				t.Error("wround result")
			}
		})
	}
}

func TestAddAt(t *testing.T) {
	type args struct {
		index      int
		elementArr []int
	}
	tests := []struct {
		name                 string
		list                 LinkedList[int]
		args                 args
		wantLen              int
		wantIndexEelemnt     int
		wantIndexPreEelemnt  int
		wantIndexNextEelemnt int
	}{
		{
			name:                 "test 1",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 3, elementArr: []int{999}},
			wantLen:              11,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  2,
			wantIndexNextEelemnt: 3,
		},
		{
			name:                 "test 2",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 3, elementArr: []int{999, 888}},
			wantLen:              12,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  2,
			wantIndexNextEelemnt: 888,
		},
		{
			name:                 "test 3",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 6, elementArr: []int{999}},
			wantLen:              11,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  5,
			wantIndexNextEelemnt: 6,
		},
		{
			name:                 "test 4",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 0, elementArr: []int{999}},
			wantLen:              11,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  -1,
			wantIndexNextEelemnt: 0,
		},
		{
			name:                 "test 5",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 0, elementArr: []int{999, 888}},
			wantLen:              12,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  -1,
			wantIndexNextEelemnt: 888,
		},
		{
			name:                 "test 6",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 10, elementArr: []int{999, 888}},
			wantLen:              12,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  9,
			wantIndexNextEelemnt: 888,
		},
		{
			name:                 "test 7",
			list:                 NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                 args{index: 10, elementArr: []int{999}},
			wantLen:              11,
			wantIndexEelemnt:     999,
			wantIndexPreEelemnt:  9,
			wantIndexNextEelemnt: -1,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			args := tt.args
			wantLen := tt.wantLen
			wantIndexElement := tt.wantIndexEelemnt
			wantIndexPreEelemnt := tt.wantIndexPreEelemnt
			wantIndexNextEelemnt := tt.wantIndexNextEelemnt
			list.AddAt(args.index, args.elementArr...)
			if list.Len() != wantLen {
				t.Error("wround wantLen")
			}
			if list.GetAt(args.index) != wantIndexElement {
				t.Error("wround wantIndexElement")
			}
			if wantIndexPreEelemnt != -1 &&
				list.GetAt(args.index-1) != wantIndexPreEelemnt {
				t.Error("wround wantIndexPreEelemnt")
			}
			if wantIndexNextEelemnt != -1 &&
				list.GetAt(args.index+1) != wantIndexNextEelemnt {
				t.Error("wround wantIndexNextEelemnt")
			}
		})
	}
}

func TestRemoveAt(t *testing.T) {
	type args struct {
		index int
	}
	tests := []struct {
		name                    string
		list                    LinkedList[int]
		args                    args
		wantLen                 int
		wantRemoveEelemnt       int
		wantNewListIndexEelemnt int
	}{
		{
			name:                    "test 1",
			list:                    NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                    args{index: 3},
			wantLen:                 9,
			wantRemoveEelemnt:       3,
			wantNewListIndexEelemnt: 4,
		},

		{
			name:                    "test 2",
			list:                    NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                    args{index: 0},
			wantLen:                 9,
			wantRemoveEelemnt:       0,
			wantNewListIndexEelemnt: 1,
		},

		{
			name:                    "test 3",
			list:                    NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:                    args{index: 9},
			wantLen:                 9,
			wantRemoveEelemnt:       9,
			wantNewListIndexEelemnt: -1,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			args := tt.args
			wantLen := tt.wantLen
			wantRemoveEelemnt := tt.wantRemoveEelemnt
			wantNewListIndexEelemnt := tt.wantNewListIndexEelemnt
			if list.RemoveAt(args.index) != wantRemoveEelemnt {
				t.Error("wround wantRemoveEelemnt")
			}
			if list.Len() != wantLen {
				t.Error("wround wantLen")
			}
			if wantNewListIndexEelemnt != -1 &&
				list.GetAt(args.index) != wantNewListIndexEelemnt {
				t.Error("wround wantNewListIndexEelemnt")
			}
		})
	}
}

func TestSortBy(t *testing.T) {

	type args struct {
		less func(int, int) bool
	}
	tests := []struct {
		name    string
		list    LinkedList[int]
		args    args
		wantArr []int
	}{
		{
			name:    "test 1",
			list:    NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:    args{less: func(i int, j int) bool { return i < j }},
			wantArr: []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
		},
		{
			name:    "test 2",
			list:    NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			args:    args{less: func(i int, j int) bool { return j < i }},
			wantArr: []int{9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
		},
		{
			name:    "test 3",
			list:    NewLinkedList(6, 3, 3, 2, 6, 4),
			args:    args{less: func(i int, j int) bool { return i < j }},
			wantArr: []int{2, 3, 3, 4, 6, 6},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			args := tt.args
			wantArr := tt.wantArr

			list.SortBy(args.less)
			arr := list.ToArray()
			if !reflect.DeepEqual(wantArr, arr) {
				t.Error("wround result")
			}
		})
	}
}

func TestClear(t *testing.T) {

	tests := []struct {
		name        string
		list        LinkedList[int]
		wantIsEmpty bool
	}{
		{
			name:        "test 1",
			list:        NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			wantIsEmpty: false,
		},
		{
			name:        "test 2",
			list:        NewLinkedList[int](),
			wantIsEmpty: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			wantIsEmpty := tt.wantIsEmpty
			if list.IsEmpty() != wantIsEmpty {
				t.Error("wround wantIsEmpty")
			}
		})
	}
}

func TestForEach(t *testing.T) {

	tests := []struct {
		name      string
		list      LinkedList[int]
		wantCount int
		wantOrder []int
	}{
		{
			name:      "test 1",
			list:      NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			wantCount: 10,
			wantOrder: []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
		},
		{
			name:      "test 2",
			list:      NewLinkedList[int](),
			wantCount: 0,
			wantOrder: []int{},
		},
		{
			name:      "test 3",
			list:      NewLinkedList[int](4, 5, 6, 2, 3),
			wantCount: 5,
			wantOrder: []int{4, 5, 6, 2, 3},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			wantCount := tt.wantCount
			wantOrder := tt.wantOrder

			var count = 0
			list.ForEach(func(i int, element int) {
				count++
				if wantOrder[i] != element {
					t.Error("wround elemnt")
				}
			})
			if wantCount != count {
				t.Error("wround count")
			}
		})
	}
}

func TestEqual(t *testing.T) {

	tests := []struct {
		name     string
		list     LinkedList[int]
		wantList LinkedList[int]
	}{
		{
			name:     "test 1",
			list:     NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
			wantList: NewLinkedList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
		},
		{
			name:     "test 2",
			list:     NewLinkedList[int](),
			wantList: NewLinkedList[int](),
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			list := tt.list
			wantList := tt.wantList

			if !wantList.Equal(list) {
				t.Error("wround equal")
			}
		})
	}
}
