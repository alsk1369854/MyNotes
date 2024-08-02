from pydantic import BaseModel
from typing import Optional

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