# https://python.langchain.com/v0.2/docs/tutorials/chatbot/
import os
from typing import Any, Dict, DefatulDict
from operator import itemgetter
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from langchain_core.runnables import RunnablePassthrough, RunnableConfig, Runnable
from langchain_google_genai import GoogleGenerativeAI
from langchain_core.messages import trim_messages, HumanMessage
from langchain_community.chat_message_histories import ChatMessageHistory
from langchain_core.chat_history import BaseChatMessageHistory
from langchain_core.runnables.history import RunnableWithMessageHistory


# 會談歷史
session_store: Dict[str, ChatMessageHistory] = {}


def get_session_history(session_id: str) -> BaseChatMessageHistory:
    if session_id not in session_store:
        session_store[session_id] = ChatMessageHistory()
    return session_store[session_id]


def exec_chatbot(api_key: str, language: str = "Chinese") -> None:
    # 1. set api key
    os.environ["GOOGLE_API_KEY"] = api_key
    # 2. create model
    model = GoogleGenerativeAI(model="gemini-pro")
    # 3. prompt template
    prompt = ChatPromptTemplate.from_messages(
        [
            (
                "human",
                "You are a helpful assistant. Answer all questions to the best of your ability in {language}.",
            ),
            MessagesPlaceholder(variable_name="messages"),
        ]
    )
    # 4. token limit
    trimmer = trim_messages(
        # 只保留短期記憶
        max_tokens=65,
        strategy="last",
        token_counter=model,
        # token 包含系統提示
        include_system=True,
        # 允許不完整的一段話存在
        allow_partial=False,
        # 才接結果由人類先發話
        start_on="human",
    )
    # 5. create chain
    chain: Runnable = (
        # 收集歷史對話中的 "messages" 的數據，傳入到 trimmer 進行裁切
        RunnablePassthrough.assign(messages=itemgetter("messages") | trimmer)
        | prompt
        | model
    )
    # 6. 歷史訊息綁定
    with_message_history = RunnableWithMessageHistory(
        chain,
        get_session_history,
        # 因為提示模板中有多個參數，這邊告訴程式 messages 是我們用來傳送訊息的
        input_messages_key="messages",
    )
    # 7. 創建歷史會談配置，相同的 session_id 會記住他的會談歷史
    config: RunnableConfig = RunnableConfig(configurable={"session_id": "abc15"})

    while text := input("text:"):
        response = with_message_history.invoke(
            {"messages": [HumanMessage(content=text)], "language": language},
            config=config,
        )
        print(response)


# # 8. 使用流式傳輸的到響應
# for r in with_message_history.stream(
#     {
#         "messages": [HumanMessage(content="hi! I'm todd. tell me a joke")],
#         "language": "English",
#     },
#     config=config,
# ):
#     print(r.content, end="|")
