# Java I/O 
> ### 字節流(8 bit)
>  + 圖片、影片等等 I/O 處理
> 
> ### 字符流/文本流(16 bit)
>  + 文本 I/O 處理

<br/>

## File
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

## 字節流(8 bit)
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

## 字符流/文本流(16 bit)
```java
static void copyTextFile(String srcPath, String destPath) {
    BufferedReader br = null;
    BufferedWriter bw = null;
    try {
        // 造文件
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        // 造字節流
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