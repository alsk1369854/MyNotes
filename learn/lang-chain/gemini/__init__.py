# https://python.langchain.com/v0.2/docs/tutorials/llm_chain/

from .google import exec_google
from .chatbot import exec_chatbot
from .simple import exec_simple
from .llm_chain import exec_llm_chain
from .retrievers import exec_retrieves

# 未使用 LangChain
exec_google = exec_google
# 使用 LangChain
exec_simple = exec_simple
exec_llm_chain = exec_llm_chain
exec_chatbot = exec_chatbot
exec_retrieves = exec_retrieves
