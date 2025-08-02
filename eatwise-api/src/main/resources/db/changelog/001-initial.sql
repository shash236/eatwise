--liquibase formatted sql

--changeset shashwatik:001
CREATE TABLE meal (
    meal_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    meal_type VARCHAR(32) NOT NULL,
    meal_time VARCHAR(5) NOT NULL, -- e.g., '13:00'
    meal_answers_json JSON NOT NULL,
    timestamp DATETIME NOT NULL
);

--changeset shashwatik:002
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE email_otp (
    email VARCHAR(255) PRIMARY KEY,
    otp VARCHAR(10),
    expires_at DATETIME
);

