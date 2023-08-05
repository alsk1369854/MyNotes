package linkedlists

import (
	"fmt"
	"reflect"
	"testing"
)

func TestMap(t *testing.T) {
	type args struct {
		linkedlist LinkedList[int]
		callback   func(index int, element int) string
	}
	tests := []struct {
		name     string
		args     args
		wantList LinkedList[string]
	}{
		{
			name: "test 1",
			args: args{
				linkedlist: NewLinkedList(0, 1, 2, 3, 4),
				callback: func(index int, element int) string {
					return fmt.Sprintf("@%d", element)
				},
			},
			wantList: NewLinkedList("@0", "@1", "@2", "@3", "@4"),
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			wantList := tt.wantList

			newList := Map(tt.args.linkedlist, tt.args.callback)

			if !reflect.DeepEqual(wantList, newList) {
				t.Error("wround object equal")
			}

			wantListToArray := wantList.ToArray()
			newListToArray := newList.ToArray()
			if !reflect.DeepEqual(wantListToArray, newListToArray) {
				t.Error("wround toArray equal")
			}

		})
	}
}
