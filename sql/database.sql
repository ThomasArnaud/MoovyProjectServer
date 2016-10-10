--
-- Structure de la table 'actor'
--

CREATE TABLE IF NOT EXISTS actor (
  id int(4) NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  first_name varchar(20) DEFAULT NULL,
  birth_date date DEFAULT NULL,
  death_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

--
-- Structure de la table 'category'
--

CREATE TABLE IF NOT EXISTS category (
  code varchar(2) NOT NULL,
  name varchar(20) NOT NULL,
  PRIMARY KEY (code)
);

--
-- Structure de la table 'film'
--

CREATE TABLE IF NOT EXISTS film (
  id int(4) NOT NULL AUTO_INCREMENT,
  title varchar(30) NOT NULL,
  duration int(3) NOT NULL,
  release_date date NOT NULL,
  budget int(8) NOT NULL,
  benefit int(8) NOT NULL,
  id_director int(2) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Structure de la table 'character'
--

CREATE TABLE IF NOT EXISTS `character` (
  id_film int(4) NOT NULL,
  id_actor int(4) NOT NULL,
  name varchar(30) NOT NULL,
  PRIMARY KEY (id_film, id_actor)
);

--
-- Structure de la table 'film_category'
--

CREATE TABLE IF NOT EXISTS film_category (
  id_film int(2) NOT NULL,
  code_category varchar(2) NOT NULL,
  PRIMARY KEY (id_film, code_category)
);

--
-- Structure de la table 'director'
--

CREATE TABLE IF NOT EXISTS director (
  id int(2) NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  first_name varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Contraintes pour la table 'film'
--
ALTER TABLE film
  ADD CONSTRAINT film_ibfk_1 FOREIGN KEY (id_director) REFERENCES director (id);

--
-- Contraintes pour la table 'character'
--
ALTER TABLE `character`
  ADD CONSTRAINT character_ibfk_1 FOREIGN KEY (id_film) REFERENCES film (id),
  ADD CONSTRAINT character_ibfk_2 FOREIGN KEY (id_actor) REFERENCES actor (id);
  
--
-- Contraintes pour la table 'film_category'
--
ALTER TABLE film_category
  ADD CONSTRAINT film_category_ibfk_1 FOREIGN KEY (id_film) REFERENCES film (id),
  ADD CONSTRAINT film_category_ibfk_2 FOREIGN KEY (code_category) REFERENCES category (code);
  