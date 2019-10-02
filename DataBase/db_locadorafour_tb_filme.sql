CREATE DATABASE  IF NOT EXISTS `db_locadorafour` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_locadorafour`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_locadorafour
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `tb_filme`
--

DROP TABLE IF EXISTS `tb_filme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_filme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `diretor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tb_filme` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_filme`
--

LOCK TABLES `tb_filme` WRITE;
/*!40000 ALTER TABLE `tb_filme` DISABLE KEYS */;
INSERT INTO `tb_filme` VALUES (1,'Iron Man','Jon Favreau'),(2,'The Incredible Hulk','Louis Leterrier'),(3,'Iron Man 2','Jon Favreau'),(4,'Thor','Kenneth Branagh'),(5,'Captain America: The First Avenger','Joe Johnston'),(6,'The Avengers','Joss Whedon'),(7,'Iron Man Three','Shane Black'),(8,'Thor: The Dark World','Alan Taylor'),(9,'Captain America: The Winter Soldier','Anthony Russo, Joe Russo'),(10,'Guardians of the Galaxy','James Gunn'),(11,'Avengers: Age of Ultron','Joss Whedon'),(12,'Ant-Man','Peyton Reed'),(13,'Captain America: Civil War','Anthony Russo, Joe Russo'),(14,'Doctor Strange','Scott Derrickson'),(15,'Guardians of the Galaxy Vol. 2','James Gunn'),(16,'Spider-Man: Homecoming','Jon Watts'),(17,'Thor: Ragnarok','Taika Waititi'),(18,'Black Panther','Ryan Coogler'),(19,'Avengers: Infinity War','Anthony Russo, Joe Russo'),(20,'Ant-Man and the Wasp','Peyton Reed'),(21,'Captain Marvel','Anna Boden, Ryan Fleck'),(22,'Avengers: Endgame','Anthony Russo, Joe Russo'),(23,'Spider-Man: Far from Home','Jon Watts'),(24,'Guardians of the Galaxy Vol. 3','James Gunn'),(25,'Untitled Disney Marvel Film',''),(26,'Black Widow','Cate Shortland'),(27,'Shang-Chi and the Legend of the Ten Rings','Destin Daniel Cretton'),(28,'Black Panther II','Ryan Coogler'),(29,'Doctor Strange in the Multiverse of Madness','Scott Derrickson');
/*!40000 ALTER TABLE `tb_filme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-02 20:01:04
