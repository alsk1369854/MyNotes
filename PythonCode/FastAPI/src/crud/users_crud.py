from typing import Optional, List
from sqlalchemy.orm import Session
from sqlalchemy.exc import SQLAlchemyError, NoResultFound
from fastapi import HTTPException, status
from ..orm import users_orm
from ..models import users_model
from ..utils import uuid_utils

def get_user(db: Session, user_id: int) -> Optional[users_orm.User]:
    return db.query(users_orm.User).filter(users_orm.User.id == user_id).first()

def get_users(db: Session, skip: int = 0, limit: int = 100) -> List[users_orm.User]:
    return db.query(users_orm.User).offset(skip).limit(limit).all()

def get_count(db: Session) -> int:
    return db.query(users_orm.User).count()

def create_user(db: Session, user: users_model.CreateUserRequestBody) -> users_orm.User:
    try:
        db_user = users_orm.User(id=uuid_utils.get_general_uuid(), **user.dict())
        db.add(db_user)
        db.commit()
        db.refresh(db_user)
        return db_user
    except SQLAlchemyError as err:
        db.rollback()
        raise HTTPException(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, detail="database error")


def delete_user(db: Session, user_id: int) -> None:
    db_user = get_user(db, user_id)
    if db_user == None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")

    try:
        db.delete(db_user)
        db.commit()
    except SQLAlchemyError as err:
        db.rollback() 
        raise HTTPException(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, detail="database error")

def update_user(db: Session, user_id: int, update_src: users_model.UpdateUserRequestBody) -> users_orm.User:
    db_user = get_user(db, user_id)
    if db_user == None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user not found")

    try:
        for field, value in update_src.dict(exclude_unset=True).items():
            setattr(db_user, field, value)
        db.commit()
        db.refresh(db_user)
        return db_user
    except SQLAlchemyError as err:
        db.rollback() 
        raise HTTPException(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, detail="database error")
