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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.bill: ~10 rows (approximately)
INSERT INTO `bill` (`id`, `create_by`, `create_time`, `update_time`, `update_by`, `discount`, `final_cost`, `order_id`, `price`, `ship_fee`) VALUES
	(25, 2, '2024-01-11 09:43:27.484183', '2024-01-11 09:43:27.508364', 2, 0, 44103, 7, 40800, 3303),
	(26, 2, '2024-01-11 09:43:27.498391', '2024-01-11 09:43:27.512353', 2, 0, 190303, 8, 187000, 3303),
	(27, 2, '2024-01-11 09:43:27.499388', '2024-01-11 09:43:27.513352', 2, 0, 190303, 9, 187000, 3303),
	(29, 2, '2024-01-16 17:03:54.520066', '2024-01-16 17:03:54.523058', 2, 0, 47406, 11, 40800, 6606),
	(30, 7, '2024-01-16 17:03:54.522060', '2024-01-16 17:03:54.524163', 2, 0, 193606, 12, 187000, 6606),
	(31, 2, '2024-01-18 13:57:08.894439', '2024-01-18 13:57:08.920381', 2, 0, 43443, 7, 40800, 2643),
	(32, 2, '2024-01-18 13:57:08.909410', '2024-01-18 13:57:08.925368', 2, 0, 189643, 8, 187000, 2643),
	(33, 2, '2024-01-18 13:57:08.910408', '2024-01-18 13:57:08.926365', 2, 0, 189643, 9, 187000, 2643),
	(34, 2, '2024-01-18 13:57:08.911405', '2024-01-18 13:57:08.926365', 2, 0, 43443, 11, 40800, 2643),
	(35, 2, '2024-01-18 13:57:08.912402', '2024-01-18 13:57:08.927362', 2, 0, 189643, 12, 187000, 2643);

-- Dumping structure for table food_order.dishes
CREATE TABLE IF NOT EXISTS `dishes` (
  `id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `like_count` int DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `approved` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3bmajnxe80ma8r7ltmhsrxyki` (`category_id`),
  CONSTRAINT `FK3bmajnxe80ma8r7ltmhsrxyki` FOREIGN KEY (`category_id`) REFERENCES `dish_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.dishes: ~56 rows (approximately)
INSERT INTO `dishes` (`id`, `description`, `image`, `like_count`, `name`, `price`, `category_id`, `active`, `approved`) VALUES
	(9552330, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/6cc91e4b-7b57-4d28-b138-995c88679160.jpg', 10, 'Cơm Thịt Bò Xào', 33900, 1060361, b'1', b'0'),
	(9552331, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/973d3cc1-e6a1-4b2a-ac24-b27ccdf4fc8f.jpg', 50, 'Cơm Sườn Chua Ngọt', 33900, 1060361, b'1', b'0'),
	(9552336, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/94365198-d2eb-44fd-893b-7e52cf9a9651.jpg', 50, 'Cơm Thịt Kho Tàu', 32200, 1060361, b'1', b'0'),
	(9552337, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/f8b5c646-6fdb-4b58-9cab-ab72fb096f20.jpg', 100, 'Cơm Thịt Rang Cháy Cạnh', 33050, 1060361, b'1', b'0'),
	(9552340, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/3b5cffc3-97f3-4cd9-a9ad-72e34e55e773.jpg', 10, 'Cơm Thịt Gà Rang', 33050, 1060361, b'1', b'0'),
	(9552342, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/73013078-b8bd-4c78-a926-69dc340f5a41.jpg', 50, 'Cơm Gà Xối Mắm', 33900, 1060361, b'1', b'0'),
	(9552343, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/e5d73a1d-2f25-4919-b1a2-1072f8e7ba94.jpg', 8, 'Cơm Thịt Lợn Xào', 32200, 1060361, b'1', b'0'),
	(9552344, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/4141c638-38c7-4486-b6c7-39a9cfb66222.jpg', 10, 'Cơm Cá Biển Chua Ngọt', 32200, 1060361, b'1', b'0'),
	(9569738, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/bfe7c13c-7f19-407c-9417-3efee664dee6.jpeg', 2, 'Cơm Cánh Gà Xốt Cay', 33150, 1060361, b'1', b'0'),
	(9577573, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/ddb450bc-e2ff-4842-9f1a-b4c6406b3600.jpeg', 10, 'Cơm Gà Xào Xả Ớt', 32200, 1060361, b'1', b'0'),
	(9577580, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/5780e44c-e4e5-4cc8-b03f-ae1a9b7e12bc.jpg', 100, 'Cơm Thịt Bò Xốt Vang', 33900, 1060361, b'1', b'0'),
	(9619513, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/bb5ab5a6-4343-4793-9b6e-318cc535c042.jpg', 10, 'Com Ga Xao Nam', 32200, 1060361, b'1', b'0'),
	(10188049, 'gồm gà xối mắm ,thịt băm,đậu phụ,rau canh thay đổi theo mùa', 'https://images.foody.vn/res/g102/1012777/s1242x1242/12e6a871-6087-48f8-900b-127360c09ad7.jpeg', 100, 'Cơm Gà Xối Mắm 2', 33050, 1060361, b'1', b'0'),
	(10264340, 'cơm,thịt bò xốt vang,đậu nhồi thịt,trứng rán,lạc rang,rau canh,nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/e478a435-e220-4d39-818a-1b44ec85-a2f99c83-201212184346.jpeg', 10, 'Combo Thịt Bò Xốt Vang + Pepsi', 42400, 1226490, b'1', b'0'),
	(10264349, 'cơm,sườn chua ngọt,đậu nhồi thịt,trứng rán,lạc rang,rau canh,nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/774be6e9-ea5a-4bc3-94b3-834241f0-686912ee-201212184319.jpeg', 10, 'Combo Sườn Chua Ngọt + Pepsi', 42400, 1226490, b'1', b'0'),
	(10264358, 'cơm,gà xối mắm,đậu nhồi thịt,trứng rán,lạc rang,rau canh,nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/34ecd8eb-204b-44e2-a8f5-316de680-e09a7b0b-201212184301.jpeg', 10, 'Combo Gà Xối Mắm + Pepsi', 42400, 1226490, b'1', b'0'),
	(10264362, 'cơm,gà xào nấm,đậu nhồi thịt,trứng rán,lạc rang,rau canh,nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/8b63a9b9-c351-4e86-99db-f3db357e-23dffb68-201212183810.jpeg', 10, 'Combo Gà Xào Nấm + Pepsi', 40700, 1226490, b'1', b'0'),
	(10264372, 'cơm ,thịt bò xào,đậu nhồi thịt,thịt băm,lạc rang,rau canh,nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/afa17beb-321f-44fc-bb8a-c1c3f428-d15d5b56-201212184333.jpeg', 8, 'Combo Thịt Bò Xào+ Pepsi', 42400, 1226490, b'1', b'0'),
	(10563915, '1 xuất chỉ được 1 phần thôi nhé các bạn', 'https://images.foody.vn/res/g102/1012777/s1242x1242/5d3165a3-ffde-4340-8643-cea5c6578f3a.jpeg', 50, 'Nước Mắm', 0, 1163426, b'1', b'0'),
	(10563924, '1 xuất chỉ được 1 phần thôi nhé các bạn', 'https://images.foody.vn/res/g102/1012777/s1242x1242/f18a32c7-6554-4047-9103-b28acec006ff.jpeg', 100, 'Tương Ớt', 0, 1163426, b'1', b'0'),
	(10680951, '1 miếng gà xối mắm', 'https://images.foody.vn/res/g102/1012777/s1242x1242/a7aaa428-c204-4a32-8c31-1c0b18adc24c.jpeg', 100, 'Gà Xối Mắm 1 Miếng', 7650, 1176819, b'1', b'0'),
	(10681020, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/2911df13-dac0-453b-acdb-2040bcc81ca0.jpeg', 50, 'Sườn Chua Ngọt 1 Miếng', 8500, 1176819, b'1', b'0'),
	(11782644, 'cơm,gà chiên mắm ,các món phụ ,rau canh.', 'https://images.foody.vn/res/g102/1012777/s1242x1242/3f3c2533-6ca1-4fb4-be74-136ca6ba-1ce5f6ea-200930100515.jpeg', 2, 'Cơm Gà Chiên Mắm Tặng Pepsi', 39850, 1296272, b'1', b'0'),
	(11782664, 'cơm,sườn chua ngọt,các món phụ,rau canh.', 'https://images.foody.vn/res/g102/1012777/s1242x1242/4dbde9cc-8f1f-4d8a-b6f0-efcbe7f0-1c8db6f0-200928234351.jpeg', 7, 'Cơm Sườn Chua Ngọt Tặng Pepsi', 39850, 1296272, b'1', b'0'),
	(11801086, 'cơm,gà chiên mắm,các món phụ,rau canh.', 'https://images.foody.vn/res/g102/1012777/s1242x1242/50e46e2f-8bbb-4ca2-9de8-3bf44d18-9a8c7262-200930134330.jpeg', 100, 'Cơm Gà Chiên Mắm Thái', 22100, 1384545, b'1', b'0'),
	(11801156, 'cơm,gà xào xả ớt,các món phụ,rau canh', 'https://images.foody.vn/res/g102/1012777/s1242x1242/8fd8b6e0-4b2d-4a4a-82f1-fbde6283-8b19ce47-200930100947.jpeg', 50, 'Cơm Gà Xào Xả Ớt 2', 27200, 1060361, b'1', b'0'),
	(11801331, 'cơm,gà rang,các món phụ,rau canh.', 'https://images.foody.vn/res/g102/1012777/s1242x1242/33efd8b7-7092-439d-b540-efac05ad-47d7f81d-200930101510.jpeg', 50, 'Cơm Gà Rang Gừng sả', 24650, 1384545, b'1', b'0'),
	(11801661, 'cơm,thịt lợn xào,các món phụ,rau canh', 'https://images.foody.vn/res/g102/1012777/s1242x1242/04b9a0f1-03cd-421a-ac1e-689a5423-ebe1a1a8-200930102249.jpeg', 10, 'Cơm Thịt Lợn Xào 2', 27200, 1060361, b'1', b'0'),
	(11801720, 'cơm,gà xào,các món phụ,rau canh,trà chanh', 'https://images.foody.vn/res/g102/1012777/s1242x1242/aef5c09d-010e-41d8-b1bd-eb0144ac-4f9b6226-200930102500.jpeg', 4, 'Cơm Gà Xào Tặng Trà Chanh', 39850, 1296272, b'1', b'0'),
	(11801752, 'cơm,thịt bò xốt vang,các món phụ,rau canh,trà chanh.', 'https://images.foody.vn/res/g102/1012777/s1242x1242/64c1abd2-5b15-4b0d-97c3-11d3ca8a-fca822a7-200930102631.jpeg', 10, 'Cơm Thịt Bò Xốt Vang Tặng Trà Chanh', 39850, 1296272, b'1', b'0'),
	(13777120, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/d31fd2fb-9c12-4ded-966f-2588d5af-d6796323-210114214945.jpeg', 10, 'Nước Coca1 Cốc /1chai', 7650, 1530170, b'1', b'0'),
	(13777122, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/e0245153-285f-488a-8a31-9fc22de5-da92a57d-210114215220.jpeg', 10, 'Nước Sprite 1 Cốc/1 Chai', 7650, 1530170, b'1', b'0'),
	(16494586, 'Cơm,sườn2 miếng,thịt băm,đậu nhồi thịt ,lạc rang', 'https://images.foody.vn/res/g102/1012777/s1242x1242/bfbefee0-5dc2-42fd-b91b-1467574b-16bcad14-210617132415.jpeg', 10, 'Cơm Vip Sườn Chua Ngọt', 35700, 1834737, b'1', b'0'),
	(16494623, 'Cơm,gà rang,trứng rán,lạc rang rau canh theo mùa', 'https://images.foody.vn/res/g102/1012777/s1242x1242/ef175b35-60e4-4407-9e7f-d654b208-0f28ca14-210617132712.jpeg', 10, 'Cơm Vip Gà Rang', 37300, 1834737, b'1', b'0'),
	(16494644, 'Cơm,cá,trứng rán,đậu nhồi thịt,lạc rang rau canh theo mùa.', 'https://images.foody.vn/res/g102/1012777/s1242x1242/97bac26c-a747-411f-990e-0e65acf9-6d1a22e1-210617132847.jpeg', 10, 'Cơm Vip Cá Xốt Cà Chua', 37300, 1834737, b'1', b'0'),
	(16494659, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/087b0129-512a-4e0d-a28e-3234d11b-0408a77b-210617133015.jpeg', 10, 'Cơm Vip Thịt Bò Xào', 37300, 1834737, b'1', b'0'),
	(16494663, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/d121c6e7-089f-4f81-910e-441a4e78-68ff67bf-210617133107.jpeg', 6, 'Cơm Vip Thịt Bò Xốt Vang', 39000, 1834737, b'1', b'0'),
	(17203681, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/17c7b1ca-21b1-4878-9c80-1cbd0d50-26f495b6-210723160031.jpeg', 3, 'Coca-Cola lẻ', 8500, 2666192, b'1', b'0'),
	(17203686, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/f30deda4-f662-48f4-9489-46606fab-3c783f79-210723160102.jpeg', 0, 'Cơm Sườn Chua Ngọt + 01 Chai Coca-Cola', 42400, 2666192, b'1', b'0'),
	(17203692, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/e53f49b2-764d-49ad-91d2-4645bbbc-4d6b7dd6-210723160110.jpeg', 6, 'Cơm Thịt Bò Sốt Vang + 01 Chai Coca-Cola', 40700, 2666192, b'1', b'0'),
	(17203696, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/2605066e-80b1-4cb5-afda-25e12ae5-cdc66b42-210723160121.jpeg', 3, 'Cơm Thịt Bò Xào + 01 Chai Coca-Cola', 40700, 2666192, b'1', b'0'),
	(17203702, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/17c7b1ca-21b1-4878-9c80-1cbd0d50-26f495b6-210723160031.jpeg', 2, '02 Coca-Cola lẻ', 17000, 2666192, b'1', b'0'),
	(17203707, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/932c3c4e-0643-4951-bc69-2fcad7e4-dc568d3e-210723160157.jpeg', 0, 'Cơm Sườn Chua Ngọt + Cơm Thịt Bò Sốt Vang + 02 Coca-Cola Chai', 83300, 2666192, b'1', b'0'),
	(17203712, '', 'https://images.foody.vn/res/g102/1012777/s1242x1242/d39cf7cb-22aa-42ee-8a71-f18b31b2-218091a6-210723160206.jpeg', 0, 'Cơm Thịt Rang Cháy Cạnh + Cơm Sườn Chua Ngọt + 02 Coca-Cola Chai', 79900, 2666192, b'1', b'0'),
	(62803571, 'Cơm', 'https://images.foody.vn/res/g102/1012777/s1242x1242/47257b0f-2263-47d1-a1d5-99a1a00a-22c4f176-220324120624.jpeg', 0, 'Cơm Thêm ', 12750, 1176819, b'1', b'0'),
	(124157080, '', 'https://images.foody.vn/default/s1242x2208/shopeefood-deli-dish-no-image.png', 0, 'Combo thịt kho tàu', 39950, 2666192, b'1', b'0'),
	(158778875, 'Cơm ,cá xốt cà chua,các món phụ,rau canh theo mùa + nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/68a262cf-5941-4969-b880-791f1dc7-f1dea786-231018131753.jpeg', 0, 'Combo cá xốt cà chua', 42400, 1226490, b'1', b'0'),
	(158779398, '1:.Cơm,gà xối mắm,các món phụ.2:cơm ,thịt bò xào,các món phụ.rau canh theo mùa + 2 coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/de48e358-f8c4-4d06-ba2b-e8a8d1e6-38937eb4-231018134952.jpeg', 0, 'Combo gà xối mắm + combo thịt bò xào', 83200, 1226490, b'1', b'0'),
	(158779541, '1: Cơm,thịt rang cháy cạnh,các món phụ.:cơm,gà xối mắm,các món phụ.3:.cơm,bò xốt vang,các món phụ.rau canh theo mùa + nước coca', 'https://images.foody.vn/res/g102/1012777/s1242x1242/247a65c1-82a0-4901-bbe4-c48961fd-810bb14b-231018135805.jpeg', 0, 'Combo thịt rang cháy cạnh+ combo gà xối mắm +combo bò xốt vang', 123150, 1226490, b'1', b'0'),
	(160027464, 'Săn ngay Coca-Cola chỉ 1Đ. Số lượng mỗi ngày có hạn. Đặt thêm món để tận hưởng ưu đãi 25K & 40K cực đã.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/ac8acebd-8487-4211-a5fe-de93d7c2-f9df1b34-231208162600.jpeg', 0, 'Coca-Cola chỉ 1Đ', 10200, 7058241, b'1', b'0'),
	(160027468, 'Giảm 25K khi đặt combo có 01 Coca-Cola. Số lượng ưu đãi có hạn.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/d999da33-5ae5-4953-bef2-94a748cd-12fdc27e-231208162628.jpeg', 0, 'Combo Cơm gà xối mắm + 01 Fanta/Sprite/Coca-Cola', 50150, 7058241, b'1', b'0'),
	(160027472, 'Giảm 25K khi đặt combo có 01 Coca-Cola. Số lượng ưu đãi có hạn.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/d999da33-5ae5-4953-bef2-94a748cd-12fdc27e-231208162628.jpeg', 0, 'Combo Cơm Gà Xào Nấm + 01 Fanta/Sprite/Coca-Cola', 50150, 7058241, b'1', b'0'),
	(160027490, 'Đặt thêm 01 Coca-Cola cùng món ăn tùy thích để tận hưởng ưu đãi 25K. Số lượng ưu đãi có hạn.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/ac8acebd-8487-4211-a5fe-de93d7c2-f9df1b34-231208162600.jpeg', 0, '1 Coca-Cola chai', 10200, 7058241, b'1', b'0'),
	(160027524, 'Giảm 40K khi đặt combo có 02 Coca-Cola. Số lượng ưu đãi có hạn.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/c650f163-c350-44c3-9b90-b6ed4025-ef8e984c-231208162742.jpeg', 0, 'Combo Cơm Gà Xối Mắm + Cơm Thịt Bò Xào + 02 Fanta/Sprite/Coca-Cola', 93500, 7058242, b'1', b'0'),
	(160027526, 'Giảm 40K khi đặt combo có 02 Coca-Cola. Số lượng ưu đãi có hạn.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/c650f163-c350-44c3-9b90-b6ed4025-ef8e984c-231208162742.jpeg', 0, 'Combo Cơm Gà Xối Mắm + Cơm Thịt Kho Tàu + 02 Fanta/Sprite/Coca-Cola', 93500, 7058242, b'1', b'0'),
	(160027541, 'Đặt thêm 02 Coca-Cola cùng món ăn tùy thích để tận hưởng ưu đãi 40K. Số lượng ưu đãi có hạn.\n', 'https://images.foody.vn/res/g102/1012777/s1242x1242/ac8acebd-8487-4211-a5fe-de93d7c2-f9df1b34-231208162600.jpeg', 0, '02 Coca-Cola chai', 20400, 7058242, b'1', b'0');

-- Dumping structure for table food_order.dish_category
CREATE TABLE IF NOT EXISTS `dish_category` (
  `id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `restaurant_id` bigint DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81dv79l740v3w7da181dmeg39` (`restaurant_id`),
  CONSTRAINT `FK81dv79l740v3w7da181dmeg39` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.dish_category: ~11 rows (approximately)
INSERT INTO `dish_category` (`id`, `name`, `restaurant_id`, `active`) VALUES
	(1060361, 'MENU', 109159, b'1'),
	(1163426, 'Gia Vị', 109159, b'1'),
	(1176819, 'Thêm Miếng', 109159, b'1'),
	(1226490, 'COMBO', 109159, b'1'),
	(1296272, 'Cơm Xuất Tặng Đồ Uống', 109159, b'1'),
	(1384545, 'HOT DEAL', 109159, b'1'),
	(1530170, 'Đồ Uống', 109159, b'1'),
	(1834737, 'Cơm Vip', 109159, b'1'),
	(2666192, 'Combo Coca-Cola', 109159, b'1'),
	(7058241, 'DEAL KHAO TẾT - GIẢM 25K', 109159, b'1'),
	(7058242, 'DEAL GẮN KẾT DIỆU KỲ - GIẢM 40K', 109159, b'1');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.item_order: ~6 rows (approximately)
INSERT INTO `item_order` (`id`, `create_by`, `create_time`, `update_time`, `update_by`, `approved`, `deleted`, `dish_id`, `quantity`) VALUES
	(7, 2, '2024-01-10 16:55:23.658462', '2024-01-18 13:57:08.944317', 2, b'1', b'0', 160027541, 2),
	(8, 2, '2024-01-10 16:55:23.669609', '2024-01-18 13:57:08.945314', 2, b'1', b'0', 160027526, 2),
	(9, 2, '2024-01-10 16:55:23.677028', '2024-01-18 13:57:08.946312', 2, b'1', b'0', 160027524, 2),
	(10, 2, '2024-01-10 16:55:23.681019', '2024-01-11 10:03:47.958373', 2, b'0', b'1', 160027490, 5),
	(11, 2, '2024-01-16 17:00:28.200029', '2024-01-18 13:57:08.946312', 2, b'1', b'0', 160027541, 2),
	(12, 7, '2024-01-16 17:00:28.226012', '2024-01-18 13:57:08.947309', 2, b'1', b'0', 160027526, 2);

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

-- Dumping data for table food_order.present_vote: ~1 rows (approximately)
INSERT INTO `present_vote` (`id`, `active`, `description`, `restaurant_name`, `url`, `total_vote`, `create_by`, `create_time`, `update_time`, `update_by`) VALUES
	(2, b'1', 'Quán này ngon lắm ae ', 'che ngon pho co', 'https://shopeefood.vn/ha-noi/che-ngon-pho-co-nhue', 1, 2, '2024-01-10 13:06:34.797909', '2024-01-10 13:06:34.832363', 2);

-- Dumping structure for table food_order.register_otp
CREATE TABLE IF NOT EXISTS `register_otp` (
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `otp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `expired` bigint DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.register_otp: ~0 rows (approximately)

-- Dumping structure for table food_order.reset_password_otp
CREATE TABLE IF NOT EXISTS `reset_password_otp` (
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `expired` bigint DEFAULT NULL,
  `otp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.reset_password_otp: ~0 rows (approximately)

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

-- Dumping data for table food_order.restaurant: ~2 rows (approximately)
INSERT INTO `restaurant` (`id`, `address`, `description`, `image`, `name`, `rating`, `url`, `selected`, `deleted`) VALUES
	(104796, '136 Ngõ 43 Cổ Nhuế, P. Cổ Nhuế 2, Bắc Từ Liêm, Hà Nội', '', 'https://images.foody.vn/res/g100/996223/prof/s1242x600/foody-upload-api-foody-mobile-kham-pha-3-quan-che--200102135359.jpg', 'Chè Ngon Phố - Cổ Nhuế', 4.5, 'https://shopeefood.vn/ha-noi/che-ngon-pho-co-nhue', b'0', b'1'),
	(109159, '97 Triều Khúc, P. Thanh Xuân Nam, Thanh Xuân, Hà Nội', '', 'https://images.foody.vn/res/g102/1012777/prof/s1242x600/image-65cf0bb6-200910115827.jpeg', 'Cơm Gia Bảo - Triều Khúc', 4.6, 'https://shopeefood.vn/ha-noi/com-gia-bao-trieu-khuc', b'1', b'0');

-- Dumping structure for table food_order.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `approved` bit(1) DEFAULT NULL,
  `value` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKipvo0v6rphsxeit032o7pjfpv` (`create_by`),
  KEY `FK6lgnb0qd4qufq0iascfdvwciu` (`update_by`),
  CONSTRAINT `FK6lgnb0qd4qufq0iascfdvwciu` FOREIGN KEY (`update_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKipvo0v6rphsxeit032o7pjfpv` FOREIGN KEY (`create_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table food_order.transaction: ~1 rows (approximately)
INSERT INTO `transaction` (`id`, `create_by`, `create_time`, `update_time`, `update_by`, `approved`, `value`) VALUES
	(1, 2, '2024-01-15 14:36:59.216132', '2024-01-15 14:47:35.913815', 2, b'1', 3000000);

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

-- Dumping data for table food_order.user: ~3 rows (approximately)
INSERT INTO `user` (`id`, `balance`, `email`, `full_name`, `password`, `role`, `status`, `approved`) VALUES
	(2, 4779341, 'cuong2905say@gmail.com', 'NGUYEN MANH CUONG', '$2a$10$CY./RW.QkWR3qRYHBFvGn.MjmT/Rrrw5mI2Xi.klqO6rWwZlmPhCu', 'ADMIN', 'VERIFIED', b'1'),
	(6, 0, 'cuong2905say1@gmail.com', 'Penaldo', '$2a$10$tzoPr6aKwNGkn6P8scD3/O3RvioJ93jdnMIMETttWQzCDpsuG3Nqe', 'USER', 'VERIFIED', b'1'),
	(7, 0, 'cuong2905say2@gmail.com', 'Pessi', '$2a$10$J5DXiif7EbGYSn8xu4sFAOvAZiLRd8AvCwgpxt8lPv6YFk6JI9J3y', 'USER', 'VERIFIED', b'1');

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

-- Dumping data for table food_order.user_cart: ~3 rows (approximately)
INSERT INTO `user_cart` (`id`, `dish_id`, `quantity`, `create_by`, `create_time`, `update_time`, `update_by`) VALUES
	(10, 160027541, 2, 2, '2024-01-10 14:03:39.970920', '2024-01-10 14:03:39.971919', 2),
	(12, 160027526, 2, 2, '2024-01-10 16:54:08.576609', '2024-01-10 16:54:08.592566', 2),
	(13, 160027524, 10, 2, '2024-01-10 16:54:16.214893', '2024-01-11 09:37:54.806097', 2);

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

-- Dumping data for table food_order.user_vote: ~1 rows (approximately)
INSERT INTO `user_vote` (`id`, `present_vote_id`, `user_id`, `vote_time`, `create_by`, `create_time`, `update_time`, `update_by`) VALUES
	(1, 2, NULL, '2024-01-10 13:06:34.838000', 2, '2024-01-10 13:06:34.840342', '2024-01-10 13:06:34.840342', 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
