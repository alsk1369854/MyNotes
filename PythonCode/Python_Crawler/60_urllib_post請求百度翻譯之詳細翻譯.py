import urllib.request
import urllib.parse
import json

base_url = 'https://fanyi.baidu.com/v2transapi?from=en&to=zh'

data = {
    'from': 'en',
    'to': 'zh',
    'query': 'translate',
    'transtype': 'translang',
    'simple_means_flag': '3',
    'sign': '501406.214447',
    'token': 'c241af3b7734e6eaad7da48220bf9b3e',
    'domain': 'common',
}
data = urllib.parse.urlencode(data)

# 取決定因素headers
headers = {
    # 'Accept': '*/*',
    # 'Accept-Encoding': 'gzip, deflate, br', # 接收的編碼方式沒有utf-8, 一定要註解掉
    # 'Accept-Language': 'zh-TW,zh;q=0.9,en-AU;q=0.8,en;q=0.7,en-US;q=0.6',
    # 'Connection': 'keep-alive',
    # 'Content-Length': '141',
    # 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    'Cookie': 'BIDUPSID=87320B425D10D873CBDE89DC40A196DE; PSTM=1643265389; BAIDUID=87320B425D10D8736AD0396A939EE7B2:FG=1; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; FANYI_WORD_SWITCH=1; HISTORY_SWITCH=1; REALTIME_TRANS_SWITCH=1; SOUND_PREFER_SWITCH=1; SOUND_SPD_SWITCH=1; APPGUIDE_10_0_2=1; H_PS_PSSID=35104_31253_35489_35774_34584_35490_35541_35796_35316_26350_35746; delPer=0; PSINO=7; BA_HECTOR=8g21818h218h2120bh1gv9fcj0r; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1643384674,1643386928,1643387063,1643429269; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1643429269; ab_sr=1.0.1_MWNiM2EyMTNkNjljODAzMGNhOGM1ZDU4NDFmNTcwMTkzZDhmZmRkNDNlYWIwNjlmYTJhY2M1OGVkMWIwZTk3MzdmNzRlYmQzM2JmOWE3ZjA0MDIwNjU1NDVjNjc2OGU2',
    # 'Host': 'fanyi.baidu.com',
    # 'Origin': 'https://fanyi.baidu.com',
    # 'Referer': 'https://fanyi.baidu.com/?aldtype=85',
    # 'sec-ch-ua': '" Not;A Brand";v="99", "Google Chrome";v="97", "Chromium";v="97"',
    # 'sec-ch-ua-mobile': '?0',
    # 'sec-ch-ua-platform': '"Windows"',
    # 'Sec-Fetch-Dest': 'empty',
    # 'Sec-Fetch-Mode': 'cors',
    # 'Sec-Fetch-Site': 'same-origin',
    # 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36',
    # 'X-Requested-With': 'XMLHttpRequest',
}

# post請求的參數 是不會拼接在url後面的 而需要方在請求對象制定的data中
# post請求參數必須進行編碼
data = data.encode('utf-8')
request = urllib.request.Request(url=base_url, data=data, headers=headers)

# 摸擬瀏覽器向服務器發出請求
response = urllib.request.urlopen(request)

# 獲取響應的數據
content = response.read().decode('utf-8')

# 字串 --> json對象
content = json.loads(content)

print(content)
