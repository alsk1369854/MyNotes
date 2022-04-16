from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By

ser = Service('C:\py_wk\python_爬蟲\chromedriver.exe')
options = Options()
browser = webdriver.Chrome(service=ser, options=options)

url = 'https://www.baidu.com/'
browser.get(url)


# 獲取 class 訊息
input = browser.find_element(By.XPATH, '//input[@id="su"]')
print(input.get_attribute('class'))

# 獲取 tag name
print(input.tag_name)

# 獲取 內容文本
link = browser.find_element(By.XPATH, '//a[text()="新闻"]')
print(link.text)



