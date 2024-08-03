from fastapi import FastAPI, Request, APIRouter
from fastapi.responses import RedirectResponse
import uvicorn
from pydantic import BaseModel, EmailStr
from src.routers import users_router

api_router = APIRouter(prefix='/api')
api_router.include_router(users_router.router)

app = FastAPI()
app.include_router(api_router)

@app.get(
    path='/', 
    summary='summary',
    description="description",
)
async def root(request: Request):
    url: str = f"{request.base_url}docs"
    return RedirectResponse(url=url)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
    