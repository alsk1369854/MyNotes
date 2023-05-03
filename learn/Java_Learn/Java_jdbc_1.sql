USE test;

# 創建 customers 表
CREATE TABLE customers(
	id INT(10),
	NAME VARCHAR(15),
	email VARCHAR(20),
	birth DATE,
	balance INT,
	photo MEDIUMBLOB
)

# 查詢 customers 表
SELECT *
FROM customers;

# 增
INSERT INTO customers(NAME, email, birth) VALUE("test", "test@gmail.com", NOW())

# 改
UPDATE customers SET NAME='test1' WHERE id =1;
UPDATE customers SET balance = balance-100 WHERE id = 1;

# 刪
DELETE FROM customers WHERE id = 3;

# 查
SELECT id,NAME,email,birth FROM customers  WHERE id = 2;

# create good table
CREATE TABLE good(
	NAME VARCHAR(10)
)

# good 總數
SELECT COUNT(*)
FROM good

# 刪除 good 內容
TRUNCATE good;

# 設定事務flag
SET autocommit = FALSE

CREATE USER ming IDENTIFIED BY 'ming'