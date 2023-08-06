package sets

import (
	"testing"
)

func TestNewSet(t *testing.T) {
	type args struct {
		elementArr []int
	}
	tests := []struct {
		name    string
		args    args
		wantSet Set[int]
	}{
		{
			name:    "test 1",
			args:    args{elementArr: []int{}},
			wantSet: NewSet[int](),
		},
		{
			name:    "test 2",
			args:    args{elementArr: []int{1, 2, 3}},
			wantSet: NewSet[int](1, 2, 3),
		},
		{
			name:    "test 3",
			args:    args{elementArr: []int{1}},
			wantSet: NewSet[int](1),
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			args := tt.args
			wantSet := tt.wantSet

			set := NewSet(args.elementArr...)
			if !wantSet.Equal(set) {
				t.Error("not eqaul")
			}
		})
	}
}

func TestContains(t *testing.T) {
	type args struct {
		elementArr []int
	}
	tests := []struct {
		name           string
		set            Set[int]
		args           args
		wantIsContains bool
	}{
		{
			name:           "test 1",
			set:            NewSet[int](1, 2, 3, 4),
			args:           args{elementArr: []int{1, 2}},
			wantIsContains: true,
		},
		{
			name:           "test 2",
			set:            NewSet[int](1, 2, 3, 4),
			args:           args{elementArr: []int{3, 4}},
			wantIsContains: true,
		},
		{
			name:           "test 3",
			set:            NewSet[int](1, 2, 3, 4),
			args:           args{elementArr: []int{2}},
			wantIsContains: true,
		},
		{
			name:           "test 4",
			set:            NewSet[int](1, 2, 3, 4),
			args:           args{elementArr: []int{1, 2, 3, 4, 5}},
			wantIsContains: false,
		},
		{
			name:           "test 4",
			set:            NewSet[int](1, 2, 3, 4),
			args:           args{elementArr: []int{3, 4, 5, 6, 7}},
			wantIsContains: false,
		},
		{
			name:           "test 5",
			set:            NewSet[int](),
			args:           args{elementArr: []int{}},
			wantIsContains: true,
		},
		{
			name:           "test 6",
			set:            NewSet[int](),
			args:           args{elementArr: []int{3, 4, 5, 6, 7}},
			wantIsContains: false,
		},
		{
			name:           "test 4",
			set:            NewSet[int](1, 2, 3, 4),
			args:           args{elementArr: []int{}},
			wantIsContains: true,
		},
		{
			name:           "test 4",
			set:            NewSet[int](2, 3, 2, 4),
			args:           args{elementArr: []int{2, 3}},
			wantIsContains: true,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			args := tt.args
			set := tt.set
			wantIsContains := tt.wantIsContains

			if set.Contains(args.elementArr...) != wantIsContains {
				t.Error("wround wantIsContains")
			}
		})
	}
}
