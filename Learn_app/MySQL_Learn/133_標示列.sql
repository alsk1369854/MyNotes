### 133
# 標示列
/*
又名為字增長列
含意: 可以不用手動的插入值, 系統提供默認的序列值

特點:
1. 標示列必須和主鍵搭配嗎? 不一定, 但要求是一個key
2. 一個表可以有幾個標示列? 至多一個!
3. 標示列的類型只能是數值型
4. 標示列可以透過 SET auto_increment_increment = 3; 來設置步長
	可以通過 手動插入值, 設置起始值

*/

# 一. 創建表時設置標示列
DROP TABLE IF EXISTS tab_identity;

CREATE TABLE tab_identity(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20)
);

TRUNCATE TABLE tab_identity;
SELECT * FROM tab_identity;

INSERT INTO tab_identity VALUES(NULL, 'john');
INSERT INTO tab_identity(NAME) VALUES('locy');
INSERT INTO tab_identity VALUES(10, 'ann');

SHOW VARIABLES LIKE '%auto_increment%';
SET auto_increment_increment = 3;

# 二. 修改表時設置標示列
ALTER TABLE tab_identity MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT;

# 三. 修改表時刪除標示列
ALTER TABLE tab_identity MODIFY COLUMN id INT;

DESC tab_identity;
SHOW INDEX FROM tab_identity;

