from selenium import webdriver

from selenium.webdriver.chrome.service import Service
ser = Service('C:\py_wk\python_爬蟲\chromedriver.exe')

url = 'https://www.baidu.com/'
# url = 'https://www.google.com/'

from selenium.webdriver.chrome.options import Options as ChromeOptions
options = ChromeOptions()

browser = webdriver.Chrome(service=ser, options=options)
browser.get(url)

# 元素定位

# # (推薦)
from selenium.webdriver.common.by import By
單筆資料
button = browser.find_element(By.XPATH, '//input[@id="su"]')
print(button)
多筆資料
button = browser.find_elements(By.XPATH, '//body//a[@class="mnav c-font-normal c-color-t"]')
print(button)

# # 根據 id 找對象
# button = browser.find_element_by_id('su')
# print(button)

# 使用 bs4 語法找對象
# button = browser.find_element_by_css_selector('#su')
# print(button)



