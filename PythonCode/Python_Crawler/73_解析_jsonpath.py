import json
import jsonpath

obj = json.load(open('./73_解析_jsonpath.json', 'r', encoding='utf-8'))

# 書店所有書的作者
# author_list = jsonpath.jsonpath(obj, '$.store.book[*].author')
# print(author_list)

# 有所作者 ..相當於XPath的//
# author_list = jsonpath.jsonpath(obj, '$..author')
# print(author_list)

# store下面的所有元素
# tag_list = jsonpath.jsonpath(obj, '$.store.*')
# print(tag_list)

# store裡面所有東西的price
# price_list = jsonpath.jsonpath(obj, '$.store..price')
# print(price_list)

# 第三本書
# book = jsonpath.jsonpath(obj, '$..book[2]')
# print(book)

# 最後一本書
# book = jsonpath.jsonpath(obj, '$..book[(@.length-1)]')
# print(book)

# 前面兩本書
# book_list = jsonpath.jsonpath(obj, '$..book[0,1]')
# book_list = jsonpath.jsonpath(obj, '$..book[:2]')
# print(book_list)

# 條件過濾要在()前面加一個?
# 過濾出所有包含isbn的書
# book_list = jsonpath.jsonpath(obj, '$..book[?(@.isbn)]')
# print(book_list)

# 哪本書超過了10塊錢
book_list = jsonpath.jsonpath(obj, '$..book[?(@.price>10)]')
print(book_list)