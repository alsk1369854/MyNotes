import urllib.request

# 創建請求對象
url = 'https://www.baidu.com/'
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
}
request = urllib.request.Request(url=url, headers=headers)

# 摸擬瀏覽器向服務器發請求
response = urllib.request.urlopen(request)

# 獲取網頁源碼
content = response.read().decode('utf-8')

# 解析網頁源碼工具
from lxml import etree

# 解析服務器響應的文件
tree = etree.HTML(content)

# 獲取想要的數據
result = tree.xpath('//input[@id="su"]/@value')[0]

# 打應結果
print(result)