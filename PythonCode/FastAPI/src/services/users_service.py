from typing import Dict, Optional, List
from ..models import users_model, common_model
from ..utils import uuid_utils, pydantic_utils
from ..crud import users_crud
from ..orm import users_orm
from fastapi import HTTPException, status
from pydantic import BaseModel
from sqlalchemy.orm import Session 

# https://fastapi.tiangolo.com/tutorial/bigger-applications/


user_dict: Dict[str, users_model.User] = {i : users_model.User(id=f'{i}', name=f'name_{i}', age=i%18) for i in range(100)}


# Create
def create(
    db: Session,
    body: users_model.CreateUserRequestBody
) -> users_orm.User:
    return users_crud.create_user(db, body)
    # return create_multiple([body])

# # Create multiple
# def create_multiple(
#     body: List[users_model.CreateUserRequestBody]
# ) -> common_model.Status:
#     users: List[users_model.User] = [users_model.User(id=uuid_utils.get_general_uuid(), **item.dict()) for item in body]
#     for user in users:
#         user_dict[user.id] = user
#     return common_model.Status(status=True)

# Get one
def get_one(
    db: Session,
    user_id: str, 
) -> users_orm.User:
    db_user = users_crud.get_user(db, user_id)
    if db_user == None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    return db_user


# Get page
def get_page(
    db: Session,
    params: common_model.PageRequestParams,
) -> common_model.PageResponse[users_model.User]:
    skip: int = (params.page_at - 1) * params.page_limit
    db_users = users_crud.get_users(db, skip, params.page_limit)  
    users = [pydantic_utils.parse_to_model(users_model.User, db_user) for db_user in db_users]
    total_count = users_crud.get_count(db)  
    return common_model.PageResponse[users_model.User](
        total_count=total_count, 
        response=users
    )

# Put
def update(
    db: Session,
    user_id: str,
    update_src: users_model.UpdateUserRequestBody
) -> users_orm.User:
    db_user = users_crud.get_user(db, user_id)
    if db_user == None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    return users_crud.update_user(db, user_id, update_src)

# Delete
def delete(
    db: Session,
    user_id: str,
) -> common_model.Status:  
    db_user = users_crud.get_user(db, user_id)
    if db_user == None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    users_crud.delete_user(db, user_id)
    return common_model.Status(status=True)

