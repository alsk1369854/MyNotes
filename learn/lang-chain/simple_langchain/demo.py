import os

from langchain_google_genai import ChatGoogleGenerativeAI
from .chain import create_chain


def create_simple(api_key: str) -> None:
    if "GOOGLE_API_KEY" not in os.environ:
        os.environ["GOOGLE_API_KEY"] = api_key

    model = ChatGoogleGenerativeAI(model="gemini-pro")
    result = model.invoke("Write a ballad about LangChain")
    print(result.content)


def create_simple_chain(api_key: str) -> None:
    create_chain(api_key)
