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

### Spring boot 預設使用: slf4j and logback 處理日誌
```text
slf4j: 日誌工具的 interface，類似於 JDBC
https://www.slf4j.org/legacy.html

logback: 日誌紀錄工具
```

### 日誌相關 application.properties 配置
```properties
# 指定日誌打印級別
#logging.level.com.example=trace

# (常用)指定日誌輸出的目錄(會在指定目錄下生成 spring.log)
logging.file.path=log

# 指定日誌輸出的文件
#logging.file.name=spring.log

# console 介面的日誌格式
logging.pattern.console=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n

# log file 的日誌格式
#logging.pattern.file=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n
```

##  webjars 置入外部工具包
### webjars 包查詢網站
https://www.webjars.org/

### 在 Maven pom.xml 檔案中加入引入對應的包
```xml
<dependencies>
    <!-- 引入-jquery-from-webjars-->
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


## thymeleaf 模板工具

### 在 pom.xml 中引入 thymeleaf
[Spring Boot Doc: dev->build->starters](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.build-systems.starters
)
```xml
<dependencies>
    <!-- 引入-thymeleaf-from-spring-starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>
```

### 屬性
![](screenshots/img.png)

### 語句表達式
![](screenshots/img_1.png)


