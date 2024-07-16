from langchain_community.utilities import GoogleSearchAPIWrapper
from langchain_core.tools import Tool
import os


def google_search(API_KEY:str, CSE_KEY:str) -> None:
    """
    api_key: 從此獲取 https://console.cloud.google.com/apis/credentials
    CSE_KEY: 從此獲取 https://programmablesearchengine.google.com/controlpanel/all
    """

    os.environ["GOOGLE_CSE_ID"] = CSE_KEY 
    os.environ["GOOGLE_API_KEY"] = API_KEY

    # Base use
    search = GoogleSearchAPIWrapper()
    # 回傳前5筆
    tool = Tool(
        name="google_search",
        description="Search Google for recent results.",
        func=lambda query: search.results(query, 5),
    )
    
    print(tool.run("今天台灣天氣"))

    # Number of Results
    # 回傳第一筆
    # search = GoogleSearchAPIWrapper(k=1)
    # tool = Tool(
    #     name="google_search",
    #     description="Search Google for recent results.",
    #     func=search.run,
    # )
    # print(tool.run("今天台灣天氣"))

    