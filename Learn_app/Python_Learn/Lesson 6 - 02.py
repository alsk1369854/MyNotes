def main():
    cards = []
    ans = 0
    for i in range(5):
        cards.append(input())
    for i in range(len(cards)):
        if cards[i] == 'J':
            ans += 11
        elif cards[i] == 'Q':
            ans += 12
        elif cards[i] == 'K':
            ans += 13
        elif cards[i] == 'A':
            ans += 1
        else:
            ans += int(cards[i])
    print(ans)


main()
