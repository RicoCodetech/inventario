CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

CREATE TABLE IF NOT EXISTS products (
    name VARCHAR(255) NOT NULL PRIMARY KEY,
    amount INT NOT NULL CHECK (amount >= 0),
    section VARCHAR(255) NOT NULL
);
