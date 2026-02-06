-- MySQL DDL for ztlog

SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------
-- Table `contents_mst`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contents_mst`;
CREATE TABLE `contents_mst` (
  `CTNT_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `CTNT_TITLE` VARCHAR(255) NOT NULL,
  `CTNT_SUBTITLE` VARCHAR(255) NOT NULL,
  `INP_USER` VARCHAR(255) NOT NULL,
  `INP_DTTM` DATETIME NOT NULL,
  `UPD_DTTM` DATETIME NOT NULL,
  PRIMARY KEY (`CTNT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `contents_dtl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contents_dtl`;
CREATE TABLE `contents_dtl` (
  `CTNT_NO` BIGINT NOT NULL,
  `CTNT_TITLE` VARCHAR(255) NOT NULL,
  `CTNT_BODY` LONGTEXT NOT NULL,
  `CTNT_PATH` VARCHAR(255) DEFAULT NULL,
  `CTNT_NAME` VARCHAR(255) DEFAULT NULL,
  `CTNT_EXT` VARCHAR(255) DEFAULT NULL,
  `INP_USER` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`CTNT_NO`),
  CONSTRAINT `fk_contents_dtl_contents_mst` FOREIGN KEY (`CTNT_NO`) REFERENCES `contents_mst` (`CTNT_NO`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `tags_mst`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tags_mst`;
CREATE TABLE `tags_mst` (
  `TAG_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `TAG_NAME` VARCHAR(255) NOT NULL,
  `INP_DTTM` DATETIME NOT NULL,
  `UPD_DTTM` DATETIME NOT NULL,
  PRIMARY KEY (`TAG_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `contents_tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contents_tags`;
CREATE TABLE `contents_tags` (
  `TAG_NO` BIGINT NOT NULL,
  `CTNT_NO` BIGINT NOT NULL,
  `SORT` INT NOT NULL,
  PRIMARY KEY (`TAG_NO`, `CTNT_NO`),
  CONSTRAINT `fk_contents_tags_tags_mst` FOREIGN KEY (`TAG_NO`) REFERENCES `tags_mst` (`TAG_NO`) ON DELETE CASCADE,
  CONSTRAINT `fk_contents_tags_contents_mst` FOREIGN KEY (`CTNT_NO`) REFERENCES `contents_mst` (`CTNT_NO`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `user_mst`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_mst`;
CREATE TABLE `user_mst` (
  `USER_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(255) DEFAULT NULL,
  `PASSWORD` VARCHAR(255) DEFAULT NULL,
  `GRANT` VARCHAR(255) DEFAULT NULL,
  `INP_DTTM` DATETIME NOT NULL,
  `UPD_DTTM` DATETIME NOT NULL,
  PRIMARY KEY (`USER_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `file_mst`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `file_mst`;
CREATE TABLE `file_mst` (
  `FILE_NO` BIGINT NOT NULL AUTO_INCREMENT,
  `FILE_PATH` VARCHAR(255) DEFAULT NULL,
  `FILE_NAME` VARCHAR(255) DEFAULT NULL,
  `FILE_EXT` VARCHAR(255) DEFAULT NULL,
  `CTNT_NO` VARCHAR(255) DEFAULT NULL,
  `INP_DTTM` DATETIME NOT NULL,
  `UPD_DTTM` DATETIME NOT NULL,
  PRIMARY KEY (`FILE_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
