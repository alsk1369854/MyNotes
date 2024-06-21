from langserve import RemoteRunnable

# simple built-in UI: http://localhost:8000/chain/playground/

remote_chain = RemoteRunnable("http://localhost:8000/chain/")
result = remote_chain.invoke({"language": "italian", "text": "hi"})
print(result)
