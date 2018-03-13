DROP TABLE users;
CREATE TABLE `users` (
	`id` VARCHAR (20) NOT NULL,
	`NAME` VARCHAR (60) NOT NULL,
	`ADDRESS` VARCHAR (250),
	`age` INT(4) NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
INSERT INTO `users` (`id`, `NAME`, `ADDRESS`,`age`) VALUES('1','孤傲苍狼','桃花岛222号','27');
INSERT INTO `users` (`id`, `NAME`, `ADDRESS`,`age`) VALUES('2','白虎神皇','逍遥津168号','27');