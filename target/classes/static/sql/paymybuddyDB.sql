DROP DATABASE payMyBuddy;

CREATE DATABASE payMyBuddy;

USE payMyBuddy;

/* -- Création des differentes table. -- */

CREATE TABLE user (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
balance FLOAT DEFAULT 0
);

CREATE TABLE transaction_bank (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id INTEGER NOT NULL,
bankAccount VARCHAR(255) NOT NULL,
amount FLOAT NOT NULL,
date DATETIME NOT NULL,
FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE transaction_user (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
payer_id INTEGER NOT NULL,
receiver_id INTEGER NOT NULL,
amount FLOAT NOT NULL,
tax FLOAT NOT NULL,
description TEXT,
date DATETIME NOT NULL,
FOREIGN KEY (payer_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (receiver_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

/* -- Création des relations entre les utilisateur pour materialiser les "contacts". -- */

CREATE TABLE user_connection (
user_id INTEGER NOT NULL,
connection_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (connection_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (user_id, connection_id)
);

/* -- Insérer les données dans les tables. -- */

INSERT INTO user (email, password) 
VALUES ('applicationBalance', 'applicationBalance');
