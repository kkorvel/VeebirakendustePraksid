DROP SCHEMA PUBLIC CASCADE;

CREATE SEQUENCE seq1 AS INTEGER START WITH 10;

CREATE TABLE person (
       id BIGINT NOT NULL PRIMARY KEY,
       name VARCHAR(255) NOT NULL
);