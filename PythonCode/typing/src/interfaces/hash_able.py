from typing import Self, Protocol


class HashAble(Protocol):
    def hash(self: Self) -> int:
        pass
