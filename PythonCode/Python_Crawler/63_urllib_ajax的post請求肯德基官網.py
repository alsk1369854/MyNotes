# http://www.kfc.com.cn/kfccda/ashx/GetStoreList.ashx?op=cname
# post
# data: cname: 北京
# pid:
# pageIndex: 1
# pageSize: 10

# http://www.kfc.com.cn/kfccda/ashx/GetStoreList.ashx?op=cname
# post
# data: cname: 北京
# pid:
# pageIndex: 2
# pageSize: 10

# http://www.kfc.com.cn/kfccda/ashx/GetStoreList.ashx?op=cname
# post
# data: cname: 北京
# pid:
# pageIndex: 3
# pageSize: 10

import urllib.request
import urllib.parse

def create_request(page):
    url = 'http://www.kfc.com.cn/kfccda/ashx/GetStoreList.ashx?op=cname'
    data = {
        'cname': '北京',
        'pid': '',
        'pageIndex': page,
        'pageSize': '10',
    }
    data = urllib.parse.urlencode(data).encode('utf-8')
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36'
    }
    request = urllib.request.Request(url=url, data=data, headers=headers)
    return request

def get_content(request):
    response = urllib.request.urlopen(request)
    content = response.read().decode('utf-8')
    return content

def down_load(page, content):
    with open('kfc_' + str(page) + '.json', 'w', encoding='utf-8') as fp:
        fp.write(content)

if __name__ == '__main__':
    start_page = int(input('請輸入起始頁: '))
    end_page = int(input('請輸入結束頁: '))

    for page in range (start_page, end_page+1):
        # 建立請求對象
        request = create_request(page)
        # 獲取網頁源碼
        content = get_content(request)
        # 下載數據
        down_load(page, content)