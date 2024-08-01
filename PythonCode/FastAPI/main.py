from fastapi import FastAPI
from pydantic import BaseModel, EmailStr
from src.routers import users

app = FastAPI()
app.include_router(users.router)

@app.get(
    path='/', 
    summary='summary',
    description="description",
)
async def root():
    return {"message": "server is running"}