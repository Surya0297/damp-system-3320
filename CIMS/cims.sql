-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: crimeinformationmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `crime`
--

DROP TABLE IF EXISTS `crime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crime` (
  `id` int NOT NULL AUTO_INCREMENT,
  `crimeId` varchar(45) NOT NULL,
  `crimeType` varchar(20) NOT NULL,
  `description` varchar(250) NOT NULL,
  `psArea` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `victimName` varchar(25) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'unsolved',
  `isDelete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `crimeId_UNIQUE` (`crimeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crime`
--

LOCK TABLES `crime` WRITE;
/*!40000 ALTER TABLE `crime` DISABLE KEYS */;
INSERT INTO `crime` VALUES (1,'CR01','Robbery','Robbed At gun point at 7 pm','City Center','2023-01-01','Surya Chauhan','solved',0),(3,'CR02','Theft','Wallet stolen at Station 12 noon','Morar','2020-02-02','Akash','solved',0),(4,'CR03','Robbery','House Robbed At Midnight','City center','2018-09-09','Suyash Sharma','unsolved',0),(5,'CR04','Theft','Car Stolen From home','Morar','2016-09-08','Vinay Yadav','unsolved',0),(6,'CR05','Robbery','Robbed house at midnight','Morar','2020-02-02','Rishabh yadav','unsolved',0);
/*!40000 ALTER TABLE `crime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crime_criminal`
--

DROP TABLE IF EXISTS `crime_criminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crime_criminal` (
  `joinId` int NOT NULL AUTO_INCREMENT,
  `crimeId` int DEFAULT NULL,
  `criminalId` int DEFAULT NULL,
  PRIMARY KEY (`joinId`),
  UNIQUE KEY `uc_crime` (`crimeId`,`criminalId`),
  KEY `criminal_crime_fk_idx` (`criminalId`),
  KEY `crime_criminal_fk_idx` (`crimeId`),
  CONSTRAINT `crime_criminal_fk` FOREIGN KEY (`crimeId`) REFERENCES `crime` (`id`),
  CONSTRAINT `criminal_crime_fk` FOREIGN KEY (`criminalId`) REFERENCES `criminal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crime_criminal`
--

LOCK TABLES `crime_criminal` WRITE;
/*!40000 ALTER TABLE `crime_criminal` DISABLE KEYS */;
INSERT INTO `crime_criminal` VALUES (1,1,1),(2,3,1);
/*!40000 ALTER TABLE `crime_criminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criminal`
--

DROP TABLE IF EXISTS `criminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `criminalId` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `identifyingMark` varchar(45) DEFAULT NULL,
  `firstArrestDate` date DEFAULT NULL,
  `arrestedFromPsArea` varchar(45) DEFAULT NULL,
  `isDelete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `criminalId_UNIQUE` (`criminalId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criminal`
--

LOCK TABLES `criminal` WRITE;
/*!40000 ALTER TABLE `criminal` DISABLE KEYS */;
INSERT INTO `criminal` VALUES (1,'CRI01','Sonu Yadav','2000-08-09','Male','Mole on neck','2006-07-04','City Center',0),(2,'CRI02','Rakesh','1995-02-02','Male','Scar on head','2000-02-02','Morar',0),(3,'CRI03','Farhan','1990-09-01','Male','Scar on right hand','2008-09-08','City Center',0),(4,'CRI04','Ramu','1998-06-03','Male','Mole on right cheek','2000-09-09','City Center',0),(5,'CRI05','Shreyansh','2000-02-02','Male','Mole on cheek','2004-03-03','Morar',0);
/*!40000 ALTER TABLE `criminal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-02 14:15:41
