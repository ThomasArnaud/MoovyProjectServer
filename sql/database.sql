--
-- Structure de la table 'actor'
--

CREATE TABLE IF NOT EXISTS actor (
  id int(4) NOT NULL,
  name varchar(20) NOT NULL,
  first_name varchar(20) DEFAULT NULL,
  birth_date date DEFAULT NULL,
  death_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

--
-- Contenu de la table 'actor'
--

INSERT INTO actor VALUES
(1, 'Reno', 'Jean', '1948-07-30', NULL),
(5, 'Portman', 'Natalie', '1981-06-09', NULL),
(7, 'Dujardin', 'Jean', '1972-06-19', NULL),
(8, 'Bourvil', '', '1917-07-27', '1970-09-23'),
(12, 'De Funes', 'Louis', '1914-07-31', '1983-01-27'),
(13, 'Anglade', 'Jean-Hugues', '1955-07-29', NULL),
(15, 'Lambert', 'Christophe', '1957-03-29', NULL);

-- --------------------------------------------------------

--
-- Structure de la table 'category'
--

CREATE TABLE IF NOT EXISTS category (
  code varchar(2) NOT NULL,
  name varchar(20) NOT NULL,
  PRIMARY KEY (code)
);

--
-- Contenu de la table 'category'
--

INSERT INTO category VALUES
('AC', 'Action'),
('CO', 'Comédie'),
('PO', 'Policier'),
('WE', 'Western');

-- --------------------------------------------------------

--
-- Structure de la table 'film'
--

CREATE TABLE IF NOT EXISTS film (
  id int(4) NOT NULL,
  title varchar(30) NOT NULL,
  duration int(3) NOT NULL,
  release_date date NOT NULL,
  budget int(8) NOT NULL,
  benefit int(8) NOT NULL,
  id_director int(2) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Contenu de la table 'film'
--

INSERT INTO film VALUES
(1, 'Léon', 110, '1994-04-14', 17531000, 69250000, 3),
(2, 'Cash', 100, '2008-04-23', 18340000, 60340000, 4),
(3, 'La grande vadrouille', 132, '1966-12-01', 7227000, 51258000, 2),
(4, 'Subway', 104, '1985-04-10', 10567000, 70500000, 3);

-- --------------------------------------------------------

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
-- Contenu de la table 'character'
--

INSERT INTO `character` VALUES
(1, 1, 'Léon'),
(1, 5, 'Mathilda'),
(2, 1, 'Maxime Dubreuil'),
(2, 7, 'Cash'),
(3, 8, 'Augustin Bouvet'),
(3, 12, 'Stanislas Lefort'),
(4, 1, 'Le Batteur'),
(4, 13, 'Le Roller'),
(4, 15, 'Fred');

-- ---------------------------------------------------------

--
-- Structure de la table 'film_category'
--

CREATE TABLE IF NOT EXISTS film_category (
  id_film int(2) NOT NULL,
  code_category varchar(2) NOT NULL,
  PRIMARY KEY (id_film, code_category)
);

--
-- Contenu de la table 'film_category'
--

INSERT INTO film_category VALUES
(1, 'PO'),
(2, 'PO'),
(3, 'AC'),
(4, 'PO');

-- ---------------------------------------------------------

--
-- Structure de la table 'director'
--

CREATE TABLE IF NOT EXISTS director (
  id int(2) NOT NULL,
  name varchar(20) NOT NULL,
  first_name varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Contenu de la table 'director'
--

INSERT INTO director VALUES
(1, 'Oury', 'Gérard'),
(2, 'Chabrol', 'Claude'),
(3, 'Besson', 'Luc'),
(4, 'Besnard', 'Eric');

-- ---------------------------------------------------------

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `film`
--
ALTER TABLE film
  ADD CONSTRAINT film_ibfk_1 FOREIGN KEY (id_director) REFERENCES director (id);

--
-- Contraintes pour la table `character`
--
ALTER TABLE `character`
  ADD CONSTRAINT character_ibfk_1 FOREIGN KEY (id_film) REFERENCES film (id),
  ADD CONSTRAINT character_ibfk_2 FOREIGN KEY (id_actor) REFERENCES actor (id);
  
--
-- Contraintes pour la table `film_category`
--
ALTER TABLE film_category
  ADD CONSTRAINT film_category_ibfk_1 FOREIGN KEY (id_film) REFERENCES film (id),
  ADD CONSTRAINT film_category_ibfk_2 FOREIGN KEY (code_category) REFERENCES category (code);
  