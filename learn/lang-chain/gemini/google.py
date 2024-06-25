import google.generativeai as genai


def exec_google(api_key: str) -> None:
    genai.configure(api_key=api_key)

    model = genai.GenerativeModel("gemini-1.5-flash")
    response = model.generate_content("Hi, I'm Ming.")
    print(response.text)
