# FastAPI

## main.py
```python
from fastapi import FastAPI
import uvicorn

app = FastAPI()

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
```

## Setup

### Virtual environment 
```bash
# Create python venv
python3.12 -m venv .venv

# Enter venv (linux)
source .venv/bin/activate
```

### Install Dependencies
```bash
pip install -r requirements.txt
```

## Run Server
- [API Documents](http://0.0.0.0:8080)
```bash
uvicorn main:app --host 0.0.0.0 --port 8080
```



## Develop

### Run Server with auto-reload
```bash
uvicorn main:app --host 0.0.0.0 --port 8080 --reload
```

### Run debug mode(vscode)

#### Install extensions
```bash
# extension ID
ms-python.debugpy
# link
https://marketplace.visualstudio.com/items?itemName=ms-python.debugpy
```

#### .vscode/launch.json
```json
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [

        {
            "name": "Python Debugger: FastAPI",
            "type": "debugpy",
            "request": "launch",
            "module": "uvicorn",
            "args": [
                "main:app",
                "--host", "0.0.0.0",
                "--port", "8080",
                "--reload"
            ],
            "jinja": true
        }
    ]
}
```

#### Start Debugging
```bash
# Change to "Run and Debug" tag
Ctrl + Shift + D

# Start Debugging
F5
```

### Update requirement dependencies
```bash
pip freeze > requirements.txt
```