package customerrors

import (
	"errors"
	"fmt"
)

func NewIsEmptyError() error {
	msg := "is empty"
	return errors.New(msg)
}

func NewIndexOutOfBoundsError(index int, length int) error {
	msg := fmt.Sprintf(
		"index %d is out of bounds for length %d",
		index, length,
	)
	return errors.New(msg)
}
