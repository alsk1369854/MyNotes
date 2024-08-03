from typing import Optional, List
from fastapi import APIRouter, status, HTTPException, Path, Query, Body, Depends
from ..models import users_model, common_model
from ..orm import common_orm, users_orm
from ..services import users_service
from sqlalchemy.orm import Session

# https://fastapi.tiangolo.com/tutorial/bigger-applications/


router = APIRouter(
    prefix='/user',
    tags=['user']
)

# Create
@router.post(
    '/create',
    summary="Create single user",
    description="create single user",
    response_model=users_model.User
)
async def create(
    body: users_model.CreateUserRequestBody = Body(..., description="Create user source"), 
    db: Session = Depends(common_orm.get_db),
) -> users_orm.User:
    return users_service.create(db, body)

# # Create multiple
# @router.post(
#     '/create-multiple',
#     summary="創建多個用戶",
#     description="創建多個用戶",
#     response_model=common_model.Status
# )
# async def create_multiple(
#     body: List[users_model.CreateUserRequestBody]
# ) -> common_model.Status:
#     return users_service.create_multiple(body)

# Get one
@router.get(
    '/one/{user_id}',
    summary="Get User by ID",
    description="Get User by ID",
    response_model=users_model.User
)
async def get_one(
    db: Session = Depends(common_orm.get_db),
    user_id: str = Path(..., description="User ID"),
) -> users_orm.User:
    return users_service.get_one(db, user_id)

# Get page
@router.get(
    '/page',
    summary="Get page user data",
    description="Get page user data",
    response_model=common_model.PageResponse[users_model.User]
)
async def get_page(
    db: Session = Depends(common_orm.get_db),
    params: common_model.PageRequestParams = Depends(common_model.PageRequestParams.as_query),
) -> common_model.PageResponse[users_model.User]:
    return users_service.get_page(db, params)

# Put
@router.put(
    '/update/{user_id}',
    summary="Update User",
    description="Update User",
    response_model=users_model.User
)
async def update(
    db: Session = Depends(common_orm.get_db),
    user_id: str = Path(..., description="User ID"),
    body: users_model.UpdateUserRequestBody = Body(..., description="Update source")
) -> users_model.User:
    return users_service.update(db, user_id, body)

# Delete
@router.delete(
    '/delete/{user_id}',
    summary="Delete User By ID",
    description="Delete User By ID",
    response_model=common_model.Status
)
async def delete(
    db: Session = Depends(common_orm.get_db),
    user_id: str = Path(..., description="User ID"),
) -> common_model.Status: 
    return users_service.delete(db, user_id)


