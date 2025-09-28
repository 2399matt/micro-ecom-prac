CREATE USER IF NOT EXISTS 'springdb'@'%' IDENTIFIED BY 'springdb';

-- Grant full privileges on all service databases
GRANT ALL PRIVILEGES ON mstest.* TO 'springdb'@'%';
GRANT ALL PRIVILEGES ON cart_service.* TO 'springdb'@'%';
GRANT ALL PRIVILEGES ON product_service.* TO 'springdb'@'%';
GRANT ALL PRIVILEGES ON order_service.* TO 'springdb'@'%';

FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS `mstest`;
USE mstest;

CREATE TABLE IF NOT EXISTS `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(45) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `enabled` tinyint DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DATABASE IF NOT EXISTS `cart_service`;
USE cart_service;


CREATE TABLE IF NOT EXISTS `cart` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `user_id` int DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `cart_item` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(90) DEFAULT NULL,
                             `price` int DEFAULT NULL,
                             `cart_id` int NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `c_id_idx` (`cart_id`),
                             CONSTRAINT `c_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DATABASE IF NOT EXISTS `product_service`;
USE product_service;

CREATE TABLE IF NOT EXISTS `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(90) DEFAULT NULL,
                            `stock` int DEFAULT NULL,
                            `price` int DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `products` VALUES (1,'Small Wooden Table',18,65),(2,'Fantastic Marble Gloves',1,87),(3,'Rustic Granite Shoes',3,14),(4,'Intelligent Aluminum Gloves',21,18),(5,'Fantastic Plastic Keyboard',8,119),(6,'Fantastic Paper Keyboard',0,104),(7,'Heavy Duty Granite Bottle',8,64),(8,'Mediocre Wool Bag',1,3),(9,'Enormous Iron Lamp',7,39),(10,'Ergonomic Plastic Bottle',3,6),(11,'Lightweight Marble Bag',9,15),(12,'Durable Iron Bench',15,37),(13,'Gorgeous Cotton Pants',22,37),(14,'Synergistic Granite Bench',6,109),(15,'Awesome Rubber Keyboard',9,11),(16,'Rustic Rubber Car',3,2),(17,'Synergistic Plastic Shirt',23,95),(18,'Enormous Bronze Plate',8,12),(19,'Lightweight Copper Table',23,7),(20,'Incredible Linen Wallet',3,69),(21,'Enormous Wooden Computer',9,75),(22,'Synergistic Marble Coat',11,65),(23,'Practical Steel Knife',5,70),(24,'Mediocre Wool Pants',20,106),(25,'Ergonomic Wooden Plate',14,50),(26,'Rustic Silk Lamp',6,16),(27,'Practical Cotton Computer',11,80),(28,'Gorgeous Silk Shoes',5,41),(29,'Gorgeous Paper Hat',21,113),(30,'Mediocre Silk Watch',18,2),(31,'Small Granite Shoes',12,76),(32,'Incredible Concrete Computer',13,43),(33,'Intelligent Cotton Knife',21,97),(34,'Rustic Rubber Gloves',20,93),(35,'Ergonomic Plastic Keyboard',17,70),(36,'Aerodynamic Bronze Bottle',11,65),(37,'Fantastic Concrete Knife',16,3),(38,'Lightweight Aluminum Knife',2,72),(39,'Intelligent Rubber Knife',7,92),(40,'Ergonomic Leather Shirt',24,104),(41,'Rustic Steel Shoes',0,26),(42,'Heavy Duty Leather Lamp',23,99),(43,'Durable Cotton Computer',10,48),(44,'Rustic Cotton Shirt',13,112),(45,'Sleek Paper Coat',12,79),(46,'Intelligent Plastic Plate',4,110),(47,'Enormous Silk Bag',13,54),(48,'Ergonomic Silk Watch',19,64),(49,'Ergonomic Iron Coat',7,26),(50,'Ergonomic Rubber Hat',19,98),(51,'Synergistic Leather Lamp',9,110),(52,'Enormous Paper Hat',21,111),(53,'Small Copper Hat',21,95),(54,'Mediocre Wooden Shirt',4,84),(55,'Practical Leather Computer',10,55),(56,'Lightweight Wooden Clock',13,76),(57,'Synergistic Aluminum Clock',22,52),(58,'Ergonomic Steel Gloves',12,40),(59,'Practical Silk Plate',16,115),(60,'Practical Granite Watch',0,18),(61,'Small Wooden Pants',23,74),(62,'Sleek Granite Car',6,49),(63,'Synergistic Leather Watch',15,52),(64,'Incredible Cotton Chair',9,59),(65,'Incredible Steel Bench',13,96),(66,'Synergistic Marble Shoes',6,31),(67,'Synergistic Linen Shirt',24,37),(68,'Intelligent Plastic Chair',6,109),(69,'Heavy Duty Granite Shirt',23,63),(70,'Heavy Duty Silk Chair',19,113),(71,'Aerodynamic Iron Watch',19,70),(72,'Durable Aluminum Knife',22,87),(73,'Small Aluminum Shoes',11,98),(74,'Sleek Marble Car',9,1),(75,'Gorgeous Concrete Gloves',18,69),(76,'Incredible Bronze Hat',21,15),(77,'Durable Plastic Clock',13,29),(78,'Enormous Silk Watch',17,20),(79,'Awesome Leather Clock',1,5),(80,'Mediocre Linen Clock',22,74),(81,'Lightweight Plastic Wallet',22,0),(82,'Intelligent Rubber Bag',14,65),(83,'Practical Linen Coat',17,97),(84,'Awesome Iron Watch',10,89),(85,'Sleek Cotton Computer',12,105),(86,'Synergistic Plastic Clock',6,65),(87,'Heavy Duty Granite Chair',3,110),(88,'Intelligent Bronze Lamp',12,59),(89,'Mediocre Marble Pants',22,92),(90,'Ergonomic Iron Chair',10,30),(91,'Fantastic Silk Wallet',3,116),(92,'Enormous Granite Gloves',13,111),(93,'Practical Copper Car',8,64),(94,'Incredible Iron Watch',13,12),(95,'Synergistic Linen Bag',3,98),(96,'Enormous Paper Plate',5,76),(97,'Heavy Duty Bronze Pants',8,34),(98,'Practical Concrete Bag',0,99),(99,'Mediocre Aluminum Hat',23,56),(100,'Enormous Concrete Knife',21,104),(101,'Gorgeous Silk Shirt',7,78),(102,'Incredible Bronze Car',10,26),(103,'Durable Leather Chair',0,9),(104,'Rustic Aluminum Shoes',22,63),(105,'Gorgeous Bronze Bag',8,68),(106,'Gorgeous Concrete Bench',3,42),(107,'Durable Granite Clock',5,49),(108,'Enormous Rubber Bench',14,71),(109,'Awesome Silk Hat',1,61),(110,'Incredible Plastic Wallet',9,51),(111,'Fantastic Granite Coat',10,15),(112,'Fantastic Granite Gloves',7,117),(113,'Awesome Rubber Shoes',17,69),(114,'Heavy Duty Plastic Pants',14,26),(115,'Sleek Concrete Plate',9,26),(116,'Heavy Duty Leather Gloves',24,98),(117,'Practical Copper Bag',12,96),(118,'Small Wool Gloves',17,77),(119,'Intelligent Bronze Hat',16,38),(120,'Small Linen Wallet',21,65),(121,'Gorgeous Cotton Plate',23,38),(122,'Awesome Granite Lamp',21,63),(123,'Rustic Bronze Bag',10,90),(124,'Durable Marble Clock',13,86),(125,'Enormous Bronze Bench',17,76),(126,'Rustic Granite Computer',3,45),(127,'Mediocre Concrete Clock',12,98),(128,'Mediocre Rubber Table',19,93),(129,'Synergistic Copper Gloves',9,5),(130,'Enormous Leather Pants',19,89),(131,'Gorgeous Cotton Gloves',5,26),(132,'Fantastic Leather Knife',21,18),(133,'Synergistic Wool Shirt',19,97),(134,'Ergonomic Copper Plate',2,84),(135,'Enormous Marble Knife',22,117),(136,'Heavy Duty Plastic Shoes',15,89),(137,'Sleek Marble Computer',6,88),(138,'Lightweight Cotton Watch',14,72),(139,'Mediocre Linen Bottle',20,34),(140,'Small Bronze Gloves',2,98),(141,'Practical Marble Bag',14,31),(142,'Intelligent Wool Wallet',21,48),(143,'Synergistic Steel Hat',17,99),(144,'Aerodynamic Aluminum Hat',14,92),(145,'Awesome Wool Keyboard',10,26),(146,'Incredible Copper Bench',10,75),(147,'Incredible Wool Clock',7,63),(148,'Fantastic Rubber Clock',17,52),(149,'Durable Granite Wallet',19,41),(150,'Rustic Linen Pants',4,80),(151,'Rustic Bronze Bench',21,83),(152,'Rustic Copper Shoes',0,111),(153,'Gorgeous Cotton Gloves',0,43),(154,'Heavy Duty Plastic Chair',13,22),(155,'Mediocre Paper Coat',23,44),(156,'Durable Rubber Shoes',8,103),(157,'Rustic Paper Keyboard',9,59),(158,'Incredible Granite Bottle',3,65),(159,'Lightweight Copper Shoes',9,83),(160,'Awesome Bronze Car',3,1),(161,'Heavy Duty Silk Clock',9,62),(162,'Practical Leather Car',16,53),(163,'Mediocre Copper Pants',24,112),(164,'Synergistic Cotton Bench',5,101),(165,'Lightweight Silk Keyboard',1,96),(166,'Practical Marble Gloves',15,62),(167,'Ergonomic Steel Plate',12,75),(168,'Intelligent Wool Watch',7,116),(169,'Practical Plastic Hat',22,22),(170,'Small Linen Computer',5,92),(171,'Ergonomic Leather Computer',21,16),(172,'Sleek Iron Computer',24,11),(173,'Small Wooden Bottle',0,43),(174,'Mediocre Plastic Hat',17,6),(175,'Practical Granite Watch',9,37),(176,'Ergonomic Wool Chair',20,39),(177,'Incredible Cotton Chair',1,47),(178,'Practical Wooden Wallet',16,97),(179,'Enormous Paper Hat',16,89),(180,'Enormous Plastic Coat',19,73),(181,'Gorgeous Bronze Pants',15,65),(182,'Sleek Iron Keyboard',24,106),(183,'Durable Marble Wallet',14,63),(184,'Enormous Linen Bag',22,34),(185,'Incredible Aluminum Computer',9,112),(186,'Fantastic Bronze Plate',1,73),(187,'Sleek Silk Bottle',16,117),(188,'Practical Steel Knife',21,63),(189,'Gorgeous Steel Wallet',3,105),(190,'Gorgeous Rubber Bag',23,100),(191,'Synergistic Rubber Pants',7,64),(192,'Intelligent Marble Watch',8,97),(193,'Practical Concrete Watch',22,43),(194,'Heavy Duty Concrete Table',3,55),(195,'Enormous Granite Knife',11,29),(196,'Sleek Wool Table',20,107),(197,'Intelligent Copper Coat',12,14),(198,'Aerodynamic Paper Bag',2,37),(199,'Aerodynamic Steel Wallet',9,9),(200,'Durable Plastic Coat',15,20),(201,'Ergonomic Wooden Lamp',17,75),(202,'Mediocre Aluminum Watch',24,97),(203,'Gorgeous Plastic Computer',17,43),(204,'Ergonomic Granite Clock',0,116),(205,'Sleek Marble Computer',23,96),(206,'Small Aluminum Bag',8,38),(207,'Ergonomic Copper Shirt',15,44),(208,'Small Rubber Bottle',7,60),(209,'Heavy Duty Wooden Chair',9,11),(210,'Heavy Duty Copper Coat',24,100),(211,'Intelligent Iron Shirt',3,44),(212,'Heavy Duty Wool Hat',22,48),(213,'Ergonomic Leather Bottle',13,114),(214,'Mediocre Bronze Table',7,50),(215,'Awesome Aluminum Gloves',14,41),(216,'Lightweight Iron Keyboard',23,110),(217,'Heavy Duty Bronze Plate',17,33),(218,'Mediocre Wooden Bag',4,11),(219,'Synergistic Iron Coat',16,43),(220,'Ergonomic Plastic Pants',9,60),(221,'Rustic Wooden Chair',1,36),(222,'Durable Rubber Table',14,6),(223,'Lightweight Leather Bag',2,103),(224,'Incredible Granite Shoes',22,88),(225,'Mediocre Granite Keyboard',15,38),(226,'Practical Linen Knife',19,30),(227,'Fantastic Copper Table',11,93),(228,'Lightweight Rubber Keyboard',7,82),(229,'Synergistic Silk Hat',15,67),(230,'Small Paper Table',14,48),(231,'Small Cotton Shirt',7,29),(232,'Fantastic Bronze Watch',15,28),(233,'Sleek Concrete Lamp',22,9),(234,'Enormous Leather Bag',3,74),(235,'Ergonomic Plastic Bottle',3,12),(236,'Mediocre Steel Shoes',9,41),(237,'Fantastic Silk Bag',14,65),(238,'Incredible Iron Lamp',13,93),(239,'Sleek Aluminum Bag',21,70),(240,'Ergonomic Steel Bag',19,103),(241,'Awesome Bronze Coat',22,6),(242,'Aerodynamic Iron Hat',24,87),(243,'Enormous Rubber Bench',4,53),(244,'Lightweight Marble Wallet',3,99),(245,'Fantastic Steel Chair',10,92),(246,'Rustic Marble Coat',6,1),(247,'Durable Iron Computer',1,69),(248,'Small Concrete Gloves',18,3),(249,'Incredible Silk Chair',22,24),(250,'Gorgeous Linen Shirt',14,2),(251,'Aerodynamic Plastic Bag',7,90),(252,'Lightweight Iron Car',7,118),(253,'Small Granite Coat',15,30),(254,'Sleek Bronze Hat',4,95),(255,'Intelligent Silk Bottle',7,42),(256,'Heavy Duty Rubber Knife',21,102),(257,'Incredible Cotton Computer',22,97),(258,'Aerodynamic Steel Hat',23,108),(259,'Incredible Wooden Table',0,0),(260,'Intelligent Aluminum Table',7,108),(261,'Rustic Aluminum Plate',24,23),(262,'Heavy Duty Leather Gloves',9,47),(263,'Ergonomic Leather Computer',15,31),(264,'Mediocre Leather Computer',14,61),(265,'Synergistic Cotton Clock',12,74),(266,'Gorgeous Aluminum Shirt',16,58),(267,'Mediocre Wool Wallet',0,115),(268,'Heavy Duty Leather Gloves',14,53),(269,'Ergonomic Paper Computer',8,67),(270,'Small Aluminum Knife',21,32);

CREATE DATABASE IF NOT EXISTS `order_service`;
USE order_service;

CREATE TABLE IF NOT EXISTS `orders` (
                          `id` varchar(15) NOT NULL,
                          `user_id` int DEFAULT NULL,
                          `total` int DEFAULT NULL,
                          `first_name` varchar(45) DEFAULT NULL,
                          `last_name` varchar(45) DEFAULT NULL,
                          `address` varchar(45) DEFAULT NULL,
                          `creation_date` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `order_items` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(90) DEFAULT NULL,
                               `price` int DEFAULT NULL,
                               `order_id` varchar(15) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `o_id_idx` (`order_id`),
                               CONSTRAINT `o_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
