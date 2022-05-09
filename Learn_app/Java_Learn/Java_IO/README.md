# Java I/O 
> ### 字節流(8 bit)
>  + 圖片、影片等等 I/O 處理
> 
> ### 字符流/文本流(16 bit)
>  + 文本 I/O 處理
>
> ### 字符編碼的轉換
>
> ### 物件的序列化
>  + 物件的寫出
>  + 物件的讀入


<p align="center">
<img height="500px" src="/Learn_app/Java_Learn/Java_IO/Java_IO_體系圖.png"/>
</p>

<br/>

> ## File
```java
// 相對路徑: 根目錄與src平行
String relative = "File.txt"

// 絕對路徑
String absolute = "C:/Dir/File.txt"

// 目錄分隔 Windows:"\\" or "/" ; Linux:"/"
File.separator; // Windows: "\\" ; Linux:"/"

// 更改位置/重命名
new File("file.txt").renameTo("dir/fileMove.txt")
```


<br/>

> ## 字節流(8 bit) : 圖片、影片等等 I/O 處理
```java
static void copyNotTextFile(String srcPath, String destPath) {
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
        // 目錄分隔符 windows:\\ ; Linux:/
        System.out.println("separator: " + File.separator);
        // 造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        // 造字節流
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        // 造緩衝流
        bis = new BufferedInputStream(fis);
        bos = new BufferedOutputStream(fos);

        // 複製文件
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
            // flush: 刷新緩衝區(提早緩衝區的寫入，一般不使用滿了會自動調用)
            // bos.flush();
        }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 關閉流(處理流會自動關閉內層流)
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bos != null) {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

<br/>

> ## 字符流/文本流(16 bit) : 文本 I/O 處理
```java
static void copyTextFile(String srcPath, String destPath) {
    BufferedReader br = null;
    BufferedWriter bw = null;
    try {
        // 造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        // 造字符流
        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(destFile); // 直接覆蓋寫入
        // FileWriter fw = new FileWriter(destFile, true); // 從文本最末端開始寫入
        // 造流
        br = new BufferedReader(fr);
        bw = new BufferedWriter(fw);

        // 複製文件
        // 方法一:
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1) {
//                bw.write(cbuf, 0, len);
////                 flush: 刷新緩衝區(提早緩衝區的寫入，一般不使用滿了會自動調用)
//                bw.flush();
//            }

        // 方法二:
        String data;
        while ((data = br.readLine()) != null) {
            bw.write(data + "\n"); // readLine()方法不包含換行符
            // flush: 刷新緩衝區(提早緩衝區的寫入，一般不使用滿了會自動調用)
            // bw.flush();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 關閉流(處理流會自動關閉內層流)
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

<br/>

> ## 字符編碼的轉換

<p align="center">
<img height="500px" src="/Learn_app/Java_Learn/Java_IO/編碼的轉換.png"/>
</p>

```java
// 範例: 使用"utf-8"讀取; 使用"gbk"寫入
static void inputStreamReaderAndOutputStreamWriter(String srcPath, String destPath) {
    InputStreamReader isr = null;
    OutputStreamWriter isw = null;
    try {
        // 造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        // 造字節流
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile); // 直接覆蓋寫入
        // 造處理流
        isr = new InputStreamReader(fis, "utf-8"); // 第二參數沒給: 默認"utf-8"
        isw = new OutputStreamWriter(fos, "gbk"); // 採用 "gbk" 字符集編碼輸出

        char[] cbuf = new char[1024];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            isw.write(cbuf, 0, len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (isr != null) {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (isw != null) {
            try {
                isw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

<br/>

> ## 物件的序列化

### 物件需要滿足如下要求，方可序列化

> 1. 需要實現接口: Serializable
> 2. 當前類提供一個全局長量: serialVersionUID
> 3. 除了當前類需要實現 Serializable 接口之外，還必須保證其內部所有屬性也必須是可序列化的(默認情況下，基本數據類型可以序列化)
> 補充: ObjectOutputStream 和 ObjectInputStream 不能序列化 static 和 transient 修飾的成員變量


### Demo 用物件
+ class Account
+ class Person

```java
// 使物件可序列化: 實現 Serializable 並提供一個全局長量 serialVersionUID
static class Account implements java.io.Serializable {
    private static final long serialVersionUID = 123123123L;
    int id;

    public Account(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }
}

// 使物件可序列化: 實現 Serializable 並提供一個全局長量 serialVersionUID
static class Person implements java.io.Serializable {
    private static final long serialVersionUID = 321321321L;
    String name;
    int age;
    transient boolean isMale; // transient 修飾數據將部會序列化(讀出來的會是預設值false)
    Account account;

    public Person(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public Person(String name, int age, boolean isMale, Account account) {
        this(name, age, isMale);
        this.account = account;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                ", account=" + account +
                '}';
    }
}
```

<br/>

### 物件的寫出
```java
// String destPath = "data.dat";
static void demoObjectOutputStream(String destPath) {
    // destPath = "data.dat";
    ObjectOutputStream oos = null;
    try {
        // 造流
        oos = new ObjectOutputStream(new FileOutputStream(destPath));
        // 寫入物件
        oos.writeObject(new String("A String Object")); // 將物件讀入緩衝區
        oos.flush(); // 刷新緩衝區，將物件寫入
        oos.writeObject(new Person("張三", 18, true));
        oos.flush();
        oos.writeObject(new Person("李四", 23, true, new Account(123)));
        oos.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 關閉資源
        if (oos != null) {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

<br/>

### 物件的讀入
```java
// String srcPath = data.dat
static void demoObjectInputStream(String srcPath) {
    ObjectInputStream ois = null;
    try {
        // 造流
        ois = new ObjectInputStream(new FileInputStream(srcPath));
        // 讀取物件數據
        Object obj = ois.readObject();
        String str = (String) obj;
        Person person1 = (Person) ois.readObject();
        Person person2 = (Person) ois.readObject();
        // 打印讀取物件
        System.out.println(str);
        System.out.println(person1);
        System.out.println(person2);
        // Output:
        // A String Object
        // Person{name='張三', age=18, isMale=false, account=null}
        // Person{name='李四', age=23, isMale=false, account=Account{id=123}}
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        // 關閉資源
        if (ois != null) {
            try {
                ois.close();
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