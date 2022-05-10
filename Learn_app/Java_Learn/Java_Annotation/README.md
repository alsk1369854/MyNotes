# Java Annotation

> 1. 文檔相關註解
> 
> 2. 編譯相關註解
> 
> 3. 自定義註解
>       + 3-1. 自定義 Annotation
>       + 3-2. 使用自定義 Annotation 

<br>

> ## 1. 文檔相關註解

<p align="center">
<img height="500px" src="/Learn_app/Java_Learn/Java_Annotation/自定義註解_Retention.png"/>
</p>

<br/>

> ## 2. 編譯相關註解

<p align="center">
<img height="500px" src="/Learn_app\Java_Learn\Java_Annotation\Java_編譯相關註解.png"/>
</p>

<br/>

> ## 3. 自定義註解

```
 Annotation 自定義註解
    - 跟蹤代碼依賴性，實現替代配置文件功能
    - 如何自定義註解: 參照SuppressWarnings定義
        1. 註解聲明為: @interface
        2. 內部定義成員，通常使用value表示
        3. 可以指定成員的默認值，使用default定義
        4. 如果自定義註解沒有成員，表明是一個標示作用

    - 如果註解有成員，在使用註解時，需要指明成員的值
    - 自定義註解必須配上註解的信息處理流程(使用反射)才有意義
    - 自定義註解通常都會指明兩個元註解: Retention, Target

    - jdk 提供4種元註解
        - 元註解: 對現有的註解進行解釋說明的註解
        1. Retention: 指定所修飾的 Annotation 的生命週期
            - RetentionPolicy.SOURCE: 被忽略在 -> javac.exe編譯.class時被忽略
            - RetentionPolicy.CLASS: (default) 被忽略在 -> java.exe 運行時不會被寫入記憶體
            - RetentionPolicy.RUNTIME: (反射應用比需標示) 不會被忽略 -> java.exe 運行時會一同載入記憶體
        2. Target: 用於指定被修飾的 Annotation 能用於修飾那些程元素
            - TYPE: 標示可修飾 -> class, interface, enum
            - FIELD: 標示可修飾 -> Field(屬性)
            - METHOD: 標示可修飾 -> Method(方法)
            - PARAMETER: 標示可修飾 -> 參數
            - CONSTRUCTOR: 標示可修飾 -> 構造器
            - LOCAL_VARIABLE: 標示可修飾 -> 局部變量
            - ANNOTATION_TYPE: 標示可修飾 -> 註解類型
            - PACKAGE: 標示可修飾 -> 包
        ********** 出現平率較低 *************
        1. Documented: 表示所修飾的註解在被 javadoc 解釋時，保留下來
        2. Inherited: (物件子類也會將註解一並繼承) 被他修飾的 Annotation 將具有繼承性

    - jdk 8 新增
        1. Repeatable: 可重複註解
            - 在 MyAnnotation 上聲明 @Repeatable(MyAnnotations.class)
            - MyAnnotation, MyAnnotations 的 Target 和 Retention 必須相同

        2. Target: 用於指定被修飾的 Annotation 能用於修飾那些程元素
            - TYPE_PARAMETER: 標示可修飾 -> 類型變量聲明(如: 泛型)
            - TYPE_USE: 標示可修飾 -> 使用類型的任何語句中
```

<br/>

### 3-1. 自定義 Annotation
```java
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

// MyAnnotation
@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation{
    String value();
    // use default value
    // String value() default "hello world!";
}

// MyAnnotations: MyAnnotation的包裝類，使其可以重複註解
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotations {
    MyAnnotation[] value();
}
```

<br/>

### 3-2. 使用自定義 Annotation 
```java
@MyAnnotation(value = "TYPE_1")
@MyAnnotation("TYPE_2")
public class Person<@MyAnnotation("TYPE_PARAMETER") E>{
    @MyAnnotation("FIELD")
    String name;
    int age;

    @MyAnnotation("CONSTRUCTOR")
    public Person(@MyAnnotation("PARAMETER") String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation("METHOD")
    public String toString() throws @MyAnnotation("TYPE_USE") RuntimeException {
        @MyAnnotation("LOCAL_VARIABLE")
        String temp = "temp";
        List<@MyAnnotation("TYPE_USE") String> list = new ArrayList<>();
        int num = (@MyAnnotation("TYPE_USE") int) 10L;
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

<br/>
<br/>

#### _END_