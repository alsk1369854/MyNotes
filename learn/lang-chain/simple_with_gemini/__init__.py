# https://python.langchain.com/v0.2/docs/tutorials/llm_chain/

import os
from .chartbot import exec_chatbot
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate


def exec_simple(api_key: str) -> None:
    # 設置 api key
    os.environ["GOOGLE_API_KEY"] = api_key
    # 創建模型
    model = ChatGoogleGenerativeAI(model="gemini-pro")
    # 傳送訊息
    response = model.invoke("Hi, my name is ming.")
    print(response.content)


def exec_chain(api_key: str) -> None:
    # 設置 api key
    os.environ["GOOGLE_API_KEY"] = api_key
    # 創建模型
    model = ChatGoogleGenerativeAI(model="gemini-pro")
    # 字串輸出轉譯器 = response.context
    parser = StrOutputParser()
    # 提示詞模板
    system_template = "請使用 {language} 來回答我的問題"
    prompt_template = ChatPromptTemplate.from_messages(
        [
            # Google Gemini 不支援 system
            # ("system", system_template),
            ("human", system_template),
            ("ai", "你好，請問你的問題是什麼？"),
            ("human", "我叫家銘"),
            ("human", "我是一名軟體工程師"),
            ("human", "今天天氣晴朗"),
            ("human", "{text}"),
        ]
    )
    # 創建鏈
    chain = prompt_template | model | parser
    # 傳送訊息
    response = chain.invoke({"language": "繁體中文", "text": input("text:")})
    print(response)


exec_chatbot = exec_chatbot
