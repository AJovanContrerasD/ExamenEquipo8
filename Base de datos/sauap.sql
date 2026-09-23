-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: sauap
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
-- Table structure for table `administrador`
--

CREATE SCHEMA IF NOT EXISTS `sauap` DEFAULT CHARACTER SET utf8 ;
USE `sauap` ;

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `idadministrador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `appaterno` varchar(50) NOT NULL,
  `apmaterno` varchar(50) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  PRIMARY KEY (`idadministrador`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'Ernesto','Cota','Cazares','ernesto','12345678');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignacion`
--

DROP TABLE IF EXISTS `asignacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignacion` (
  `idasignacion` int(11) NOT NULL AUTO_INCREMENT,
  `idprofesor` int(11) NOT NULL,
  `idunidad` int(11) NOT NULL,
  `idadministrador` int(11) NOT NULL,
  `grupo` varchar(10) NOT NULL,
  `semestre` varchar(10) NOT NULL,
  `diasemana` varchar(15) NOT NULL,
  `tipo` varchar(15) NOT NULL DEFAULT 'Clase',
  `horainicio` time NOT NULL,
  `horafin` time NOT NULL,
  PRIMARY KEY (`idasignacion`),
  KEY `fk_asignacion_profesor` (`idprofesor`),
  KEY `fk_asignacion_unidad` (`idunidad`),
  KEY `fk_asignacion_administrador` (`idadministrador`),
  CONSTRAINT `fk_asignacion_administrador` FOREIGN KEY (`idadministrador`) REFERENCES `administrador` (`idadministrador`),
  CONSTRAINT `fk_asignacion_profesor` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`),
  CONSTRAINT `fk_asignacion_unidad` FOREIGN KEY (`idunidad`) REFERENCES `unidad_aprendizaje` (`idunidad`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion`
--

LOCK TABLES `asignacion` WRITE;
/*!40000 ALTER TABLE `asignacion` DISABLE KEYS */;
INSERT INTO `asignacion` VALUES (6,21,1,1,'629','2026-2','Lunes','Clase','07:00:00','10:00:00'),(8,21,2,1,'141','2026-2','Miercoles','Clase','08:00:00','10:00:00');
/*!40000 ALTER TABLE `asignacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `idprofesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `appaterno` varchar(50) NOT NULL,
  `apmaterno` varchar(50) NOT NULL,
  `rfc` varchar(13) NOT NULL,
  `idadministrador` int(11) NOT NULL,
  PRIMARY KEY (`idprofesor`),
  KEY `fk_profesor_administrador` (`idadministrador`),
  CONSTRAINT `fk_profesor_administrador` FOREIGN KEY (`idadministrador`) REFERENCES `administrador` (`idadministrador`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (21,'Ernesto','Cota','Cazares','COCE850312AB2',1);
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_aprendizaje`
--

DROP TABLE IF EXISTS `unidad_aprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidad_aprendizaje` (
  `idunidad` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `horasclase` int(11) NOT NULL,
  `horastaller` int(11) NOT NULL,
  `horaslaboratorio` int(11) NOT NULL,
  `idadministrador` int(11) NOT NULL,
  PRIMARY KEY (`idunidad`),
  KEY `fk_unidad_administrador` (`idadministrador`),
  CONSTRAINT `fk_unidad_administrador` FOREIGN KEY (`idadministrador`) REFERENCES `administrador` (`idadministrador`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_aprendizaje`
--

LOCK TABLES `unidad_aprendizaje` WRITE;
/*!40000 ALTER TABLE `unidad_aprendizaje` DISABLE KEYS */;
INSERT INTO `unidad_aprendizaje` VALUES (1,'Calculo Diferencial',3,1,0,1),(2,'Programacion Orientada a Objetos',2,0,3,1);
/*!40000 ALTER TABLE `unidad_aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-22 16:12:14
