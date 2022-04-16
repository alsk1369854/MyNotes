import urllib.request

url = 'https://www.starbucks.com.tw/products/drinks/view.jspx?catId=1'

request = urllib.request.Request(url = url)

response = urllib.request.urlopen(request)

content = response.read().decode('utf-8')

# //div[@class="content"]//h1[@class="title_cn"]
from bs4 import BeautifulSoup

soup = BeautifulSoup(content, 'lxml')

name_list = soup.select('div[class="content"] h1[class="title_cn"]')

for name in name_list:
    print(name.get_text())
