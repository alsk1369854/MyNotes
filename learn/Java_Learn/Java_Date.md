# Java Date

## 常用類: LocalDate, LocalTime, LocalDateTime
```java
LocalDateTime localDateTime = LocalDateTime.now();
System.out.println(localDateTime);//2022-05-04T23:50:33.843
```

### now() 獲取當前的日期, 時間, 日期+時間
+ LocalDate: 2022-05-04
+ LocalTime: 23:50:33.843
+ LocalDateTime: 2022-05-04T23:50:33.843

### of() 設定指定的 年 月 日 時 分 秒
+ localDateTime: 2020-10-06T13:23

### getXxx() 獲取特定時間單位訊息
+ getDayOfMonth():	4
+ getDayOfWeek():		WEDNESDAY
+ getMonth():			MAY
+ getMonthValue():	5
+ getMinute():		50

### withXxx() 設置相關的屬性, 回傳一個設定後的新時間, 原時間不變
+ withDayOfMonth(12) 設定這個月的第幾天
	+ setting localDateTime:	2022-05-04T23:50:33.843
	+ new localDateTime:		2022-05-12T23:50:33.843

+ plusMonths(3) 原基礎上再加幾個月
	+ setting localDateTime:	2022-05-04T23:50:33.843
	+ new localDateTime:		2022-08-04T23:50:33.843

+ minusDays(6) 原基礎上減去幾天
	+ setting localDateTime:	2022-05-04T23:50:33.843
	+ new localDateTime:		2022-04-28T23:50:33.843


<br/>

## Instant(時間戳) (UTS)中央時間
```java
Instant instant = Instant.now();
System.out.println(instant);//2022-05-04T15:50:33.876Z
```
### now() 獲取(UTS)對應的表準時間
+ instant: 2022-05-04T15:50:33.876Z

### atOffset(ZoneOffset.ofHours(8)) 添加時間偏移量(+08:00)
+ UTS:			2022-05-04T15:50:33.876Z
+ UTS(+08:00):	2022-05-04T23:50:33.876+08:00

### toEpochMilli() 獲取自 1970年 1月 1日 0時 0分 0秒(UTC) 開始到擷取時間的毫秒數
+ instant:	2022-05-04T15:50:33.876Z
+ milli:		1651679433876

### Instant.ofEpochMilli(1651676498471L) 通過給定毫秒數, 獲取Instant實例
+ newInstant:	2022-05-04T15:01:38.471Z


<br/>

## DateTimeForMatter 格式化或解析 日期, 時間
```java
// 制定編碼格式
DateTimeForMatter dateFormatter = DateTimeForMatter.ofPattern("yyyy-MM-dd hh:mm:ss");
// 格式化
String str = dateFormatter.format(LocalDateTime.now());
System.out.println(str);//2022-05-04 11:50:33

// 解義
TemporalAccessor accessor = dateFormatter.parse("2022-05-04 11:43:30");
System.out.println(accessor);//{MinuteOfHour=43, HourOfAmPm=11, MilliOfSecond=0, NanoOfSecond=0, SecondOfMinute=30, MicroOfSecond=0},ISO resolved to 2022-05-04
```
### 方式一: 預定義的表準格式 。 如: DateTimeFormatter.ISO_LOCAL_DATE_TIME;
#### ISO_LOCAL_DATE_TIME, ISO_OFFSET_DATE, ISO_LOCAL_TIME
+ format(LocalDateTime.now())
	+ return String:	2022-05-04T23:50:33.885

#### 解析: 字串 --> 日期 
+ dateFormatter.parse("2019-02-18T15:42:18.797")
	+ return TemporalAccessor:	{},ISO resolved to 2019-02-18T15:42:18.797

### 方式二: 本地化相關的格式。 如: ofLocalizedDateTime(FormatStyle.SHORT)
#### 適用於LocalDateTime: FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT
+ format(LocalDateTime.now())
  + return String:	2022/5/4 下午 11:50

#### 適用於LocalDate: FormatStyle.FULL, FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT 
+ ofLocalizedDate(FormatStyle.FULL);
+ format(LocalDate.now())
    + return String:	2022年5月4日 星期三


### 重點: 方式三: 自定義的格式。 如 ofPattern("yyyy-MM-dd hh:mm:ss")
+ 格式化 format(LocalDateTime.now())
	+ return String:	2022-05-04 11:50:33
+ 解析 parse("2022-05-04 11:43:30")
	+ return TemporalAccessor:	{MinuteOfHour=43, HourOfAmPm=11, MilliOfSecond=0, NanoOfSecond=0, SecondOfMinute=30, MicroOfSecond=0},ISO resolved to 2022-05-04

<br/>

## Java Code
```java
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

public class Main {
    public static void main(String[] args) {
        // LocalDate, LocalTime, LocalDateTime
        System.out.println("#LocalDate, LocalTime, LocalDateTime");

        // now() 獲取當前的日期, 時間, 日期+時間
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("\n@ now() 獲取當前的日期, 時間, 日期+時間" +
                "\nLocalDate: " + localDate +
                "\nLocalTime: " + localTime +
                "\nLocalDateTime: " + localDateTime
        );

        // of() 設定指定的 年 月 日 時 分 秒
        localDateTime = LocalDateTime.of(2020, 10, 6, 13, 23);
        System.out.println("\n@ of() 設定指定的 年 月 日 時 分 秒" +
                "\nlocalDateTime: " + localDateTime);

        // getXxx() 獲取特定時間單位訊息
        localDateTime = LocalDateTime.now();
        System.out.println("\n@ getXxx() 獲取特定時間單位訊息" +
                "\ngetDayOfMonth():\t" + localDateTime.getDayOfMonth() +
                "\ngetDayOfWeek():\t\t" + localDateTime.getDayOfWeek() +
                "\ngetMonth():\t\t\t" + localDateTime.getMonth() +
                "\ngetMonthValue():\t" + localDateTime.getMonthValue() +
                "\ngetMinute():\t\t" + localDateTime.getMinute()
        );

        // 體現不可變性
        // withXxx() 設置相關的屬性, 回傳一個設定後的新時間, 原時間不變
        LocalDateTime newLocalDateTime = localDateTime.withDayOfMonth(12);
        System.out.println("\n@ withXxx() 設置相關的屬性, 回傳一個設定後的新時間, 原時間不變" +
                "\nwithDayOfMonth(12) 設定這個月的第幾天" +
                "\n\tsetting localDateTime:\t" + localDateTime +
                "\n\tnew localDateTime:\t\t" + newLocalDateTime
        );
        newLocalDateTime = localDateTime.plusMonths(3);
        System.out.println("\nplusMonths(3) 原基礎上再加幾個月" +
                "\n\tsetting localDateTime:\t" + localDateTime +
                "\n\tnew localDateTime:\t\t" + newLocalDateTime
        );
        newLocalDateTime = localDateTime.minusDays(6);
        System.out.println("\nminusDays(6) 原基礎上減去幾天" +
                "\n\tsetting localDateTime:\t" + localDateTime +
                "\n\tnew localDateTime:\t\t" + newLocalDateTime
        );

        // Instant(時間戳) (UTS)中央時間
        System.out.println("\n\n=================================" +
                "\n# Instant(時間戳) (UTS)中央時間");

        // now() 獲取(UTS)對應的表準時間
        Instant instant = Instant.now();
        System.out.println("\n@ now() 獲取(UTS)對應的表準時間" +
                "\n instant: " + instant
        );

        // atOffset(ZoneOffset.ofHours(8)) 添加時間偏移量(+08:00)
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("\n@ atOffset(ZoneOffset.ofHours(8)) 添加時間偏移量(+08:00)" +
                "\n UTS:\t\t\t" + instant +
                "\n UTS(+08:00):\t" + offsetDateTime
        );

        // toEpochMilli() 獲取自 1970年 1月 1日 0時 0分 0秒(UTC) 開始到擷取時間的毫秒數
        long milli = instant.toEpochMilli();
        System.out.println("\n@ toEpochMilli() 獲取自 1970年 1月 1日 0時 0分 0秒(UTC) 開始到擷取時間的毫秒數" +
                "\n instant:\t" + instant +
                "\n milli:\t\t" + milli
        );

        // ofEpochMilli() 通過給定毫秒數, 獲取Instant實例
        Instant newInstant = Instant.ofEpochMilli(1651676498471L);
        System.out.println("\n@ Instant.ofEpochMilli(1651676498471L) 通過給定毫秒數, 獲取Instant實例" +
                "\n newInstant:\t" + newInstant
        );

        // DateTimeForMatter 格式化或解析 日期, 時間
        System.out.println("\n\n=================================" +
                "\n# DateTimeForMatter 格式化或解析 日期, 時間");
        // 方式一: 預定義的表準格式。 如: ISO_LOCAL_DATE_TIME, ISO_OFFSET_DATE, ISO_LOCAL_TIME
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String str = dateFormatter.format(LocalDateTime.now());
        System.out.println("\n@ 方式一: 預定義的表準格式 。 如: DateTimeFormatter.ISO_LOCAL_DATE_TIME;\nISO_LOCAL_DATE_TIME, ISO_OFFSET_DATE, ISO_LOCAL_TIME" +
                "\nformat(LocalDateTime.now())" +
                "\n\treturn String:\t" + str
        );

        // 解析: 字串 --> 日期 dateFormatter.parse("2019-02-18T15:42:18.797")
        TemporalAccessor parse = dateFormatter.parse("2019-02-18T15:42:18.797");
        System.out.println("\n解析: 字串 --> 日期 \ndateFormatter.parse(\"2019-02-18T15:42:18.797\")" +
                "\n\treturn TemporalAccessor:\t" + parse
        );

        // 方式二: 本地化相關的格式。 如: ofLocalizedDateTime(FormatStyle.SHORT)
        // 適用於LocalDateTime: FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT
        dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        str = dateFormatter.format(LocalDateTime.now());
        System.out.println("\n@ 方式二: 本地化相關的格式。 如: ofLocalizedDateTime(FormatStyle.SHORT)\n適用於LocalDateTime: FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT" +
                "\n format(LocalDateTime.now())" +
                "\n\treturn String:\t" + str
        );

        // 適用於LocalDate: FormatStyle.FULL, FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT
        dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        str = dateFormatter.format(LocalDate.now());
        System.out.println("\nofLocalizedDate(FormatStyle.FULL);\n// 適用於LocalDate: FormatStyle.FULL, FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT" +
                "\n format(LocalDate.now())" +
                "\n\treturn String:\t" + str
        );


        // 重點: 方式三: 自定義的格式。 如 ofPattern("yyyy-MM-dd hh:mm:ss")
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        // 格式化
        str = dateFormatter.format(LocalDateTime.now());
        System.out.println("\n@ 重點: 方式三: 自定義的格式。 如 ofPattern(\"yyyy-MM-dd hh:mm:ss\")" +
                "\n// 格式化 format(LocalDateTime.now())" +
                "\n\treturn String:\t" + str
        );
        // 解析
        TemporalAccessor accessor = dateFormatter.parse("2022-05-04 11:43:30");
        System.out.println("// 解析 parse(\"2022-05-04 11:43:30\")" +
                "\n\treturn TemporalAccessor:\t" + accessor
        );

    }
}
```

<br/>

#### _END_
