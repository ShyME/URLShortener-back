-- MySQL Script generated by MySQL Workbench
-- Tue Sep  1 18:47:38 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema url_shortener
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `url_shortener` ;

-- -----------------------------------------------------
-- Schema url_shortener
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `url_shortener` DEFAULT CHARACTER SET utf8 ;
USE `url_shortener` ;

-- -----------------------------------------------------
-- Table `url_shortener`.`url_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `url_shortener`.`url_items` ;

CREATE TABLE IF NOT EXISTS `url_shortener`.`url_items` (
  `ID` BIGINT NOT NULL AUTO_INCREMENT,
  `LONG_URL` VARCHAR(254) NOT NULL UNIQUE,
  `SHORT_URL_SUFFIX` VARCHAR(30) NOT NULL UNIQUE,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * FROM `url_shortener`.`url_items`;
