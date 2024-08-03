# FastAPI

### run with debug

#### 安裝 vscode 延伸模組

```bash
# install vscode plugin
ms-python.debugpy
```

#### main.py

```python
from fastapi import FastAPI
import uvicorn

app = FastAPI()

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
```

### cli

```bash
# develop
uvicorn main:app --reload

# release
uvicorn main:app --host 0.0.0.0 --port 80
```

### swagger api docs

```bash
http://127.0.0.1:8000/docs
```
