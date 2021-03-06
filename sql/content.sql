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
(15, 'Lambert', 'Christophe', '1957-03-29', NULL),
(16, 'Downey Jr.', 'Robert', '1965-04-04', NULL),
(17, 'Evans', 'Chris', '1981-06-13', NULL);

--
-- Contenu de la table 'category'
--

INSERT INTO category VALUES
(1, 'Action'),
(2, 'Comédie'),
(3, 'Policier'),
(4, 'Western');

--
-- Contenu de la table 'director'
--

INSERT INTO director VALUES
(1, 'Oury', 'Gérard'),
(2, 'Chabrol', 'Claude'),
(3, 'Besson', 'Luc'),
(4, 'Besnard', 'Eric'),
(5, 'Whedon', 'Joss');

--
-- Contenu de la table 'film'
--

INSERT INTO movie VALUES
(1, 'Léon', 110, '1994-04-14', 17531000, 69250000, 3),
(2, 'Cash', 100, '2008-04-23', 18340000, 60340000, 4),
(3, 'La grande vadrouille', 132, '1966-12-01', 7227000, 51258000, 2),
(4, 'Subway', 104, '1985-04-10', 10567000, 70500000, 3),
(5, 'The Avengers', 143, '2012-04-25', 220000000, 1518594000, 5);

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
(4, 15, 'Fred'),
(5, 16, 'Iron Man'),
(5, 17, 'Captain America');

--
-- Contenu de la table 'film_category'
--

INSERT INTO movie_category VALUES
(1, 3),
(2, 3),
(3, 1),
(4, 3),
(5, 1);

INSERT INTO `user` VALUES
(1, 'Bruno', 'Buiret', 'bruno.buiret@etu.univ-lyon1.fr', NOW(), 'abb6d80c7043dbccaa67b767ce55a0441aa9d0774d57962796cabd520dbdedbc'),
(2, 'Thomas', 'Arnaud', 'thomas.arnaud@etu.univ-lyon1.fr', NOW(), 'abb6d80c7043dbccaa67b767ce55a0441aa9d0774d57962796cabd520dbdedbc'),
(3, 'Alexis', 'Rabilloud', 'alexis.rabilloud@etu.univ-lyon1.fr', NOW(), 'abb6d80c7043dbccaa67b767ce55a0441aa9d0774d57962796cabd520dbdedbc'),
(4, 'Christian', 'Vial', 'christian.vial@univ-lyon1.fr', NOW(), 'abb6d80c7043dbccaa67b767ce55a0441aa9d0774d57962796cabd520dbdedbc');