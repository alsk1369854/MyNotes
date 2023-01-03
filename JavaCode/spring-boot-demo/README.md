# Sprint Boot

## Spring Boot 標籤
### @SpringBootApplication 表示此類，是一個 Spring boot 應用
com.example.DemoApplication.java
```java
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```

### @RestController 表示此類為，會回傳東西給瀏覽器的controller
com.example.controller.HelloController.java
```java
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

}
```

### @Component // 為 Spring boot 組件
### @Configuration // 配置類
### @ConfigurationProperties(prefix = "person") // 配置類，從 person 下取值
### @PropertySource(value = "classpath:person.properties") // 配置 profile 來源
```java
@Component
//@Configuration
@ConfigurationProperties(prefix = "person")
@PropertySource(value = "classpath:person.properties")
public class Person {
    //    @Value("${person.name}") // 不同的配置取值方式，搭配 @Configuration 申明為配置類
    private String name;
    //    @Value("#{10+5}")
    private Integer age;
    //    @Value("true")
    private Boolean isMale;
    ....
}
```

## application[.properties|.yml] 配置文檔
/src/resources/...
### 配置文檔模組
```text
建立一個配置模組文檔命名格式
application-{module}.properties
```

## slf4j 與 logback，日誌工具
Spring boot 預設使用: slf4j and logback 處理日誌
```text
slf4j: 日誌工具的 interface，類似於 JDBC
https://www.slf4j.org/legacy.html

logback: 日誌紀錄工具
```

##  webjars 置入外部工具包
### webjars 包查詢網站
https://www.webjars.org/

### 可在 Maven pom.xml 檔案中加入配置，即可導入
```xml
<dependencies>
    <!-- 引入-jquery-webjars-->
    <dependency>
        <groupId>org.webjars.npm</groupId>
        <artifactId>jquery</artifactId>
        <version>3.6.3</version>
    </dependency>
</dependencies>
```
### 使用 api 索取包
http://localhost/webjars/jquery/...
![](screenshots/Snipaste_2023-01-03_23-35-56.png)
