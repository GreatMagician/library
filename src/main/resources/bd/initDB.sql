DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 10;

CREATE TABLE authors
(
  id         int8 PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR(20) NOT NULL,
  family     VARCHAR(30) NOT NULL
);


CREATE TABLE books (
  id          int8 PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  description VARCHAR,
  pathcover   VARCHAR,
  author_id   int8 REFERENCES authors (id) ON DELETE CASCADE
);

