from typing import Dict, Optional
from fastapi import APIRouter, HTTPException, Path, Query, Body, status
from pydantic import BaseModel
from ..utils import uuidutils

# https://fastapi.tiangolo.com/tutorial/bigger-applications/

router = APIRouter(
    prefix='/users',
    tags=['users']
)

class Message(BaseModel):
    message: str

class User(BaseModel):
    id: str
    name: str
    age: int

class CreateUserRequestBody(BaseModel):
    name: str
    age: int

class UpdateUserRequestBody(BaseModel):
    name: Optional[str]
    age: Optional[int]

users: Dict[str, User] = {
    "1" : User(**{
        "id": "1",
        "name": "string",
        "age": 0
    })
}

# Create
@router.post(
    '/',
    response_model=User
)
async def create_user(body: CreateUserRequestBody):
    id = uuidutils.get_general_uuid()
    users[id] = User(id=id, **body.dict())
    return users[id]

# Get
@router.get(
    '/{user_id}',
    response_model=User
)
async def get_user_by_id(
    user_id: str = Path(..., description="使用者ID description")
) -> User:
    if user_id not in users:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    return users[user_id]

# Get item
@router.get(
    '/labe-value/{user_id}',
    response_model=Message 
)
async def get_user_value(
    user_id: str = Path(..., description="User ID"),
    label: str = Query(..., title="Label title", description="User label name")
    # label: Optional[str] = Query(None, title="Label title", description="User label name")
):
    if user_id not in users:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    user: User = users[user_id]

    if label not in user.dict():
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user label not found")

    return Message(message=str(user.dict()[label]))

# Put
@router.put(
    '/{user_id}',
    response_model=User
)
async def update_user_by_id(
    user_id: str = Path(..., description="使用者ID"),
    body: UpdateUserRequestBody = Body(..., description="請求體")
):
    print(body)

