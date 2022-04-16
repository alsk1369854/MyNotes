from bs4 import BeautifulSoup

# 透過解析本地文件 來將bs4的基礎語法進行講解
# 默認打開的文件的編碼格式是gbk 所以在打開文件的時候需要指定編碼
soup = BeautifulSoup(open('75_解析_bs4的基本使用.html', encoding='utf-8'), 'lxml')

# # 根據標籤名查找節點
# # 找到第一個符合條件的數據
# print(soup.a)
# # 獲取標籤的屬性
# print(soup.a.attrs)

# bs4的一些函數
# (1)find
# 返回第一個符合條件的數據
# print(soup.find('a'))

# 根據title的值來找對應的標籤對象
# print(soup.find('a',title='a2'))

# 根據class的值來找對應的標籤對象  ***注意class需要添加下滑線
# print(soup.find('a', class_='c1'))

# (2)find_all 返回一個陣列,包含所有的a標籤
# print(soup.find_all('a'))

# 如果想獲取多個標籤數據, 那麼需要在find_all的參數中添加的是陣列的數據
# print(soup.find_all(['a','span']))

# limit查找前幾個數據
# print(soup.find_all('li', limit=2))

# (3)select(推薦) 返回一個陣列,包含所有的a標籤
# print(soup.select('a'))

# 可以透過 "." 來代表class, 這操作我們叫做為類選擇器
# print(soup.select('.c1'))

# print(soup.select('#l1'))

# 屬性選擇器 => 透過屬性來尋找對應的標籤
# 查找到li標籤中有id的標籤
# print(soup.select('li[id]'))

# 查找到li標籤中id為l2的標籤
# print(soup.select('li[id="l2"]'))

# 層級選擇器
#  後代選擇器
# 找到div下面的li
# print(soup.select("div li"))

# 子代選擇器
#  某標籤的第一級子標籤
# 注意: 很多計算機語言中, 如果沒加空格, 可能會沒有輸出, 但bs4中,不會報錯
# print(soup.select('div > ul > li'))

# print(soup.select('a,li'))

# # 節點訊息
# #  獲取節點內容
# obj = soup.select('#d1')[0]
# # 如果標籤對象中, 只有內容, 那麼string和get_text()都可以使用
# print(obj.string)
# # 如果標籤中還有標籤, 那麼string獲取不到數據, 而get_text()是可以獲取數據的
# print(obj.get_text())

# 節點的屬性
# obj = soup.select('#p1')[0]
# name是標籤的名字
# print(obj.name)
# 將屬性作為一個字典返回
# print(obj.attrs)

# 獲取節點屬性
obj = soup.select('#p1')[0]
print(obj.attrs.get('class')) # 推薦
print(obj.get('class'))
print(obj['class'])