# Java Stream
```
    1. Stream 關注的是對數據的運算，與CPU打交道
           - 集合關注的是數據存儲，與內存打交道
    2. Stream
        2-1. 自己不會存儲元素
        2-2. 部會改變原對象，相反，他們會返回一個持有結果的新Stream
        2-3. 操作是延遲執行的。 這意味著他們會等到需要結果的時候才執行

    3. Stream 執行流程
        3-1. Stream 的實例化
               3-1-1. 創建 Stream 方式一: 透過Collection
               3-1-2. 創建 Stream 方式二: 透過Arrays
               3-1-3. 創建 Stream 方式三: 透過Stream的 of()
               3-1-4. 創建 Stream 方式四: 創造無限流，透過Stream
        3-2. 一系列中間操作: 中間操作鏈，對數據源的數據進行處理
                3-2-1. 篩選與切片
                        3-2-1-1. filter(Predicate p) - 接收Lambda， 從流中排除某些元素
                        3-2-1-2. limit(n) - 截斷流，使其元素不超過給定的數量
                        3-2-1-3. skip(n) - 跳過元素，返回一個扔掉前 n 個元素的流，若流中元素不足 n 個，則返回一個空stream
                        3-2-1-4. distinct() - 篩選，通過流所生成元素的 hashCode() 和 equals() 去除重複元素
                3-2-2. 映射
                        3-2-2-1. map(Function f) - 接收一個函數作為參數，遍歷每個源素並返回遍歷後的映射 Stream
                        3-2-2-2. flatMap(Function f) - 接收一個函數作為參數，將流每個值都換成另一個流，然後把所有流連接成一個流。
                3-2-3. 排序
                        3-2-3-1. sorted() - 自然排序
                        3-2-3-2. sorted(Comparator com) - 定製排序
        3-3. 終止操作: 一旦執行終止操作，就執行中間操作鏈，並產生結果，之後，不會再被使用
                3-3-1. 匹配與查找
                        3-3-1-1. allMatch(Predicate p) - 檢查是否匹配所有元素
                        3-3-1-2. anyMatch(Predicate p) - 檢查是否至少匹配一個元素
                        3-3-1-3. noneMatch(Predicate p) - 檢查是否沒有匹配元素
                        3-3-1-4. findFirst - 返回第一個於元素
                        3-3-1-5. findAny - 返回當前流中的任意元素
                        3-3-1-6. count - 返回流中元素的總數
                        3-3-1-7. max(Comparator c) - 返回流中最大值
                        3-3-1-8. min(Comparator c) - 返回流中最小值
                        3-3-1-9. forEach(Consumer c) - 內部跌代
                3-3-2. 歸約
                        3-3-2-1. reduce(T identity, BinaryOperator) - 有初始值的，返復結合，得到一個 T
                        3-3-2-1. reduce(BinaryOperator) - 返復結合，得到一個 Optional<T>
                3-3-3. 收集
                        3-3-3-1. collect(Collector c) - 將流轉換為其他形式， List 或 Set
```
<br/>

## Demo class EmployeeData
```java
public class EmployeeData {
    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>(Arrays.asList(
                new Employee("Ming", 24),
                new Employee("han", 20),
                new Employee("jj", 16),
                new Employee("Mark", 30),
                new Employee("jin", 10)
        ));
        return list;
    }
}

class Employee {
    String name;
    int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        return name != null ? name.equals(employee.name) : employee.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}

```
<br/>

## 3-1. Stream 的實例化
### 3-1-1. 創建 Stream 方式一: 透過Collection
```java
    // 3-1-1. 創建 Stream 方式一: 透過Collection
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees(); // [Employee{name='Ming', age=24}, Employee{name='han', age=20}, Employee{name='jj', age=16}, Employee{name='Mark', age=30}, Employee{name='jin', age=10}]

        // Collection   default Stream<E> stream(): 一個順序流(有序的)
        Stream<Employee> stream = employees.stream();
        stream.forEach(System.out::println);
        /* output
            Employee{name='Ming', age=24}
            Employee{name='han', age=20}
            Employee{name='jj', age=16}
            Employee{name='Mark', age=30}
            Employee{name='jin', age=10}
        * */
        System.out.println();

        // Collection   default Stream<E> parallelStream(): 一個並行流(無序的)
        Stream<Employee> employeeStream = employees.parallelStream();
        employeeStream.forEach(System.out::println);
        /* output
            Employee{name='jj', age=16}
            Employee{name='Ming', age=24}
            Employee{name='jin', age=10}
            Employee{name='Mark', age=30}
            Employee{name='han', age=20}
        * */
        System.out.println();
    }
```
<br/>

### 3-1-2. 創建 Stream 方式二: 透過Arrays
```java
    // 3-1-2. 創建 Stream 方式二: 透過Arrays
    @Test
    public void test2() {
        int[] arr = {1, 3, 5, 4, 6};
        // Arrays       static <T> Stream<T> stream(T[] array): 一個順序流(有序的)
        IntStream stream = Arrays.stream(arr);
        stream.forEach(System.out::println);
        /* output
            1
            3
            5
            4
            6
        * */
        System.out.println();

        Employee[] employees = {new Employee("a", 1), new Employee("b", 2), new Employee("c", 3)};
        Stream<Employee> stream1 = Arrays.stream(employees);
        stream1.forEach(System.out::println);
        /* output
            Employee{name='a', age=1}
            Employee{name='b', age=2}
            Employee{name='c', age=3}
        * */
        System.out.println();
    }
```
<br/>

### 3-1-3. 創建 Stream 方式三: 透過Stream的 of()
```java
    // 3-1-3. 創建 Stream 方式三: 透過Stream的 of()
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5, 4);
        integerStream.forEach(System.out::println);
        /* output
            1
            2
            3
            5
            4
        * */
        System.out.println();
    }
```
<br/>

### 3-1-4. 創建 Stream 方式四: 創造無限流，透過Stream
```java
    // 3-1-4. 創建 Stream 方式四: 創造無限流，透過Stream
    @Test
    public void test4() {
        // iterator
        // Stream       public static<T> Stream<T> iterator(final T, seed, final UnaryOperator<T> f)
        Stream.iterate(0, t -> t + 2).limit(3).forEach(System.out::println);
        /* output
            0
            2
            4
        * */
        System.out.println();

        // generate
        // Stream       public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(3).forEach(System.out::println);
        /* output
            0.21521072513109807
            0.6960669415873914
            0.10128325219280565
        * */
    }
```
<br/>


## 3-2. 一系列中間操作: 中間操作鏈，對數據源的數據進行處理
### 3-2-1. 篩選與切片
```java
    // 3-2-1. 篩選與切片
    @Test
    public void test5() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 3-2-1-1. filter(Predicate p) - 接收Lambda， 從流中排除某些元素
        System.out.println("filter(Predicate p)");
        employees.stream().filter(e -> e.age > 15).forEach(System.out::println);
        /* output
            Employee{name='Ming', age=24}
            Employee{name='han', age=20}
            Employee{name='jj', age=16}
            Employee{name='Mark', age=30}
        */
        System.out.println();

        // 3-2-1-2. limit(n) - 截斷流，使其元素不超過給定的數量
        System.out.println("limit(n)");
        employees.stream().limit(2).forEach(System.out::println);
        /* output
            Employee{name='Ming', age=24}
            Employee{name='han', age=20}
        * */
        System.out.println();

        // 3-2-1-3. skip(n) - 跳過元素，返回一個扔掉前 n 個元素的流，若流中元素不足 n 個，則返回一個空stream
        System.out.println("skip(n)");
        employees.stream().skip(2).forEach(System.out::println);
        /* output
            Employee{name='jj', age=16}
            Employee{name='Mark', age=30}
            Employee{name='jin', age=10}
         */
        System.out.println();

        // 3-2-1-4. distinct() - 篩選，通過流所生成元素的 hashCode() 和 equals() 去除重複元素
        System.out.println("distinct()");
        employees.addAll(Arrays.asList(
                new Employee("a", 1),
                new Employee("a", 1),
                new Employee("a", 2)
        ));
        employees.stream().distinct().forEach(System.out::println);
        /* output
            Employee{name='Ming', age=24}
            Employee{name='han', age=20}
            Employee{name='jj', age=16}
            Employee{name='Mark', age=30}
            Employee{name='jin', age=10}
            Employee{name='a', age=1}
            Employee{name='a', age=2}
        * */
    }
```
<br/>


### 3-2-2. 映射
```java
    // 3-2-2. 映射
    @Test
    public void test6() {
        String[] arr = {"aa", "bb", "cc"};

        // 3-2-2-1. map(Function f) - 接收一個函數作為參數，遍歷每個源素並返回遍歷後的映射 Stream
        System.out.println("map(Function f)");
        Arrays.stream(arr).map(s -> s.toUpperCase()).forEach(System.out::println);
        System.out.println();
        // output
        // AA
        // BB
        // CC

        // 3-2-2-2. flatMap(Function f) - 接收一個函數作為參數，將流每個值都換成另一個流，然後把所有流連接成一個流。
        System.out.println("flatMap(Function f)");
        Arrays.stream(arr).flatMap(s -> {
            List<Character> list = new ArrayList<>();
            for (Character c : s.toCharArray()) {
                list.add(c);
            }
            return list.stream();
        }).forEach(System.out::println);
        //output
        // a
        // a
        // b
        // b
        // c
        // c
    }
```
<br/>

### 3-2-3. 排序
```java
    // 3-2-3. 排序
    @Test
    public void test7() {
        // 3-2-3-1. sorted() - 自然排序
        System.out.println("sorted()");
        int[] arr = {1, 5, 8, 4, 9, -78, 123, 5};
        Arrays.stream(arr).sorted().forEach(System.out::println);
        /* output
            -78
            1
            4
            5
            5
            8
            9
            123
        * */
        System.out.println();

        // 3-2-3-2. sorted(Comparator com) - 定製排序
        System.out.println("sorted(Comparator com)");
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((t1, t2) -> -Integer.compare(t1.age, t2.age)).forEach(System.out::println);
        /* output
            Employee{name='Mark', age=30}
            Employee{name='Ming', age=24}
            Employee{name='han', age=20}
            Employee{name='jj', age=16}
            Employee{name='jin', age=10}
        * */
    }
```
<br/>


## 3-3. 終止操作: 一旦執行終止操作，就執行中間操作鏈，並產生結果，之後，不會再被使用

### 3-3-1. 匹配與查找
```java
    // 3-3-1. 匹配與查找
    @Test
    public void test8() {
        List<Employee> employees = EmployeeData.getEmployees();
        // 3-3-1-1. allMatch(Predicate p) - 檢查是否匹配所有元素
        System.out.println("allMatch(Predicate p)");
        boolean t1 = employees.stream().allMatch(t -> t.name.startsWith("M"));
        System.out.println(t1 + "\n"); // false

        // 3-3-1-2. anyMatch(Predicate p) - 檢查是否至少匹配一個元素
        System.out.println("anyMatch(Predicate p)");
        boolean t2 = employees.stream().anyMatch(t -> t.name.startsWith("M"));
        System.out.println(t2 + "\n"); // true

        // 3-3-1-3. noneMatch(Predicate p) - 檢查是否沒有匹配元素
        System.out.println("noneMatch(Predicate p)");
        boolean t3 = employees.stream().noneMatch(t -> t.name.startsWith("M"));
        System.out.println(t3 + "\n"); // false

        // 3-3-1-4. findFirst - 返回第一個於元素
        System.out.println("findFirst()");
        Optional<Employee> t4 = employees.stream().findFirst();
        System.out.println(t4 + "\n"); // Optional[Employee{name='Ming', age=24}]

        // 3-3-1-5. findAny - 返回當前流中的任意元素
        System.out.println("findAny()");
        Optional<Employee> t5 = employees.parallelStream().findAny();
        System.out.println(t5 + "\n"); // Optional[Employee{name='jj', age=16}]

        // 3-3-1-6. count - 返回流中元素的總數
        System.out.println("count()");
        long t6 = employees.parallelStream().count();
        System.out.println(t6 + "\n"); // 5

        // 3-3-1-7. max(Comparator c) - 返回流中最大值
        System.out.println("max(Comparator c)");
        Optional<Employee> t7 = employees.stream().max((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println(t7 + "\n"); // Optional[Employee{name='Mark', age=30}]

        // 3-3-1-8. min(Comparator c) - 返回流中最小值
        System.out.println("min(Comparator c)");
        Optional<Employee> t8 = employees.stream().min((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println(t8 + "\n"); // Optional[Employee{name='jin', age=10}]

        // 3-3-1-9. forEach(Consumer c) - 內部跌代
        System.out.println("forEach(Consumer c)");
        employees.stream().forEach(System.out::println);
        // output
        // Employee{name='Ming', age=24}
        // Employee{name='han', age=20}
        // Employee{name='jj', age=16}
        // Employee{name='Mark', age=30}
        // Employee{name='jin', age=10}
    }
```
<br/>

### 3-3-2. 歸約
```java
    // 3-3-2. 歸約
    @Test
    public void test9() {
        int[] arr = {1, 2, 3, 4, 5};
        // 3-3-2-1. reduce(T identity, BinaryOperator) - 有初始值的，返復結合，得到一個 T
        int t1 = Arrays.stream(arr).reduce(0, Integer::sum);
        System.out.println(t1 + "\n"); // 15

        // 3-3-2-1. reduce(BinaryOperator) - 返復結合，得到一個 T
        OptionalInt t2 = Arrays.stream(arr).reduce(Integer::sum);
        System.out.println(t2 + "\n"); // OptionalInt[15]
    }
```
<br/>

### 3-3-3. 收集
#### 3-3-3-1. collect(Collector c) - 將流轉換為其他形式， List 或 Set
```java
    // 3-3-3. 收集
    @Test
    public void test10(){
        List<Employee> employees = EmployeeData.getEmployees();

        // 3-3-3-1. collect(Collector c) - 將流轉換為其他形式， List 或 Set
        System.out.println("collect(Collector c)");
        System.out.println("to List");
        List<Employee> t1 = employees.stream().filter(e -> e.name.contains("j")).collect(Collectors.toList());
        System.out.println(t1 + "\n"); // [Employee{name='jj', age=16}, Employee{name='jin', age=10}]

        System.out.println("to Set");
        Set<Employee> t2 = employees.stream().filter(e -> e.age > 18).collect(Collectors.toSet());
        System.out.println(t2); // [Employee{name='Mark', age=30}, Employee{name='Ming', age=24}, Employee{name='han', age=20}]
    }
```
<br/>


<br/>

#### _END_