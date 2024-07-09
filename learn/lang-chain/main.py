import gemini
import asyncio
import os
from dotenv import load_dotenv, dotenv_values

# load_dotenv()


def main() -> None:
    api_key: str = os.environ["GOOGLE_API_KEY"]
    # gemini.exec_google(api_key)
    # gemini.exec_simple(api_key)
    # gemini.exec_llm_chain(api_key)
    # gemini.exec_chatbot(api_key)
    asyncio.run(gemini.exec_retrieves(api_key))


if __name__ == "__main__":
    main()
