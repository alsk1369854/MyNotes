import gemini
import google_search
import asyncio
import os
from dotenv import load_dotenv, dotenv_values





def main() -> None:
    env = dotenv_values('.env')
    api_key: str = os.environ["GOOGLE_API_KEY"]
    # gemini.exec_google(api_key)
    # gemini.exec_simple(api_key)
    # gemini.exec_llm_chain(api_key)
    # gemini.exec_chatbot(api_key)
    # asyncio.run(gemini.exec_retrieves(api_key))
    google_search.google_search(env["GOOGLE_API_KEY"], env["GOOGLE_CSE_ID"])



if __name__ == "__main__":
    main()
