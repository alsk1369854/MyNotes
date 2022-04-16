import urllib.request
import urllib.parse

import ssl
ssl._create_default_https_context = ssl._create_unverified_context

# base_url = 'https://www.baidu.com/s?'
base_url = 'https://www.google.com/search?'

data = {
    'q' : '林俊傑',
    'sex' : '男',
    'location' : '台灣',
}

# urlencode 將data 傳換成unicode編碼形式
new_data = urllib.parse.urlencode(data)
print('@DataToUnicode ' + new_data)

# 請求資源路徑
url = base_url + new_data

# 請求對象的定制
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
}
request = urllib.request.Request(url=url, headers=headers)

# 摸擬瀏覽器向服務器發送請求
response = urllib.request.urlopen(request)

# 獲取網頁源數據
content = response.read().decode('utf-8')

# 打印數據
print(content)