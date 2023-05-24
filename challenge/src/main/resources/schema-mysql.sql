CREATE DATABASE  IF NOT EXISTS `retobbva`;
USE `retobbva`;

DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency` (
  `id` char(3) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(25) DEFAULT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(25) DEFAULT NULL,
  `currency_amount` double NOT NULL,
  `destination_amount` double NOT NULL,
  `destination_currency` char(3) DEFAULT NULL,
  `origin_amount` double NOT NULL,
  `origin_currency` char(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
