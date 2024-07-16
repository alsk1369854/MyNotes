import os
from langchain_core.tools import Tool
from langchain_google_genai import GoogleGenerativeAI
from langchain_google_community import GoogleSearchAPIWrapper
from langchain_google_vertexai import ChatVertexAI
from langgraph.prebuilt import create_react_agent
from langchain_core.messages import HumanMessage


def exec_agent(api_key: str, cse_id: str) -> None:
    # 設定環境參數
    os.environ["GOOGLE_API_KEY"] = api_key
    os.environ["GOOGLE_CSE_ID"] = cse_id 

    # 定義工具
    search = GoogleSearchAPIWrapper()
    tool = Tool(
        name="google_search",
        description="Search Google for recent results.",
        func=lambda query: search.results(query, 2),
    )
    # print(tool.run("今日新聞"))
    tools = [tool]

    # 定義模型
    model = ChatVertexAI(model="gemini-pro", project="gen-lang-client-0279096178")
    print(model.invoke("HI"))

    # 綁定模型與工具
    agent_executor = create_react_agent(model, tools)
    # print(agent_executor.invoke([HumanMessage(content="嗨")]))
