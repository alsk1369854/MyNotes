##複習
###一. 數據庫好處:
	1. 可持久化數據到本地
	2. 結構化查詢

###二. 數據結構常見觀念
	1. DB: 數據庫, 存儲數據的容器
	2. DBMS: 數據庫管理系統, 又稱為數據庫軟件或數據庫產品, 用於創造或管理DB
	3. SQL: 結構化查詢語言, 用於和數據庫通信的語言, 不是某個數據庫軟件持有的, 而是幾乎所有的主流數據庫軟件通用的語言

###三. 數據庫存儲數據的特點
	1. 數據庫放到表中, 然後表再放到庫中
	2. 一個庫可以有多張表, 每張表具有唯一表名用來標示自己
	3. 表中有一個或多個列, 列又稱為"字段"或"屬性", 相當於java中的"屬性"
	4. 表中每一行數據, 相當於java中"對象"

###四. 常見的數據庫\管理系統
	MySQL
	Oracle
	db2
	SQLserver

##MySQL 介紹
	一. 背景
		前身屬於瑞典一家公司, MySQL AB
		08年 被 sun公司收購
		09年 sun 被 oracle收購	

	二. 優點
		1. 開源, 免費, 成本低
		2. 性能高, 移植性也好
		3. 體積小, 便於安裝

	三. 安裝
		屬於c/s架構軟件, 一般來講安裝服務端
		企業版
		社區版
		



##命令集
###(cmd)
 
	1. 訪問 MySQL
	mysql 【-h 主機名 -P 端口號】-r 用戶名 -p密碼
	
	2. 退出 MySQL
	exit

	3. 開啟 MySQL 庫
	net start 庫名

    4. 關閉 MySQL 庫
	net stop 庫名

	5. 查看 MySQL 版本
	mysql --version

###(MySql)

	1. 查看當前所有數據庫
	show databases;

	2. 查開指定庫
	use 庫名;

	3. 查看當前庫所有列表
	show tables;

	4. 查看其他庫所有列表
	select table from 庫名;

	5. 創建新表
	create table 表名(
	
	  列名 列類型,
	  列名 列類型,
	     ...
	);

	6. 查看表結構
	desc 表名;

	7. 查看當前版本
	select version();

## 進階1: 基礎查詢
	/*
	語法:
	select 查詢列表 from 表名;
	
	類似於: System.out.println(打印東西);
	
	1. 查詢列表可以是: 表中字段、 常量值、 表達式、 函數
	2. 查詢結果是一個虛擬的表格
	*/
	
	打開資料庫:
	USE myemployees;

###1. 查詢表中 單個 字段
	SELECT last_name FROM employess;

###2. 查詢表中 多個 字段
	SELECT last_name,salary,email FROM employees;

###3. 差詢表中 所有 字段
	SELECT * FROM employees;

###4. 查詢常量值
	SELECT 100;
	SELECT 'john';

###5. 查詢表達式
	SELECT 100%98;

###6. 差詢函數
	SELECT VERSION();

###7. 起別名
	/*
	1. 便於理解
	2. 如果要查詢的字段有重名的清況, 使用別名可區分開來
	*/

	方式一: 使用AS
	SELECT 100%98 AS 結果;
	SELECT last_name AS 姓, first_name AS 名 FROM employees;

	方式二: 使用空格
	SELECT last_name 姓, first_name 名 FROM employees;

	案例: 差詢salary, 顯示結果為 out put
	SELECT salary AS "out put" FROM employees;

###8 去重
	
	案例: 查詢員工表中涉及到所有的部門編號
	SELECT DISTINCT department_id FROM employees;

###9. +號的作用
	/*
	java 中的+號:
	1. 運算符: 兩個操作數都為數值型
	2. 連接符: 只要有一個操作數為字符串

	MySQL 中的+號:
	僅僅只有一個功能:運算符

	select 100+90; 兩個操作數都為數直型, 則作加法運算
	select '123'+90; 其中一方為字符型, 試圖將字符型數值轉換成數值型
							如果轉換成功, 則繼續作加法預算
	select 'john'+90;		如果轉換失敗, 則將字符行數值轉換成0
	
	select null+10; 只要其中一方為null, 則結果肯定為null
	*/
	
	案例: 查詢員工名和姓連接成一個字段. 並顯示為 姓名
	SELECT CONCAT('a','b','c') AS 結果;
	SELECT CONCAT(last_name,first_name) AS 姓名
	FROM employees;
	
###10. IFNULL()
	SELECT IFNULL(commission_pct, 0) AS 獎金
	FROM employees;
	
## 進階2: 條件查詢

	語法:
		SELECT 查詢列表
		FROM 表名
		WHERE 篩選條件;	
	
	分類:
		一. 按 條件表達式 篩選
		條件運算符: > < = != <> >= <=
		
		二. 按 邏輯表達式 篩選
		邏輯運算符:
				&& || !
				and or not
		
		&&和and: 兩個條件都為true, 結果為true, 反之為false
		||或or: 只要有一條件為true, 結果為true, 反之為false
		!或not: 如果連結的條件本身為false, 結果為true, 反之為false

		三. 模糊查詢
				like
				between and
				in
				is null

	一. 按條件表達式篩選
	案例1: 查詢工資>12000
		SELECT *
		FROM employees
		WHERE salary>12000;
	
	案例2: 查詢部門編號不等於90號的員工名和部門編號
		SELECT 
				last_name,
				department_id
		FROM
				employees
		WHERE
				department_id <> 90

	二. 按邏輯表達式篩選
	案例1: 查詢工資z在10000到20000之間的員工名, 工資以及獎金
		SELECT
				last_name,
				salary,
				commission_pct
		FROM
				employees
		WHERE
				salary >= 10000 AND salary <= 20000
		
	案例2: 查詢部門編號不是在90到110之間, 或者工資高於15000的員工訊息
		SELECT
				*
		FROM 
				employees
		WHERE
				department_id < 90 or department > 110 or salary > 15000

### 1. NOT(): 不再此條件內為true
	
	案例: 查詢部門編號不是在90到110之間, 或者工資高於15000的員工訊息
		SELECT
				*
		FROM 
				employees
		WHERE
				NOT(department_id >= 90 and department <= 110) or salary > 15000

### 2. 模糊查詢
	1.like
	特點:
	一般和通配符搭配使用
		通配符:
		% 任意多個字符,包含0個字符
		_ 任意單個字符
	2.betwwen and
	3.in
	4.is null | in not null

###2-1. like
	案例1: 查詢員工名中包含字符a的員工訊息
	SELECT 
			*
	FROM 
			employees
	WHERE
			last_name LIKE '%a%';
	
	案例2: 查詢員工名中第三個字符為n,第五個字符為l的員工名和工資
	SELECT
			last_name, salary
	FROM
			employees
	WHERE
			last_name LIKE '__n_l%';

	案例3: 查詢員工名中第二個字符為_的員工名
	SELECT
			last_name
	FROM
			employees
	WHERE
			last_name LIKE '_$_%' ESCAPE '$';

###2-2. between and()
	1.使用包含臨界值

	案例1: 查詢員工編號在100到120之間的員工訊息
	SELECT 
			*
	FROM 
			employees
	WHERE 
			employees_id >= 100 AND employees_id <= 120;	
	----------------------------------
	SELECT 
			*
	FROM 
			employees
	WHERE 
			employees_id BETWEEN 100 AND 120;

###2-3. in()
	1.判斷某字段的值是否屬於in列表中的某一項
	
	案例: 查詢員工的工種編號 IT_PROG, AD_VP, AD_PRES 中的一個員工名和工種編號
	SELECT 
			last_name, job_id
	FROM
			employees
	WHERE
			job_id IN('IT_PROG', 'AD_VP', 'ADPRES');

###2-4. is null / is not null
	1.	=或<>不能用於判斷null值
		is null 或 is not null 可以判斷null值
	
	案例1: 查詢沒有獎金的員工名和獎金率
	SELECT
			last_name, commission_pct
	FROM
			employees
	WHERE
			commission_pct IS NULL;

	
	案例2: 查詢有獎金的員工名和獎金率
	SELECT
			last_name, commission_pct
	FROM
			employees
	WHERE
			commission_pct IS NOT NULL;

###安全等於 <=>
	案例1: 查詢沒有獎金的員工名和獎金率
	SELECT
			last_name, commission_pct
	FROM
			employees
	WHERE
			commission_pct <=> NULL;

	案例2: 查詢工資為12000的員工訊息
	SELECT
			*
	FROM
			employees
	WHERE
			salary <=> 12000;

##進階3: 排序查詢
	引入:
		SELECT * FROM employees
	
	語法:
		SELECT 	查詢列表
		FROM 	表
		【WHERE 	篩選條件】
		ORDER BY 排序列表 【ASC|DESC】;

	特點:
		1.	默認:  升序(由小到大)
			ASC:  升序(由小到大)
			DESC: 降序(由大到小)
		2.	ORDER BY 子句可以支持單個字段, 多個字段, 表達式, 函數, 別名
		3.	ORDER BY 子句一般是放在查詢語句的最後面, limit子句除外

	案例1: 查詢員工訊息, 要求工資從高到低排序
		SELECT
				*
		FROM
				employees
		ORDER BY 
				salary DESC;
	
	案例2: 查詢部門編號 >=90 的員工訊息, 按入職時間先後進行排序【添加篩選條件】
		SELECT		*
		FROM   		employees
		WHERE  		department_id >= 90
		ORDER BY 	hiredate ASC;

	案例3: 按年薪的高低顯示員工的信息和年薪【按表達式排序】
		SELECT	*, salary*12*(1+IFNULL(commission_pct,0)) AS 年薪
		FROM	employees
		ORDER BY salary*12*(1+IFNULL(commission_pct,0)) DESC;

	案例4: 按年薪的高低顯示員工的信息和年薪【按別名排序】
		SELECT	*, salary*12*(1+IFNULL(commission_pct,0)) AS 年薪
		FROM	employees
		ORDER BY 年薪 DESC;

	案例5: 按姓名的長度顯示員工姓名和工資【按函數排序】
		SELECT		CONCAT(last_name, first_name) AS 姓名, salary
		FROM		employees
		ORDER BY	LENGTH(CONCAR(last_name, first_name)) ASC;

	案例6: 查詢員工訊息, 要求先按工資升序, 在按員工編號降序【按多個字段排序】
		SELECT		*
		FROM		employees
		ORDER BY	salary ASC, employees_id DESC;

##進階3: 排序練習
#####1. 查詢員工的姓名和部門編號和年薪, 按年薪降序, 按姓名升序
	SELECT
			last_name, department_id, salary*12*(1+IFNULL(commission_pct,0)) AS 年薪
	FROM
			employees
	ORDER BY
			年薪 DESC, last_name ASC;

#####2. 選擇工資不在8000到17000的員工姓名和工資, 按工資降序	
	SELECT
			last_name, salary
	FROM
			employees
	WHERE
			salary NOT BETWEEN 8000 AND 17000
	ORDER BY
			salary DESC;

#####3. 查詢郵箱中包含e的員工訊息, 並先按郵箱的字節數降序, 在案部門編號升序
	SELECT
			*
	FROM
			employees
	WHERE
			email LIKE '%e%'
	ORDER BY
			LENGTH(email) DESC, department_id ASC;

##進階4: 常見函數
	概念: 類似java中的method, 將一組邏輯語句封裝在方法中, 對外暴露方法名

	好處:
			1.隱藏了現實細節
		 	2.提高代碼的重用性

	調用: DELECT 函數名(實參列表) 【FROM 表】

	特點:
			1. 叫什麼 (函數名)
			2. 幹什麼 (函數功能)
	
	分類
			1. 單行函數 例如: CONCAT, LENGTH, IFNULL等
			2. 分組函數
  			功能: 做統計使用, 又稱為統計函數, 聚合函數, 組函數
	
###一. 字符函數
		1. LENGTH: 獲取參數值的字節個數
		SELECT LENGTH('john');
		SELECT LENGTH('張三豐hahaha');
		
		SHOW VARIABLES LIKE '%char%'

		2. CONCAT 拼接字符串
		SELECT CONCAT(last_name, '_', first_name) AS 姓名 FROM employees;

		3. UPPER, LOWER
		SELECT UPPER('john');
		SELECT LOWER('joHn');
		
		示例: 將姓邊大寫, 名變小寫, 然後拼接
		SELECT 
				CONCAT(UPPER(last_name), '_', LOWER(first_name)) AS 姓名 
		FROM 
				employees;

		4. SUBSTR, SUBSTRING
		注意: 索引從1開始
		#擷取從指定索引處後面所有字符
		SELECT SUBSTR('李莫愁愛上了陸展元',7) AS out_put;
		
		#擷取從指定索引處指定字符的長度
		SELECT SUBSTR('李莫愁愛上陸展元',1,3) AS　out_put;
	 	
		案例: 姓名中首字符大寫, 其他字符小寫然後用_拼接, 顯示出來
		SELECT 
				CONCAT(UPPER(SUBSTR(last_name,1,1)), '_', LOWER(CONCAT(SUBSTR(last_name,2), first_name))) AS out_put
		FROM 
				employees;

		5. INSTR 返回字串第一次出現的索引, 如果找不到返回0
		SELECT 
				INSTR('楊不梅愛上了殷六俠','殷六俠') AS out_put;
			
		6. trim
		#去空白
		SELECT TRIM('   張翠山    ') AS out_put;
		
		#去掉字符'a'
		SELECT TRIM('a' FROM 'aaaaaaaaaaaaaaaa張aaa翠山aaaaaaaaaaa') AS out_put;

		7. lpad 用指定的字符實現左填充指定長度
		SELECT LPAD('張翠山', 2, '*') AS out_put;

		8. rpad 用指定的字符實現右填充指定長度
		SELECT RPAD('張翠山', 10, 'ab') AS out_put;

		9. replace 替換
		SELECT REPLACE('周芷若周芷若張無忌愛上周芷若', '周芷若', '趙敏') AS out_put;

###二. 數學函數
		1. round: 四捨五入
		SELECT ROUND(1.65);
		SELECT ROUND(1.567, 2);

		2. ceil: 向上取整, 返回>=該參數的最小整數 (無條件捨去)
		SELECT CEIL(1.02);

		3. floor: 向下取者, 返回<=該參數的最大整數 (無條件進位)
		SELECT FLOOR(-9.99);

		4. truncate: 截斷(到小數點後第n位)
		SELECT TRUNCATE(1.69999, 1);

		5. mod: 取餘數
		# mod(a,b): a-a/b*b
		SELECT MOD(-10,3);
		SELECT 10%3;

###三. 日期函數
		1. now: 返回當前系統日期+時間
		SELECT NOW();

		2. curDate(): 返回當前系統日期, 不包含時間
		SELECT CURDATE();

		3. curTime(): 返回當前時間, 不包含日期
		SELECT CURTIME();

		4. Year(date): 可獲取指定的部分, 年, 月, 日, 時, 分, 秒
		SELECT YEAR(NOW()) AS 年;
		SELECT YEAR("1996-08-06") AS 年; 
		SELECT YEAR(hiredate) AS "Year" FROM employees;

		SELECT MONTH(NOW()) AS "月";
		SELECT MONTHNAME(NOW()) AS "月";

		SELECT DAY(NOW()) AS "日";
		SELECT HOUR(NOW()) AS "時";
		SELECT MINUTE(NOW()) AS "分";
		SELECT SECOND(NOW()) AS "秒";

		5. str_to_date(): 將字符通過指定的格式轉換成日期
		SELECT STR_TO_DATE("1996-08-06", "%Y-%c-%d");
		// 查詢日職日期為1992-4-3的員工訊息
		SELECT * FROM employees WHERE hiredate = "1992-4-3";
		SELECT * FROM employees WHERE hiredate = STR_TO_DATE("4-3 1992", "%c-%d %Y");

		6. date_format(): 將日期轉換成字符
		SELECT DATE_FORMAT(NOW(), "%y年%m月%d日") AS "output";
		// 查詢有獎金的員工名和入職日期(XX月/XX日/ XX年)
		SELECT 
				last_name, DATE_FORMAT(hiredate, "%m月/%d日/ %y年") AS "入職日期"
		FROM 
				employees
		WHERE 
				commission_pct IS NOT NULL;

		7. DateDIFF(datw1, date2): 計算日期間差距的天數 前-後
		SELECT DATEDIFF(MAX(hiredate), MIN(hiredate)) AS "differenct" FROM employees; 


###四. 其他函數
		SELECT VERSION();
		SELECT DATABASE();
		SELECT USER();

###五. 流程控制函數
		1. if函數: if else 的效果
		SELECT IF(10>6, "true", "false");

		SELECT 
			last_name, commission_pct, IF(commission_pct IS NULL, "沒獎金", "有獎金") AS "備註"
		FROM
			employees;
		
		2. case函數的使用一: switch case 的效果
		/*
		case 要判斷的字段或表達式
		when 常量1 then 要顯示的值1或語句1;
		when 常量2 then 要顯示的值2或語句2;
		...
		else 要顯示的值n或語句n;
		end
		*/	

		案例: 查詢員工的工資, 要求
			部門=30, 顯示的工資為1.1倍
			部門=40, 顯示的工資為1.2倍
			部門=50, 顯示的工資為1.3倍
			其他部門, 顯示的工資為員工資

		SELECT salary AS "原始工資", department_id,
		CASE department_id
		WHEN 30 THEN salary*1.1
		WHEN 40 THEN salary*1.2
		WHEN 50 THEN salary*1.3
		ELSE salary
		END AS "新工資"
		FROM employees;
		
		3. case 函數的使用二: 類似於多重if
		/*
		case
		when 條件1 then 要顯示的值1或語句1;
		when 條件2 then 要顯示的值2或語句2;
		...
		else 要顯示的值n或語句n;
		end		
		*/
		
		案例: 查詢員工的工資的情況
			如果工資>20000, 顯示A級別
			如果工資>15000, 顯示B級別
			如果工資>10000, 顯示C級別
			否則, 顯示D級別

		SELECT salary,
		CASE
		WHEN salary>20000 THEN "A"
		WHEN salary>15000 THEN "B"
		WHEN salary>10000 THEN "C"
		ELSE "D"
		END AS "工資級別"
		FROM employees;			

### 分組函數
		/*
		功能: 用作統計使用, 又稱為聚合函數或統計函數或組函數

		分類:
		sum 求和, avg 平均值, max 最大值, min 最小值, count 計算個數

		特點:
		1. sum, avg 一般用於處理數值型
		2. max, min, count 可以處理任何類型
		3. 以上分組函數都忽略NULL值
		4. 可以和distinct搭配實現去重的運算
		5. 一般使用count(*)用作統計行數
		6. 和分組函數一同查詢的字段要求是 group by 後的字段
		*/
		
		1. 簡單 的使用
		SELECT SUM(salary) FROM employees;
		SELECT AVG(salary) FROM employees;
		SELECT MIN(salary) FROM employees;
		SELECT MAX(salary) FROM employees;
		SELECT COUNT(salary) FROM employees;

		2. 是否忽略null
		// SUM(), AVG()
		SELECT SUM(commission_pct), AVG(commission_pct), SUM(commission_pct)/35, SUM(commission_pct)/107 FROM employees;

		// MAX(), MIN()
		SELECT MAX(commission_pct), MIN(commission_pct) FROM(employees);

		// COUNT()	
		SELECT COUNT(commission_pct) FROM employees;

		3. 和distinct搭配
		SELECT SUM(DISTINCT(salary)), SUM(salary) FROM employees;
		SELECT COUNT(DISTINCT(salary)), COUNT(salary) FROM employees;

		4. count函數的詳細介紹
		// 獲取資料行數 **兩者解果一樣**
		SELECT COUNT(*) FROM employees;
		SELECT COUNT(1) FROM employees;

		效率:
		MYISAM 儲存引擎下, count(*)的效率高
		INNOBD 儲存引擎下, count(*)和count(1)的效率差不多, 嚴格上count(字段)效率高一些	

		5. 和分組函數一同查詢的字段有限制
		SELECT AVG(salary), employee_id FROM employees;

## 進階5: 分組查詢
		/*
		語法:
			select 分組函數, 列 (要求出現在 group by 的後面)
			from 表
			【where 篩選條件】
			group by 分組的列表
			【order by 子句】
		注意:
			查詢列表必須特殊, 要求是分組函數和 group by 後面出現的字段

		特點:
			1. 分組查詢中的篩選條件分為兩類
							數據源				位置						關鍵字
			分組前篩選		原始表				group by 子句的前面		where
			分組後篩選		分組後的結果集		group by 子句的後面		having

			# 分組函數做為條件肯定是放在having子句中
			# 能用分組前篩選的, 就優先考慮使用分組前篩選
			2.	group by 子句支持單個字段分組, 多個字段分組(多個字段之間用逗號隔開沒有順序要求), 表達式或函數(用的較少)
			3.	也可以添加排序(排序放在整個分組查詢的最後)
		*/

		## 簡單的		
		案例1: 查詢每個工種的最高工資
			SELECT MAX(salary), job_id
			FROM employees
			GROUP BY job_id;

		案例2: 查詢每個位置上的部門個數
			SELECT COUNT(*), location_id
			FROM departments
			GROUP BY location_id;

		## 添加篩選條件
		案例1: 查詢油箱中包含a字符的, 每個部門的平均工資
			SELECT AVG(salary), department_id
			FROM employees
			WHERE email LIKE '%a%'
			GROUP BY department_id;

		案例2: 查詢有獎金的每個領導手下員工的最高工資
			SELECT AVG(salary), department_id
			FROM employees
			WHERE email LIKE '%a%'
			GROUP BY department_id;

		## 添加分組後篩選條件: having
		案例1: 查詢哪個部門的員工個數>2
			SELECT COUNT(*), department_id
			FROM employees
			GROUP BY department_id
			HAVING COUNT(employee_id)>2;

		案例2: 查詢每個工種有獎金的員工的最高工資>12000的工種編號和最高工資
			SELECT MAX(salary), job_id
			FROM employees
			WHERE commission_pct IS NOT NULL 
			GROUP BY job_id
			HAVING MAX(salary)>12000;

		案例3: 查詢領導編號>102的每個領導手下的最低工資>5000的領導編號是哪個, 以及其最低工資
			SELECT manager_id, MIN(salary)
			FROM employees
			WHERE manager_id>102
			GROUP BY manager_id
			HAVING MIN(salary)>5000;

		## 按表達式或函數分組
		案例1: 按員工姓名長度分組, 查詢每一組員工個數, 篩選員工個數>5的有那些
			SELECT COUNT(*) AS "c", LENGTH(last_name) AS "len_name"
			FROM employees
			GROUP BY len_name
			HAVING c>5;

		## 按多個字段分組
		案例: 查詢每個部門每個工種的員工的平均工資
			SELECT AVG(salary), department_id, job_id
			FROM employees
			GROUP BY department_id, job_id;
		
		## 添加排序
		案例: 查詢每個部門每個工種的員工的平均工資, 並且按平均工資的高低顯示	
			SELECT AVG(salary) AS "a", department_id, job_id
			FROM employees
			WHERE department_id IS NOT NULL
			GROUP BY department_id, job_id
			HAVING a>10000
			ORDER BY a DESC;

## 進階6: 連接查詢
		/*
		含意:
			又稱多表查詢, 當查詢的字段來自於多個表時, 就會用到連接查詢
		
		笛卡爾乘積現象: 
			表1 有m行, 表2 有n行, 結果=m*n行
			發生原因: 沒有有效的連結條件
			如何避免: 添加有效的連結條件
		
		分類:
			按年代分類:
				sq192標準: 僅僅支持內連結
					1. 等值連接:
						1. 多表等值連結的結果為多表的交集部分
						2. n表連結, 至少需要n-1個連結條件
						3. 多表的順序沒有要求
						4. 一般需要為表起別名
						5. 可以搭配前面介紹的所有子句使用, 比如排序, 分組, 篩選
				sq199標準【推薦】: 支持內連結+外連結(左外和右外)+交叉連結
			按功能分類:
				內連結:
					等值連接
					非等值連結
					自連結
				外連結:
					左外連結
					右外連結
					全外連結
				交叉連結:
		*/
		#1. 等值連接:
		案例1: 查詢女神名和對應的男神名		
			SELECT NAME, boyName 
			FROM boys, beauty
			WHERE beauty.boyfriend_id = boys.id;

		案例2: 查詢員工名和對應的部門名
			SELECT last_name, department_name
			FROM employees, departments
			WHERE employees.department_id = departments.department_id;
		
		2. 為表起別名
		/*
		1. 提高語句的簡潔度
		2. 區分多個重名的字段
		
		注意:
			如果為表起了別名, 則查詢的字段就不能使用原來的表名去限定
		*/
		# 查詢員工名,　工種號, 工種名	
			SELECT last_name, e.job_id, job_title
			FROM employees AS e, jobs AS j
			WHERE e.job_id = j.job_id;
		
		4. 可以加篩選?
		案例: 查詢有獎金的員工名, 部門名
			SELECT last_name, department_name, commission_pct
			FROM employees AS e, departments AS d
			WHERE e.commission_pct IS NOT NULL 
			AND d.department_id = e.department_id;

		案例2: 查詢城市名中第二個字符為o的部門名和城市名
			SELECT d.department_name, l.city
			FROM locations AS l, departments AS d
			WHERE l.location_id = d.location_id
			AND l.city LIKE "_o%";
		
		5. 可以加分組?
		案例1: 查詢每個城市的部門個數
			SELECT l.city, COUNT(*)
			FROM locations AS l, departments AS d
			WHERE l.location_id = d.location_id
			GROUP BY l.city; 

		案例2:  查詢有獎金的每個部門的部門名和部門的領導編號和該部門的最低工資
			SELECT d.department_name, d.manager_id, MIN(e.salary)
			FROM departments AS d, employees AS e
			WHERE d.department_id = e.department_id
			AND commission_pct IS NOT NULL
			GROUP BY d.department_id, d.manager_id;

		6. 可以加排序?
		案例: 查詢每個工種的工種名和員工的個數, 並且按員工數降序
			SELECT j.job_title, COUNT(*)
			FROM jobs AS j, employees AS e
			WHERE j.job_id = e.job_id
			GROUP BY job_title
			ORDER BY COUNT(*) DESC;

		7. 可以實現三表連結?
		案例: 查詢員工名, 部門名和所在的城市
			SELECT e.last_name, d.department_name, l.city
			FROM employees AS e, departments AS d, locations AS l
			WHERE e.department_id = d.department_id
			AND d.location_id = l.location_id;
		
		#2. 非等值連結
		案例1: 查詢員工的工資和工資級別
			SELECT e.last_name, e.salary, jg.grade_level
			FROM employees AS e, job_grades AS jg
			WHERE e.salary BETWEEN jg.lowest_sal AND highest_sat; 

		#3. 自連接
		案例1: 查詢員工名和上級名稱
			SELECT e.last_name, e.employee_id, m.last_name, m.employee_id
			FROM employees AS e, employees AS m
			WHERE e.manager_id = m.employee_id;

		二. sql99 語法
		/*
		語法:
			select 查詢列表 
			from 表1 別名 【連接類型】
			join 表2 別名 
			no 連接條件
			【where 篩選條件】
			【group by 分組】
			【having 篩選條件】
			【order by 排序條件】

		內連結: (*): inner
			等值連接
			非等值連結
			自連結
		外連結:
			左外連結 (*): left 【outer】
			右外連結 (*): right 【outer】
			全外連結 full: 【outer】
		交叉連結: cross
		*/ 

		一. 內連接
		/*
		語法:
			select 查詢列表
			from 表1 別名
			inner join 表2 別名
			on 連接條件;
	
		分類:
			等值
			非等值
			自連接

		特點:
			1. 添加排序, 分組, 篩選
			2. inner 可以省略
			3. 篩選條件放在where後面, 連接條件放在on後面, 提高分離性, 便於閱讀
			4. inner join 連結和sql192語法中等值連結效果是一樣的, 都是查詢多表的交集
		*/

		1. 等值連接
		案例1: 查詢員工名, 部門名
			SELECT last_name, department_name
			FROM employees AS e
			INNER JOIN departments AS d
			ON e.department_id = d.department_id;
		
		案例2: 查詢名字中包含e的員工名和工種名(添加篩選) 
			SELECT last_name, department_name
			FROM employees AS e
			INNER JOIN departments AS d
			ON e.department_id = d.department_id;
		
		案例3: 查詢部門個數>3 的城市名和部門個數, (添加分組+篩選)
			SELECT city, COUNT(*)
			FROM departments AS d
			INNER JOIN locations AS l
			ON d.location_id = l.location_id
			GROUP BY l.city
			HAVING COUNT(*) > 3;

		案例4: 查詢哪個部門的部門員工個數>3的部門名和員工個數, 並按個數降續 (添加排序)
			SELECT d.department_name, COUNT(*)
			FROM departments AS d
			INNER JOIN employees AS e
			ON d.department_id = e.department_id
			GROUP BY d.department_id
			HAVING COUNT(*) > 3
			ORDER BY COUNT(*) DESC;

		案例5: 查詢員工名, 部門名, 工種名, 並按部門名降序 (添加三表連接)
			SELECT e.last_name, d.department_name, j.job_title
			FROM employees AS e
			INNER JOIN departments AS d ON e.department_id = d.department_id
			INNER JOIN jobs AS j ON e.job_id = j.job_id
			ORDER BY d.department_name DESC;

		二. 非等值接
		案例1: 查詢員工的工資級別			
			SELECT e.last_name, jg.grade_level
			FROM employees AS e
			INNER JOIN job_grades AS jg ON e.salary BETWEEN jg.lowest_sal AND highest_sat; 
		
		案例2: 查詢工資級別的個數>20的個數, 並且按工資級別降序
			SELECT e.last_name, jg.grade_level, COUNT(*)
			FROM employees AS e
			INNER JOIN job_grades AS jg ON e.salary BETWEEN jg.lowest_sal AND highest_sat 
			GROUP BY jg.grade_level
			HAVING COUNT(*) > 20
			ORDER BY jg.grade_level DESC;

		三. 自連接
		案例1: 查詢員工名和上級名
			SELECT e.last_name, m.last_name
			FROM employees AS e
			INNER JOIN employees AS m ON e.manager_id = m.employee_id;

		案例2: 查詢姓名中包含字符k的員工名和上級名
			SELECT e.last_name, m.last_name
			FROM employees AS e
			INNER JOIN employees AS m ON e.manager_id = m.employee_id
			WHERE e.last_name LIKE "%k%";
		
		1. 外連接
		/*
		應用場景: 用於查詢一個表中有, 另一個表沒有的紀錄
		
		特點:
			1. 外連接的查詢結果為主表中的所有紀錄
				如果子表中有和他匹配的,　則顯示匹配的值
				如果子表中沒有和他匹配的,　則顯示null
			2. 左外連接, left join 左邊的是主表
			   右外連接, right join 右邊的是主表
			3. 左外和右外交換兩個表的順序, 可以實現同樣的效果 
			4. 全外連結 = 內連接的結果 + 表1中有但表2沒有的 + 表2中有但表1沒有的
		*/

		引入: 查詢男朋友 不在男神表的女神名

			SELECT * FROM beauty;
			SELECT * FROM boys;
			
			#左外連接
				SELECT b.name, bo.*
				FROM beauty AS b
				LEFT OUTER JOIN boys AS bo ON b.boyfriend_id = bo.id
				WHERE bo.id IS NULL;

			#右外連接
				SELECT b.name, bo.*
				FROM boys AS bo
				RIGHT OUTER JOIN beauty AS b ON b.boyfriend_id = bo.id
				WHERE bo.id IS NULL;

		案例1: 哪個部門沒有員工
		#左外
			SELECT d.*, e.employee_id
			FROM departments AS d
			LEFT OUTER JOIN employees AS e ON d.department_id = e.department_id
			WHERE e.employee_id IS NULL;	

		#全外(MYSQL 不能用)
			SELECT b.*, bo.*
			FROM beauty AS b
			FULL OUTER JOIN boys AS bo ON b.boyfriend_id = bo.id;

		#交叉連接(笛卡爾乘積)			
			SELECT b.*, bo.*
			FROM beauty AS b
			CROSS JOIN boys AS bo;

## 進階7: 子查詢
	/*
	含意:
		出現在其他語句中的select語句, 稱為子查詢或內查詢
		外部的查詢語句, 稱為主查詢或外查詢
	
	分類:
		按子查詢出現的位置:
			select 後面:
				僅支持標量子查詢
			from 後面:
				支持表子查詢
			where 或 having 後面:
				標量子查詢 (單行)
				列子查詢 (多列)

				行子查詢
			
			exists 後面 (相關子查詢):
				表子查詢

	按結果集的行列數不同:
		標量子查詢 (結果集只有一行一列)
		列子查詢 (解果集只有一列多行)
		行子查詢 (解果集有一行多列)
		表子查詢 (解果集一般為多行多列)	
	*/

	一. where或having後面
		1. 標量子查詢 (單行子查詢)
		2. 列子查詢 (多行子查詢)
		
		3. 行子查詢 (多列多行) 

		特點:
			1. 子查詢方在小括號內
			2. 子查詢一般放在條件的右側
			3. 標量子查詢, 一般搭配著單行操做符使用
				> < >= <= = <>
			
			列子查詢, 一般搭配多行操作符使用
				in, any/some, all

			4. 子查詢的執行優先於主查詢執行, 主查詢的條件用到了子查詢的結果
		
		一, 標量子查詢
		
			案例1: 誰的工資比 Abel 高?
				
				1. 查詢 Abel 的工資
					SELECT last_name, salary
					FROM employees
					WHERE last_name LIKE("Abel");
				2. 查詢員工訊息, 滿足 salary > *1.* 解果
					SELECT last_name, salary
					FROM employees
					WHERE salary > (
						SELECT salary
						FROM employees
						WHERE last_name LIKE("Abel")
					);

			案例2: 返回 job_id 與 141 號員工相同, salary 比 143 號員工多的員工 姓名, job_id, 和工資
				SELECT last_name, job_id, salary
				FROM employees
				WHERE job_id = (	
					# 查詢141號員工的job_id
					SELECT job_id
					FROM employees
					WHERE employee_id = 141
				) 
				AND salary > (
					# 查詢143號員工的salary
					SELECT salary
					FROM employees
					WHERE employee_id = 143
				);

			案例3: 返回公司工資最少的員工的last_name, job_id, 和 salary		
				SELECT last_name, job_id, salary
				FROM employees
				WHERE salary = (
					# 查詢公司 最低工資 
					SELECT MIN(salary)
					FROM employees
				)

			案例4: 查詢最低工資大於50號部門最低工資的部門id和其最低工資
				SELECT department_id, MIN(salary)
				FROM employees
				GROUP BY department_id
				HAVING MIN(salary) > (
					# 查詢50號部門的最低工資
					SELECT MIN(salary)
					FROM employees
					WHERE department_id = 50
				)

			# 非法使用				
				SELECT department_id, MIN(salary)
				FROM employees
				GROUP BY department_id
				HAVING MIN(salary) > (
					# 用了行子查詢 有多個值 > 不可做多值判斷 
					SELECT salary
					FROM employees
					WHERE department_id = 50
				)

		二, 列子查詢
			案例1: 返回location_id 是 1400或1700的部門中的所有員工姓名
				SELECT last_name, location_id
				FROM employees AS e
				INNER JOIN departments AS d
				ON d.department_id = e.department_id
				WHERE d.department_id IN (
					# 查詢 location_id 是 1400 或 1700 的部門編號
					SELECT DISTINCT department_id
					FROM departments
					WHERE location_id IN (1400, 1700)
				)
		
			案例2: 返回其他工種中比 job_id 為 "IT_PROG" 工種任意工資低的員工的員工號, 姓名, job_id 以及 salary						
				SELECT employee_id, last_name, job_id, salary
				FROM employees
				WHERE job_id != "IT_PROG"
				AND salary < ANY(
					# 查詢 job_id 為 "IT_PROG" 部門的工資
					SELECT DISTINCT salary
					FROM employees
					WHERE job_id = "IT_PROG"
				)

			案例3: 返回其他工種中比 job_id 為 "IT_PROG" 工種任意工資低的員工的員工號, 姓名, job_id 以及 salary		
				SELECT employee_id, last_name, job_id, salary
				FROM employees
				WHERE job_id != "IT_PROG"
				AND salary < ALL(
					# 查詢 job_id 為 "IT_PROG" 部門的工資
					SELECT DISTINCT salary
					FROM employees
					WHERE job_id = "IT_PROG"
				)
			
		三, 行子查詢
			案例1: 查詢員工編號最小並且工資最高的員工訊息
				SELECT * 
				FROM employees
				WHERE (employee_id, salary) = (
					SELECT  MIN(employee_id), MAX(salary)
					FROM employees
				)

	二. select後面
		案例1: 查詢每個部門的員工個數
			SELECT d.*,(
				SELECT COUNT(*)
				FROM employees AS e
				WHERE e.department_id = d.department_id
			) AS countEmployees 
			FROM departments AS d

		案例2: 查詢員工號 = 102的部門名		
			SELECT (
				SELECT d.department_name
				FROM departments AS d
				WHERE d.department_id = e.department_id
			) AS "department"
			FROM employees AS e
			WHERE e.employee_id = 102

			#或
			SELECT e.employee_id, e.department_id, d.department_name
			FROM employees AS e
			INNER JOIN departments AS d
			ON d.department_id = e.department_id
			WHERE e.employee_id = 102;

	三. from 後面
		案例1: 查詢每個部門的平均工資的工資等級
			SELECT jg.grade_level, ag_dep.ag, ag_dep.department_id
			FROM(
				# 查詢每個部門平均工資
				SELECT AVG(salary) AS ag, department_id
				FROM employees 
				GROUP BY department_id
			) AS ag_dep
			INNER JOIN job_grades AS jg
			ON ag_dep.ag BETWEEN jg.lowest_sal AND jg.highest_sat;\

	四. exists 後面 (相關子句)
		/*
		語法:
			exists(完成的查詢語句)
			SELECT EXISTS(SELECT employee_id FROM employees)
		結果:
			1 或 0
		*/
		
		案例1: 查詢員工名和部門名
			#exists			
			SELECT d.department_name
			FROM departments AS d
			WHERE EXISTS(
				SELECT *
				FROM employees AS e
				WHERE e.department_id = d.department_id
			)

			#in
			SELECT department_name
			FROM departments AS d
			WHERE department_id IN(
				SELECT DISTINCT department_id
				FROM employees AS e
			)

		案例2: 查詢沒有女朋友的男神訊息
			#exists
			SELECT bo.*
			FROM boys AS bo
			WHERE NOT EXISTS (
				SELECT b.boyfriend_id
				FROM beauty AS b
				WHERE bo.id = b.boyfriend_id
			)

			#in
			SELECT bo.*
			FROM boys AS bo
			WHERE bo.id NOT IN (
				SELECT b.boyfriend_id
				FROM beauty AS b
			)

##進階8: 分頁查詢
	/*
	應用場景: 當要顯示的數據, 一頁顯示不全, 需要分頁提交sql請求

	語法:
		select 查詢列表
		from 表
		【join type join 表2
		on 連接條件
		where 篩選條件
		group by 分組字段
		having 分組後的篩選
		order by 排序字段】
		limit 【offset】, size;

		offset: 要顯示條目的起始索引(起始索引從0開始)
		size 要顯示的條目個數
	
	特點:
		1. limit 語句 放在查詢語句的最後
		2. 公式
			要顯示的頁數 page, 每頁的條目數size
			select 查詢列表
			from 表
			limit (page-1)*size, size

			size = 10
			page	index
			1		0
			2		10
			3		20
	*/
	案例1: 查詢前5條員工訊息
		SELECT *
		FROM employees
		LIMIT 0,5

		# 如果起始索引為0可以省略
		SELECT *
		FROM employees
		LIMIT 5

	案例2: 查詢第11條到第25條
		SELECT *
		FROM employees
		LIMIT 10, 15
	
	案例3: 有獎金的員工訊息, 並且工資較高的前10名顯示出來
		SELECT *
		FROM employees
		WHERE commission_pct IS NOT NULL
		ORDER BY salary DESC
		LIMIT 10

## 進階9: 聯合查詢
	/*
	union 聯合 合併: 
		將多條查詢語句的解果合併成一個結果
	
	語法:
		查詢語句1
		union
		查詢語句2
		union
		...

	應用場景:
		要查詢的解果來自於多個表,　且多個表沒有直接的連接關係, 但查詢的訊息一致時

	特點:
		1. 要求多條查詢語句的查詢列數是一致的
		2. 要求多條查詢語句的查詢的每一列的類型和順序最好是一致
		3. union關鍵字默認去重, 如果使用union all 可以包含重複項
	*/
	
	#引入的案例: 查詢部門號 > 90 或 郵箱包含 a 的員工訊息SELECT *
		# 一般用法
		FROM employees
		WHERE department_id > 90
		OR email LIKE "%a%"
		
		# union 用法
		SELECT *
		FROM employees
		WHERE department_id > 90
		UNION
		SELECT *
		FROM employees
		WHERE email LIKE "%a%"

	案例1: 查詢中國用戶中男性的訊息及外國用戶中年男性的用戶訊息
		SELECT id, cname, csex 
		FROM t_ca
		WHERE csex = "男"
		UNION ALL
		SELECT t_id, tName, tGender
		FROM t_ua
		WHERE tGender = "male"

### DML 語言
	/*
	數據操作語言:
		插入: insert
		修改: update
		刪除: delete
	*/
	#一. 插入語句
		/*
		語法:
			insert into 表名(列名,...) values(值1,...);
		*/

		# 1. 插入的值的類型要與列的類型一致或兼容
			INSERT INTO beauty(id, NAME, sex, borndate, phone, photo, boyfriend_id)
			VALUES (13, '唐藝昕', '女', '1990-4-23', '1898888888', NULL, 2)
		
		# 2. 不可以為null的列必須插入值。 可以為null的列如何插入值?
			方式一:
			INSERT INTO beauty(id, NAME, sex, borndate, phone, photo, boyfriend_id)
			VALUES (13, '唐藝昕', '女', '1990-4-23', '1898888888', NULL, 2)
			
			放式二:
			INSERT INTO beauty(id, NAME, sex, borndate, phone, boyfriend_id)
			VALUES (14, '金星', '女', '1990-4-23', '1388888888', 9)

		# 3. 列的順序是否可以調換
			INSERT INTO beauty (NAME, sex, id, phone)
			VALUES ('蔣欣', '女', 16, '110')

		# 4. 列數和值的個數必須一致
			INSERT INTO beauty (NAME, sex, id, phone)
			VALUES ('關曉彤', '女', 17, '110')

		# 5. 可以省略列名, 默認所有列, 而且列的順序和表中列順序一致
			INSERT INTO beauty
			VALUES (18, '張飛', '男', NULL, '119', NULL, NULL)

		#方式二
		/*
		語法:
			insert into 表名
			set 列名 = 值, 列名 = 值,...
		*/
		
		INSERT INTO beauty
		SET id=19, NAME='劉濤', phone='999'
		
		# 兩種方式大pk
		1. 方式一支持插入多行, 方式二不支持
			INSERT INTO beauty
			VALUES(23, '唐藝昕1', '女', '1990-4-23', '1898888888', NULL, 2),
			(24, '唐藝昕2', '女', '1990-4-23', '1898888888', NULL, 2),
			(25, '唐藝昕3', '女', '1990-4-23', '1898888888', NULL, 2)

		2. 方式一支持子查詢, 方式二不支持			
			INSERT INTO beauty(id,NAME,phone)
			SELECT id,boyName,'11809866'
			FROM boys
			WHERE id<3

	#二. 修改語句
	/*
	1. 修改單表的紀錄
		語法:
			update 表名
			set 列=新值, 列=新值,...
			where 篩選條件
	2. 修改多表紀錄【補充】
	*/
	
	1. 修改單表的紀錄
	案例1: 修改beauty表中姓唐的女神的電話為1399888899;
		UPDATE beauty
		SET phone = '1399888899'
		WHERE NAME LIKE '唐%'

	案例2: 修改boys表中id號為2的名稱為張飛, 魅力值為 10		
		UPDATE boys
		SET boyName = '張飛', userCP = 10
		WHERE id = 2

	#三. 刪除語句
	/*
	方式一: delete
	語法:
		1. 單表的刪除【*】
			delete from 表名 where 篩選條件
		2. 多表的刪除【補充】
	
			sql92語法:
				delete 表1的別名, 表2的別名
				from 表1 別名, 表2 別名
				where 連接條件
				and 篩選條件;
		
			sql99語法:
				delete 表1的別名, 表2的別名
				from 表1 別名
				inner|left|right join 表2 別名 on 連接條件
				where 篩選條件;

	方式二: truncate
	語法: truncate table 表名;
	*/

	方式一: delete
	1.單表的刪除
	案例: 刪除手機號碼以9結尾的女神訊息
		DELETE FROM beauty
		WHERE phone LIKE '%9';

	2.多表的刪除
	案例: 刪除張無忌的女朋友的訊息
		DELETE b
		FROM beauty AS b
		INNER JOIN boys AS bo ON b.boyfriend_id = bo.id
		WHERE bo.boyName = '张无忌';

	案例: 刪除黃曉明的信息以及他女友的信息
		DELETE b, bo
		FROM beauty AS b
		INNER JOIN boys AS bo ON b.boyfriend_id = bo.id
		WHERE bo.boyName = '黄晓明';

	方式二: truncate語句
	案例: 將魅力值>100的男神訊息刪除
		TRUNCATE TABLE boys;

	delete v.s truncate
	/*
	1. delete 可以加where 條件, truncate 不能加
	2. truncate 刪除, 效率高一點
	3. 假如要刪除的表中有自增長列,
	   如果用delete 刪除後, 在插入數據, 自增長列的值從斷點開始, 
	   而truncate 刪除後, 在插入數據, 自增長列從1開始。
	4. truncate 刪除沒有返回值, delete 刪除有返回值
	5. truncate 刪除不能回滾, delete 刪除可以回滾
	*/
	
	【案例講解】
	1. 創建以下表
		CREATE TABLE my_employees(
			id INT(10),
			First_name VARCHAR(10),
			last_name VARCHAR(10),
			userid VARCHAR(10),
			salary DOUBLE(10,2)
		)
		CREATE TABLE users(
			id INT,
			userid VARCHAR(10),
			department_id INT
		)

	2. 顯示表的數據結構		
		DESC my_employees;
		DESC users;	

	3. 向 my_employees 表插入數據
		INSERT INTO my_employees
		VALUES(1, 'Patel', 'Ralph', 'Rpatel', 895),
		(2, 'Dancs', 'Betty', 'Bdancs', 860),
		(3, 'Biri', 'Ben', 'Bbiri', 1100),
		(4, 'Newman', 'Chad', 'Cnewman', 750),
		(5, 'Ropeburn', 'Audrey', 'Aropebur', 1550);
	
	4. 向 users 表插入數據
		INSERT INTO users
		VALUES(1, 'Rpatel', 10),
		(2, 'Bdancs', 10),
		(3, 'Bbiri', 20),
		(4, 'Cenwman', 30),
		(5, 'Aropebru', 40);

	5. 將 3號 員工的 last_name 修改為 'drelxer'
		UPDATE my_employees
		SET last_name = 'drelxer'
		WHERE id = 3;
	
	6. 將所有工資少於900的員工的工資修改為1000
		UPDATE my_employees
		SET salary = 1000
		WHERE salary < 900;

	7. 將userid 為Bbiri的user表和my_employees表的紀錄全部刪除
		DELETE u,me
		FROM users AS u
		JOIN my_employees AS me ON u.userid = me.userid
		WHERE u.userid = 'Bbiri';

	8. 刪除所有數據
		DELETE FROM my_employees;
		DELETE FROM users;

	9. 檢查所做修正
		SELECT * FROM my_employees;
		SELECT * FROM users;	

	10. 清空 my_employees 表
		TRUNCATE my_employees;

	#DDL
	/*
	數據定義語言
	
	庫和表的管理
	
	一. 庫的管理
	創建, 修改, 刪除
	二. 表的管理
	創建, 修改, 刪除

	創建: create
	修改: alter
	刪除: drop
	*/

	一. 庫的管理
	1. 庫的創建
		語法: 
			create database [if not exists] 庫名;
		
		案例: 創建庫Books					
			CREATE DATABASE IF NOT EXISTS Books

	2. 庫的修改
		1. 改庫名: 
		step1 關閉mysql server
		step2 進入庫檔案位置 C:\ProgramData\MySQL\MySQL Server 8.0\Data 
		step3 改名後重啟server

		2. 更改文字編碼
			ALTER DATABASE books CHARACTER SET utf8mb4

	3. 庫的刪除
		DROP DATABASE IF EXISTS books

	二. 表的管理
	1. 表的創建
	/*
	語法:
	create table 表名(
		列名 列的類型【(長度) 約束】,
		列名 列的類型【(長度) 約束】,
		列名 列的類型【(長度) 約束】,
		...
		列名 列的類型【(長度) 約束】,
	)
	*/
	
	案例: 創建表book
		CREATE TABLE book(
			id INT, #編號
			bName VARCHAR(20), #書名
			price DOUBLE, #價格
			authorId INT, #作者編號
			publishDate DATETIME #出版日期
		)		
		CREATE TABLE author(
			id INT,
			au_name VARCHAR(20),
			nation VARCHAR(10)
		)

	2. 表的修改
	/*
	alter table 表名 add|drop|modity|change column 列名 【列類型 列約束】;
	*/
		1. 修改列名
			ALTER TABLE book CHANGE COLUMN publishDate pubDate DATETIME
 
		2. 修改列的類型或約束
			ALTER TABLE book MODIFY COLUMN pubDate TIMESTAMP;

		3. 添加列
			ALTER TABLE book ADD COLUMN annual DOUBLE;

		4. 刪除列			
			ALTER TABLE book DROP COLUMN annual;
			
		5. 修改表名
			ALTER TABLE author RENAME TO book_author;

	3. 表的刪除
		DROP TABLE IF EXISTS book_author;
		
		通常使用寫法:	
			DROP DATABASE IF EXISTS 舊庫名;
			CREATE DATABASE 新庫名;
			
			DROP TABLE IF EXISTS 舊表名;
			CREATE TABLE 表名();
	
	4. 表的複製
		/*

		DROP TABLE IF EXISTS author;
		CREATE TABLE author(
			id INT,
			au_name VARCHAR(20),
			nation VARCHAR(10)
		);

		INSERT INTO author VALUES
		(1, '村上春樹', '日本'),
		(2, '莫言', '中國'),
		(3, '冯唐', '中國'),
		(4, '金庸', '中國');

		*/

		1. 僅複製表的結構
			CREATE TABLE copy LIKE author;
		
		2. 複製表的結構外加數據
			CREATE TABLE copy2 
			SELECT * FROM author;

		3. 只複製部分數據
			CREATE TABLE copy3
			SELECT id, au_name
			FROM author
			WHERE nation = '中國';

		4. 僅複製某些字段		
			CREATE TABLE copy4
			SELECT id, au_name
			FROM author
			WHERE 0;
				
	# 案例講解
		1. 創建表 dept1
			/*
				Name	Null?	Type
				id				int(7)
				name			varchar(25)
			*/

			USE test;
			CREATE TABLE dept1(
				id INT(7),
				NAME VARCHAR(25)
			);
			DESC dept1;

		2. 將departments中的數據插入新表dept2中
			CREATE TABLE dept2
			SELECT department_id, department_name
			FROM myemployees.departments;

		3. 創建表 emp5
			/*
				Name	Null?	Type
				id				int(7)
				First_name		varchar(25)
				Last_name		varchar(25)
				Dept_id			int(7)	
			*/
		
			CREATE TABLE emp5(
				id INT(7),
				First_name VARCHAR(25),
				Last_name VARCHAR(25),
				Dept_id INT(7)
			);
			DESC emp5;

		4. 將 Last_name 的長度增加到50
			ALTER TABLE emp5 MODIFY COLUMN last_name VARCHAR(50); 

		5. 根據表 employees 創建 employees2
			CREATE TABLE employees2 LIKE myemployees.employees;

		6. 刪除表 emp5
			DROP TABLE IF EXISTS emp5;

		7. 將表 employees2 重命名為 emp5
			ALTER TABLE employees2 RENAME TO emp5;

		8. 在表 dept 和 emp5 中添加新列 test_column, 並檢查所有的操作
			ALTER TABLE emp5 ADD COLUMN test_column INT;

		9. 直接刪除表 emp5 中的列 test_column
			ALTER TABLE emp5 DROP COLUMN test_column;

### 常見數據類型 
	/*
	數值型: 
		整數
		小數:
			定點數
			浮點數

	字符型:
		較短的文本: char, varchar
		較長的文本: text, blob(較長的二進制數據)

	日期型:
	*/

	一. 整形
		/*
		分類:
			tinyint, smallint, mediumint, int/integer, bigint
			1			2		3			4			8
		
		特點:
			1. 如果不設置無符號或是有符號, 默認是有符號, 如果想設置無符號, 需要添加unsigned 關鍵字
			2. 如果插入的數值超出了整數的範圍, 會報 out of range 異常
			3. 如果不設置長度, 會有默認長度
			   長度代表了顯示的最大寬度, 如果不夠用0在左邊填充, 但必須搭配zerofill使用!
		*/
	
		1.如何設置無符號和有符號
			DROP TABLE IF EXISTS tab_int;
			CREATE TABLE tab_int(
				t1 INT,
				t2 INT UNSIGNED
			);
			DESC tab_int;
			
			SELECT * FROM tab_int;
			
			INSERT INTO tab_int VALUES(-12345, 12345);
			INSERT INTO tab_int VALUES(-123456, -123456);
			INSERT INTO tab_int VALUES(9999999999,9999999999);

	二. 小數
		/*
		分類:
			1.浮點型
				float(M, D)
				double(M, D)

			2. 定點型
				dec(M, D)
				decimal(M, D)

		特點:
			1. 
				M: 整數部位 + 小數部位
				D: 小數部位
			
			2. 
				M 和 D 都可以省略
				如果是 decimal 則 M默認為10, D默認為0
				如果是 float 和 double, 則會根據插入的數值的精度來決定精度

			3. 
				定點型的精確度較高, 如果要求插入數值的精度較高如貨幣運算等等, 則考慮使用			
		*/
		
		案例:			
			DROP TABLE IF EXISTS tab_float;
			CREATE TABLE tab_float(
				t1 FLOAT,
				t2 DOUBLE,
				t3 DECIMAL(5, 2)
			);
			DESC tab_float;
			
			SELECT * FROM tab_float;
			
			INSERT INTO tab_float VALUES(123.345, 12355.6644, 345.12);

		原則:
			/*
			所選擇的類型越簡單越好, 能保存數值的類型越小越好
			*/

	三. 字符型	
		/*

		較短的文本:
			char
			varchar

		其他:
			binary 和 varbinary 用於包存較短二進制
			enum 用於保存枚舉
			set 用於保存集合

		較長的文本:
			text
			blob(較大的二進制)	


		特點:
		
				寫法			M的意思							特點				空間的耗費	效率
		char	char(M)		最大的字符數, 可以省略, 默認為1		固定長度的字符	比較耗費		高
		varchar	varchar(M)	最大的字符數, 不可以省略			可變長度的字符	比較節省		低

		*/

	四. 日期型
		/*
		分類:
			date 只包存日期
			time 只保存時間
			year 只保存年

			datetime 保存日期+時間
			timestamp 保存日期+時間

		特點:
			
							字節				範圍					時區影響
			datetime		8				1000-9999			不受
			timestamp		4				1970-2038			受

		*/


##123
