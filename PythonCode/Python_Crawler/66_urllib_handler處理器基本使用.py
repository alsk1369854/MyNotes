import urllib.request

url = 'https://www.baidu.com/'

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
}

request = urllib.request.Request(url=url, headers=headers)

# handler  build_opener  open

# (1) 獲取handler對象
handler = urllib.request.HTTPHandler()

# (2) 獲取opener對象
opener = urllib.request.build_opener(handler)

# (3) 調用open方法
response = opener.open(request)

content = response.read().decode('utf-8')

print(content)

