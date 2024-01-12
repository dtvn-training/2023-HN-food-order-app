-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.35 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for food_order
CREATE DATABASE IF NOT EXISTS `food_order` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `food_order`;

-- Dumping structure for table food_order.bill
CREATE TABLE IF NOT EXISTS `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `final_cost` int DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `price` int DEFAULT NULL,
  `ship_fee` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcbnax01bn4fwxfnbuvhwoey09` (`create_by`),
  KEY `FKs2gt452btmhn0dwtfmj8mnice` (`update_by`),
  KEY `FK7tqmnr9oldv45w7svhqcrvuhg` (`order_id`),
  CONSTRAINT `FK7tqmnr9oldv45w7svhqcrvuhg` FOREIGN KEY (`order_id`) REFERENCES `item_order` (`id`),
  CONSTRAINT `FKcbnax01bn4fwxfnbuvhwoey09` FOREIGN KEY (`create_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs2gt452btmhn0dwtfmj8mnice` FOREIGN KEY (`update_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.dishes
CREATE TABLE IF NOT EXISTS `dishes` (
  `id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `like_count` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3bmajnxe80ma8r7ltmhsrxyki` (`category_id`),
  CONSTRAINT `FK3bmajnxe80ma8r7ltmhsrxyki` FOREIGN KEY (`category_id`) REFERENCES `dish_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.dish_category
CREATE TABLE IF NOT EXISTS `dish_category` (
  `id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `restaurant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81dv79l740v3w7da181dmeg39` (`restaurant_id`),
  CONSTRAINT `FK81dv79l740v3w7da181dmeg39` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.item_order
CREATE TABLE IF NOT EXISTS `item_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `approved` bit(1) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `dish_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmdjfq0o92dr9tv5pncg8up92l` (`create_by`),
  KEY `FKg2ildhu611tp9wwh6ylar98ps` (`update_by`),
  KEY `FK76adxq90g79wy8gpm8f4n7p30` (`dish_id`),
  CONSTRAINT `FK76adxq90g79wy8gpm8f4n7p30` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FKg2ildhu611tp9wwh6ylar98ps` FOREIGN KEY (`update_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmdjfq0o92dr9tv5pncg8up92l` FOREIGN KEY (`create_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.present_vote
CREATE TABLE IF NOT EXISTS `present_vote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `restaurant_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `total_vote` int DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnj03wkabpkvdnstyjxcpxyp6r` (`create_by`),
  KEY `FKix8cnv09ybi5ftx4qsmgp1tf5` (`update_by`),
  CONSTRAINT `FKix8cnv09ybi5ftx4qsmgp1tf5` FOREIGN KEY (`update_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnj03wkabpkvdnstyjxcpxyp6r` FOREIGN KEY (`create_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.register_otp
CREATE TABLE IF NOT EXISTS `register_otp` (
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `otp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `expired` bigint DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.reset_password_otp
CREATE TABLE IF NOT EXISTS `reset_password_otp` (
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `expired` bigint DEFAULT NULL,
  `otp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.restaurant
CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `selected` bit(1) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `balance` int DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` enum('USER','ADMIN') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` enum('VERIFIED','NOT_VERIFY') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `approved` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.user_cart
CREATE TABLE IF NOT EXISTS `user_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dish_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7jwuk3954rsbw3nbuhvgkeahs` (`dish_id`),
  KEY `FKbdnfyj7b1yayw16stdxi7x24t` (`create_by`),
  KEY `FKm4meqq3jg5m7dlljajsbva5vf` (`update_by`),
  CONSTRAINT `FK7jwuk3954rsbw3nbuhvgkeahs` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FKbdnfyj7b1yayw16stdxi7x24t` FOREIGN KEY (`create_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm4meqq3jg5m7dlljajsbva5vf` FOREIGN KEY (`update_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_order.user_vote
CREATE TABLE IF NOT EXISTS `user_vote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `present_vote_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `vote_time` datetime(6) DEFAULT NULL,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2q50phs57njg6g0qvha1r8703` (`user_id`),
  KEY `FKjn1rvdg2jn95ev23opal5xnsy` (`present_vote_id`),
  KEY `FKj26apf4ms63o19hnf6its5hiq` (`create_by`),
  KEY `FK817sccdbhsbji0cd6xlfb0puj` (`update_by`),
  CONSTRAINT `FK2q50phs57njg6g0qvha1r8703` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK817sccdbhsbji0cd6xlfb0puj` FOREIGN KEY (`update_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKj26apf4ms63o19hnf6its5hiq` FOREIGN KEY (`create_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjn1rvdg2jn95ev23opal5xnsy` FOREIGN KEY (`present_vote_id`) REFERENCES `present_vote` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
