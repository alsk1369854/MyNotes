from fastapi import FastAPI, Request, APIRouter
from fastapi.responses import RedirectResponse
import uvicorn
from pydantic import BaseModel, EmailStr
from src.routers import users_router
from src.orm import common_orm




# api_router = APIRouter(prefix='/api')
# api_router.include_router(users_router.router)

# print("init1")
# app = FastAPI()
# print("init2")
# app.include_router(api_router)

# @app.get(
#     path='/', 
#     summary='summary',
#     description="description",
# )
# async def root(request: Request):
#     url: str = f"{request.base_url}docs"
#     return RedirectResponse(url=url)

app = FastAPI()

def init() -> None:
    common_orm.Base.metadata.create_all(bind=common_orm.engine)
    api_router = APIRouter(prefix='/api')
    api_router.include_router(users_router.router)
    app.include_router(api_router)

def main() -> None:
    @app.get(
        path='/', 
        summary='summary',
        description="description",
    )
    async def root(request: Request):
        url: str = f"{request.base_url}docs"
        return RedirectResponse(url=url)


init()
main()

# if __name__ == "__main__":
#     uvicorn.run(app, host="0.0.0.0", port=8000)
    