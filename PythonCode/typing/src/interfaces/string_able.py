from typing import Protocol, Self


class StringAble(Protocol):
    def toString(self: Self) -> str:
        pass
