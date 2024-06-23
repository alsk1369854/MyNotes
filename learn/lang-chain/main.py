# from gemini import model_factory
from simple_langchain import demo


def main() -> None:
    api_key: str = "..."
    # model_factory.create_model(api_key)
    demo.create_simple_chain(api_key)


if __name__ == "__main__":
    main()
