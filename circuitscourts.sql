/*
Navicat MySQL Data Transfer

Source Server         : BetGame
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : circuitscourts

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-12-12 18:27:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `identifiant` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('5', 'nassim', '123456', '2');
INSERT INTO `accounts` VALUES ('6', 'martin', '123456', '1');
INSERT INTO `accounts` VALUES ('7', 'martin2', '123456', '2');
INSERT INTO `accounts` VALUES ('8', 'admin', '123456', '3');

-- ----------------------------
-- Table structure for `administrateur`
-- ----------------------------
DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `numTel` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of administrateur
-- ----------------------------

-- ----------------------------
-- Table structure for `producteur`
-- ----------------------------
DROP TABLE IF EXISTS `producteur`;
CREATE TABLE `producteur` (
  `numSiret` int(15) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(50) NOT NULL,
  `proprietaire` varchar(50) NOT NULL,
  `numTel` varchar(12) NOT NULL,
  `coordoneesGPS` varchar(50) NOT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`numSiret`),
  KEY `accId` (`accountId`),
  CONSTRAINT `accId` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of producteur
-- ----------------------------
INSERT INTO `producteur` VALUES ('1', 'test', 'test', 'test', 'test', '5');

-- ----------------------------
-- Table structure for `vehicule`
-- ----------------------------
DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE `vehicule` (
  `numImmat` varchar(50) NOT NULL,
  `poids` float NOT NULL,
  `numSiret` int(11) NOT NULL,
  PRIMARY KEY (`numImmat`),
  KEY `numProd` (`numSiret`),
  CONSTRAINT `numProd` FOREIGN KEY (`numSiret`) REFERENCES `producteur` (`numSiret`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vehicule
-- ----------------------------
INSERT INTO `vehicule` VALUES ('15', '150', '1');
INSERT INTO `vehicule` VALUES ('17', '700', '1');

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `idC` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`idC`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('2', 'nassim', 'nassim');

-- ----------------------------
-- Table structure for `tournee`
-- ----------------------------
DROP TABLE IF EXISTS `tournee`;
CREATE TABLE `tournee` (
  `idT` int(10) NOT NULL AUTO_INCREMENT,
  `horaireDebut` date NOT NULL,
  `horaireFin` date NOT NULL,
  `numSiret` int(15) NOT NULL,
  `numImmat` varchar(50) NOT NULL,
  PRIMARY KEY (`idT`),
  KEY `numSiret` (`numSiret`),
  KEY `numImmat` (`numImmat`),
  CONSTRAINT `numImmat` FOREIGN KEY (`numImmat`) REFERENCES `vehicule` (`numImmat`),
  CONSTRAINT `numSiret` FOREIGN KEY (`numSiret`) REFERENCES `producteur` (`numSiret`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tournee
-- ----------------------------
INSERT INTO `tournee` VALUES ('2', '0000-00-00', '0000-00-00', '1', '15');
INSERT INTO `tournee` VALUES ('3', '2022-12-12', '2022-12-13', '1', '17');

-- ----------------------------
-- Table structure for `commande`
-- ----------------------------
DROP TABLE IF EXISTS `commande`;
CREATE TABLE `commande` (
  `numCommande` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `poids` float NOT NULL,
  `horaireDebut` varchar(50) NOT NULL,
  `horaireFin` varchar(50) NOT NULL,
  `idClient` int(10) NOT NULL,
  `idTournee` int(10) NOT NULL,
  `numSiret` int(15) NOT NULL,
  PRIMARY KEY (`numCommande`),
  KEY `idClient` (`idClient`),
  KEY `idTournee` (`idTournee`),
  KEY `numS` (`numSiret`),
  CONSTRAINT `idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idC`),
  CONSTRAINT `idTournee` FOREIGN KEY (`idTournee`) REFERENCES `tournee` (`idT`),
  CONSTRAINT `numS` FOREIGN KEY (`numSiret`) REFERENCES `producteur` (`numSiret`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commande
-- ----------------------------
INSERT INTO `commande` VALUES ('1', 'test', '15', '10', '20', '2', '2', '1');
INSERT INTO `commande` VALUES ('2', 'test', '15', '10', '20', '2', '2', '1');
INSERT INTO `commande` VALUES ('11', 'testMArtin', '120', '10', '20', '2', '2', '1');