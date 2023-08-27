

# Formatted String Literals(Python3.6)
# 这种格式化字符串的方法是由Python3.6新加入的. 它允许你在字符串内内嵌Python运算式:
# 它也同时支持str.format()方法中的那些格式化符,且使用方法也一样:

def test_1():
    a = 11
    b = 2
    result = f'{a} + {b} = {a + b};{a} - {b} = {a - b}'
    print(result)
# test_1()


def test_2():
    num = 222
    result = f'{num} 的16进制表示是 {num:#x}'
    print(result)
# test_2()


# 模板字符串
# Python的标准库中提供了一个 Template 类,可以进行简单的内容替换.
# 注意,模板字符串并别有格式化符这种东西,因此要让age以浮点型形式显示需要用float函数先转成浮点型.
# 使用模板字符串的优点在于比较安全, 比如上面那个format方法的隐患, 改成用模板字符,则会报错

from string import Template
def test_3():
    name = "earth"
    age = 4600000000
    templ = Template("$name is $age old")
    result = templ.substitute(name=name,age=float(age))
    print(result)
# test_3()