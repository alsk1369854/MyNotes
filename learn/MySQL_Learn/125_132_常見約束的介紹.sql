
### 125
# 常見約束

/*
含意: 一種限制, 用於限制表中數據, 為了保證表中的數據的準確和可靠性
	
分類: 六大約束
		NOT NULL: 非空, 用於保證該字段的值不能為空
			比如姓名、學號
		DEFAULT: 默認, 用於保證該字段有默認值
			比如說性別
		PRIMARY KEY: 主鍵, 用於保證該字段的值具有唯一性, 並且非空
			比如學號, 員工編號
		UNIQUE: 唯一,　用於保證該字段的值具有唯一性, 可以為空
			比如說座位號
		CHECK: 檢查約束【MySQL中不支持】
			比如年齡, 性別
			
添加約束時機:
	1. 創建表時
	2. 修改表時
	
約束的添加分類:
	列級約束
		六大約束語法上都支持, 但外鍵約束沒有效果
		可以在單列添加多個約束		
			stuName VARCHAR(20) NOT NULL UNIQUE, #非空且唯一
		
	表級約束
		除了非空、默認, 其他的都支持
		
### 128
主鍵和唯一鍵的大對比:
	
		保證唯一性	是否允許為空	一個表中可以有多少個	是否允許組合
	主鍵	O		X			至多1個		O, 但不推薦
	唯一	O		O			可以有多個	O, 但不推薦	
	
	select * from major;
	select * from stuinfo;
	insert into major values(1, 'java'), (2, 'h5');
	delete from stuinfo;
	insert into stuinfo values
	(1, 'john', '男', null, 19, 1),
	(1, 'ann', '男', null, 19, 2);	

### 129
外鍵:
	1. 要求在從表設置外鍵關係
	2. 從表的外鍵列的類型和主表的關聯列類型要求一致或兼容, 名稱無要求
	3. 主表關聯必須是一個key (一般是 主鍵primay key 或 唯一unique)
	4. 插入數據時, 先插入主表, 在插入從表
		刪除表時, 先刪除從表, 在刪除主表

*/

CREATE TABLE 表名(
	字段名 字段類型 列級約束,
	字段名 字段類型,
	表級約束
)

### 126
# 一. 創鍵表時添加約束

# 1.添加列級約束
/*
語法:
	直接在字段和類別後面追加 約束類型即可
	
	只支持: 默認、非空、主鍵、唯一
*/
CREATE DATABASE IF NOT EXISTS students;

USE students;

CREATE TABLE stuinfo(
	id INT PRIMARY KEY, #主鍵
	stuName VARCHAR(20) NOT NULL UNIQUE, #非空且唯一
	gender CHAR(1) CHECK(gender='男' OR gender='女'), #檢查
	seat INT UNIQUE, #唯一
	age INT DEFAULT 18, #默認約束
	major INT REFERENCES major(id) #外鍵
)

CREATE  TABLE major(
	id INT PRIMARY KEY,
	majorName VARCHAR(20)
)

DESC stuinfo;

# 查看stuinfo表中所有的索引, 包括主鍵、外鍵、唯一
SHOW INDEX FROM stuinfo;


### 127
# 2. 添加表級約束
/*
語法: 在各個字段的最下面
【constraint 約束名】 約束類型(字段名)

*/

DROP TABLE IF EXISTS stuinfo;
CREATE TABLE IF NOT EXISTS stuinfo(
	id INT,
	stuName VARCHAR(20),
	gender CHAR(1),
	seat INT,
	age INT,
	majorId INT,
	
	CONSTRAINT pk PRIMARY KEY(id), #主鍵
	CONSTRAINT uq UNIQUE(seat), #唯一鍵
	CONSTRAINT ck CHECK(gender='男' OR gender='女'), #檢查
	CONSTRAINT fk_stuinfo_major FOREIGN KEY(majorId) REFERENCES major(id) #外鍵
) 

SHOW INDEX FROM stuinfo;


DROP TABLE IF EXISTS stuinfo;
CREATE TABLE IF NOT EXISTS stuinfo(
	id INT,
	stuName VARCHAR(20),
	gender CHAR(1),
	seat INT,
	age INT,
	majorId INT,
	
	PRIMARY KEY(id, stuName), #組合主鍵
	UNIQUE(seat), #唯一鍵
	CHECK(gender='男' OR gender='女'), #檢查
	FOREIGN KEY(majorId) REFERENCES major(id) #外鍵
) 

SHOW INDEX FROM stuinfo;

# 通用寫法:
CREATE TABLE IF NOT EXISTS stuinfo(
	id INT PRIMARY KEY, #主鍵
	stuName VARCHAR(20) NOT NULL, #非空
	gender CHAR(1) CHECK(gender='男' OR gender='女'), #檢查
	seat INT UNIQUE, #唯一
	age INT DEFAULT 18, #預設
	majorId INT,
	CONSTRAINT fk_stuinfo_major FOREIGN KEY(majorId) REFERENCES major(id)
)

### 130
# 二. 修改表時添加約束
/*
1. 添加列級約束
alter table 表名 modify column 字段名 字段類型 新約束;

2. 添加表級約束
alter table 表名 add 【constraint 約束名】 約束類型(字段名);

*/
DROP TABLE IF EXISTS stuinfo;
CREATE TABLE IF NOT EXISTS stuinfo(
	id INT,
	stuName VARCHAR(20),
	gender CHAR(1),
	seat INT,
	age INT,
	majorId INT
);
DESC stuinfo;
SHOW INDEX FROM stuinfo;

# 1.添加分非空約束
ALTER TABLE stuinfo MODIFY COLUMN stuName VARCHAR(20) NOT NULL;

# 2.添加默認約束
ALTER TABLE stuinfo MODIFY COLUMN age INT DEFAULT 18;

# 3.添加主鍵
#	1.列級
ALTER TABLE stuinfo MODIFY COLUMN id INT PRIMARY KEY;
#	2.表集
ALTER TABLE stuinfo ADD CONSTRAINT pk PRIMARY KEY(id);

# 4.添加唯一
#	1.列級
ALTER TABLE stuinfo MODIFY COLUMN seat INT UNIQUE;
#	2.表級
ALTER TABLE stuinfo ADD UNIQUE(seat);

# 5.添加外鍵
ALTER TABLE stuinfo ADD CONSTRAINT fk_stuinfo_major FOREIGN KEY(majorId) REFERENCES major(id);

### 131
# 三. 修改表時刪除約束

# 1.刪除非空約束
ALTER TABLE stuinfo MODIFY COLUMN stuName VARCHAR(20) NULL;

# 2.刪除默認約束
ALTER TABLE stuinfo MODIFY COLUMN age INT;

# 3.刪除主鍵
ALTER TABLE stuinfo DROP PRIMARY KEY;

# 4.刪除唯一
ALTER TABLE stuinfo DROP INDEX seat;

# 5.刪除外鍵
ALTER TABLE stuinfo DROP FOREIGN KEY fk_stuinfo_major;

DESC stuinfo;
SHOW INDEX FROM stuinfo;



