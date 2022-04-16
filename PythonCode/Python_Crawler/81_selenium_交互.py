from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By

ser = Service('C:\py_wk\python_爬蟲\chromedriver.exe')
options = Options()
browser = webdriver.Chrome(service=ser, options=options)

url = 'https://www.baidu.com/'
browser.get(url)

import time

# 輸入搜索內容
time.sleep(5)
search_input = browser.find_element(By.XPATH, '//input[@id="kw"]')
search_input.send_keys('周杰倫')

# 按下搜索按鈕
time.sleep(5)
search_button = browser.find_element(By.XPATH, '//input[@id="su"]')
search_button.click()

# 下滑至頁尾
time.sleep(5)
js_page_bottom = 'document.documentElement.scrollTop=100000'
to_page_bottom = browser.execute_script(js_page_bottom)

# 選擇下一頁
time.sleep(5)
next_page_button = browser.find_element(By.XPATH, '//a[@class="n"]')
next_page_button.click()

# 返回上一步
time.sleep(5)
browser.back()

# 回前一步
time.sleep(5)
browser.forward()

# 退出
time.sleep(5)
browser.quit()