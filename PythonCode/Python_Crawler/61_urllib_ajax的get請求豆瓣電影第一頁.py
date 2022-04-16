import urllib.request


url = 'https://movie.douban.com/j/chart/top_list?type=5&interval_id=100%3A90&action=&start=0&limit=20'
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'
}

# 請求對象制定
request = urllib.request.Request(url=url, headers=headers)

# 獲取響應數據
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')

# (3) 數據下載到本地
# open方法默認清況下使用的是gdk的編碼 如果我們想要保存漢字 那麼需要再open方法中指定編碼格式為utf-8
# encodding = 'utf-8'
# fp = open('douban.json', 'w', encoding='utf-8')
# fp.write(content)

with open('douban1.json', 'w', encoding='utf-8') as fp:
    fp.write(content)

