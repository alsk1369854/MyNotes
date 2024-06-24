import simple_with_gemini


def main() -> None:
    api_key: str = "..."
    # simple_with_gemini.exec_simple(api_key)
    # simple_with_gemini.exec_chain(api_key)
    simple_with_gemini.exec_chatbot(api_key)


if __name__ == "__main__":
    main()
