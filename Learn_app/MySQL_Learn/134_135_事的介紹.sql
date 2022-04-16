### 134
# TCL
/*
Transaction Control Language 事務控制語言

事務:
一個或一組SQL語句組成一個執行單元, 這個執行單元要馬全部執行, 要馬全部不執行

案例: 轉帳
張三豐	1000
郭襄	1000

update 表 set 張三豐的餘額=500 where name='張三豐'
意外
update 表 set 郭襄的餘額=1500 where name='郭襄'

事務的特性:
ACID
原子性(Atomicity): 一個事務不可再分割, 要馬都執行要馬都不執行
一致性(Consistency): 一個事務執行會使數據從一個一致狀態切換到另一個一致狀態
隔離性(Isolation): 一個事務的執行不受其他事務的干擾
持久性(Durability): 一個事務一旦提交, 則會永久的改變數據庫的數據



### 135
事務的創建
	隱式事務: 事務沒有明顯的開啟和結束的標記
	比如: insert, update, delete語句
	
delete from 表 where id =1;

	顯示事務: 事務具有明顯的開啟和結束標記
	前提: 必須先設定自動提交功能為禁用
	set autocommit = 0;
	
步驟1: 開啟事務
set autocommit=0;
start transaction; 【可選的】
步驟2: 編寫事務中的SQL語句(select insert update delete)
語句1;
語句2;
...

步驟3: 結束事務
commit; 提交事務
rollback; 回滾事務

updata 表 set 張三的餘額=500 where name='張三'
意外
updata 表 set 李四的餘額=1500 where name='李四'

*/
SHOW VARIABLES LIKE 'autocommit'
SHOW ENGINES


# 創建測試用表
DROP TABLE id EXISTS acc;

CREATE TABLE acc(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_name VARCHAR(20),
	balance DOUBLE
);
INSERT INTO acc(user_name, balance)
VALUES('張三', 1000), ('李四', 1000);

SELECT * FROM acc;

# 開啟事務
SET autocommit = 0;
START TRANSACTION;
# 編寫一組事務的語句
UPDATE acc SET balance =1000 WHERE user_name = '張三';
UPDATE acc SET balance =1000 WHERE user_name = '李四';
# 結束事務
COMMIT;
# 回滾事務
# rollback;

