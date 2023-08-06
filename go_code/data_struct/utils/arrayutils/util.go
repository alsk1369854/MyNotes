package arrayutils

func Equal[T comparable](a []T, b []T) (isEqual bool) {
	if len(a) != len(b) {
		return false
	}

	for i, item := range a {
		if item != b[i] {
			return false
		}
	}

	return true
}
