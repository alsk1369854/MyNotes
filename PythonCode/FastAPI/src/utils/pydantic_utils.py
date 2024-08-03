from pydantic import BaseModel, create_model
from ..orm import common_orm
from typing import Optional, Type, TypeVar

T = TypeVar('T', bound=BaseModel)

def parse_to_model(model_cls: Type[T], orm_model: common_orm.Base) -> T:
    """
    將 SQLAlchemy ORM 模型轉換為 Pydantic 模型。

    :param model_cls: Pydantic 模型類
    :param orm_model: SQLAlchemy ORM 模型實例
    :return: Pydantic 模型實例
    """
    model_data = {field: getattr(orm_model, field) for field in model_cls.__annotations__.keys()}
    return model_cls(**model_data)

def partial_model(model_cls: Type[T]) -> Type[T]:
    """
    返回給定 Pydantic 模型的部分（所有字段可選）的新模型類。
    """
    optional_fields = {k: (Optional[v], None) for k, v in model_cls.__annotations__.items()}
    return create_model(f'Partial{model_cls.__name__}', **optional_fields)

def omit_model(model_cls: Type[T], fields_to_omit: set) -> Type[T]:
    """
    返回給定 Pydantic 模型的省略指定字段的新模型類。
    """
    fields = {k: (v, ...) for k, v in model_cls.__annotations__.items() if k not in fields_to_omit}
    return create_model(f'Omit{model_cls.__name__}', **fields)


