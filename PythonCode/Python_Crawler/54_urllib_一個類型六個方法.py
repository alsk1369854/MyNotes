import urllib.request

import ssl
ssl._create_default_https_context = ssl._create_unverified_context



# url = 'https://www.baidu.com/'
url = 'https://sites.google.com/view/chiamingliang/about-ming'
# 摸擬瀏覽器向服務器發送請求
response = urllib.request.urlopen(url)

# 一個類型六個方法

# response是一個HTTPResponse的類型
# print(type(response))

# 按照一個字節一個字節的去讀
# content = response.read()
# print(content)

# 返回多少個字節
# content = response.read(5)
# print(content)

# 讀取一行
# content = response.readline()
# print(content)

# 讀完所有行
# content = response.readlines()
# print(content)

# 返回狀態 如果是200 那麼表示我們邏輯正確
# print(response.getcode())

# 返回url地址
# print(response.geturl())

# 獲取是一個狀態訊息
print(response.getheaders())


# 一個類型 HTTPResponse
# 六個方法 read  readline  readlines  getcode  geturl  getheaders
