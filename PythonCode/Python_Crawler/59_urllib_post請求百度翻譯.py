import urllib.request
import urllib.parse
import json

import ssl
ssl._create_default_https_context = ssl._create_unverified_context

base_url = 'https://fanyi.baidu.com/sug'

data = {
    'kw': 'spider'
}
data = urllib.parse.urlencode(data)

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'
}

# post請求的參數 是不會拼接在url後面的 而需要方在請求對象制定的data中
# post請求參數必須進行編碼
data = data.encode('utf-8')
request = urllib.request.Request(url=base_url, data=data, headers=headers)

# 摸擬瀏覽器向服務器發出請求
response = urllib.request.urlopen(request)

# 獲取響應的數據
content = response.read().decode('utf-8')

# 字串 --> json對象
content = json.loads(content)

print(content)
