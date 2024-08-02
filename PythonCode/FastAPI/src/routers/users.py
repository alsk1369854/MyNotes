from typing import Dict, Optional, List
from fastapi import APIRouter, HTTPException, Path, Query, Body, status
from pydantic import BaseModel
from .. import utils, models

# https://fastapi.tiangolo.com/tutorial/bigger-applications/

class User(BaseModel):
    id: str
    name: str
    age: int

class Message(BaseModel):
    message: str

class Status(BaseModel):
    status: bool

class CreateUserRequestBody(BaseModel):
    name: str
    age: int

class UpdateUserRequestBody(BaseModel):
    name: Optional[str] = None
    age: Optional[int] = None


user_dict: Dict[str, User] = {
    "1" : User(**{
        "id": "1",
        "name": "string",
        "age": 0
    })
}

router = APIRouter(
    prefix='/user',
    tags=['user']
)

# Create
@router.post(
    '/create',
    summary="創建用戶",
    description="創建用戶",
)
async def create_user(body: CreateUserRequestBody) -> User:
    id = utils.uuid_utils.get_general_uuid()
    user_dict[id] = User(id=id, **body.dict())
    return user_dict[id]

# Get one
@router.get(
    '/one/{user_id}',
    summary="獲取用戶根據ID",
    description="獲取用戶根據ID",
)
async def get_user_by_id(
    user_id: str = Path(..., description="使用者ID description")
) -> User:
    if user_id not in user_dict:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    return user_dict[user_id]

# Get list
@router.get(
    '/list',
    summary="獲取用戶列表",
    description="獲取用戶列表",
)
async def get_users() -> List[User]:
    return user_dict.values()

# Check something
@router.get(
    '/has-key/{user_id}',
    summary="檢查是否存指定參數",
    description="檢查是否存指定參數",
)
async def get_user_value(
    user_id: str = Path(..., description="User ID"),
    key: str = Query(..., description="User label name")
) -> Message:
    if user_id not in user_dict:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    user: User = user_dict[user_id]

    if key not in user.dict():
        return Message(message="no")
    return Message(message="yes")


# Put
@router.put(
    '/update/{user_id}',
    summary="修改使用者",
    description="修改使用者",
)
async def update_user_by_id(
    user_id: str = Path(..., description="使用者ID"),
    body: UpdateUserRequestBody = Body(...)
) -> User:
    if user_id not in user_dict:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")

    user = user_dict[user_id]
    for key, value in body.dict().items():
        if value != None:
            user.__setattr__(key, value)  

    return user_dict[user_id]

# Delete
@router.delete(
    '/delete/{user_id}',
    summary="刪除指定用戶",
    description="刪除指定用戶",
)
async def delete_user(
    user_id: str = Path(..., description="用戶ID")
) -> Status:  
    del user_dict[user_id]
    return {}
    # return Status(status=True)


