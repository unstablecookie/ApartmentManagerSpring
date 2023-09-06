# ApartmentManagerSpring
Apartment Manager with Spring MVC

MVC приложение на основе Spring 4.
представляет собой менеджер недвижимости и владельцев этой недвижимости .
реализован поиск, загрузка файлов, in-memory авторизация .
написан совсемстно с использованием PostgreSQL ,(таблицы users, property_new )

create table users (
	id bigint  PRIMARY KEY,
	first_name varchar(20) NOT NULL,
	last_name varchar(20) NOT NULL,
	user_name varchar(20) NOT NULL UNIQUE
);

create table property_new (
	id bigint  PRIMARY KEY,
	type varchar(20) NOT NULL,
	area smallint NOT NULL,
	build smallint NOT NULL,
	photo bytea
);

и их связывание
ALTER TABLE property_new ADD COLUMN user_id bigint NOT NULL
CONSTRAINT property_user_fk_user_id REFERENCES users (id)
ON UPDATE CASCADE ON DELETE CASCADE; 

понадобится также 2 отдельные таблички sequence для корректного формирования ID пользователей и собственности.




