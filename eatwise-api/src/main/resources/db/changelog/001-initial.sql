--liquibase formatted sql

--changeset shashwatik:001
CREATE TABLE users (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

--changeset shashwatik:002
INSERT INTO users (id, name) VALUES (1, 'Alice'), (2, 'Bob');