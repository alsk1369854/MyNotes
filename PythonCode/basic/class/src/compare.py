import unittest
from typing import Protocol, Self, Any


class Equatable(Protocol):
    def equalTo(self: Self, other: Self) -> bool:
        pass

    def __eq__(self: Self, other: object) -> bool:
        if not isinstance(other, self.__class__):
            return NotImplemented
        return self.equalTo(other)


class Comparable(Equatable, Protocol):
    def lessThen(self: Self, other: Self) -> bool:
        pass

    def __lt__(self: Self, other: Self) -> bool:
        return self.lessThen(other)

    def __le__(self: Self, other: Self) -> bool:
        return (self == other) or (self < other)


class Obj(Comparable):
    value: int = 0

    def __init__(self: Self, value: int) -> None:
        self.value = value

    def equalTo(self: Self, other: Self) -> bool:
        return self.value == other.value

    def lessThen(self: Self, other: Self) -> bool:
        return self.value < other.value


# python -m unittest src.compare
class TestCompare(unittest.TestCase):
    def testEqual(self: Self) -> None:
        self.assertEqual(True, Obj(1) == Obj(1))
        self.assertEqual(True, Obj(-1) == Obj(-1))
        self.assertEqual(False, Obj(-1) == Obj(1))

    def testLessThen(self: Self) -> None:
        self.assertEqual(True, Obj(1) < Obj(2))
        self.assertEqual(False, Obj(2) < Obj(2))

    def testLessOrEqual(self) -> None:
        self.assertEqual(True, Obj(1) <= Obj(1))
        self.assertEqual(True, Obj(1) <= Obj(2))
        self.assertEqual(False, Obj(2) <= Obj(1))

    def testGreaterThen(self) -> None:
        self.assertEqual(True, Obj(2) > Obj(1))
        self.assertEqual(False, Obj(2) > Obj(2))

    def testGreaterOrEqual(self) -> None:
        self.assertEqual(True, Obj(1) >= Obj(1))
        self.assertEqual(True, Obj(2) >= Obj(1))
        self.assertEqual(False, Obj(1) >= Obj(2))
