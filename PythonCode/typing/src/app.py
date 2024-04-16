from typing import (
    List,
    Dict,
    Set,
    Optional,
    Sequence,
    Tuple,
    Callable,
    TypeVar,
    Union,
    Generic,
    Self,
    Any,
    Iterable,
)
from .interfaces import StringAble, HashAble


# 自定義類型
Vector = Sequence[int]


# 可選參數 Optional[int] ==  None | int
def function_typing(a: int, b: int = 10, c: Optional[int] = None) -> int:
    if c:
        return a + b + c
    else:
        return a + b


# 函數傳參
def function_callback(args: Sequence[int], func: Callable[[int, int], int]) -> int:
    return func(args[0], args[1])


# 泛型定義
# 類型只能是 int 或 str 類型
T1 = TypeVar("T1", int, str)
# 類型只能是 Sequence[int] 或 Sequence[str] 本身或子類
T2 = TypeVar("T2", bound=Union[Sequence[int], Sequence[str]])


# 泛型應用1
def get_index(seq: Sequence[T1], index: int) -> T1:
    return seq[index]


# 泛型應用2 (繼承在前，類型在後)
class GenericValue(StringAble, Generic[T2]):
    value: T2

    def __init__(self: Self, value: T2) -> None:
        super().__init__()
        self.value = value

    def toString(self: Self) -> str:
        return str(self.value)


# 實作 Protocol
class ImplementProtocol(StringAble, HashAble):
    def __init__(self) -> None:
        super().__init__()
        pass

    def toString(self: Self) -> str:
        return str(self.__dict__)

    def hash(self: Self) -> int:
        return self.__hash__()


class App:
    @staticmethod
    def run() -> None:
        mu_func_output = function_typing(1)
        print(f"Function: {mu_func_output}")

        my_dict: Dict[str, bool] = {"apple": True, "banana": False}
        print(f"Dict: {my_dict}")

        my_Set: Set[float] = {0.1, 0.1, 0.2}
        print(f"Set: {my_Set}")

        my_list: List[int] = [1, 2, 3]
        print(f"List: {my_list}")

        my_tuple: Tuple[int, int, int] = (1, 2, 3)
        print(f"Tuple: {my_tuple}")

        my_sequence: Sequence[int] = [1, 2, 3]
        my_sequence = (1, 2, 3)
        print(f"Sequence: {my_sequence}")

        my_union: List[Union[int, str]] = [1, 2, "3"]
        print(f"Union: {my_union}")

        my_function_callback_output = function_callback([1, 2], lambda a, b: a + b)
        print(f"Callable: {my_function_callback_output}")

        my_type: Vector = [1, 2, 3]
        print(f"MyType: {my_type}")

        print(f"TypeVar: {get_index([1, 2], 1)}, {get_index(['a', 'b'], 0)}")

        generic_value: GenericValue[List[int]] = GenericValue([1, 2, 3])
        print(f"Generic: {generic_value.toString()}")

        implement_protocol: ImplementProtocol = ImplementProtocol()
        print(f"Protocol: {implement_protocol.hash()}")
