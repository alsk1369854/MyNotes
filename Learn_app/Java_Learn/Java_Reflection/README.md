# Java Reflection (反射)

> 1. Reflection 的調用
> 
> 2. Get Class
> 
> 3. Class Loader

> 4. Get Class Information
>       + 4-1. Get Generic Superclass (獲取帶泛型的父類和interface的泛型型別)
>       + 4-2. Get Annotation (獲取註解)


<br/>

> ## 1. Reflection 的調用
```java
// java.lang.reflect.Constructor;
// java.lang.reflect.Field;
// java.lang.reflect.Method;
@Test
public void testReflection() throws Exception {
    Class<Person> personClass = Person.class;

    // 反射實現: constructor 調用
    // new Person() (常用)
    Person temp = personClass.newInstance();
    System.out.println(temp); // Person{name='null', age=0}
    // new Person("Ming", 20);
    Constructor<Person> cons = personClass.getDeclaredConstructor(String.class, int.class);
    Person person = cons.newInstance("Ming", 20);
    System.out.println(person); // Person{name='Ming', age=20}

    // 反射實現: int personAge = person.age;
    Field age = personClass.getDeclaredField("age");
    // get
    int personAge = (int) age.get(person);
    System.out.println(personAge); // 20
    // set
    age.set(person, 23);
    System.out.println(person); // Person{name='Ming', age=23}

    // 反射實現(private 私有調用): String returnValue = person.sayHi("Hi~");
    Method sayHi = personClass.getDeclaredMethod("sayHi", String.class);
    sayHi.setAccessible(true); // 如是被 private 所修飾，需要設定此
    String returnValue = (String) sayHi.invoke(person, "Hi~");
    System.out.println(returnValue); // Ming: Hi~
}
```

<br/>

> ## 2. Get Class
```java
// java.lang.Class (java預設引入的類)
@Test
public void testGetClass() throws ClassNotFoundException {
    // 方法一
    Class c1 = Person.class;
    System.out.println(c1); // class Java_Reflection.Person
    // 方法二
    Class c2 = new Person().getClass();
    System.out.println(c2); // class Java_Reflection.Person
    // 方法三 (最常用) // src/Java_Reflection.Person
    Class c3 = Class.forName("Java_Reflection.Person");
    System.out.println(c3); // class Java_Reflection.Person
    // 比較(都是同一個)
    System.out.println(c1 == c2); // true
    System.out.println(c1 == c3); // true
}
```

<br/>

> ## 3. Class Loader
 
```java
// java.util.Properties;
// 文檔: src/person.properties
    // name=Main
    // password=aaabbb
public class DemoClassLoader {
    public static void main(String[] args) throws IOException {
        // 獲取當前類的ClassLoader ;src/Java_Reflection.DemoClassLoader.java
        // 根目錄為當前module的src
        ClassLoader classLoader = DemoClassLoader.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("person.properties");

        // 讀取配置文件
        Properties pros = new Properties();
        pros.load(is);

        // 輸出配置文件內容
        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println("name: " + name + ", password: " + password); // name: Main, password: aaabbb
    }
}
```

<br/>

> ## 4. Get Class Information
> ### Demo Object
```java
import Java_Annotation.MyAnnotation;
interface MyInterface<E> {}
class Animal<E> {}
@MyAnnotation(value = "Person")
public class Person extends Animal<String> implements MyInterface<Integer> {
    private String name;
    public int age;
    public Person() {}
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }
    private String sayHi(String msg) {
        return name + ": " + msg;
    }
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
<br/>

> ### 4-1. Get Generic Superclass (獲取帶泛型的父類和interface的泛型型別)

```java
// 獲取帶泛型的父類和interface的泛型型別
@Test
public void testGetGenericSuperclass() {
    Class personClass = Person.class;

    // 獲取代泛型的interface
    Type[] genericInterfaces = personClass.getGenericInterfaces();
    for (Type item : genericInterfaces) {
        System.out.println(item); // Java_Reflection.MyInterface<java.lang.String>
    }
    // 獲取代泛型的父類class
    Type genericSuperclass = personClass.getGenericSuperclass();
    System.out.println(genericSuperclass); // Java_Reflection.Animal<java.lang.String>
    ParameterizedType paramType = (ParameterizedType) genericSuperclass;
    // 獲取泛型類型
    Type[] actualTypeArguments = paramType.getActualTypeArguments();
    // 方式一
    System.out.println(actualTypeArguments[0].getTypeName()); // java.lang.String
    // 方式二
    System.out.println(((Class) actualTypeArguments[0]).getName()); // java.lang.String
}
```

<br/>

> ### 4-2. Get Annotation (獲取註解)

```java
// 獲取註解
@Test
public void testGetAnnotation() {
    Class personClass = Person.class;

    Annotation[] annotations = personClass.getAnnotations();
    for (Annotation item : annotations) {
        System.out.println(item); // @Java_Annotation.MyAnnotation(value=Person)
    }
}
```

<br/>
<br/>

#### _END_