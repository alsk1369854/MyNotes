# https://python.langchain.com/v0.2/docs/tutorials/chatbot/
from typing import Callable
from operator import itemgetter
import os
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from langchain_core.runnables import RunnablePassthrough
from langchain_openai import ChatOpenAI


# 1. 建立語言模型
os.environ["OPENAI_API_KEY"] = getpass.getpass()
model = ChatOpenAI(model="gpt-3.5-turbo")

# 2. 創建提示模板
prompt = ChatPromptTemplate.from_messages(
    [
        (
            "system",
            "You are a helpful assistant. Answer all questions to the best of your ability in {language}.",
        ),
        MessagesPlaceholder(variable_name="messages"),
    ]
)


# 3. 設定 token 裁減
trimmer = trim_messages(
    max_tokens=65,
    strategy="last",
    token_counter=model,
    include_system=True,
    allow_partial=False,
    start_on="human",
)


# 4. 創建鏈
chain = (
    # 將歷史 dict 中 key="messages" 的數據，傳入到 trimmer 進行裁切
    RunnablePassthrough.assign(messages=itemgetter("messages") | trimmer)
    | prompt
    | model
)


# 5. 創建訊息歷史
def get_message_history() -> Callable[[str], BaseChatMessageHistory]:
    store = {}

    def get_session_history(session_id: str) -> BaseChatMessageHistory:
        if session_id not in store:
            store[session_id] = ChatMessageHistory()
        return store[session_id]

    return get_session_history


get_session_history = get_message_history()


# 6. 將模型與訊息歷史綁定
with_message_history = RunnableWithMessageHistory(
    chain,
    get_session_history,
    # 因為提示模板中有多個參數，這邊告訴程式 messages 是我們用來傳送訊息的
    input_messages_key="messages",
)


# 7. 創建歷史會談配置，相同的 session_id 會記住他的會談歷史
config = {"configurable": {"session_id": "abc15"}}

# 8. 使用流式傳輸的到響應
for r in with_message_history.stream(
    {
        "messages": [HumanMessage(content="hi! I'm todd. tell me a joke")],
        "language": "English",
    },
    config=config,
):
    print(r.content, end="|")
