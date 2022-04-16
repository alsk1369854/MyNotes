import urllib.request
import json
import jsonpath

# get請求淘票票網站地區列表
url = 'https://dianying.taobao.com/cityAction.json?activityId&_ksTS=1643705367602_108&jsoncallback=jsonp109&action=cityAction&n_s=new&event_submit_doGetAllRegion=true'
headers = {
    # ':authority': 'dianying.taobao.com',
    # ':method': 'GET',
    # ':path': '/cityAction.json?activityId&_ksTS=1643705367602_108&jsoncallback=jsonp109&action=cityAction&n_s=new&event_submit_doGetAllRegion=true',
    # ':scheme': 'https',
    'accept': 'text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01',
    # 'accept-encoding': 'gzip, deflate, br',
    'accept-language': 'zh-TW,zh;q=0.9,en-AU;q=0.8,en;q=0.7,en-US;q=0.6',
    'cookie': 't=ecd2fded2c679b56748782590aee8a71; cookie2=1e435f9375d76a5051bc06fa1defbfa4; v=0; _tb_token_=efeed3dbe657; cna=NSd0Gn/tRWoCAdyOHJxdEDmF; xlly_s=1; tb_city=110100; tb_cityName="sbG+qQ=="; tfstk=ccQNBbGn3PUwfG8b2FT4c4m6cPKOZeQcPldvjGghqSNtep-GidHvtwfRBtlEnhf..; l=eBxEgKxggvPV1SRWBO5Churza77TNIRb4cFzaNbMiInca69htFM_cNCpWXBwSdtj_tCvjetPp0kihRLHR3AgCc0c07kqm05n3xvO.; isg=BE1Nmk95ENSPGbT4DPhM2i58XGnHKoH86uQdLo_SIORThm04V3pJzNwY8BLgFZm0',
    'referer': 'https://dianying.taobao.com/?spm=a1z21.3046609.city.1.1b6a112aF4gvmY&city=110100',
    'sec-ch-ua': '" Not;A Brand";v="99", "Google Chrome";v="97", "Chromium";v="97"',
    'sec-ch-ua-mobile': '?0',
    'sec-ch-ua-platform': '"Windows"',
    'sec-fetch-dest': 'empty',
    'sec-fetch-mode': 'cors',
    'sec-fetch-site': 'same-origin',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36',
    'x-requested-with': 'XMLHttpRequest',
}
request = urllib.request.Request(url=url, headers=headers)
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')

# 將響應裁切成基本json格式
content = content.split('(')[1].split(')')[0]

# 保存響應至本地
with open('./74_解析_jsonpath解析淘票票網站.json', 'w', encoding='utf-8') as fp:
    fp.write(content)

# 打開json解析必要數據
obj = json.load(open('./74_解析_jsonpath解析淘票票網站.json', 'r', encoding='utf-8'))
city_list = jsonpath.jsonpath(obj, '$..regionName')

# 打印數據
print(city_list)

