/* -- Insérer les données dans les tables. -- */

USE payMyBuddy;

INSERT INTO `user` (`email`, `password`, `balance`) 
VALUES 
('userPat@gmail.com', '$2a$10$LrwlbMpw/GNhHszhsu.sPe6Uumcp/YcWnmOIy7UrzJonJAQ1voKa6', 0),
('userMax@gmail.com', '$2a$10$.hYZW/pAXCEnuPu7wEJX7.SKNVDkqmysIaVm/IlxTOLFbY795/5cq', 142.2),
('userVal@gmail.com', '$2a$10$BKLKyHm/s5vFHKdJRqwMReOC4yf4t02i7JmSvGrhimd9VNaamYEcS', 52.77),
('userLeo@gmail.com', '$2a$10$07TAjcgshIi1A2tjXWWH4./b4cPTS7.8pdRfd/OdF2goF9DQq6X66', 60);

UPDATE user SET balance = 5.03 WHERE id = 1 AND email = 'applicationBalance'; 

INSERT INTO `transaction_bank` (`user_id`, `bankAccount`, `amount`, `date`) 
VALUES 
(2, 'patBank', -500, '2022-12-06 10:24:32'),
(3, 'maxBank', 500, '2022-12-06 10:24:32'),
(3, 'maxBank', -100, '2022-12-06 10:24:32'),
(3, 'maxBank', -60, '2022-12-06 10:24:32'),
(3, 'maxBank', 120, '2022-12-06 10:24:32'),
(3, 'maxBank', -200, '2022-12-06 10:24:32'),
(4, 'valBank', 250, '2022-12-06 10:24:32'),
(4, 'valBank', 250, '2022-12-06 10:24:32');

INSERT INTO `transaction_user` (`payer_id`, `receiver_id`, `amount`, `tax`, `description`, `date`) 
VALUES 
(3, 2, 500, 2.5, 'Place de cinéma', '2022-12-06 10:24:32'),
(4, 3, 100, 0.5, 'Courses', '2022-12-06 10:24:32'),
(4, 3, 50, 0.25, 'Restaurant', '2022-12-06 10:24:32'),
(4, 3, 250, 1.25, 'Jardinage', '2022-12-06 10:24:32'),
(4, 3, 45, 0.23, 'Boulangerie', '2022-12-06 10:24:32'),
(3, 5, 60, 0.3, 'Jeux-vidéo', '2022-12-06 10:24:32');

INSERT INTO `user_connection` (`user_id`, `connection_id`) 
VALUES 
(3, 2),
(3, 5),
(4, 5),
(4, 3);