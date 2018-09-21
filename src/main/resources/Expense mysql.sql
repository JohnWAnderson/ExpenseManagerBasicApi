DROP DATABASE IF EXISTS expenseManagement;
CREATE DATABASE expenseManagement;
USE expenseManagement; 

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
	id int NOT NULL AUTO_INCREMENT NOT NULL,
	username varchar(20) NOT NULL,
	email varchar(55) NOT NULL,
	password varchar(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (username),
	UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS items;
CREATE TABLE items
(
	id int NOT NULL AUTO_INCREMENT NOT NULL,
    user_id int Not Null,
	name varchar(100) Not Null,
	descrition varchar(255) DEFAULT Null,
	cost int Not Null,
	PRIMARY KEY (id),
	CONSTRAINT `FK_DETAIL` FOREIGN KEY (`user_id`) 
	REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


insert into users(username, email, password) value("John", "john@email.com", "password");
insert into users(username, email, password) value("Bob", "bob@email.com","password");


insert into Items(user_id, name, descrition, cost) value(1, "rent", "Payed at the start of the 5th of monthly", 600);
insert into Items(user_id, name, descrition, cost) value(1, "car payment", "Payed at the start of the 5th of monthly", 300);
insert into Items(user_id, name, descrition, cost) value(1, "food", "weekly trip to the store", 100);

insert into Items(user_id, name, descrition, cost) value(2, "gas money", "need to fill tank to full", 60);