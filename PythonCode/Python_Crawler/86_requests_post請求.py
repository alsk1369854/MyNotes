import requests

url = 'https://fanyi.baidu.com/sug'


headers ={
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36'
}

data = {
    'kw' : 'hello'
}

# url = 地址
# data 參數
# headers 字典
request = requests.post(url=url, data=data, headers=headers)
request.encoding = 'utf-8'
print(request.text)

import json
content = json.loads(request.text)
print(content)