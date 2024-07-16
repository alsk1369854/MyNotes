import os
from langchain_community.tools.tavily_search import TavilySearchResults

def tavily_search(api_key:str) -> None:
    os.environ["TAVILY_API_KEY"] = api_key
    
    # 定義工具
    search = TavilySearchResults(max_results=2)
    print(search.invoke("to day"))
    tools = [search]
