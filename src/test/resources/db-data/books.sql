truncate table book cascade;
INSERT INTO book (id, author, isbn, price, title, publisher, publication_year, version, created_date, last_modified_date)
VALUES (-1, 'Author 1', 'ISBN-1', 10.99, 'Book Title 1', 'Publisher 1', 2022, 1, NOW(), NOW());

INSERT INTO book (id, author, isbn, price, title, publisher, publication_year, version, created_date, last_modified_date)
VALUES (-2, 'Author 2', 'ISBN-2', 19.99, 'Book Title 2', 'Publisher 2', 2023, 1, NOW(), NOW());

INSERT INTO book (id, author, isbn, price, title, publisher, publication_year, version, created_date, last_modified_date)
VALUES (-3, 'Author 3', 'ISBN-3', 14.99, 'Book Title 3', 'Publisher 3', 2021, 1, NOW(), NOW());

INSERT INTO book (id, author, isbn, price, title, publisher, publication_year, version, created_date, last_modified_date)
VALUES (-4, 'Author 4', 'ISBN-4', 8.99, 'Book Title 4', 'Publisher 4', 2022, 1, NOW(), NOW());
