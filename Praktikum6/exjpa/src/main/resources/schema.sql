DROP SCHEMA PUBLIC CASCADE;

CREATE SEQUENCE seq1
  AS INTEGER
    START WITH 1;

CREATE TABLE address (
  id     BIGINT       NOT NULL PRIMARY KEY,
  street VARCHAR(255) NOT NULL
);

CREATE TABLE person (
  id         BIGINT NOT NULL PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  address_id BIGINT,
  FOREIGN KEY (address_id)
  REFERENCES address
    ON DELETE RESTRICT
);

ALTER TABLE person ADD UNIQUE (name);

CREATE TABLE phone (
  id        BIGINT       NOT NULL PRIMARY KEY,
  number    VARCHAR(255) NOT NULL,
  person_id BIGINT       NOT NULL,
  FOREIGN KEY (person_id)
  REFERENCES person
    ON DELETE RESTRICT
);
