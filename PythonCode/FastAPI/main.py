import uvicorn
from fastapi import FastAPI, Request, APIRouter
# from fastapi.responses import RedirectResponse
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from fastapi.openapi.docs import get_swagger_ui_html
from pydantic import BaseModel, EmailStr
from src.routers import users_router
from src.orm import common_orm

app = FastAPI()
templates = Jinja2Templates(directory="templates")

@app.on_event("startup")
def startup() -> None:
    common_orm.Base.metadata.create_all(bind=common_orm.engine)
    api_router = APIRouter(prefix='/api')
    api_router.include_router(users_router.router)
    app.include_router(api_router)
    app.mount("/static", StaticFiles(directory="static"), name="static")

@app.get("/api", include_in_schema=False)
async def custom_swagger_ui_html_cdn(request: Request):
    return templates.TemplateResponse("swagger_ui.html", {
        "request": request,
        "title": f"{app.title} - Swagger UI",
        "openapi_url": app.openapi_url,
    })

# @app.get(
#     path='/api', 
#     summary='summary',
#     description="description",
# )
# async def root(request: Request):
#     url: str = f"{request.base_url}swagger"
#     return RedirectResponse(url=url)



# if __name__ == "__main__":
#     uvicorn.run(app, host="0.0.0.0", port=8000)
    