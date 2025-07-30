--liquibase formatted sql

--changeset shashwatik:001
CREATE TABLE meal (
    meal_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    timestamp DATETIME NOT NULL,
    meal_type VARCHAR(32) NOT NULL,
    meal_time VARCHAR(5) NOT NULL, -- e.g., '13:00'
    meal_answers JSON NOT NULL
);


--changeset shashwatik:002