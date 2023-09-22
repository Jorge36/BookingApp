-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: db_booking
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authentication_token`
--

DROP TABLE IF EXISTS `authentication_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authentication_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_authentication_token_user1_idx` (`user_id`),
  CONSTRAINT `fk_authentication_token_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authentication_token`
--

LOCK TABLES `authentication_token` WRITE;
/*!40000 ALTER TABLE `authentication_token` DISABLE KEYS */;
INSERT INTO `authentication_token` VALUES (1,'2023-07-21 00:48:59.517000','22bfc1ac-fab3-4d63-88a8-76f7579d8751',1),(2,'2023-07-21 00:40:16.961000','c7b36cdf-9562-451e-90f5-19404441b46a',2),(3,'2023-07-25 00:28:15.671000','967757e5-d552-4f19-b44e-ed5ed24f2a80',3),(68,'2023-08-15 00:10:29.547000','84207a9e-d323-48e2-915c-73afb4e39fb1',68),(69,'2023-08-15 00:12:05.622000','4d593698-fbf7-4fdc-b39e-3dd18f547e26',69),(70,'2023-08-15 00:19:13.976000','e21ef374-6cfc-4c0b-a4a1-bbdfaadc9f4d',70),(71,'2023-08-15 00:43:17.061000','2b4f00ca-584f-4a1d-8043-e519c524cee8',71),(72,'2023-08-15 00:45:03.866000','d2fe6004-6f53-4ed1-837d-bde886665b70',72),(73,'2023-08-15 00:12:29.292000','c5cc0be8-c932-4877-ac0a-072c96d740b4',73),(74,'2023-08-15 00:25:46.104000','de919a21-943f-4b29-b019-43668036313f',74),(75,'2023-08-15 00:48:54.262000','486d3e46-f5bf-4da7-8e64-244296e83887',75),(76,'2023-08-15 00:52:37.863000','6a95c76d-4af9-47ca-8f9f-5375be0ee1bb',76),(77,'2023-08-15 00:58:20.570000','f89902ac-0fd8-494f-af48-69034386668c',77),(78,'2023-08-15 00:59:26.624000','8c1520f0-4d8d-44c8-9720-3a5ff29d87ac',78),(79,'2023-08-15 00:00:41.242000','341b5875-a08b-4a37-998d-b6e007441f14',79),(80,'2023-08-15 00:50:14.184000','938a90a3-196b-48aa-93b3-2e98154451ea',80),(81,'2023-08-15 00:32:11.225000','f1ab4333-704d-41df-9b52-329007a0b648',81);
/*!40000 ALTER TABLE `authentication_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_datetime` datetime(6) NOT NULL,
  `user_id` int NOT NULL,
  `table_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_booking_user_idx` (`user_id`),
  KEY `fk_booking_table1_idx` (`table_id`),
  CONSTRAINT `fk_booking_table1` FOREIGN KEY (`table_id`) REFERENCES `table_for_booking` (`id`),
  CONSTRAINT `fk_booking_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (2,'2023-07-21 14:00:00.000000',1,2),(3,'2023-07-21 20:00:00.000000',1,2),(4,'2023-07-21 20:00:00.000000',1,3),(5,'2023-07-22 14:00:00.000000',1,4),(6,'2023-07-23 14:00:00.000000',2,5),(7,'2023-08-01 20:00:00.000000',3,5),(8,'2023-08-01 20:00:00.000000',1,3),(9,'2023-08-01 14:00:00.000000',1,3),(10,'2023-08-01 12:00:00.000000',3,1),(11,'2023-08-05 16:00:00.000000',3,2),(12,'2023-08-05 16:00:00.000000',2,4),(13,'2023-08-05 18:00:00.000000',1,2),(14,'2023-08-05 16:00:00.000000',1,3),(15,'2023-08-05 12:00:00.000000',2,3),(16,'2023-08-05 12:00:00.000000',1,5);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_for_booking`
--

DROP TABLE IF EXISTS `table_for_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_for_booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity_people` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_for_booking`
--

LOCK TABLES `table_for_booking` WRITE;
/*!40000 ALTER TABLE `table_for_booking` DISABLE KEYS */;
INSERT INTO `table_for_booking` VALUES (1,5),(2,2),(3,3),(4,2),(5,3);
/*!40000 ALTER TABLE `table_for_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `mobile_phone` varchar(15) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `logged_in` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'johnmick@gmail.com','Solucion1234@','123-456-7890','Jhon Mick',_binary '\0'),(2,'davidpaul@hotmail.com','Solucion1234@','123-456-7890','David Paul',_binary '\0'),(3,'juanloza@gmail.com','Solucion1234@','123-456-7890','Juan Loza',_binary '\0'),(68,'pablo@hotmail.com','1234567890','22','Pablo Perez',_binary '\0'),(69,'marc@hotmail.com','1234567890','08716755480','Marc Nort',_binary '\0'),(70,'marcnort@hotmail.com','1234567890','08716755480','Marc Nort',_binary '\0'),(71,'brian@hotmail.com','1234567890','08716755480','Brian Oconeel',_binary '\0'),(72,'gary@hotmail.com','1234567890','08716755480','Gary Lind',_binary '\0'),(73,'lisa@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(74,'Bno@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(75,'bono@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(76,'poringa@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(77,'robert@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(78,'pato@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(79,'eeee@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(80,'hola@hotmail.com','1234567890','08716755480','Lisa Lind',_binary '\0'),(81,'perro@hotmail.com','e807f1fcf82d132f9bb018ca6738a19f','08716755480','Lisa Lind',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-17 12:41:40
