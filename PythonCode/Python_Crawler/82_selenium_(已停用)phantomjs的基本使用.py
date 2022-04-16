from selenium import webdriver
from selenium.webdriver.common.by import By

path = 'phantomjs.exe'
browser = webdriver.PhantomJS(path)

url = 'https://www.baidu.com/'
browser.get(url)

browser.save_screenshot('baidu.png')





