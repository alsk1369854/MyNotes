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
#### 使用推薦順序
    1. ReentrantLock (新方法，靈活性高)
    2. 同步代碼塊 synchronized(){}
    3. 同步方法 private synchronized void method(){}

## 方式一: Lock鎖 --- JDK5.0新增
### ReentrantLock 與 synchronized 差異處
    相同: 兩者都可以解決線程安全問提
    不同: 
       1. synchronized機制在執行完成相應的同步代碼以後，自動釋放同步監視器
       2. Lock需要手動的同步(lock())，同時結束同步也需要手動實現(unLock())
    
### 使用 Lock鎖 範例
```java
class MyThread implements Runnable{
    private int ticket = 100;
    // 實例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    public void run(){
        while(true){
            try{
                // 調用上鎖方法: lock()
                lock.lock()
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + " 我的票號: " + ticket);
                    ticket--;
                }
            }finally{
                // 調用解鎖方法: unlock()
                lock.unlock();
            }
        }
    }
}


```

## 方式二: 同步代碼塊 synchronized(){}
    synchronized(同步監視器){
        // 需要被同步的代碼
    }
    說明:   1. 操作共享數據的代碼，即為需要被同步的代碼。 -->不能包含代碼多了，也不能包含代碼少了
           2. 共享數據: 多個執行緒共同操作的變量，比如: ticket是共享數據
           3. 同步監視器，俗稱: 鎖。任何一個類的對象，都可以充當鎖。
                要求: 多個線程必須要共用同一把鎖。

            補充: 在實現Runnable接口創建多線程的方式中，我們可以考慮使用this充當同步監視器。
### 實現Runnable接口 範例 
```java
class MyThread implements Runnable{
    // 本身創建就是同一個類 所有都是共用，不須加 static
    private int ticket = 100;
    Object obj = new Object();
    public void run(){
        while(true){
            // 需要同步的代碼區域
            // synchronized(this){
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

<br/>

### 繼承Thread類 範例
```java
class MyThread extends Thread{
    // 加上 static 讓對象為同一對象
    private static Object obj = new Object();
    private static int ticket = 100;
    public void run(){
        while(true){
            // 需要同步的代碼區域
            // synchronized(MyThread.class){
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

<br/>

## 方式三: 同步方法 private synchronized void method(){}

### 實現Runnable接口 範例
```java
class MyThread implements Runnable{
    // 本身創建就是同一個類 所有都是共用，不須加 static
    private int ticket = 100;
    public void run(){
        while(true){
            // 調用同步方法
            show();
        }
    }

    // 創建同步方法 
    private synchronized void show(){// 此時的鎖是 this
        if(ticket > 0){
            System.out.println('我的票號' + ticket);
            ticket--;
        }
    }
}
```


<br/>

### 繼承Thread類 範例
```java
class MyThread extends Thread{
    // 加上 static 讓對象為同一對象
    private static Object obj = new Object();
    private static int ticket = 100;
    public void run(){
        while(true){
            // 調用同步方法
            show();
        }
    }

    // 創建同步方法 加上 static 
    private static synchronized void show(){// 此時的鎖是 MyThread.class
        if(ticket > 0){
            System.out.println(Thread.currentThread().getName() + '我的票號' + ticket);
            ticket--;
        }
    }
}

```

<br/>

# 線程通信 涉及wait(), notify(), notifyAll()
```
涉及到的三個方法:
    1. wait(): 一旦執行此方法，當前線程就會進入阻塞狀態，並 "釋放同步監視器"。
    2. notify(): 一旦執行此方法，就會喚醒被wait的一個線程。如果有多個線程被wait，就喚醒優先級高的線程。
    3. notifyAll(): 一旦執行此方法，就會喚醒所有被wait的線程

說明:
    1. wait(), notify(), notifyAll()三個方法必須使用在同步代碼塊或同步方法中。
    2. wait(), notify(), notifyAll()三個方法的調用者必須是同步代碼塊或同步方法中的同步監視器否則，會出現IllegalMonitorStateException異常
    3. wait(), notify(), notifyAll()三個方法是定義在java.loan.Object類中。

sleep() 和 wait() 的相異處?
    1. 相同點:
        一但執行方法，都可以使得當前線程進入阻塞狀態。
    2. 不同點:
        1) 兩個方法聲明的位置不同: 
            Thread類中聲明sleep()。
            Object類中聲明wait()。
        2) 調用的要求不同: 
            sleep()可以在任何需要的場景下調用()。
            wait()必須使用在同步代碼塊或同步方法中。
        3) 關於是否釋放同步監視器: 如果兩個方法都使用在同步代碼塊或同步方法中
            sleep()不會釋放同步鎖。
            wait()會釋放同步鎖。
```

### 交換累加至1000 範例
```java
class MyThread implements Runnable {
    private int number = 1;
    @Override
    public void run() {
        while (number <= 1000) {
            synchronized (this) {
                notify();
                System.out.println(Thread.currentThread().getName() + ": Number: " + number);
                number++;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        Thread t1 = new Thread(mt);
        t1.setName("Thread 1");
        Thread t2 = new Thread(mt);
        t2.setName("Thread 2");
        t1.start();
        t2.start();
    }
}
```



# 創建 執行緒的第三種方式 "Callable" -> JDK5 新增
### 就由 FutureTask 類 獲取call()回傳值
```java
FutureTask ft = new FutureTask(new Callable(){
    public Object call() throws Exception{
        ....
        return Object
    }
})
```
### 實現Callable比實現Runnable更強大
```
1. call()可以有返回值。
2. call()可以拋出異常，被外面的操作捕獲，獲取異常訊息
3. Callable支持泛型
```

<br/>

### 範例代碼 獲得1~100偶數合
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 1. 創建一個實現Callable的實現類
class MyThread implements Callable {
    // 2. 實現call方法，將此線程需要執行的操作聲明在call()中
    @Override
    public Object call() throws Exception {
        int num = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                num += i;
            }
        }
        return num;
    }
}


public class Main {
    public static void main(String[] args) {
        // 3. 創建 Callable 對象
        MyThread myThread = new MyThread();
        // 4. 將此 Callable 接口實現類的對象作為參數傳遞FutureTask溝造器中，創建FutureTask的對象
        // 查看構造器快捷鍵:ctrl + alt + / ; KotKeyName:parameter info
        FutureTask futureTask = new FutureTask(myThread);

        // 5. 將FutureTask的對象作為參數傳遞到Thread類構造器中，創建Thread對象，並調用start()
        new Thread(futureTask).start();

        try {
            // 6. 獲取Callable中call方法的返回值
            // get()返回值即為futureTask構造器參數Callable實現類重寫的call()的返回值。
            Object output = futureTask.get();
            System.out.println("Outpub: " + output);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

```

<br/>

# 創建 執行緒的第四種方式 "線程池" --> JDK5.0 新增
### 好處:
  + 提高響應速度(減少創建線程的時間)
  + 將低資源消耗(重複利用線程池中線程，不需要每次都創建)
  + 便於線程管理
    + corePoolSize: 核心池的大小
    + maximumPoolSize: 最大線程數
    + keepAliveTime: 線程沒有任務時最多保持多長時間後會終止
    + ....

### 線程池相關API: ExecutorService 和 Executors
+ ExecutorService: 真正的線程池接口。 常見子類ThreadPoolExecutor
  +  void execute(Runnable command): 執行任務/命令，沒有返回值，一般用來執行Runnable
  +  <T> Future<T> submit(Callable<T> task): 執行任務，有返回值，一般來執行Callable
  +  void shutdown(): 關閉連接池
  
+ Executors: 工具類、線程池的工廠類，用於創建並返回不同類型的線程池
  + Executors.newCachedThreadPool(): 創建一個可根據需要創建新線程的線程池
  + Executors.newFixedThreadPool(n): 創建一個可重用固定線程數的線程池
  + Executors.newSingleThreadExecutor(): 創建一個只有一個線程的線程池
  + Executors.newScheduledThreadPool(n): 創建一個線程池，它可安排在給定延遲後運行命令或者定期地運行。

### 範例代碼 創建Executors.newFixedThreadPool
```java
import java.util.concurrent.*;
class RunThread implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 0");
        }
    }
}

class CallThread implements Callable {
    public Object call() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 1");
            sum += 1;
        }
        return sum;
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        // 1. 提供指定線程數量的線程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 設置線程屬性
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        service1.setCorePoolSize(2);
        // service1.setKeepAliveTime();

        // 2. 執行指定的線程的操作，須提供Runnable接口或Callable接口實現類的對象
        service.execute(new RunThread());// 適用於Runnable

        Future future = service.submit(new CallThread());// 適用於Callable
        FutureTask futureTask = (FutureTask) future;
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 3. 關閉連接池
        service.shutdown();
    }
}

```
