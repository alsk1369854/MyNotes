# Python Flask With Heroku

## Flask: python 服務器架設工具
### installation
```shell
$ pip install flask
```

### server code
```python
from flask import Flask

# 設定當前執行模組
app = Flask(__name__)


# 設計 API 接口
@app.route('/')  # web router
def home():
    return 'hello, python'


@app.route('/test')
def test():
    return 'test page'


if __name__ == '__main__':
    app.run()

```

### run server
```shell
$ python app.py

# 指定 8888端口
$ flask run --port:8888
```
<br/>

## Heroku: 遠端服務器平台
### installation
```text
git : https://git-scm.com/downloads
heroku cli : https://devcenter.heroku.com/articles/heroku-cli 
```
### config file
#### requirements.txt : 專案中用到的工具
```text
flask
gunicorn
```
#### runtime.txt : 轉案運行的 python 版本
```text
python-3.9.12
```

#### Procfile : heroku 服務器啟動配置 
```text
# app: 為python檔案名稱
# :app 為 app.py 裡的 Flask 對象變數名稱
web gunicorn main:app
```

### push to heroku
```shell
# 建立 git 包管理
$ git init
$ git add .
$ git commit -a -m 'update message'

# 登入 heroku
$ heroku login
# 將 heroku app 遠程庫地址加入 remote 中
$ heroku git:remote -a {youer heroku app name}

# 將本地庫文件推送至遠程庫
$ git push keroku master
```