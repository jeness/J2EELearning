-- 1. db create
create database personinfo;

-- 2. table create
create table personaldata (
	id int primary key auto_increment,
	user_name varchar(30) not null,
	gender int,
	age int,
	birthday date,
	email varchar(30),
	mobile varchar(11),
	create_user varchar(30),
	create_date date,
	update_user varchar(30),
	update_date date,
	isdel int
)engine=INNODB default charset=utf8 auto_increment=1;

alter database personinfo DEFAULT CHARACTER set 'utf8';
set character_set_client='utf8';
set character_set_connection='utf8';