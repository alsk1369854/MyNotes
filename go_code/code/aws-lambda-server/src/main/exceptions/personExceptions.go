package exceptions

import (
	"errors"
)

func PersonIdNotFoundException(personId string) error {
	return errors.New("person id: " + personId + " not found")
}

func PersonJsonParseException() error {
	return errors.New("person JSON parse error")
}
