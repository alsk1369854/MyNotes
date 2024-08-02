from fastapi import FastAPI, APIRouter
from pydantic import BaseModel, EmailStr
from src.routers import users


api_router = APIRouter(prefix='/api')
api_router.include_router(users.router)

app = FastAPI()
app.include_router(api_router)

@app.get(
    path='/', 
    summary='summary',
    description="description",
)
async def root():
    return {"message": "server is running"}