import urllib.request


# url = 'https://www.baidu.com/s?wd=ip?'
url = 'https://myip.com.tw/'

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
}

request = urllib.request.Request(url=url, headers=headers)

proxies = {
    'http' : 'http://112.25.41.136:80'
}

handler = urllib.request.ProxyHandler(proxies= proxies)
# handler = urllib.request.HTTPHandler()

opener = urllib.request.build_opener(handler)

response = opener.open(request)
# response = urllib.request.urlopen(request)

content = response.read().decode('utf-8')

with open('ip.html', 'w', encoding='utf-8') as fp:
    fp.write(content)