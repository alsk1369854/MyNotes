import os
from dotenv import load_dotenv, dotenv_values
# import asyncio
# import gemini
# import google_search
from . import tool 
# import model
# import tutor

# from .tutor import agent


def run() -> None:
    env = dotenv_values('.env')

    # lang-chain
    # gemini.exec_google(api_key)
    # gemini.exec_simple(api_key)
    # gemini.exec_llm_chain(api_key)
    # gemini.exec_chatbot(api_key)
    # asyncio.run(gemini.exec_retrieves(api_key))
    # google_search.google_search(env["GOOGLE_API_KEY"], env["GOOGLE_CSE_ID"])
    # gemini.exec_agent(env["GOOGLE_API_KEY"], env["GOOGLE_CSE_ID"])

    # tool
    # tool.tavily_search(env["TAVILY_API_KEY"])
    tool.python_repl()

    # model
    # model.openai(env["OPENAI_API_KEY"])

    # tutor
    # agent(env["GOOGLE_CSE_ID"], env["OPENAI_API_KEY"])
