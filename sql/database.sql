--
-- Base de données 'moovy'
--
CREATE DATABASE `moovy` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `moovy`;

--
-- Structure de la table 'actor'
--

CREATE TABLE IF NOT EXISTS `actor` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `death_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Structure de la table 'category'
--

CREATE TABLE IF NOT EXISTS `category` (
  `code` varchar(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Structure de la table 'movie'
--

CREATE TABLE IF NOT EXISTS `movie` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `duration` int(3) NOT NULL,
  `release_date` date NOT NULL,
  `budget` int(8) NOT NULL,
  `benefit` int(8) NOT NULL,
  `id_director` int(2) NOT NULL,
  PRIMARY KEY (`id`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Structure de la table 'character'
--

CREATE TABLE IF NOT EXISTS `character` (
  `id_movie` int(4) NOT NULL,
  `id_actor` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id_movie`, `id_actor`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Structure de la table 'movie_category'
--

CREATE TABLE IF NOT EXISTS `movie_category` (
  `id_movie` int(2) NOT NULL,
  `code_category` varchar(2) NOT NULL,
  PRIMARY KEY (`id_movie`, `code_category`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Structure de la table 'director'
--

CREATE TABLE IF NOT EXISTS `director` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Stucture de la table utilisateur
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `created_at` datetime NOT NULL,
  `password` char(64) NOT NULL,
  PRIMARY KEY(`id`)
)
CHARACTER SET utf8 COLLATE utf8_general_ci
ENGINE=InnoDB;

--
-- Contraintes pour la table 'movie'
--
ALTER TABLE `movie`
  ADD CONSTRAINT `movie_ibfk_1` FOREIGN KEY (`id_director`) REFERENCES `director` (`id`);

--
-- Contraintes pour la table 'character'
--
ALTER TABLE `character`
  ADD CONSTRAINT `character_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES movie (`id`),
  ADD CONSTRAINT `character_ibfk_2` FOREIGN KEY (`id_actor`) REFERENCES actor (`id`);

--
-- Contraintes pour la table 'movie_category'
--
ALTER TABLE `movie_category`
  ADD CONSTRAINT `movie_category_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES movie (`id`),
  ADD CONSTRAINT `movie_category_ibfk_2` FOREIGN KEY (`code_category`) REFERENCES category (`code`);

--
-- Contraintes pour la table 'user'
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibu_1` UNIQUE KEY (`email`);