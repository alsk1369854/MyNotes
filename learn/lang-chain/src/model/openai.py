import os
from langchain_openai import ChatOpenAI



def openai(api_key: str) -> None:
    os.environ["OPENAI_API_KEY"] = api_key
    
    model = ChatOpenAI(model="gpt-3.5-turbo")
    print(model.invoke("嗨"))