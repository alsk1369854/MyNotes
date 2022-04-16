# 使用urllib來獲取Google首頁源碼
import urllib.request

import ssl
# ssl._create_default_https_context = ssl._create_unverified_context

# (1)定義一個url 就是你要訪問的地址
# url = 'https://www.google.com.tw/'
# url = 'https://www.baidu.com/'
url = 'http://sites.google.com/view/chiamingliang/about-ming'

# (2)摸擬瀏覽器向服務器發送請求
response = urllib.request.urlopen(url)

# (3)獲取響應中的頁面的源碼 content 是內容的意思
# read方法 返回的是字節形式的二進制數據
# 我要將二進制的數據轉換為字符串
# 二進制-->字符串 解碼 decode('編碼的格式')
content = response.read().decode('utf-8')

# (4)打印數據
print(response.status)
print(response)
# print(content)