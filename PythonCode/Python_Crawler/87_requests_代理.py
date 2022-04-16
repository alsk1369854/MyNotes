import requests

url = 'http://www.ez2o.com/App/Net/IP'

proxy = {
    'http': '112.105.59.206:80'
}


request = requests.get(url=url, proxies=proxy)

print(request)

with open('ip.html', 'w', encoding='utf-8') as f:
    f.write(request.text)
