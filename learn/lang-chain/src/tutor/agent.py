import os
from ..utils import PrintUtils
from langchain_google_community import GoogleSearchAPIWrapper
from langchain_openai import ChatOpenAI
from langchain_core.tools import Tool
from langchain_core.messages import HumanMessage, SystemMessage
from langgraph.prebuilt import create_react_agent
from langgraph.checkpoint.sqlite import SqliteSaver




def agent(google_cse_id: str, openai_api_key: str) -> None:
    # 設定環境參數
    os.environ["GOOGLE_CSE_ID"] = google_cse_id 
    os.environ["OPENAI_API_KEY"] = openai_api_key

    # 定義工具
    search = GoogleSearchAPIWrapper()
    tool = Tool(
        name="google_search",
        description="Search Google for recent results.",
        func=lambda query: search.results(query, 5),
    )
    tools = [tool]

    # 定義模型 
    model = ChatOpenAI(model="gpt-3.5-turbo")

    # 綁定模型
    memory = SqliteSaver.from_conn_string(":memory:")
    agent_executor = create_react_agent(model, tools, checkpointer=memory)

    config = {"configurable": {"thread_id": "abc123"}}
    for message in agent_executor.stream(
        {
            "messages" : [
                SystemMessage(content="使用中文來回答問題"),
                HumanMessage(content="apple M3 與 apple M2 比較？")
            ]
        }, 
        config
    ):
        print(message)
        print()


    # printUtils.printDict(response.__dict__)
    
    # print(f"ContentString: {response.content}")
    # print(f"ToolCalls: {response.tool_calls}")