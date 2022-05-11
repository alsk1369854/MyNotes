# Java Lambda 表達式

1. Lambda 表達式介紹
    + 1-1. FunctionalInterface 函數接口
    + 1-2. java 8 內置的4大核心函數式接口
    + 1-3. java 8 內置的其他函數式接口
 
2. 一些`實例抽象方法`與`Lambda 表達式`比較
 
3. 一些`Lambda 表達式`使用範例

4. 引用
    + 4-1. 方法引用
      + 4-1-1. 情況一: 對象 :: 非靜態方法
      + 4-1-2. 情況二: 類 :: 靜態方法
      + 4-1-3. 情況三: 類 :: 非靜態方法 (傳入的第一個值為調用對象, 其餘為傳入參數)
    + 4-2. 構造引用
    + 4-3. 數組引用


<br/>

## 1. Lambda 表達式介紹
### 1-1. FunctionalInterface 函數接口
```java
// interface 必須直有一個要被實現的抽象方法
@FunctionalInterface
interface MyFunctionalInterface<T> {
    String sayHi(String name);
}
@Test
public void myTest(){
    // MyFunctionalInterface<String> myfun = t -> t + ", Hi~";
    MyFunctionalInterface<String> myfun = (t) -> {
        return t + " Hi~";
    };
    System.out.println(myfun.sayHi("Ming")); // Ming, Hi~
}
```
<br/>

### 1-2. java 8 內置的4大核心函數式接口
<p align="center">
<img height="500px" src="Learn_app\Java_Learn\Java_Lambda\函數式接口_四大基礎的.png"/>
</p>
<br/>

### 1-3. java 8 內置的其他函數式接口
<p align="center">
<img height="500px" src="Learn_app\Java_Learn\Java_Lambda\函數式接口_其他.png"/>
</p>
<br/>

## 2. 一些`實例抽象方法`與`Lambda 表達式`比較
```java
@Test
public void test() {
    // General
    Runnable run1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Run_1");
        }
    };
    run1.run(); // Run_1

    // Lambda
    Runnable run2 = () -> System.out.println("Run_2");
    run2.run(); // Run_2

    System.out.println("===========================================");

    // General
    Comparator<Integer> comp1 = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };
    int comp1Return = comp1.compare(5, 8);
    System.out.println("comp1: " + comp1Return); // comp1: -1

    // Lambda
    // Comparator<Integer> comp2 = (Integer o1, Integer o2) -> {
    //     return Integer.compare(o1, o2);
    // };
    Comparator<Integer> comp2 = (o1, o2) -> o1.compareTo(o2);

    int comp2Return = comp2.compare(8, 5);
    System.out.println("comp2: " + comp2Return); // comp2: 1

    // 方法引用
    Comparator<Integer> comp3 = Integer::compare;
    int comp3Return = comp3.compare(5, 5);
    System.out.println("comp3: " + comp3Return); // comp3: 0
}
```

<br/>

## 2. 一些`Lambda 表達式`使用範例
```java
@Test
public void testConsumer() {
    // General
    test1(400, new Consumer<Integer>() {
        @Override
        public void accept(Integer money) {
            System.out.println("消費了: " + money);
        }
    }); // 消費了: 400

    // Lambda
    test1(500, money -> System.out.println("消費了: " + money)); // 消費了: 500
}

public void test1(int money, Consumer<Integer> cons) {
    cons.accept(money);
}

@Test
public void testPredicate() {
    List<String> list = Arrays.asList("台北", "台中", "高雄");
    List<String> result1 = test2(list, new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s.contains("台");
        }
    });
    System.out.println(result1); // [台北, 台中]

    List<String> result2 = test2(list, s -> !s.contains("台"));
    System.out.println(result2); // [高雄]

}

public List<String> test2(List<String> list, Predicate<String> pre) {
    List<String> result = new ArrayList<>();
    for (String item : list) {
        if (pre.test(item)) {
            result.add(item);
        }
    }
    return result;
}
```
<br/>

## 4. 引用
```
    一. 方法引用
        1. 使用情境: 當要傳遞給Lambda體的操作，已經有實現的方法了，可以使用方法引用
        2. 方法引用，本質上就是Lambda表達式，而Lambda表達式作為函數式接口的實例，所以方法引用，也是函數式接口實例。
        3. 使用格式: 類(或對象) :: 方法名
        4. 具體分為如下的三種情況:
            - 情況一: 對象 :: 非靜態方法
            - 情況二: 類 :: 靜態方法

            - 情況三: 類 :: 非靜態方法 (傳入的第一個值為調用對象, 其餘為傳入參數)

        5. 方法引用使用要求: 要求接口中的抽象方法的形參列表和返回值類型 [必須] 與 方法引用的方法形參列表和返回值類型 [相同]!
            - 針對 (情況一, 情況二)

    二. 構造引用
        和方法引用類似，函數式接口的抽象方法的形參列表和構造器的形參列表一致。
        抽象方法的返回值即為構造器所屬的類的類型

    三. 數組引用
        可以把數組引用看做是一個特殊類，則寫法與構造器引用一致。
```
#### Demo 範例類
```java
class Employee {
    String name;
    public Employee() {}
    public Employee(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
```
<br/>

### 4-1. 方法引用

#### 4-1-1. 情況一: 對象 :: 非靜態方法
```java
// 一. 方法引用
// 情況一: 對象::實例方法
// Consumer     void accept(T t)
// PrintStream  void println(T t)
@Test
public void test1() {
    Consumer<String> con1 = s -> System.out.println(s);
    con1.accept("Con1");

    PrintStream ps = System.out;
    Consumer<String> con2 = ps::println;
    con2.accept("Con2");
}

// Supplier     T get()
// Employee     String getName()
@Test
public void test2() {
    Employee employee = new Employee("Ming");
    Supplier<String> sup1 = () -> employee.getName();
    System.out.println(sup1.get()); // Ming

    Supplier<String> sup2 = employee::getName;
    System.out.println(sup2.get()); // Ming
}
```
<br/>

#### 4-1-2. 情況二: 類 :: 靜態方法
```java
// 情況二: 類 :: 靜態方法
// Comparator       int compare(T t1, T t2)
// Integer          int compare(T t1, T t2)
@Test
public void test3() {
    Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
    int com1Return = com1.compare(1, 2); // -1
    System.out.println(com1Return);

    Comparator<Integer> com2 = Integer::compare;
    int com2Return = com2.compare(2, 1); // 1
    System.out.println(com2Return);
}

// Function     R apply(T t)
// Math         Long round(Double d)
@Test
public void test4() {
    Function<Double, Long> fun1 = d -> Math.round(d);
    System.out.println(fun1.apply(12.3)); // 12

    Function<Double, Long> fun2 = Math::round;
    System.out.println(fun2.apply(12.6)); // 13
}
```
<br/>

#### 4-1-3. 情況三: 類 :: 非靜態方法 (傳入的第一個值為調用對象, 其餘為傳入參數)
```java
// 情況三: 類 :: 非靜態方法 (傳入的第一個值為調用對象, 其餘為傳入參數)
// Comparator       int compare(T t1, T t2)
// String           int t1.compareTo(t2)
@Test
public void test5() {
    Comparator<String> com1 = (t1, t2) -> t1.compareTo(t2);
    System.out.println(com1.compare("a", "b")); // -1

    Comparator<String> com2 = String::compareTo;
    System.out.println(com2.compare("a", "c")); // -2
}

// BiPredicate<String, String>     boolean test(T t1, U t2)
// String           boolean t1.equals(t2)
@Test
public void test6() {
    BiPredicate<String, String> bi1 = (t1, t2) -> t1.equals(t2);
    System.out.println(bi1.test("a", "a")); // true

    BiPredicate<String, String> bi2 = String::equals;
    System.out.println(bi2.test("a", "b")); // false
}

// Function<Employee, String>       R apply(T t)
// Employee                         String getName()
@Test
public void test7() {
    Employee employee = new Employee("Ming");
    Function<Employee, String> fun1 = e -> e.getName();
    System.out.println(fun1.apply(employee)); // Ming

    Function<Employee, String> fun2 = Employee::getName;
    System.out.println(fun2.apply(employee)); // Ming
}
```
<br/>

### 4-2. 構造引用
```java
// 二. 構造引用
// Supplier     T get()
@Test
public void test8() {
    Supplier<Employee> sup1 = () -> new Employee();
    System.out.println(sup1.get());

    Supplier<Employee> sup2 = Employee::new;
    System.out.println(sup2.get());
}

// Function<Employee, String>       R apply(T t)
@Test
public void test9() {
    Function<String, Employee> fun1 = t -> new Employee(t);
    System.out.println(fun1.apply("Ming1")); // Employee{name='Ming1'}

    Function<String, Employee> fun2 = Employee::new;
    System.out.println(fun2.apply("Ming2")); // Employee{name='Ming2'}
}
```
<br/>

### 4-3. 數組引用
```java
// 三. 數組引用
// Function<Integer, String[]>      R apply(T t)
@Test
public void test10() {
    Function<Integer, String[]> fun1 = t -> new String[t];
    String[] fun1Return = fun1.apply(5); // [null, null, null, null, null]
    System.out.println(Arrays.toString(fun1Return));

    Function<Integer, String[]> fun2 = String[]::new;
    String[] fun2Return = fun2.apply(10);
    System.out.println(Arrays.toString(fun2Return)); // [null, null, null, null, null, null, null, null, null, null]
}
```
<br/>

<br/>

#### _END_
