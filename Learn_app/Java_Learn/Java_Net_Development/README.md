# Java Net Development
> IP
>  + java.net.InetAddress 類 (用於表示一個IP)


> 端口號 
>  + 每個進程用不同的端口號做標示
>  + 範圍: 0 ~ 65535


> Demo TCP
>

<br/>

> ## InetAddress
### 獲取建立IP
```java
@Test
public void testGetIP(){
    try {
        // 獲取IP
        InetAddress inet; // 用來標示 IP 的類
        inet = InetAddress.getByName("192.168.3.4"); // IPV4
        System.out.println("IPV4:\t" + inet);
        inet = InetAddress.getByName("127.0.0.1"); // 本地 IP 方法1
        System.out.println("本地IP:\t" + inet);
        inet = InetAddress.getLocalHost(); // 本地 IP 方法2
        System.out.println("本地IP:\t" + inet);
        inet = InetAddress.getByName("www.google.com"); // 域名: 經過網路DNS轉換IP
        System.out.println("域名IP:\t" + inet);
        System.out.println("getHostName() -> 域名:\t" + inet.getHostName()); // 獲取域名
        System.out.println("getHostAddress() -> IP:\t" + inet.getHostAddress()); // 獲取IP
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
}
```

<br/>

> ## Demo TCP
### 先開 Server 在開 Client
```java
// Demo TCP
// Client
@Test
public void testTCPClient() {
    Socket socket = null;
    OutputStream os = null;
    try {
        // 1. 創建Socket對象，指名服務器IP和端口號
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        socket = new Socket(inet, 3000);
        // 2. 獲取一個輸出流，用於輸出數據
        os = socket.getOutputStream();
        // 3. 寫出數據的操作
        os.write("Hi, I'm Client".getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 4. 關閉資源
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// Server
@Test
public void testTCPServer() {
    ServerSocket ss = null;
    Socket socket = null;
    InputStream is = null;
    ByteArrayOutputStream baos = null;
    try {
        // 1. 創建服務器的 ServerSocket 指明自己的端口號
        ss = new ServerSocket(3000);
        // 2. 調用 accept() 表示接收來自客戶端的 Socket
        socket = ss.accept();
        // 3. 獲取輸入流
        is = socket.getInputStream();
        // 4. 讀取數據
        baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        // 5. 打印數據
        System.out.println(baos.toString()); //Hi, I'm Client
        System.out.println("收到來自: " + socket.getInetAddress().getHostAddress() + " 的數據"); //收到來自: 127.0.0.1 的數據
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 6. 關閉資源
        if (baos != null) {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (ss != null) {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

<br/>
<br/>

#### _END_