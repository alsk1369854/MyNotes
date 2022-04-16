import urllib.request
from lxml import etree

# https://sc.chinaz.com/tupian/gudianmeinvtupian.html
# https://sc.chinaz.com/tupian/gudianmeinvtupian_2.html

def create_request(page):
    if (page == 1):
        url = 'https://sc.chinaz.com/tupian/gudianmeinvtupian.html'
    else:
        url = 'https://sc.chinaz.com/tupian/gudianmeinvtupian_' + str(page) + '.html'
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36"
    }
    return urllib.request.Request(url=url, headers=headers)

def get_content(request):
    response = urllib.request.urlopen(request)
    return response.read().decode('utf-8')

def down_load(content):
    html_tree = etree.HTML(content)
    name_list = html_tree.xpath('//div[@id="container"]//img/@alt')
    src_list = html_tree.xpath('//div[@id="container"]//img/@src2')

    for i in range(len(name_list)):
        urllib.request.urlretrieve('https:' + src_list[i], './classic_beauty_imgs/' + name_list[i] + '.jpg')

if __name__ == '__main__':
    start_page = int(input('起始頁: '))
    end_page = int(input('終止頁: '))

    for page in range(start_page, end_page+1):
        request = create_request(page)
        content = get_content(request)
        down_load(content)
