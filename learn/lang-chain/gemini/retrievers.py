# https://python.langchain.com/v0.2/docs/tutorials/retrievers/

import os
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.documents import Document
from langchain_core.runnables import RunnableLambda, RunnablePassthrough
from langchain_chroma import Chroma
from langchain_google_genai import (
    GoogleGenerativeAI,
    GoogleGenerativeAIEmbeddings,
    chat_models,
    embeddings,
)


async def exec_retrieves(api_key: str) -> None:
    os.environ["GOOGLE_API_KEY"] = api_key

    # Documents
    documents = [
        Document(
            page_content="Dogs are great companions, known for their loyalty and friendliness.",
            metadata={"source": "mammal-pets-doc"},
        ),
        Document(
            page_content="Cats are independent pets that often enjoy their own space.",
            metadata={"source": "mammal-pets-doc"},
        ),
        Document(
            page_content="Goldfish are popular pets for beginners, requiring relatively simple care.",
            metadata={"source": "fish-pets-doc"},
        ),
        Document(
            page_content="Parrots are intelligent birds capable of mimicking human speech.",
            metadata={"source": "bird-pets-doc"},
        ),
        Document(
            page_content="Rabbits are social animals that need plenty of space to hop around.",
            metadata={"source": "mammal-pets-doc"},
        ),
    ]

    # Vector stores
    vectorstore = Chroma.from_documents(
        documents,
        embedding=GoogleGenerativeAIEmbeddings(model="models/embedding-001"),
    )
    # 1. 根據與字串查詢的相似性傳回文件
    # print(vectorstore.similarity_search("cat"))
    # 2. 非同步查詢
    # print(await vectorstore.asimilarity_search("cat"))
    # 3. 回傳分數
    # print(vectorstore.similarity_search_with_score("cat"))
    # 4. 根據與嵌入查詢的相似性傳回文件
    # embedding = GoogleGenerativeAIEmbeddings(model="models/embedding-001").embed_query(
    #     "cat"
    # )
    # print(vectorstore.similarity_search_by_vector(embedding))

    # Retrievers
    # 方法一
    # retriever = RunnableLambda(vectorstore.similarity_search).bind(k=1)  # select top result
    # print(retriever.batch(["cat", "shark"]))
    # 方法二
    retriever = vectorstore.as_retriever(
        search_type="similarity",
        search_kwargs={"k": 1},
    )
    # print(retriever.batch(["cat", "shark"]))

    message = """
Answer this question using the provided context only.

{question}

Context:
{context}
"""

    prompt = ChatPromptTemplate.from_messages([("human", message)])
    llm = GoogleGenerativeAI(model="gemini-pro")

    # temp = {"context": retriever, "question": RunnablePassthrough()} | prompt
    # result = temp.invoke("tell me about cats")
    # print(result)

    rag_chain = {"context": retriever, "question": RunnablePassthrough()} | prompt | llm
    response = rag_chain.invoke("tell me about cats")
    print(response)