from typing import Dict
from fastapi import APIRouter, HTTPException, Path
from pydantic import BaseModel
from ..utils import uuidutils

# https://fastapi.tiangolo.com/tutorial/bigger-applications/

router = APIRouter(
    prefix='/users',
    tags=['users']
)

class User(BaseModel):
    id: str
    name: str
    age: int

class CreateUserRequestBody(BaseModel):
    name: str
    age: int

users: Dict[str, User] = {}

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
    user_id: str
    # user_id: str = Path(None, title="使用者ID title", description="使用者ID description")
):
    if user_id not in users:
        raise HTTPException(status_code=404, detail="user not found")
    return users[user_id]

