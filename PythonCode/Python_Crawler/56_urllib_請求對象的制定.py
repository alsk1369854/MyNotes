import urllib.request

# url = 'http://www.baidu.com'

import ssl
ssl._create_default_https_context = ssl._create_unverified_context

url = 'http://sites.google.com/view/chiamingliang/about-ming'

# url的組成
# https://www.google.com/search?q=美女


# http/https    www.google.com    80/443      s      q=美女      #
#   協議          主機              端口號      路徑     參數      錨點
# http      80
# https     443
# mysql     3306
# oracle    1521

headers = {
    'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'
}

# 因為urlopen方法中不能儲存字典 所以headers不能傳遞進去
# 請求對象的制定
# 注意 因為參數順序的問題  不能直接寫url 和headers 中間還有data 所以我們需關鍵字傳參
request = urllib.request.Request(url=url, headers=headers)

response = urllib.request.urlopen(request)

content = response.read().decode('utf8')

print(content)