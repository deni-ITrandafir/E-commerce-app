-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `categorii`
--

DROP TABLE IF EXISTS `categorii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorii` (
  `ID_Categorie` int NOT NULL AUTO_INCREMENT,
  `NumeCategorie` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_Categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comenzi`
--

DROP TABLE IF EXISTS `comenzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comenzi` (
  `ID_Comanda` int NOT NULL AUTO_INCREMENT,
  `ID_utilizator` int DEFAULT NULL,
  `DataComanda` datetime DEFAULT CURRENT_TIMESTAMP,
  `Status` enum('In curs','Verificata','Anulata') DEFAULT 'In curs',
  `Total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID_Comanda`),
  KEY `ID_utilizator` (`ID_utilizator`),
  CONSTRAINT `comenzi_ibfk_1` FOREIGN KEY (`ID_utilizator`) REFERENCES `utilizatori` (`ID_Utilizator`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cos`
--

DROP TABLE IF EXISTS `cos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cos` (
  `ID_Cos` int NOT NULL AUTO_INCREMENT,
  `ID_Utilizator` int DEFAULT NULL,
  `ID_Produs` int DEFAULT NULL,
  `Cantitate` int DEFAULT NULL,
  PRIMARY KEY (`ID_Cos`),
  UNIQUE KEY `ID_Utilizator` (`ID_Utilizator`,`ID_Produs`),
  KEY `ID_Produs` (`ID_Produs`),
  CONSTRAINT `cos_ibfk_1` FOREIGN KEY (`ID_Utilizator`) REFERENCES `utilizatori` (`ID_Utilizator`),
  CONSTRAINT `cos_ibfk_2` FOREIGN KEY (`ID_Produs`) REFERENCES `produse` (`ID_Produs`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detaliicomanda`
--

DROP TABLE IF EXISTS `detaliicomanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detaliicomanda` (
  `ID_DetaliiComanda` int NOT NULL AUTO_INCREMENT,
  `ID_Comanda` int DEFAULT NULL,
  `ID_Produs` int DEFAULT NULL,
  `Cantitate` int DEFAULT NULL,
  `Pret` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID_DetaliiComanda`),
  KEY `ID_Comanda` (`ID_Comanda`),
  KEY `ID_Produs` (`ID_Produs`),
  CONSTRAINT `detaliicomanda_ibfk_1` FOREIGN KEY (`ID_Comanda`) REFERENCES `comenzi` (`ID_Comanda`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detaliicomanda_ibfk_2` FOREIGN KEY (`ID_Produs`) REFERENCES `produse` (`ID_Produs`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `ID_Favorite` int NOT NULL AUTO_INCREMENT,
  `ID_Utilizator` int DEFAULT NULL,
  `ID_Produs` int DEFAULT NULL,
  PRIMARY KEY (`ID_Favorite`),
  UNIQUE KEY `ID_Utilizator` (`ID_Utilizator`,`ID_Produs`),
  KEY `ID_Produs` (`ID_Produs`),
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`ID_Utilizator`) REFERENCES `utilizatori` (`ID_Utilizator`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`ID_Produs`) REFERENCES `produse` (`ID_Produs`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produse`
--

DROP TABLE IF EXISTS `produse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produse` (
  `ID_Produs` int NOT NULL AUTO_INCREMENT,
  `ID_Categorie` int NOT NULL,
  `Brand` varchar(50) DEFAULT NULL,
  `Model` varchar(50) DEFAULT NULL,
  `Memorie` varchar(50) DEFAULT NULL,
  `Culoare` varchar(50) DEFAULT NULL,
  `Pret` decimal(10,2) DEFAULT NULL,
  `Stoc` int DEFAULT NULL,
  `Imagine` text,
  PRIMARY KEY (`ID_Produs`),
  KEY `FK_Produse_Categorii` (`ID_Categorie`),
  CONSTRAINT `FK_Produse_Categorii` FOREIGN KEY (`ID_Categorie`) REFERENCES `categorii` (`ID_Categorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `produse_ibfk_1` FOREIGN KEY (`ID_Categorie`) REFERENCES `categorii` (`ID_Categorie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utilizatori`
--

DROP TABLE IF EXISTS `utilizatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizatori` (
  `ID_Utilizator` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `Parola` varchar(255) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Prenume` varchar(50) NOT NULL,
  `Telefon` varchar(50) NOT NULL,
  `Oras` varchar(50) DEFAULT NULL,
  `Judet` varchar(50) DEFAULT NULL,
  `Strada` varchar(50) DEFAULT NULL,
  `Numar` int DEFAULT NULL,
  `IsAdmin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID_Utilizator`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-16 23:54:57
