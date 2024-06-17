
-- for add publishers
INSERT INTO TB_PUBLISHER (name,country,city,founded_year,email) VALUES
('Norma','Colombia','Bogota',2001,'norma@gmail.com'),
('HarperCollins','Estados Unidos','Nueva York',2000,'hc@gmail.com');

-- for add authors
INSERT INTO TB_AUTHOR (full_name,country,city,birthdate) VALUES
('Gabriel Garcia Marquez','Colombia','Santa Marta','1927-03-06'),
('Stephen King','Estados Unidos','Portland','1947-09-21');

-- for add books
INSERT INTO TB_BOOK (title,edition,publication_year,publisher_id) VALUES
('El coronel no tiene quien le escriba','1',1961,1),
('El resplandor','1',1977,2);

-- for add book-author relationships
INSERT INTO TB_BOOK_AUTHOR (book_id, author_id) VALUES
(1,1),
(2,2);