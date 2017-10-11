DROP SCHEMA public CASCADE;

CREATE SEQUENCE seq1 START WITH 1;

CREATE TABLE PERSON (
   id BIGINT NOT NULL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   age int
);

INSERT INTO PERSON (id, name, age)
  VALUES (NEXT VALUE FOR seq1, 'Jane', 20);
INSERT INTO PERSON (id, name, age)
  VALUES (NEXT VALUE FOR seq1, 'John', 21);