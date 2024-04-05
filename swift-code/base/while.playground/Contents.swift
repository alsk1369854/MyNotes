
// 一般的 while 使用
var count = 0
while (count < 5){
    print(count, terminator: ", ")
    count += 1
}
print()

// 無論如何都會先執行 1 次後再進行 while 判斷
repeat {
    print("repeat while")
} while (false)
