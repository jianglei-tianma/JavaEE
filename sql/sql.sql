-- 创建用户表
CREATE TABLE bs_user(
 id INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(100) NOT NULL,
 PASSWORD VARCHAR(100) NOT NULL,
 email VARCHAR(100)
)

-- 创建图书表
CREATE TABLE books(
id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
author VARCHAR(100) NOT NULL,
price DOUBLE(11,2) NOT NULL,
sales INT NOT NULL,
stock INT NOT NULL,
img_path VARCHAR(100)
)
--创建订单
CREATE TABLE orders(
	id VARCHAR(100) PRIMARY KEY,
	order_time datetime not null,
	total_count int not null,
	total_amount DOUBLE(11,2) not null,
	state INT not null,
	user_id int not null,
	FOREIGN KEY(user_id) REFERENCES bs_user(id)
)

--创建订单项表
CREATE TABLE order_items(

	id int PRIMARY KEY auto_increment,
	count int not null,
	amount DOUBLE(11,2) not null,
	title VARCHAR(100) not null,
	author VARCHAR(100) not null,
	price double(11,2) not null,
	img_path VARCHAR(100) not null,
	order_id VARCHAR(100) not null,
	FOREIGN KEY(order_id) REFERENCES orders(id)

)