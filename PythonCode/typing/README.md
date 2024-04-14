### create venv

```bash
# create venv
python3 -m venv .venv

# start  venv
# by windows
.venv\Scripts\activate.bat
# by linux or macOS
source .venv/bin/activate

# exit venv
deactivate
```

### install mypy

```bash
pip install mypy
```

### install vscode tool

```bash
名稱: Mypy Type Checker
識別碼: ms-python.mypy-type-checker
描述: Linting support for python files using `mypy`.
版本: 2023.2.0
發行者: Microsoft
VS Marketplace 連結: https://marketplace.visualstudio.com/items?itemName=ms-python.mypy-type-checker
```

### create pyproject.toml

- 配置後將不用在手動輸入 mypy 命令
- 啟動 vscode Python Select linter
  - 1. clt + shift + P > Python Select Linter > mypy

```bash
[tool.mypy]
strict = true
```

### start mypy type checking

```
mypy src/*.py --strict
```
