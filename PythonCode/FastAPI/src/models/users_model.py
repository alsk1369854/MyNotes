from pydantic import BaseModel, Field
from fastapi import Query, HTTPException
from ..utils import pydantic_utils


class User(BaseModel):
    id: str = Field(..., description="User ID")
    name: str = Field(..., description="User name")
    age: int

    class Config:
        orm_mode = True

CreateUserRequestBody = pydantic_utils.omit_model(User, ('id'))
UpdateUserRequestBody = pydantic_utils.partial_model(pydantic_utils.omit_model(User, ('id')))


# class CountUserRequestParams(BaseModel):
#     key: str
#     value: str

#     @classmethod
#     def as_query(
#         cls, 
#         key: Optional[str] = Query(None, description="key of user variable"), 
#         value: Optional[str] = Query(None, description="value of user variable value")
#     ):             
#         if (key is None) != (value is None):
#             raise HTTPException(
#                 status_code=400,
#                 detail="Both 'key' and 'value' must be provided together or not at all."
#             )

#         return cls(key=key, value=value)