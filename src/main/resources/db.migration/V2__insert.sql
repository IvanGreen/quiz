INSERT INTO `quiz_db`.`users` (`username`, `password`)
VALUES ('admin', '$2a$10$3sLj0tznM4.BYXMQYFwlSuPvDHI0o8xoODuga22st2hDeANn/pvGK');
INSERT INTO `quiz_db`.`users` (`username`, `password`)
VALUES ('user', '$2a$10$3sLj0tznM4.BYXMQYFwlSuPvDHI0o8xoODuga22st2hDeANn/pvGK');
INSERT INTO `quiz_db`.`users` (`username`, `password`)
VALUES ('Incognita', '$2a$10$3sLj0tznM4.BYXMQYFwlSuPvDHI0o8xoODuga22st2hDeANn/pvGK');


INSERT INTO `quiz_db`.`roles` (`name`)
VALUES ('ROLE_ADMIN');
INSERT INTO `quiz_db`.`roles` (`name`)
VALUES ('ROLE_USER');

INSERT INTO `quiz_db`.`users_roles` (`user_id`, `role_id`)
VALUES ('1', '1');
INSERT INTO `quiz_db`.`users_roles` (`user_id`, `role_id`)
VALUES ('2', '2');
INSERT INTO `quiz_db`.`users_roles` (`user_id`, `role_id`)
VALUES ('3', '2');


INSERT INTO `quiz_db`.`quiz_statuses` (`title`)
VALUES ('Formed');
INSERT INTO `quiz_db`.`quiz_statuses` (`title`)
VALUES ('Submitted');
