# 　透過登入進入到主頁面

# __EVENTTARGET:
# __EVENTARGUMENT:
# __VIEWSTATE: wJLtHfOie1dleDXMFjxiCuxmqfb02NbIWXca0pFWXIWevpWeYGKdXFKu8XgnD4paAahx1t/PhHS+x+YJB/UNo9C8Hvl3i+DSp8K2eQKAEC1mO0/kNoyvY1kvAa9AeGYJ4ktye6sdaHAk3IyfjUfwr8A7Hn2+aI/JNyQmh5yEXjcKgqhLnhwQs5SEqKnL3LQpPi+0kCDHuzqlUt3fLpXWeSaeMO0X3ErdH2YNS9rZ0XQwFaEobZS/mz6r59fgxUmLLh4WEDH2wOiEb5X8SOIHs4jT88dIiahQt72XKk4vGyNvCneG2k2PGxkZq8g+ck+aTmh6f7LfCsUgwVkIPIXd+w+2Wc3dbAaTtFgpINvuXtus2ECL3PpbDm2VL/qQ0Ty3+vlrGREvjCA0hnLZWPkw3fPmuHhJh9jEBR5Zbly3F025XGIAWiXELAEHKJyfBBMuJAEpxcUpLeYMJOisEhWUvIQ2nZaFvh6rRdQBPNdzDALogvG5LqDRpCrYGK60T+Tp0YgmfwoclxOyXiiDkHuIeKKE1EhrQOmBhPRIz/Zp11itm4v3ZNNRqZpbGqErtksSYNLOHFeRek8kg/whNmGtuxWb1uRSagTMJzZREZVYBiBeVaw5RIoJ51KFi9BTbuurPiIuR0Ldh/sk7//HAGVFm9Douq9gAMalT6afDdI+Z04J35zbNz35NsHRG2S7jtrcAXYrtUGQjkntqqhSP+gARVpTepwIQHX5Kl0odYziJt1jyE1tclMPN2W7nLXeYC2QPxJkrt3vF9poVk4xC0m7d7mVwmiDA+UOrEeL+khyTV2FiwHuZaZxAPj+MWJmoENP6UdUgEzOsD9a/xx1r2uEYE2TyKTLCP48vmmMwDqbTSm49ytASO2uPSGNfEgc+6vR7IWNv9g/npxiaS8tdi5YX4HHjHOh6+bPrZxETmr+n/dgLJkuCcwKjsIKXKSwhn7JhbIUBtXlyte4dODzvnER3NnPuhNAWbAd1MohcX3R7uJdxRMvMivWXALvjDSax32yjfxLm4YPYYeAK2ogY3/nhwn2P7Gfp3/e/QstDrtNjAZGWk3HVB8UwW5jIyQQWcmCfuRiTw==
# __VIEWSTATEGENERATOR: D84C7206
# __VIEWSTATEENCRYPTED:
# uLoginID: 410877033
# uPassword: z1369854qpxm
# uAuthorizationCodeInput: aaa
# uLogin: 登入
# Hidden1:
# uGoogleToken:
# uLoginID2:


# 我們觀察到 __VIEWSTATE __VIEWSTATEGENERATOR 是一個可變化的量

# 難點:
#     1. __VIEWSTATE  __VIEWSTATEGENERATOR 一般請況下看不到數據, 都是在頁面的源碼中
#             我們觀察到這兩個數據再頁面的源碼中 所以我們需要獲取頁面的源碼 然後進行解析就可獲取到了
#     2. 驗證碼


import requests

login_url = 'https://sso.nknu.edu.tw/userLogin/login.aspx?cUrl=/default.aspx'

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.60 Safari/537.36'
}
session = requests.session()
# 獲取源碼數據
request = session.get(url=login_url, headers=headers)

login_page_content = request.text

# 獲取 __VIEWSTATE  __VIEWSTATEGENERATOR 數據
from bs4 import BeautifulSoup

soup = BeautifulSoup(login_page_content, 'lxml')

# 獲取__VIEWSTATE
viewstate = soup.select('#__VIEWSTATE')[0].attrs.get('value')

# 獲取 __VIEWSTATEGENERATOR
viewstategenerator = soup.select('#__VIEWSTATEGENERATOR')[0].attrs.get('value')

print('@__VIEWSTATE: ' + viewstate)
print('@__VIEWSTATEGENERATOR: ' + viewstategenerator)

# 獲取 驗證碼圖片
login_img_code = soup.find(attrs={"title": "*"}).get('src')
login_img_code_url = 'https://sso.nknu.edu.tw' + login_img_code
print('@login_img_code_url: ' + login_img_code_url)

# import urllib.request
# img = urllib.request.urlretrieve(img_code_url, 'img.jpg')


# requests 裡有一個方法 session() 透過 session 的返回職就能使請求變成一個物件

response_login_img_code = session.get(login_img_code_url, headers=headers)
# 注意此時要使用二進制數據， 因為我們要下載圖片
login_img_code_content = response_login_img_code.content
# print(request_img_code.text)
# 下載 驗證碼圖片
with open('login_img_code.jpg', 'wb')as fp:
    fp.write(login_img_code_content)





input_login_img_code = input('輸入驗證碼: ')

# 提交登陸請求地址
login_submit_url = 'https://sso.nknu.edu.tw/userLogin/login.aspx?cUrl=%2fdefault.aspx'

login_form_data = {
    '__EVENTTARGET': '',
    '__EVENTARGUMENT': '',
    '__VIEWSTATEENCRYPTED': '',

    # __VIEWSTATE
    '__VIEWSTATE': viewstate,

    # __VIEWSTATEGENERATOR
    '__VIEWSTATEGENERATOR': viewstategenerator,

    # 學號
    'uLoginID': '410877033',

    # 密碼
    'uPassword': 'z1369854qpxm',

    # 驗證碼
    'uAuthorizationCodeInput': input_login_img_code,

    'uLogin': '登入',
    'Hidden1': '',
    'uGoogleToken': '',
    'uLoginID2': '',
}


response_login_submit = session.post(login_submit_url, data=login_form_data, headers=headers)

content = response_login_submit.text
# print(content)
with open('student.html', 'w', encoding='utf-8')as fp:
    fp.write(content)