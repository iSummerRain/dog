DROP TABLE users;
CREATE TABLE `users` (
	`id` VARCHAR (20) NOT NULL,
	`name` VARCHAR (60) NOT NULL,
	`password` VARCHAR (100),
	`address` VARCHAR (250),
	`age` INT(4) NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';
INSERT INTO `users` (`id`, `name`, `password`,`address`,`age`) VALUES('1','孤傲苍狼','123456','桃花岛222号','27');
INSERT INTO `users` (`id`, `name`, `password`,`address`,`age`) VALUES('2','白虎神皇','987654','逍遥津168号','27');