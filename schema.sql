CREATE DATABASE IF NOT EXISTS inventario;
USE inventario;

CREATE TABLE IF NOT EXISTS productos (
    name VARCHAR(255) NOT NULL PRIMARY KEY,
    section VARCHAR(255) NOT NULL,
    amount INT NOT NULL CHECK (amount >= 0)
);