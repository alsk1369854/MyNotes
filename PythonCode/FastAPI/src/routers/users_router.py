from typing import Optional, List
from fastapi import APIRouter, status, HTTPException, Path, Query, Body, Depends
from ..models import users_model, common_model
from ..services import users_service

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
    response_model=common_model.Status
)
async def create(
    body: users_model.CreateUserRequestBody
) -> common_model.Status:
    return users_service.create(body)

# Create multiple
@router.post(
    '/create-multiple',
    summary="創建多個用戶",
    description="創建多個用戶",
    response_model=common_model.Status
)
async def create_multiple(
    body: List[users_model.CreateUserRequestBody]
) -> common_model.Status:
    return users_service.create_multiple(body)

# Get one
@router.get(
    '/one/{user_id}',
    summary="獲取用戶根據ID",
    description="獲取用戶根據ID",
    response_model=users_model.User
)
async def get_one(
    user_id: str = Path(..., description="用戶ID")
) -> users_model.User:
    return users_service.get_one(user_id)

# Get list
@router.get(
    '/list',
    summary="獲取用戶列表",
    description="獲取用戶列表",
    response_model=List[users_model.User]
)
async def get_list() -> List[users_model.User]:
    return users_service.get_list()

@router.get(
    '/page',
    summary="Get page user data",
    description="Get page user data",
    response_model=common_model.PageResponse[users_model.User]
)
async def get_page(
    params: common_model.PageRequestParams = Depends(common_model.PageRequestParams.as_query)
) -> common_model.PageResponse[users_model.User]:
    return users_service.get_page(params)

@router.post(
    '/count',
    summary="Count data based on conditions",
    description="Count data based on conditions",
    response_model=common_model.Count
)
async def count(
    body: List[common_model.CountCondition] = Body(..., description="Conditions for count data")
) -> common_model.Count:
    return users_service.count(body)

# @router.get(
#     '/count',
#     summary="獲取用戶資料總量",
#     description="獲取用戶資料總量"
# )
# async def get_count(
#     params: Optional[users_model.CountUserRequestParams] = Depends(users_model.CountUserRequestParams.as_query),
# ) -> common_model.Count:
#     print(params)
#     return users_service.get_count(params)

# Check something
@router.get(
    '/has-key/{user_id}',
    summary="檢查是否存指定參數",
    description="檢查是否存指定參數",
    response_model=common_model.Status
)
async def get_user_value(
    user_id: str = Path(..., description="用戶ID"),
    key: str = Query(..., description="用戶參數key")
) -> common_model.Status:
    return users_service.get_user_value(user_id, key)

# Put
@router.put(
    '/update/{user_id}',
    summary="修改使用者",
    description="修改使用者",
    response_model=users_model.User
)
async def update(
    user_id: str = Path(..., description="用戶ID"),
    body: users_model.UpdateUserRequestBody = Body(..., description="要修改的參數")
) -> users_model.User:
    return users_service.update(user_id, body)

# Delete
@router.delete(
    '/delete/{user_id}',
    summary="刪除指定用戶",
    description="刪除指定用戶",
    response_model=common_model.Status
)
async def delete(
    user_id: str = Path(..., description="用戶ID")
) -> common_model.Status: 
    return users_service.delete(user_id)


