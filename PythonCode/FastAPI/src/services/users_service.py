from typing import Dict, Optional, List
from ..models import users_model, common_model
from ..utils import uuid_utils
from fastapi import HTTPException, status
from pydantic import BaseModel

# https://fastapi.tiangolo.com/tutorial/bigger-applications/


user_dict: Dict[str, users_model.User] = {i : users_model.User(id=f'{i}', name=f'name_{i}', age=i%18) for i in range(100)}


# Create
def create(
    body: users_model.CreateUserRequestBody
) -> common_model.Status:
    return create_multiple([body])

# Create multiple
def create_multiple(
    body: List[users_model.CreateUserRequestBody]
) -> common_model.Status:
    users: List[users_model.User] = [users_model.User(id=uuid_utils.get_general_uuid(), **item.dict()) for item in body]
    for user in users:
        user_dict[user.id] = user
    return common_model.Status(status=True)

# Get one
def get_one(
    user_id: str 
) -> users_model.User:
    if user_id not in user_dict:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")
    return user_dict[user_id]

# Get list
def get_list() -> List[users_model.User]:
    return list(user_dict.values())

# Get page
def get_page(params: common_model.PageRequestParams) -> common_model.PageResponse[users_model.User]:
    users = get_list()
    users_len: int = len(users)
    start_index = (params.page_at - 1) * params.page_size
    end_index = min(start_index + params.page_size, users_len)
    return common_model.PageResponse[users_model.User](total_count=users_len, response=users[start_index:end_index])

# Get count
def count(
    conditions: List[common_model.CountCondition]
) -> common_model.Count:
    users: List[users_model.User] = get_list()
    def equal_filter_handler(key: str, item: users_model.User, condition: common_model.CountEqualThenCondition) -> bool:
        value = item.__getattribute__(key) 
        if (isinstance(value, BaseModel)):
            return value.dict() == condition.eq
        else:
            return value == condition.eq

    key: str = ""
    try:
        for condition in conditions:
            key = condition.key
            if (isinstance(condition, common_model.CountEqualThenCondition)):
                users = list(filter(lambda item: equal_filter_handler(key, item, condition), users))
            elif (isinstance(condition, common_model.CountGreaterThenCondition)):
                users = list(filter(lambda item: item.__getattribute__(key) > condition.gt, users))
            elif (isinstance(condition, common_model.CountLessThenCondition)):
                users = list(filter(lambda item: item.__getattribute__(key) < condition.ls, users))
            else:
                raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="condition struct error")
    except:
        raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail=f"key: '{key}' is not defined")

    count: int = len(users)
    return common_model.Count(count=count)

# Check something
def get_user_value(
    user_id: str,
    key: str,
) -> common_model.Status:
    if user_id not in user_dict:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")

    user: User = user_dict[user_id]
    if key not in user.dict():
        return Status(False)
    return common_model.Status(True)


# Put
def update(
    user_id: str,
    body: users_model.UpdateUserRequestBody
) -> users_model.User:
    if user_id not in user_dict:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")

    user = user_dict[user_id]
    for key, value in body.dict().items():
        if value != None:
            user.__setattr__(key, value)  
    return user

# Delete
async def delete(
    user_id: str
) -> common_model.Status:  
    del user_dict[user_id]
    return common_model.Status(status=True)


