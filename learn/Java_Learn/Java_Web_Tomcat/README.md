# Java Web
## 簡介
### 1. tomcat 防止中文亂碼
+ tomcat 8 之前
+ tomcat 8 之後
### 2. Servlet 繼承關係
+ 1 繼承關係
+ 2 相關方法
### 3 Servlet的生命週期
### 4 Http協議
### 5 會話
### 6 服務器內部轉發以及客戶端重定向
### 7 Thymeleaf 用來幫助我們做view render 的一個技術
### 8 保存作用域
+ request
+ session
+ application

## 1. tomcat 防止中文亂碼
### tomcat 8 之前
#### get:
```java
String fname = request.getParameter("name");        
byte[] bytes = fname.getBytes("ISO-8859-1");
String name = new String(bytes,"utf-8");
```  
#### post:
```java
request.setCharacterEncoding("utf-8");
String fname = request.getParameter("name");
```
<br/>

### tomcat 8 之後
#### get:
```java
// 不用配置
```  
#### post:
```java
request.setCharacterEncoding("utf-8");
String fname = request.getParameter("name");
```

## 2. Servlet 繼承關係 - 重點查看的是(service(request, response))
### 1 繼承關係
    javax.servlet.Servlet (interface)
        javax.servlet.GenericServlet (abstract)
            javax.servlet.http.HttpServlet (abstract)

### 2 相關方法
    1. javax.servlet.Servlet (interface):
        init(ServletConfig var1) - 初始化方法
        void service(ServletRequest var1, ServletResponse var2) - 服務方法
        void destroy() - 銷毀方法

    2. javax.servlet.GenericServlet (abstract):
        public abstract void service(ServletRequest var1, ServletResponse var2) - 仍然是抽象的

    3. javax.servlet.http.HttpServlet (abstract):
        protected void service(HttpServletRequest req, HttpServletResponse resp) - 不是抽象的
        1. String method = req.getMethod(); - 獲取請求的方式
        2. 各種if判斷，根據請求方式不同，決定去調用不同的do方法
            if (method.equals("POST")) {
                this.doPost(req, resp);
            } else if (method.equals("PUT")) {
                this.doPut(req, resp);
            } else if (method.equals("DELETE")) {
            ....

        3. 在HttpServlet這個類中，do方法都差不多:
           protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
               String msg = lStrings.getString("http.method_post_not_supported");
               this.sendMethodNotAllowed(req, resp, msg);
           }
           private void sendMethodNotAllowed(HttpServletRequest req, HttpServletResponse resp, String msg) throws IOException {
               String protocol = req.getProtocol();
               if (protocol.length() != 0 && !protocol.endsWith("0.9") && !protocol.endsWith("1.0")) {
                   resp.sendError(405, msg);
               } else {
                   resp.sendError(400, msg);
               }
            }
    4. 小節
        1) 繼承關係: HttpServlet -> GenericServlet -> Servlet
        2) Servlet中的核心方法: init(), service(), destroy()
        3) 服務方法: 當有請求過來時， service方法會自動響應(其實是tomcat容器調用的)
            在HttpServlet中我們會去分析請求方式: 到底是get, post head 還是 ...
            然後在決定調用的是哪一個do開頭的方法
            那麼在HttpServlet中這些do方法默認都是405的實現風格-要我們子類去實現對應的方法，否則默認會報405錯誤
        4) 因此，我們在創建servlet時，我們才會去考愈請求方式，從而決定重寫哪個do方法

## 3 Servlet的生命週期
    1. 生命週期: 從出身到死亡的過程就是生命週期。對應servlet中的三個方法: init(), service(), destroy() 
    2. 默認情況下:
        第一次請求時，這個servlet會先進行實例化(調用構造方法), 初始化(調用init()), 然後服務(調用service())
        從第二次請求開始，每一次都是服務(調用service())
        當容器關閉時，其中所有的servlet實例會被銷毀，調用銷毀方法
    3. 當透過案例我們發現:
        - Servlet實例tomcat只會創建一個，所有的請求都是這個實例去響應。
        - 默認情況下，第一次請求時，tomcat才會去實例化，初始化，然後在服務，這樣的
            好處? 提高系統的啟動速度; 
            壞處? 第一次訪問時需要創建實例降低了第一次訪問的響應速度
        - 因此得出結論: 如果要提高系統啟動速度，當前默認情況就是這樣。如果需要提高響應速度，我們應該設定servlet的初始化時機。
    4. Servlet的初始化時機
        - 默認是第一次接收請求時 實例化, 初始化
        - 我們可以透過配置web.xml中<load-on-startup>來設計servlet啟動的先後順序，數字越小，啟動順序越靠前，最小值0
                <servlet>
                    <servlet-name>AddServlet</servlet-name>
                    <servlet-class>javaweb01.servlets.AddServlet</servlet-class>
                    <load-on-startup>1</load-on-startup>
                </servlet>
    5. Servlet在容器中是: 單例的，線程不安全的
        - 單例: 所有的請求都是同一個實例去響應
        - 線程不安全的: 一個線程需要根據這個實例中的某個成員變量值去做邏輯判斷。但是在中間某個時機，另一個線程改變了這個程成員變量，倒置其他線程出現判斷錯誤
            - 盡量不要在 servlet中定義成員變量。
            - 如果不得不定義成員變量，那麼不要
                1. 不要修改成員變量的值                    
                2. 不要根據成員變量的值做邏輯判斷

### 4 Http協議
    1. Http 稱為超文本傳輸協議
    2. Http 是無狀態的
    3. Http 請求響應包含兩個部分: 請求、響應
        - 請求: 
            請求包含三個部分: 1.請求行 ; 2.請求消息头 ; 3.請求主體
            1. 請求行包含三個信息: 1.請求方式 ; 2.請求的URL ; 3.請求的協議(一般都是HTTP1.1) // GET /localhost:8080/HTTP/1.
            2. 請求消息头中包含了很多Client要告訴Server的訊息。 如: 瀏覽器型號、版本，Client能接收的內容類型 ....
            3. 請求體: 三種情況
                get放式: 沒有請求體，但有一個QueryString
                post放式: 有請求體， From Data
                json格式: 有請求體， Request Payload
        - 響應:
            響應也包含三部分: 1.響應行 ; 2.響應头 ; 3.響應主體
            1. 響應行包含三個訊息: 1.協議 2.響應狀態碼(200) 3.響應狀態(ok)
            2. 響應頭: 包含了服務器的訊息, 服務器發送給瀏覽器的訊息(內容媒體類型, 編碼, 內容長度...)
            3. 響應體: 響應的實際內容(比如請求add.html頁面，響應就是<html><head><body><form>...)

### 5 會話
    1. Http是無狀態的
        - HTTP 無狀態: 服務器無法判斷兩次請求是否是同一個Client發送的，還是不同Client所發送的
        - 無狀態帶來的實現問題: 第一次請求式是添加商品到購物車，第二次請求是結帳; 如果兩次請求Server無法區分是同一個Client，就會導致混亂
        - 透過會話跟蹤技術解絕無狀態的問題  
    2. 會話跟蹤技術
        - Client第一次發送請求給Server，服務器獲取session，獲取不到，則創建新的，然後響應給Client端
        - 下一次Client給Server發請求時，會把session帶給Server，那麼Server就能獲取到了，因此Server就判斷這一次請求和上次某次請求是同一個Client端，從而能夠區分開客戶端
        - 常用API:
            HttpSession session = request.getSession(); -> 獲取當前會話，沒有則創建一個新的
            request.getSession(true); -> 效果和不帶參數相同
            request.getSession(false); -> 獲取當前會話，沒有則返回null，不會創建新的

            session.getId(); -> 獲取 sessionID
            session.isNew(); -> 判斷當前session是否是新的
            session.getMaxInactiveInterval(); -> session的非激活間隔時常，默認1800秒 (如: 網頁閒置過久後，需要重新登入)
            session.setMaxInactiveInterval(1800); - > 設定session的非激活間隔時常，為1800秒
            session.invalidate(); -> 強制性讓會話失效
            ....
    3. session保存作用域: 在Server端為會話生成一個存儲訊息的Map
        - session保存作用域是和具體的某一個session對應的
        - 常用API:
            void session.setAttribute(String key, Object value);
            Object session.getAttribute(String key);
            void session.removeAttribute(String key);

### 6 服務器內部轉發以及客戶端重定向
    1. Server內部轉發: request.getRequestDispatcher("demo2").forward(request,response); // Server內部轉發給 /demo2 處理請求  
        - 一次請求響應的過程，對於Client端而言，內部經過多少次轉發，Client端是不知道的
        - 最終地址欄沒有變化
    2. Client重定向: response.sendRedirect("demo2"); // 要求Client端立刻向 http://.../demo2 發送請求
        - 兩次請求響應的過程。客戶端肯定知道請求URL有變化
        - 地址欄有變化

### 7 Thymeleaf 用來幫助我們做view render 的一個技術
    1. 添加thymeleaf的jar包
    2. 新建一個Servlet類ViewBaseServlet
    3. 在web.xml文件中添加配置
        - 配置前墜 view-prefix
            <context-param>
                <param-name>view-prefix</param-name>
                <param-value>/</param-value>
            </context-param>

        - 配置後墜 view-suffix
            <context-param>
                <param-name>view-suffix</param-name>
                <param-value>.html</param-value>
            </context-param>
    4. 使得我們的Servlet繼承ViewBaseServlet
    5. 根據邏輯View 得到 物理 View名稱
        此處View名稱是 index
        那麼thymeleaf會將這個，邏輯View名稱對應到，物理View名稱上去
        邏輯View名稱: index
        物理View名稱: view-prefix + 邏輯View名稱 + view-suffix
        所以真實View名稱:  /       +    index    +     .html
        super.processTemplate("index", req, resp);
    6. 使用thymeleaf的標籤
        th:if   ,   th:unless   ,   th:each ,   th:text

### 8 保存作用域
    原始強況下，保存作用域我們可以認為有四個: page(頁面級別，現在幾乎不用), request, session, application
    1) request: 一次請求響應範圍
    2) session: 一次會話範圍有效
    3) application: 一次應用程序範圍有效

### 9 路徑問題
    1) 絕對路徑
    2) 相對路徑

### 10 xml
    1) 概念
        HTML: 超文本標記語言
        XML: 可懬展的標記語言
        HTML是XML的一個子集
    2) XML包含三個部分:
        1) XML聲明，而且聲明代碼必須在XML文件第一行 <?xml version="1.0" encoding="utf-8"?>
        2) DTD 文檔類型
        3) XML 正文

### 11 DispatchServlet 使用反射提供一個中央Servlet
    1. 配置 Controller Bean 這個對應依據我們存儲在 applicationContext.xml 中
        <bean id="customer" class="JavaWeb.controllers.CustomerController"></bean>
        通過DOM技術我們去解析XML文件，在中央控制器中形成一個 beanMap 容器，用來存放所有的 controller 組件實例
    2. 根據 url 定位到能處理這個請求的 controller 組件
        1) 從 url 中提取 request.getServletPath() // /customer.do  ->  customer 
        2) 根據 customer 從 beanMap 中找到對應的 controller 實例
        3) 根據獲取到的 operate 的值定位到我們 controller 中需要調用的方法
    3. 調用 controller 組件中的方法
        1) 配置 IDEA 編譯打包參數名稱
            IDEA: File -> Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler
                Additional command line parameters: -parameters
        2) 獲取參數
            獲取即將要調用的方法參數簽名訊息: Parameter[] parameters = method.getParameters();
            通過調用 parameter.getName() 獲取參數名稱
            準備 Object[] values 這個陣列用來存放傳入方法對應的值
            另外，我們需要考慮參數類型問題，需要做類型轉換的工作。 透過 parameters[i].getParameterizedType().getTypeName() // "java.lang.Integer"
        2) 執行方法
            String returnValue = (String) method.invoke(controller, values);
        3) 視圖處理
            if (returnValue.startsWith("redirect:")) {
                response.sendRedirect(returnValue.substring("redirect:".length()));
            } else {
                super.processTemplate(returnValue, request, response);
            }
            


th:action="@{/update.cust}"
th:href="@{/editor(custId=${cust.id})}

th:object="${session.cust}"
    th:value="*{id}"
    th:value="*{name}"

th:text="${cust.id + '---' + cust.name}"

th:if="${#lists.isEmpty(session.custList)}"
th:unless="${#lists.isEmpty(session.custList)}"
th:each="cust : ${session.custList}"

// 302: Client 重定向
// 200: 正常響應
// 404: 找不到資源
// 405: 請求方式不支持
// 500: 服務器內部錯誤