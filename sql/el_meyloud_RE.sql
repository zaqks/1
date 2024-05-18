-- MariaDB dump 10.19-11.3.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: el_meyloud_RE
-- ------------------------------------------------------
-- Server version	11.3.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agents`
--

DROP TABLE IF EXISTS `agents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccp` varchar(255) DEFAULT NULL,
  `ccp_key` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nin` varchar(255) DEFAULT NULL,
  `phonenum` varchar(255) DEFAULT NULL,
  `rip` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `x` float NOT NULL,
  `y` float NOT NULL,
  `endh` int(11) NOT NULL,
  `endw` int(11) NOT NULL,
  `starth` int(11) NOT NULL,
  `startw` int(11) NOT NULL,
  `admin` bit(1) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agents`
--

LOCK TABLES `agents` WRITE;
/*!40000 ALTER TABLE `agents` DISABLE KEYS */;
INSERT INTO `agents` VALUES
(1,'11111111','11','agent1@gmail.com','moh','1234567890','0567849302','1234567890123','e nigrou',30,20,0,0,0,0,'','1234567890',''),
(2,'77665544','34','agent2@gmail.com','lagent','0000000000','0987654857','123456789012345','li hasb roh directeur',36.7642,3.1468,17,4,8,0,'\0','1234567890',''),
(4,'77665544','34','agent4@gmail.com','lagent2','0000000123','0987654097','123456765212345','li hasb roh directeurrr',36.7642,3.1468,17,4,8,0,'\0','1234567890','');
/*!40000 ALTER TABLE `agents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ccp` varchar(8) DEFAULT NULL,
  `ccp_key` varchar(2) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nin` varchar(10) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `phonenum` varchar(13) DEFAULT NULL,
  `rip` varchar(15) DEFAULT NULL,
  `sells` bit(1) NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `x` float NOT NULL,
  `y` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES
(1,'12345678','12','client1@gmail.com','Cristinao','1234567890','1234567890','0777777777','123456789012345','','Ronaldo',36.7295,3.0905),
(2,'12345678','90','client2@gmail.com','el bachir','1234567892','1234567890','0677886655','123456789012345','','ben dada',36.7295,3.0905);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contracts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime(6) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `dst_client_id` int(11) DEFAULT NULL,
  `offer_id` int(11) DEFAULT NULL,
  `src_client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnbhb51819uegckw5t2kmm8xui` (`agent_id`),
  KEY `FKrft5kqxajp9wr41wit3aw3kap` (`dst_client_id`),
  KEY `FK3vdvpti6cakyel9fs5lm940mt` (`offer_id`),
  KEY `FK35tly79s7qbo1hr2kc7xk4jvk` (`src_client_id`),
  CONSTRAINT `FK35tly79s7qbo1hr2kc7xk4jvk` FOREIGN KEY (`src_client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK3vdvpti6cakyel9fs5lm940mt` FOREIGN KEY (`offer_id`) REFERENCES `offers` (`id`),
  CONSTRAINT `FKnbhb51819uegckw5t2kmm8xui` FOREIGN KEY (`agent_id`) REFERENCES `agents` (`id`),
  CONSTRAINT `FKrft5kqxajp9wr41wit3aw3kap` FOREIGN KEY (`dst_client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
INSERT INTO `contracts` VALUES
(1,'2024-05-17 18:50:06.755000',2,4,1,1,1),
(2,'2024-05-18 18:55:36.086000',0,4,2,1,1),
(3,'2024-05-18 22:21:27.754000',3,4,1,2,1),
(4,'2024-05-18 22:24:13.021000',1,4,2,2,1);
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avlbl` bit(1) NOT NULL,
  `checked` bit(1) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `rent` bit(1) NOT NULL,
  `property_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhgol4w22m5h4fq9m3r3qakkvj` (`property_id`),
  CONSTRAINT `FKhgol4w22m5h4fq9m3r3qakkvj` FOREIGN KEY (`property_id`) REFERENCES `properties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES
(1,'\0','','un tres belle maision pour les vaccances',999999,'',1),
(2,'\0','','no dogs, safe, no drug dealers',777777777,'\0',1);
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `properties`
--

DROP TABLE IF EXISTS `properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(64) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `floors` int(11) NOT NULL,
  `grgs` int(11) NOT NULL,
  `pools` int(11) NOT NULL,
  `rooms` int(11) NOT NULL,
  `surf` int(11) NOT NULL,
  `x` float NOT NULL,
  `y` float NOT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvttunomu46b10ykqhp6v0ryx` (`owner_id`),
  CONSTRAINT `FKhvttunomu46b10ykqhp6v0ryx` FOREIGN KEY (`owner_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `properties`
--

LOCK TABLES `properties` WRITE;
/*!40000 ALTER TABLE `properties` DISABLE KEYS */;
INSERT INTO `properties` VALUES
(1,'t7t l point ta3 constantine ta3 l3ami9in','belle vuee',0,0,0,1,100,0,0,1);
/*!40000 ALTER TABLE `properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_imgs`
--

DROP TABLE IF EXISTS `property_imgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_imgs` (
  `id` int(11) NOT NULL,
  `imgs` varchar(255) NOT NULL,
  KEY `FKi7dv1ipdm3xxr16u1couxlyj1` (`id`),
  CONSTRAINT `FKi7dv1ipdm3xxr16u1couxlyj1` FOREIGN KEY (`id`) REFERENCES `properties` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_imgs`
--

LOCK TABLES `property_imgs` WRITE;
/*!40000 ALTER TABLE `property_imgs` DISABLE KEYS */;
INSERT INTO `property_imgs` VALUES
(1,'/images/properties/Wed_May_08_07:25:29_CET_2024_Screenshot_20240428_102820.png'),
(1,'/images/properties/Wed_May_08_07:25:29_CET_2024_Screenshot_20240424_023213.png'),
(1,'/images/properties/Wed_May_08_07:25:29_CET_2024_Screenshot_20240424_002641.png'),
(1,'/images/properties/Wed_May_08_07:25:29_CET_2024_Screenshot_20240426_093007.png'),
(1,'/images/properties/Wed_May_08_07:25:29_CET_2024_Screenshot_20240424_000508.png');
/*!40000 ALTER TABLE `property_imgs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visits`
--

DROP TABLE IF EXISTS `visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime(6) DEFAULT NULL,
  `missed` bit(1) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `passed` bit(1) NOT NULL,
  `self` bit(1) NOT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `offer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjiqjtc1qc8jsct1fbtcmuk7v6` (`agent_id`),
  KEY `FKitoc4h8xfvle8onuqxb1fl0ix` (`client_id`),
  KEY `FKlua3dgen2xdooiyg99jkbu3qr` (`offer_id`),
  CONSTRAINT `FKitoc4h8xfvle8onuqxb1fl0ix` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKjiqjtc1qc8jsct1fbtcmuk7v6` FOREIGN KEY (`agent_id`) REFERENCES `agents` (`id`),
  CONSTRAINT `FKlua3dgen2xdooiyg99jkbu3qr` FOREIGN KEY (`offer_id`) REFERENCES `offers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
INSERT INTO `visits` VALUES
(1,'2024-05-27 08:00:00.000000','\0','','','',4,1,1),
(2,'2024-05-28 08:00:00.000000','\0','','','\0',4,2,1),
(3,'2024-05-28 09:00:00.000000','\0','','','',4,1,2),
(4,'2024-05-28 10:00:00.000000','\0','','','\0',4,2,2);
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-18 23:51:07
