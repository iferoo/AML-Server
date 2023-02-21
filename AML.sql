DROP SCHEMA IF EXISTS `AML`;

CREATE SCHEMA `AML`;

use `AML`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,

  UNIQUE KEY `NAME_UNIQUE` (`name`),

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `branch`;

CREATE TABLE `branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `bank_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),

  KEY `FK_BANK_ID` (`bank_id`),
  CONSTRAINT `FK_BANK` FOREIGN KEY (`bank_id`) 
  REFERENCES `bank` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `salary` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `branch_id` int(11) NOT NULL,

  PRIMARY KEY (`id`),
  
  KEY `FK_BRANCH` (`branch_id`),
  CONSTRAINT `FK_BRANCH_EMP` 
  FOREIGN KEY (`branch_id`) 
  REFERENCES `branch` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,

  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,

  PRIMARY KEY (`id`),


  KEY `FK_CUSTOMER` (`customer_id`),
  CONSTRAINT `FK_CUSTOMER_ACC` 
  FOREIGN KEY (`customer_id`) 
  REFERENCES `customer` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  KEY `FK_BRANCH` (`branch_id`),
  CONSTRAINT `FK_BRANCH_CUS` 
  FOREIGN KEY (`branch_id`) 
  REFERENCES `branch` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_EMPLOYEE` (`employee_id`),
  CONSTRAINT `FK_EMPLOYEE_ACC` 
  FOREIGN KEY (`employee_id`) 
  REFERENCES `employee` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method` varchar(45) DEFAULT NULL,
  `operation` varchar(45) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `date` datetime DEFAULT current_timestamp,

  `account_id` int(11),
  
  PRIMARY KEY (`id`),
  
  KEY `FK_ACCOUNT` (`account_id`),
  
  CONSTRAINT `FK_ACCOUNT_TRAN` FOREIGN KEY (`account_id`) 
  REFERENCES `account` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `transfer`;

CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) NOT NULL,
  `reciever_id` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_TRANSACTION` (`transaction_id`),
  CONSTRAINT `FK_TRANSFER_TRANSC` FOREIGN KEY (`transaction_id`) 
  REFERENCES `transaction` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_RECIEVER` (`reciever_id`),
  CONSTRAINT `FK_TRANSFER_REC` FOREIGN KEY (`reciever_id`) 
  REFERENCES `account` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET FOREIGN_KEY_CHECKS = 1;