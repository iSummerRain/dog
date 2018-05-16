DROP TABLE IF EXISTS users;
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

DROP TABLE IF EXISTS `base_static_data`;
CREATE TABLE `base_static_data` (
	`CODE_TYPE_CD` varchar(30) NOT NULL COMMENT '编码类型代码',
	`CODE_VAL` varchar(255) NOT NULL COMMENT '编码值',
	`CODE_NAME` varchar(255) NOT NULL COMMENT '编码名称',
	`CODE_DESC` varchar(512) DEFAULT NULL COMMENT '编码描述',
	`ARGE_SEQNO` int(11) DEFAULT NULL COMMENT '排列序号',
	`CRT_TIME` datetime NOT NULL COMMENT '创建时间',
	`MODF_TIME` datetime NOT NULL COMMENT '修改时间',
	`OP_PRSN_ID` varchar(40) NOT NULL COMMENT '操作人员ID',
	PRIMARY KEY (`CODE_TYPE_CD`,`CODE_VAL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='静态参数表';

INSERT INTO mybatis.base_static_data
(CODE_TYPE_CD, CODE_VAL, CODE_NAME, CODE_DESC, ARGE_SEQNO, CRT_TIME, MODF_TIME, OP_PRSN_ID)
VALUES ('CUST_STAR_LEVEL', '1', '一星', '一星级客户', '1', NOW(), NOW(), 'S9527');

INSERT INTO mybatis.base_static_data
(CODE_TYPE_CD, CODE_VAL, CODE_NAME, CODE_DESC, ARGE_SEQNO, CRT_TIME, MODF_TIME, OP_PRSN_ID)
VALUES ('CUST_STAR_LEVEL', '2', '二星', '二星级客户', '2', NOW(), NOW(), 'S9527');

INSERT INTO mybatis.base_static_data
(CODE_TYPE_CD, CODE_VAL, CODE_NAME, CODE_DESC, ARGE_SEQNO, CRT_TIME, MODF_TIME, OP_PRSN_ID)
VALUES ('CUST_STAR_LEVEL', '3', '三星', '三星级客户', '3', NOW(), NOW(), 'S9527');

INSERT INTO mybatis.base_static_data
(CODE_TYPE_CD, CODE_VAL, CODE_NAME, CODE_DESC, ARGE_SEQNO, CRT_TIME, MODF_TIME, OP_PRSN_ID)
VALUES ('CUST_STAR_LEVEL', '4', '四星', '四星级客户', '4', NOW(), NOW(), 'S9527');

INSERT INTO mybatis.base_static_data
(CODE_TYPE_CD, CODE_VAL, CODE_NAME, CODE_DESC, ARGE_SEQNO, CRT_TIME, MODF_TIME, OP_PRSN_ID)
VALUES ('CUST_STAR_LEVEL', '5', '五星', '五星级客户', '5', NOW(), NOW(), 'S9527');

INSERT INTO mybatis.base_static_data
(CODE_TYPE_CD, CODE_VAL, CODE_NAME, CODE_DESC, ARGE_SEQNO, CRT_TIME, MODF_TIME, OP_PRSN_ID)
VALUES ('CUST_STAR_LEVEL', '99', 'VIP', 'VIP客户', '6', NOW(), NOW(), 'S9527');
