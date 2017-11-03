USE stepahead;
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
-- Table structure for table `tbljob`
--

DROP TABLE IF EXISTS `tbljob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbljob` (
  `jobId` bigint(20) NOT NULL AUTO_INCREMENT,
  `additionalSkills` varchar(255) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `jobLocation` varchar(255) DEFAULT NULL,
  `jobResponsibilities` varchar(255) DEFAULT NULL,
  `jobTitle` varchar(255) DEFAULT NULL,
  `postedDate` date DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `domainId` bigint(20) DEFAULT NULL,
  `packageId` bigint(20) DEFAULT NULL,
  `personId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`jobId`),
  KEY `FK2wtd4t2urm1am8c2juj1xuq2h` (`companyId`),
  KEY `FKplcdc29cndf5bnsujfrb12b1n` (`domainId`),
  KEY `FKrnuhstr5ukpt7307vrede499p` (`packageId`),
  KEY `FKgq5nfljqa5l6o0ho14dmnrk5g` (`personId`),
  CONSTRAINT `FK2wtd4t2urm1am8c2juj1xuq2h` FOREIGN KEY (`companyId`) REFERENCES `tblcompany` (`companyId`),
  CONSTRAINT `FKgq5nfljqa5l6o0ho14dmnrk5g` FOREIGN KEY (`personId`) REFERENCES `tblperson` (`personId`),
  CONSTRAINT `FKplcdc29cndf5bnsujfrb12b1n` FOREIGN KEY (`domainId`) REFERENCES `tbldomain` (`domainId`),
  CONSTRAINT `FKrnuhstr5ukpt7307vrede499p` FOREIGN KEY (`packageId`) REFERENCES `tblpackage` (`packageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbljob`
--

LOCK TABLES `tbljob` WRITE;
/*!40000 ALTER TABLE `tbljob` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbljob` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-26  8:41:44
