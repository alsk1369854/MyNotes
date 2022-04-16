import urllib.request
import random


url = 'https://www.baidu.com/s?wd=ip?'
# url = 'https://myip.com.tw/'

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
}

request = urllib.request.Request(url=url, headers=headers)

proxies_pool = [
    {'http': 'http://203.75.190.21:80'},
    {'http': 'http://123.205.32.127:80'},
]
proxies = random.choice(proxies_pool)
print(proxies)

handler = urllib.request.ProxyHandler(proxies = proxies)

opener = urllib.request.build_opener(handler, urllib.request.HTTPHandler)

response = opener.open(request)

content = response.read().decode('utf-8')

with open('proxies_pool.html', 'w', encoding='utf-8') as fp:
    fp.write(content)