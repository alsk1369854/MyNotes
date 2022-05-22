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
    javax.com.servlet.Servlet (interface)
        javax.com.servlet.GenericServlet (abstract)
            javax.com.servlet.http.HttpServlet (abstract)

### 2 相關方法
    1. javax.com.servlet.Servlet (interface):
        init(ServletConfig var1) - 初始化方法
        void service(ServletRequest var1, ServletResponse var2) - 服務方法
        void destroy() - 銷毀方法

    2. javax.com.servlet.GenericServlet (abstract):
        public abstract void service(ServletRequest var1, ServletResponse var2) - 仍然是抽象的

    3. javax.com.servlet.http.HttpServlet (abstract):
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
                <com.servlet>
                    <com.servlet-name>AddServlet</com.servlet-name>
                    <com.servlet-class>javaweb01.servlets.AddServlet</com.servlet-class>
                    <load-on-startup>1</load-on-startup>
                </com.servlet>
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
        <bean id="customer" class="JavaWeb.customer.controllers.CustomerController"></bean>
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

## 12 Servlet 初始化方法
    1. 初始化方法有兩個: init(), init(config)
        1) 帶參數:
            public void init(ServletConfig config) throws ServletException {
                this.config = config;
                this.init();
            }
        2) 未帶參數:
            public void init() throws ServletException {
            }
        3)　我們想要在 Servlet 初始化時做一些準備工作，那麼我們可以重寫init方法
            我們可以透過下方步驟獲取初始化設置數據
            - 獲取config對象: ServletConfig config = getServletConfig();
            - 獲取參數值: String value = config.getInitParameter(key);
    2. 在web.xml文件中配置Servlet
        <!--  ServletContext context = getServletContext()  -->
        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </context-param>
        <!--  ServletConfig config = getServletConfig()  -->
        <com.servlet>
            <com.servlet-name>IndexServlet</com.servlet-name>
            <com.servlet-class>com.servlet.IndexServlet</com.servlet-class>
            <init-param>
                <param-name>name</param-name>
                <param-value>Ming</param-value>
            </init-param>
        </com.servlet>
        <!--  IndexServlet 映射  -->
        <com.servlet-mapping>
            <com.servlet-name>IndexServlet</com.servlet-name>
            <url-pattern>/index</url-pattern>
        </com.servlet-mapping>
        
    3. 也可以透過註解的方式進行配置:
        @WebServlet(urlPatterns = {"/index"},
                initParams = {
                        @WebInitParam(name = "name", value = "Ming")
                }
        )
    
    4. Servlet 中的 ServletContext 和 <context-param>
        1) 獲取ServletContext，有很多
            在初始化方法中獲取: 
                ServletContext context = getServletContext();
                String contextConfigLocation = context.getInitParameter("contextConfigLocation");
            在 service() 方法中也可以透過 request對象獲取:
                request.getServletContext();
                request.getSession().getServletContext();
        2) 獲取初始化值:
            String value = servletContext.getInitParameter(key);

## 13 什麼是業務層
    MVC: Model(模型)、View(視圖)、Controller(控制器)
    View: 用於做數據展示以及和用戶交互的一個介面
    Controller: 能夠接收客戶端的清求，具體的業務功能還是需要借助模型組件來完成
    Model: 模型分為很多種: 有比較簡單的 pojo/vo(value object)，有業務模型組件，有數據庫訪問組件
        1) pojo/vo: 值模型
        2) DAO: 數據庫方問模型
        3) BO: 業物邏輯模型
        4) DTO: 數據傳輸模型
    
    區分業務對象和數據訪問對象:
        1) DAO中的方法都是單精度方法。 什麼叫單精度? 一個方法只考慮一個操作， 比如添加，那就是insert操作、查詢操作select、更新操作update、刪除操作delete
        2) BO中的方法屬於業務方法，而實際的業務是比較複雜的，因此業務方法的粒度是比較粗的
            例: 註冊
                1. 檢查用戶名是否已經被註冊 - DAO中的select操作
                2. 向用戶表新增一條新用戶數據 - DAO中的insert操作
                3. 向用戶積分表新增一條數據(默認100分) - DAO中的insert操作
                4. 向系統消息表新增一條數據(XXX新用戶註冊了，需要根據通訊錄向他發送驗證信) - DAO中的insert操作
                5. 向系統日誌表新增一條數據(XXX用戶 IP:xxx.xxx.xxx 日期:xxxx/xx/xx 註冊) - DAO中的insert操作
                6. ....

## 14 IOC
    1) 耦合/依賴
        依賴是指XXX離不開xxx
        在軟體系統中，層與層之間是存在依賴的。我們也稱之為耦合
        我們系統架構或者是設計的一個原則是: 高內聚低耦合。
        層內部的組件因該是高度聚合的，而層與層之間的關西因該是低耦合的，最理想的情況是0耦合(就是沒有耦合)
    2) IOC - 控制反轉 / DI - 依賴注入
        控制反轉:
        1) 之前在Servlet中，我們創建service對象， CustomerService customerService = new CustomerServiceImpl();
            這句話如果出現在servlet中某個方法內部，那麼這個customerService的作用域(生命週期)因該就是這個方法級別的;
            如果這句話出現在servlet的類中，也就是說customerService是一個成員變量，那麼這個customerService的作用域(生命週期)因該就是這個servlet實例級別
        2) 之後我們在applicationContext.xml中定義了這個customerService。然後透過解析XML，產生customerService的實例，存放在beanMap中，這個beanMap在一個beanFactory中
            因此，我們轉移(改變)了之前的service實例，dao實例等等他們的生命週期。控制權從程序員轉移到beanFactory。這個現象我們稱之為控制反轉。

        依賴注入:
        1) 之前我們在控制層出現代碼: CustomerService customerService = new CustomerServiceImpl();
            那麼，控制層和service層級存在耦合。
        2) 之後，我們將代碼修改成 CustomerService customerService = null;
            然後，在配置文件中配置:
            <bean id="customer" class="JavaWeb.customer.controllers.CustomerController">
                <property name="customerService" ref="customerService"/>
            </bean>

## 15 過濾器 Filter
    1) Filter也屬於Servlet規範
    2) Filter開發步驟: 新建類實現Filter接口，然後實現其中三個方法: init, doFilter, destoy
        配置Filter, 可以用註解@WebFilter, 也可以使用xml文件 <filter><filter-mapping>
    3) Filter在配置時, 和servlet一樣，也可配置通配符，例如: @WebFileter("*.do") 表示攔截所有以.do結尾的請求
    4) 過濾器鏈
        1) 執行的順序依次是: AFilter BFileter CFileter Servlet CFilter2 BFileter2 AFileter2 
        2) 如果採取的是註解的方式進行配置， 那麼過濾器鏈的攔截順序是按照類名遞增的先後順序排序的
        3) 如果採取的是xml的方式進行配置，那麼按照配置的先後順序來進行排序


## 16 事務管理 (TransActionManager, ThreadLocal, OpenSessionInViewFilter)
    1) 涉及到的組件
        - OpenSessionInViewFilter
        - TransactionManager
        - ConnUtil(JDBCUtils)
        - BaseDAO
    
    2) ThreadLocal
        - get(), set(obj)
        - ThreadLocal稱之為本地線程。我們可以透過set方法在當前線程上存儲數據，透過get方法在當前線程上獲取數據
        - set 方法原碼分析:
            public void set(T value) {
                Thread t = Thread.currentThread();  // 獲取當前線程
                ThreadLocalMap map = getMap(t);     // 每個線程都維護各自的一個容器(TreadLocalMap)
                if (map != null) {
                    map.set(this, value);           // 這裡的key對應的是ThreadLocal，因為我們的組件中需要傳輸(共享數據)的對象可能會有多個工具箱
                } else {
                    createMap(t, value);            // 默認情況下map是每有初始化的，那麼第一次往其中添加數據時，進行初始化
                }
            }

        - get 
            public T get() {
                Thread t = Thread.currentThread();  // 獲取當前線程
                ThreadLocalMap map = getMap(t);     // 獲取和這個線程(企業)相關的ThreadLocalMap(也就是工作紐帶的集合)
                if (map != null) {
                    ThreadLocalMap.Entry e = map.getEntry(this);    this指的是ThreadLocal對象，通過他才能知道是哪一個工作紐帶
                    if (e != null) {
                        @SuppressWarnings("unchecked")
                        T result = (T)e.value;  // entry.value就可以獲取到工具箱了
                        return result;
                    }
                }
                return setInitialValue();
            }
                
## 17 監聽器(Listener, ContextLoaderListener)
    1) ServletContextListener - 監聽ServletContext對象創建和銷毀過程
    2) HttpSessionListener - 監聽HttpSession對象創建和銷毀過程
    3) ServletRequestListener - 監聽ServletRequest對象創建和銷毀過程
    
    4) ServletContextAttributeListener - 監聽ServletContext的包存作用愈的改動(add, remove, replace)
    5) HttpSessionAttributeListener - 監聽HttpSession的包存作用愈的改動(add, remove, replace)
    6) ServletRequestAttributeListener - 監聽ServletRequest的包存作用愈的改動(add, remove, replace)

    7) HttpSessionBindingListener - 監聽某個對象在Session域中的創建與移除
    8) HttpSessionActivationListener - 監聽某個對象在Session域中的序列化和反序列化

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