
CREATE TABLE IF NOT EXISTS TB_PUBLISHER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    founded_year INT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TB_AUTHOR(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS TB_BOOK(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    edition VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL,
    publisher_id BIGINT,
    FOREIGN KEY (publisher_id) REFERENCES TB_PUBLISHER(id)
);

-- Join Table for Book and Author Many-to-Many relationship
CREATE TABLE IF NOT EXISTS TB_BOOK_AUTHOR(
    book_id BIGINT,
    author_id BIGINT,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES TB_BOOK(id),
    FOREIGN KEY (author_id) REFERENCES TB_AUTHOR(id)
);