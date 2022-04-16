from lxml import etree

# 至google https://chrome.google.com/webstore/category/extensions
# 安裝 "XPath Helper" 工具
# 重開瀏覽器 開/關工具 "ctrl + shift + x"

# xpath解析
# (1)本地文件                                            etree.parse
# (2)服務器響應數據 response.read().decode('utf-8') ****  etree.HTML()

# xpath解析本地文件
tree = etree.parse('70_解析_xpath基本使用.html')

### tree.xpath('xpath路徑')
# 路徑
# //: 查找所有子孫節點, 不考慮層級關係
# / : 找子節點

# 查找ul下面的li
# text()獲取標籤內容
# li_list = tree.xpath('//body/ul/li/text()')

# 查找所有有id的屬性的li標籤
# li_list = tree.xpath('//ul/li[@id]/text()')

# 查找id為l1的li標籤 **注意引號問題
# li_list = tree.xpath('//ul/li[@id="l1"]/text()')

# 查找id為l1的li標籤的class的屬性值
# li_list = tree.xpath('//ul/li[@id="l1"]/@class')

### 以下為較少用查詢方法
# 查詢id中包含l的li標籤
# li_list = tree.xpath('//ul/li[contains(@id, "l")]/text()')

# 查詢id的值以c開頭的li標籤
# li_list = tree.xpath('//ul/li[starts-with(@id, "c")]/text()')

# 查詢id為l1和class為c1的li標籤
# li_list = tree.xpath('//ul/li[@id="l1" and @class="c1"]/text()')

# 查詢id為l1或c3的li標籤 **注意xpath是以標籤為單位
li_list = tree.xpath('//ul/li[@id="l1"]/text() | //ul/li[@id="c3"]/text()')

# 打印數據
print(li_list)
print(len(li_list))