/*
Navicat MySQL Data Transfer

Source Server         : BetGame
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : circuit_court

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-12-08 17:08:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `administrateur`
-- ----------------------------
DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of administrateur
-- ----------------------------
INSERT INTO `administrateur` VALUES ('1', 'martin', 'martin');
INSERT INTO `administrateur` VALUES ('2', 'lounadi', 'nassim');
INSERT INTO `administrateur` VALUES ('3', 'moutsouraev', 'magomed');
INSERT INTO `administrateur` VALUES ('6', 'test', 'add');
INSERT INTO `administrateur` VALUES ('7', 'martin', 'martin');
INSERT INTO `administrateur` VALUES ('8', 'test', 'add');
INSERT INTO `administrateur` VALUES ('9', 'martino', 'lebg');
INSERT INTO `administrateur` VALUES ('10', 'test', 'add');
INSERT INTO `administrateur` VALUES ('13', 'test', 'add');
INSERT INTO `administrateur` VALUES ('14', 'test2', 'add2');
INSERT INTO `administrateur` VALUES ('15', 'test2', 'add2');
INSERT INTO `administrateur` VALUES ('16', 'test2', 'add2');
INSERT INTO `administrateur` VALUES ('17', 'test2', 'add2');

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `idC` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`idC`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', 'luffy', 'zoro');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commande
-- ----------------------------
INSERT INTO `commande` VALUES ('1', 'pomme', '5', '0000-00-00', '0000-00-00', '1', '1', '1');
INSERT INTO `commande` VALUES ('2', 'annanas', '150', '15', '16', '1', '1', '1');
INSERT INTO `commande` VALUES ('3', 'annanas', '150', '15', '16', '1', '1', '1');
INSERT INTO `commande` VALUES ('4', 'fraise', '50', '15', '16', '1', '1', '1');
INSERT INTO `commande` VALUES ('5', 'annanas', '150', '15', '16', '1', '1', '1');
INSERT INTO `commande` VALUES ('6', 'annanas', '150', '15', '16', '1', '1', '1');
INSERT INTO `commande` VALUES ('8', 'annanas', '150', '15', '16', '1', '1', '1');
INSERT INTO `commande` VALUES ('9', 'annanas', '150', '15', '16', '1', '1', '1');

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
  PRIMARY KEY (`numSiret`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of producteur
-- ----------------------------
INSERT INTO `producteur` VALUES ('1', 'chambray', ' martino', '0782937471', '12548854');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tournee
-- ----------------------------
INSERT INTO `tournee` VALUES ('1', '0000-00-00', '0000-00-00', '1', '7855485478');

-- ----------------------------
-- Table structure for `vehicule`
-- ----------------------------
DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE `vehicule` (
  `numImmat` varchar(50) NOT NULL,
  `poids` float NOT NULL,
  PRIMARY KEY (`numImmat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vehicule
-- ----------------------------
INSERT INTO `vehicule` VALUES ('7855485478', '1000');
