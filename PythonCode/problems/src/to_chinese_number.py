import re


def to_chinese_number(num: int) -> str:
    print(num)
    # 1234 1234
    sub_cases: List[int] = []
    while num > 0 :
        sub_cases.append(num % 10000)
        num //= 10000

    words: List[str] = ['零','壹','貳','參','肆','伍','陸','柒','捌','玖']
    units: List[str] = ['','拾','佰','仟']
    big_units: List[str] = ['','萬','億']
    def handleZero(content: str) -> str:
        content = re.sub(r'零+', '零', content)
        content = re.sub(r'零$', '', content)
        return content

    def transform(num: int) -> str:
        sb: List[str] = []
        i: int = 0
        while num > 0:
            digital: int = num % 10
            c: str = words[digital]
            u: str = units[i]
            if(digital != 0):
                sb.append(u)
            sb.append(c)
            num //= 10
            i += 1    
        sb.reverse()
        ans: str = "".join(sb)
        ans = handleZero(ans)
        return ans
    
    sb: List[str] = []
    for i, sub_case in enumerate( sub_cases):
        u: str = big_units[i]
        c: str = transform(sub_case)
        if c == '':
            u = ''
            c = '零'
        sb.append(u)
        sb.append(c)

    sb.reverse()
    ans: str = "".join(sb)
    ans = handleZero(ans)
    print(ans)
    return ans