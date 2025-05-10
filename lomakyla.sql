-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lomakyla
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `asiakas`
--

DROP TABLE IF EXISTS `asiakas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiakas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sahkoposti` varchar(255) NOT NULL,
  `nimi` varchar(255) NOT NULL,
  `puhelinnumero` varchar(255) NOT NULL,
  `maa` varchar(255) NOT NULL,
  `yritys` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiakas`
--

LOCK TABLES `asiakas` WRITE;
/*!40000 ALTER TABLE `asiakas` DISABLE KEYS */;
/*!40000 ALTER TABLE `asiakas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lasku`
--

DROP TABLE IF EXISTS `lasku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lasku` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hinta` float NOT NULL,
  `laskutustapa` varchar(255) NOT NULL,
  `erapaiva` date NOT NULL,
  `tila` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lasku`
--

LOCK TABLES `lasku` WRITE;
/*!40000 ALTER TABLE `lasku` DISABLE KEYS */;
/*!40000 ALTER TABLE `lasku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mokki`
--

DROP TABLE IF EXISTS `mokki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mokki` (
  `id` int NOT NULL AUTO_INCREMENT,
  `osoite` varchar(255) NOT NULL,
  `tila` tinyint NOT NULL,
  `koko` int NOT NULL,
  `huoneet` int NOT NULL,
  `hinta_per_yo` float NOT NULL,
  `luotu` datetime NOT NULL,
  `paivitetty` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mokki`
--

LOCK TABLES `mokki` WRITE;
/*!40000 ALTER TABLE `mokki` DISABLE KEYS */;
/*!40000 ALTER TABLE `mokki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varaus`
--

DROP TABLE IF EXISTS `varaus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `varaus` (
  `varaus_id` int NOT NULL AUTO_INCREMENT,
  `aloituspaiva` date NOT NULL,
  `lopetuspaiva` date NOT NULL,
  `luotu` datetime NOT NULL,
  `paivitetty` datetime NOT NULL,
  `asiakas_id` int NOT NULL,
  `mokki_id` int NOT NULL,
  `lasku_id` int DEFAULT NULL,
  PRIMARY KEY (`varaus_id`),
  KEY `varaus_asiakas_id_foreign_idx` (`asiakas_id`),
  KEY `varaus_mokki_id_foreign_idx` (`mokki_id`),
  KEY `varaus_lasku_id_foreign_idx` (`lasku_id`),
  CONSTRAINT `varaus_asiakas_id_foreign` FOREIGN KEY (`asiakas_id`) REFERENCES `asiakas` (`id`),
  CONSTRAINT `varaus_lasku_id_foreign` FOREIGN KEY (`lasku_id`) REFERENCES `lasku` (`id`),
  CONSTRAINT `varaus_mokki_id_foreign` FOREIGN KEY (`mokki_id`) REFERENCES `mokki` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varaus`
--

LOCK TABLES `varaus` WRITE;
/*!40000 ALTER TABLE `varaus` DISABLE KEYS */;
/*!40000 ALTER TABLE `varaus` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-10 21:32:41
