# Java Optional

```
    1. Optional 使用: 為防止 NullPointException 發生的類
        創建 Optional 對象
            Optional.of(T t): 此時t一定不能為null
            Optional.ofNullable(T t): 此時t可以為null
        判斷 Optional 中是否包含對象
            boolean isPresent(): 檢查是否為空
        獲取 Optional 中的對象
            <T> orElse(T t): 返回Optional中對象，如果Optional中對象為空則返回傳入項
            <T> get(): 返回Optional中對象，如果為空則 throws Exception
```
<br/>

## Demo class Boy, class Girl
```java
class Boy {
    Girl girl;
    public Boy() {}
    public Boy(Girl girl) {
        this.girl = girl;
    }
    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}

class Girl {
    String name;
    Girl(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}
```
<br/>

## 1. Optional 使用: 為防止 NullPointException 發生的類
```java
    @Test
    public void test1(){
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("Hum"));

        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    public String getGirlName(Boy boy){
        // 創建 Optional<Boy>
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        // 此時boy一定非null
        boy = optionalBoy.orElse(new Boy(new Girl("Chan")));

        Girl girl = boy.girl;
        // 創建 Optional<Girl>
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        // 此時girl一定非null
        girl = optionalGirl.orElse(new Girl("Lin"));

        return girl.name;
    }
```
<br/>

<br/>

#### _END_

