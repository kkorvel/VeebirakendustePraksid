DROP SCHEMA public CASCADE;

CREATE SEQUENCE seq1 START WITH 1;

CREATE TABLE post (
       id BIGINT NOT NULL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       text VARCHAR(255) NOT NULL
);

INSERT INTO post (id, title, text) VALUES (NEXT VALUE FOR seq1, 'Post 1', 'Post 1 text ...');
INSERT INTO post (id, title, text) VALUES (NEXT VALUE FOR seq1, 'Post 2', 'Post 2 text ...');
