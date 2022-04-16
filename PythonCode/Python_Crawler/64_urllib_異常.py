
import urllib.request
import urllib.error

# 502 -> 5051 請求位址錯 HTTPError
# url = 'https://developer.mozilla.org/zh-CN/docs/Glossary/5021'

# 主機錯誤 -> URLError
url = 'http://www.dog1112222.com'

headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'
    }

try:
    request = urllib.request.Request(url=url, headers=headers)

    response = urllib.request.urlopen(request)

    content = response.read().decode('utf-8')

    print(content)
except urllib.error.HTTPError:
    print('502 -> 5051 請求位址錯 HTTPError')
except urllib.error.URLError:
    print('主機錯誤 -> URLError')