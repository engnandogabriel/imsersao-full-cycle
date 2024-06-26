-- -----------------------------------------------------
-- Schema partner_app1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `partner_app1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `partner_app1` ;

-- -----------------------------------------------------
-- Table `partner_app1`.`Event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `partner_app1`.`Event` ;

CREATE TABLE IF NOT EXISTS `partner_app1`.`Event` (
  `id` VARCHAR(191) NOT NULL,
  `name` VARCHAR(191) NOT NULL,
  `description` VARCHAR(191) NOT NULL,
  `date` DATETIME(3) NOT NULL,
  `createdAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updatedAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `partner_app1`.`Spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `partner_app1`.`Spot` ;

CREATE TABLE IF NOT EXISTS `partner_app1`.`Spot` (
  `id` VARCHAR(191) NOT NULL,
  `name` VARCHAR(191) NOT NULL,
  `status` ENUM('available', 'reserved') NOT NULL,
  `createdAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updatedAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `eventId` VARCHAR(191) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Spot_name_eventId_key` (`name` ASC, `eventId` ASC) VISIBLE,
  INDEX `Spot_eventId_fkey` (`eventId` ASC) VISIBLE,
  CONSTRAINT `Spot_eventId_fkey`
    FOREIGN KEY (`eventId`)
    REFERENCES `partner_app1`.`Event` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `partner_app1`.`ReservationHistory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `partner_app1`.`ReservationHistory` ;

CREATE TABLE IF NOT EXISTS `partner_app1`.`ReservationHistory` (
  `id` VARCHAR(191) NOT NULL,
  `email` VARCHAR(191) NOT NULL,
  `ticketKind` ENUM('full', 'half') NOT NULL,
  `status` ENUM('reserved', 'canceled') NOT NULL,
  `createdAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updatedAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `spotId` VARCHAR(191) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `ReservationHistory_spotId_fkey` (`spotId` ASC) VISIBLE,
  CONSTRAINT `ReservationHistory_spotId_fkey`
    FOREIGN KEY (`spotId`)
    REFERENCES `partner_app1`.`Spot` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `partner_app1`.`Ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `partner_app1`.`Ticket` ;

CREATE TABLE IF NOT EXISTS `partner_app1`.`Ticket` (
  `id` VARCHAR(191) NOT NULL,
  `email` VARCHAR(191) NOT NULL,
  `ticketKind` ENUM('full', 'half') NOT NULL,
  `createdAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updatedAt` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `spotId` VARCHAR(191) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Ticket_spotId_key` (`spotId` ASC) VISIBLE,
  CONSTRAINT `Ticket_spotId_fkey`
    FOREIGN KEY (`spotId`)
    REFERENCES `partner_app1`.`Spot` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
