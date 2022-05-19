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

# 查看數據庫的隔離級別
SELECT @@transaction_isolation;

# REPEATABLE-READ / READ-COMMITTED
# 設定當前 mySQL 連接的隔離級別 為 READ COMMITTED
SET transaction_isolation='READ-COMMITTED';

# 設定數據庫系統全局的隔離級別 READ COMMITTED
SET global transaction_isolation='READ-COMMITTED';

# 補充
# 創建mysql數據用戶
CREATE USER 'ming'@'localhost' IDENTIFIED BY 'ming';

# 授予權限
# 授予通過網路方式登入的 ming 用戶，對所有庫所有表的全部權限
GRANT ALL PRIVILEGES ON *.* TO 'ming'@'%';
# 給ming用戶使用本地命令行方式，授予 test庫下的所有表的數據CRUD操作權限
GRANT SELECT,INSERT,DELETE,UPDATE ON test.* TO 'ming'@'localhost';


