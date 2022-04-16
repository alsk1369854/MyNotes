# Java Thread 常用方法

```
1. start()
    啟動當前執行緒; 調用當前執行緒的run()

2. run()
    通常需要重寫Thread類中的此方法，將創建的執行緒要執行的操作聲明在此方法中

3. currentThread()
    靜態方法
    返回執行當前代碼的執行緒

4. getName()
    獲取當前執行緒的名字

5. setName()
    設置當前執行緒的名字

6. yieid()
    釋放當前CPU的執行權

7. join()
    在執行緒a中調用b的join()，此時a執行緒就會進入阻塞狀態，直到執行緒b完全執行完以後，執行緒a才會結束阻塞狀態。

8. stop() 已被標明過時
    當執行此方法時，強制結束當前執行緒

9. sleep(Long millitime)
    讓當前執行緒"睡眠"指定 millitime 毫秒，在指定的 millitime 毫秒時間內，當前執行緒是阻塞狀態

1. isAlive()
    判斷當前執行緒是否存活
```

# 執行緒的優先級
```
1.  MAX_PRIORITY: 10
    MIN_PRIORITY: 1
    NORM_PRIO0RITY: 5 --> 默認優先級

2. 如何獲取和設定當前執行緒的優先級:
    getPriority(): 獲取執行緒的優先級
    setPriority(int p): 設定執行緒的的先級

    說明: 高優先級的執行緒要搶佔低優先級執行緒CPU的執行權。但是只是從機率上來說，高優先級的執行緒高機率的情況下被執行。並不意味者只有當高優先級的執行緒執行完了以後，低優先權的執行緒才執行。
```

# 創建 執行緒的兩種方式
### 方式一: 繼承 Thread 類
    1. 創建一個繼承 Thread 類的子類
    2. 重寫Thread類的run() --> 將此執行緒的操作聲明在run()中
    3. 創建Thread類的子類的對象
    4. 通過此對象調用start()

#### 範例:
```java
class MyThread extends Thread{
    @Override
    public void run(){
        ....
    }
}

class Main {
    public static void main(String[] args){
        Thread t1 = new MyThread()
        t1.start()
    }
} 
```
<br/>

### 放式二: 實現 Runnable interface
    1. 創建一個實現了Runnable接口的類
    2. 實現類去實現Runnable中的抽象方法: run()
    3. 創建實現Runnable類的對象
    4. 將此對象作為參數傳遞到Thread類的構造器中，創建Thread類的對象
    5. 透過Thread類的對象調用start()

#### 範例:
```java
class MyThread implements Runnable{
    @Override
    public void run(){
        ....
    }
}

class Main {
    public static void main(String[] args){
        MyThread myT = new MyThread();
        Thread t1 = new Thread(myT);
        t1.start()
    }
}
```

## 比較創建執行緒的兩種方式
    開發中: 優選擇: 實現 Runnable 接口的方式
    原因:   1. 實現的方式沒有類的單一繼承性的侷限性
            2. 實現的方式更適合來處理多個執行緒共享數據的情況

    聯繫: public class Thread implements Runnable
    相同點: 兩種方式都需要重寫run(), 將執行緒要執行的邏輯聲明在run()中

# 直執行緒安全問題
### 在Java中，我們透過同步機制，來解決執行緒安全問題
## 方式一: 同步代碼塊
    synchronized(同步監視器){
        // 需要被同步的代碼
    }
    說明:   1. 操作共享數據的代碼，即為需要被同步的代碼
           2. 共享數據: 多個執行緒共同操作的變量，比如: ticket是共享數據
           3. 同步監視器，俗稱: 鎖。任何一個類的對象，都可以充當鎖。
                要求: 多個線程必須要共用同一把鎖。
### 範例
```java
class MyThread implements Runnable{
    private int ticket = 100;
    Object obj = new Object();
    run(){
        while(true){
            // 需要同步的代碼區域
            synchronized(obj){
                if(ticket > 0){
                    System.out.println('我的票號' + ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}
``` 