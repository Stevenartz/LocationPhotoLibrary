-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema uls_lps
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema uls_lps
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `uls_lps` DEFAULT CHARACTER SET utf8 ;
USE `uls_lps` ;

-- -----------------------------------------------------
-- Table `uls_lps`.`besitzer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uls_lps`.`besitzer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firma` INT(11) NOT NULL,
  `ansprechpartner` VARCHAR(45) NULL DEFAULT NULL,
  `telefon` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `uls_lps`.`adresse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uls_lps`.`adresse` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Strasse` VARCHAR(45) NOT NULL,
  `plz` VARCHAR(45) NOT NULL,
  `ort` VARCHAR(45) NOT NULL,
  `gebaeude` TEXT NOT NULL,
  `gpsbreite` DECIMAL(13,10) NULL DEFAULT NULL,
  `gpslaenge` DECIMAL(13,10) NULL DEFAULT NULL,
  `east` BIT(1) NULL DEFAULT NULL,
  `north` BIT(1) NULL DEFAULT NULL,
  `Besitzer_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Adresse_Besitzer_idx` (`Besitzer_id` ASC),
  CONSTRAINT `fk_Adresse_Besitzer`
    FOREIGN KEY (`Besitzer_id`)
    REFERENCES `uls_lps`.`besitzer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `uls_lps`.`photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uls_lps`.`photo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `gpsbreite` DECIMAL(13,10) NULL,
  `gpslaenge` DECIMAL(13,10) NULL,
  `east` BIT NULL,
  `north` BIT NULL,
  `dateiname` VARCHAR(45) NOT NULL,
  `breite` VARCHAR(45) NULL DEFAULT NULL,
  `hoehe` VARCHAR(45) NULL DEFAULT NULL,
  `titel` VARCHAR(45) NULL DEFAULT NULL,
  `bemerkung` TEXT NULL DEFAULT NULL,
  `datumimport` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `uls_lps`.`adresse_photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uls_lps`.`adresse_photo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Adresse_id` INT(11) NOT NULL,
  `Photo_id` INT(11) NOT NULL,
  `zugeordnetam` DATETIME NOT NULL,
  `isthauptsitzphoto` BIT(1) NULL DEFAULT NULL,
  INDEX `fk_Adresse_Photo_Adresse1_idx` (`Adresse_id` ASC),
  INDEX `fk_Adresse_Photo_Photo1_idx` (`Photo_id` ASC),
  CONSTRAINT `fk_Adresse_Photo_Adresse1`
    FOREIGN KEY (`Adresse_id`)
    REFERENCES `uls_lps`.`adresse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Adresse_Photo_Photo1`
    FOREIGN KEY (`Photo_id`)
    REFERENCES `uls_lps`.`photo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
