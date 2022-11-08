DROP DATABASE payMyBuddy IF EXISTS payMyBuddy;

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
amount FLOAT,
date DATE,
FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE transaction_user (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
payer_id INTEGER NOT NULL,
receiver_id INTEGER NOT NULL,
amount FLOAT,
description TEXT,
date DATE,
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

INSERT INTO `user` (`email`, `password`, `balance`) 
VALUES 
('userPat@gmail.com', 'userPat', 500),
('userMax@gmail.com', 'userMax', 500),
('userVal@gmail.com', 'userVal', 500),
('userLeo@gmail.com', 'userLeo', 0);

INSERT INTO `transaction_bank` (`user_id`, `bankAccount`, `amount`) 
VALUES 
(1, '12345', 500),
(2, '23456', 500),
(2, '23456', -100),
(3, '34567', 250),
(3, '45678', 250);

INSERT INTO `transaction_user` (`payer_id`, `receiver_id`, `amount`, `description`) 
VALUES 
(1, 2, 12, 'Place de cinéma'),
(1, 2, 25, 'Restaurant McDonalds'),
(4, 3, 200, 'Jardinage'),
(3, 1, 60, 'Achat de jeux-vidéo');

INSERT INTO `user_connection` (`user_id`, `connection_id`) 
VALUES 
(1, 2),
(4, 3),
(3, 1);