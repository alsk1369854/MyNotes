import gemini
import asyncio


def main() -> None:
    api_key: str = "..."
    # gemini.exec_google(api_key)
    # gemini.exec_simple(api_key)
    # gemini.exec_llm_chain(api_key)
    # gemini.exec_chatbot(api_key)
    asyncio.run(gemini.exec_retrieves(api_key))


if __name__ == "__main__":
    main()
