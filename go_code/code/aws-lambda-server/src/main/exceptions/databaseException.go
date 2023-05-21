package exceptions

import "errors"

func DatabaseCreateException() error {
	return errors.New("database create error")
}

func DatabaseDeleteException() error {
	return errors.New("database delete error")
}
