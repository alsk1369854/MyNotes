import os
from langchain_google_genai import ChatGoogleGenerativeAI


def exec_simple(api_key: str) -> None:
    # 設置 api key
    os.environ["GOOGLE_API_KEY"] = api_key
    # 創建模型
    model = ChatGoogleGenerativeAI(model="gemini-pro")
    # 傳送訊息
    response = model.invoke("Hi, my name is ming.")
    print(response.content)
