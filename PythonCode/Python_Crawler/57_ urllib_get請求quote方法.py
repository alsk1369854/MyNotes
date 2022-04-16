import urllib.request
import urllib.parse
import ssl
ssl._create_default_https_context = ssl._create_unverified_context
# https://www.google.com/search?q=%E7%BE%8E%E5%A5%B3
# https://www.google.com/search?q=美女

url = 'https://www.google.com/search?q='

# 將美女兩個字變成unicode編碼形式
# 我們需要依賴於urllib.parse
name = urllib.parse.quote('美女')
url += name

headers = {
    'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'
}
# 請求對象的制定
request = urllib.request.Request(url=url, headers=headers)

# 摸擬瀏覽器向服務器發送請求
response = urllib.request.urlopen(request)

# 獲取響應內容
content = response.read().decode('utf8')

# 打印數據
print(content)