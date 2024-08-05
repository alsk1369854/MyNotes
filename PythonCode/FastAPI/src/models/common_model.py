from pydantic import BaseModel, Field
from typing import TypeVar, Generic, Self, List, Union, Any, Dict
from fastapi import Query

PT = TypeVar('PT')
class PageResponse(BaseModel, Generic[PT]):
    total_count: int = Field(..., description="Total of data")
    response: List[PT] = Field(..., description="Data of request page")

class PageRequestParams(BaseModel):
    page_at: int
    page_limit: int

    @classmethod
    def as_query(
        cls,
        page_at: int = Query(1, gt=0, description="Request page number"),
        page_limit: int = Query(10, gt=0, description="One page size")
    ) -> Self:
        return cls(page_at=page_at, page_limit=page_limit)

class Message(BaseModel):
    message: str = Field(..., description="Server message")

class Status(BaseModel):
    status: bool = Field(..., description="Status of task")


# JsonDataType = Union[str, int, float, bool, None, List[Any], Dict[str, Any]]

# class Count(BaseModel):
#     count: int = Field(..., description="Count of conditions")

# class CountEqualThenCondition(BaseModel):
#     key: str = Field(..., description="Data variable name")
#     eq: JsonDataType = Field(..., description="Variable equal condition")

# class CountGreaterThenCondition(BaseModel):
#     key: str = Field(..., description="Data variable name")
#     gt: int = Field(..., description="Variable greater then condition")

# class CountLessThenCondition(BaseModel):
#     key: str = Field(..., description="Data variable name")
#     ls: int = Field(..., description="Variable less then condition")

# CountCondition = Union[CountEqualThenCondition, CountGreaterThenCondition, CountLessThenCondition]

