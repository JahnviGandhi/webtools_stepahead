-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: stepahead
-- ------------------------------------------------------
-- Server version	5.6.30-log

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
-- Table structure for table `tblhrperson`
--

DROP TABLE IF EXISTS `tblhrperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblhrperson` (
  `personId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `positionId` bigint(20) DEFAULT NULL,
  `domainId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`personId`),
  KEY `FKtgfu5b3c70luhcgwvplntxglv` (`companyId`),
  KEY `FK71ntqi443vjfk6nnhn5pxt8p1` (`positionId`),
  KEY `FK69am43nc4x1iuvkd1rhvip4sn` (`domainId`),
  CONSTRAINT `FK69am43nc4x1iuvkd1rhvip4sn` FOREIGN KEY (`domainId`) REFERENCES `tbldomain` (`domainId`),
  CONSTRAINT `FK6sy6urpssbctxnordc4el7lsf` FOREIGN KEY (`personId`) REFERENCES `tbluser` (`personId`),
  CONSTRAINT `FK71ntqi443vjfk6nnhn5pxt8p1` FOREIGN KEY (`positionId`) REFERENCES `tblposition` (`positionId`),
  CONSTRAINT `FKtgfu5b3c70luhcgwvplntxglv` FOREIGN KEY (`companyId`) REFERENCES `tblcompany` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblhrperson`
--

LOCK TABLES `tblhrperson` WRITE;
/*!40000 ALTER TABLE `tblhrperson` DISABLE KEYS */;
INSERT INTO `tblhrperson` VALUES (2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tblhrperson` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-26  8:41:46
