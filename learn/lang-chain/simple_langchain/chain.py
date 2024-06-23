# https://python.langchain.com/v0.2/docs/tutorials/llm_chain/

from typing import List
import os

from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate


def create_chain(api_key: str) -> None:
    # 在 base 中將其設定為環境變數
    os.environ["GOOGLE_API_KEY"] = api_key

    model = ChatGoogleGenerativeAI(model="gemini-pro")

    parser = StrOutputParser()

    system_template = "請使用 {language} 來回答我的問題"
    prompt_template = ChatPromptTemplate.from_messages(
        [
            # Google Gemini 不支援 SystemMessage
            # ("system", system_template),
            ("human", system_template),
            ("ai", "你好，請問你的問題是什麼？"),
            ("human", "我叫家銘"),
            ("human", "我是一名軟體工程師"),
            ("human", "今天天氣不錯"),
            ("human", "{text}"),
        ]
    )

    chain = prompt_template | model | parser
    # respond = chain.invoke({"language": "繁體中文", "text": "我叫什麼名字"})
    respond = chain.invoke({"language": "繁體中文", "text": input("text:")})
    print(respond)
