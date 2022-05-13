
CREATE TABLE customers(
	id INT(10),
	NAME VARCHAR(15),
	email VARCHAR(20),
	birth DATE,
	photo MEDIUMBLOB
)

SELECT *
FROM customers;

// 增
INSERT INTO customers(NAME, email, birth) VALUE("test", "test@gmail.com", NOW())

// 改
UPDATE customersset NAME='test1' WHERE id =1;

