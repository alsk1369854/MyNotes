# (1) 安裝selenium工具
# 安裝chromedriver
#  http://chromedriver.storage.googleapis.com/index.html
# 如何查詢
#  chrome右上角 --> 說明 --> 關於
# 安裝selenium工具包
#  pip install selenium

# (2) 導入selenium
from selenium import webdriver

# (3) 創建瀏覽器操作對象
path = 'chromedriver.exe'
browser = webdriver.Chrome(path)

# (4) 訪問網站
url = 'https://tw.jd.com/'
browser.get(url)

# (5)獲取網頁源碼
content = browser.page_source
print(content)