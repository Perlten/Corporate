-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: CorporateCA2-Krak-Test
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `ADDRESS`
--

LOCK TABLES `ADDRESS` WRITE;
/*!40000 ALTER TABLE `ADDRESS` DISABLE KEYS */;
INSERT INTO `ADDRESS` VALUES (1,'The new place','Tagensvej 208',1),(2,'Expensive','Pengevej 420',2);
/*!40000 ALTER TABLE `ADDRESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ADDRESS_INFOENTITY`
--

LOCK TABLES `ADDRESS_INFOENTITY` WRITE;
/*!40000 ALTER TABLE `ADDRESS_INFOENTITY` DISABLE KEYS */;
INSERT INTO `ADDRESS_INFOENTITY` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `ADDRESS_INFOENTITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CITYINFO`
--

LOCK TABLES `CITYINFO` WRITE;
/*!40000 ALTER TABLE `CITYINFO` DISABLE KEYS */;
INSERT INTO `CITYINFO` VALUES (1,'Kobenhavn NV',2400),(2,'Hellerup',2900);
/*!40000 ALTER TABLE `CITYINFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `COMPANY`
--

LOCK TABLES `COMPANY` WRITE;
/*!40000 ALTER TABLE `COMPANY` DISABLE KEYS */;
INSERT INTO `COMPANY` VALUES (2,39042110,'Very corporate',1000000,'Corporate',4);
/*!40000 ALTER TABLE `COMPANY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `HOBBY`
--

LOCK TABLES `HOBBY` WRITE;
/*!40000 ALTER TABLE `HOBBY` DISABLE KEYS */;
INSERT INTO `HOBBY` VALUES (1,'A geeky hobby','Chess');
/*!40000 ALTER TABLE `HOBBY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `HOBBY_PERSON`
--

LOCK TABLES `HOBBY_PERSON` WRITE;
/*!40000 ALTER TABLE `HOBBY_PERSON` DISABLE KEYS */;
INSERT INTO `HOBBY_PERSON` VALUES (1,1);
/*!40000 ALTER TABLE `HOBBY_PERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `INFOENTITY`
--

LOCK TABLES `INFOENTITY` WRITE;
/*!40000 ALTER TABLE `INFOENTITY` DISABLE KEYS */;
INSERT INTO `INFOENTITY` VALUES (1,'P','adlass97@gmail.com'),(2,'C','corporate@money.dk');
/*!40000 ALTER TABLE `INFOENTITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PERSON`
--

LOCK TABLES `PERSON` WRITE;
/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
INSERT INTO `PERSON` VALUES (1,'Adam','Lass');
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PHONE`
--

LOCK TABLES `PHONE` WRITE;
/*!40000 ALTER TABLE `PHONE` DISABLE KEYS */;
INSERT INTO `PHONE` VALUES (1,'A very nice phone',20681825,1),(2,'Very corporate, remember encryption!',32622145,2);
/*!40000 ALTER TABLE `PHONE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-03 10:02:44

