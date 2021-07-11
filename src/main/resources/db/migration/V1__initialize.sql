SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `users`;

CREATE TABLE IF NOT EXISTS `users`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `password` char(80)    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE IF NOT EXISTS `roles`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE IF NOT EXISTS `users_roles`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `FK_ROLE_ID_idx` (`role_id`),
    CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_statuses`;

CREATE TABLE IF NOT EXISTS `quiz_statuses`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `questions`;

CREATE TABLE IF NOT EXISTS `questions`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    `type`  varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `answers`;

CREATE TABLE IF NOT EXISTS `answers`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `title`       varchar(45) NOT NULL,
    `question_id` int         NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_QUESTION_ID_idx` (`question_id`),
    CONSTRAINT `FK_QUESTION_ID` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `quiz`;

CREATE TABLE IF NOT EXISTS `quiz`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `quiz_name`   varchar(45) NOT NULL,
    `create_at`   timestamp   NOT NULL,
    `finished_at` timestamp   NOT NULL,
    `quiz_status` int         NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_QUIZ_STATUS_idx` (`quiz_status`),
    CONSTRAINT `FK_QUIZ_STATUS` FOREIGN KEY (`quiz_status`) REFERENCES `quiz_statuses` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_question`;

CREATE TABLE IF NOT EXISTS `quiz_question`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `quantity`    int NOT NULL,
    `quiz_id`     int NOT NULL,
    `question_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_QUIZ_ID_2_idx` (`quiz_id`),
    KEY `FK_QUESTION_ID_2_idx` (`question_id`),
    CONSTRAINT `FK_QUESTION_ID_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
    CONSTRAINT `FK_QUIZ_ID_2` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `outcomes`;

CREATE TABLE IF NOT EXISTS `outcomes`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `question_answer`;

CREATE TABLE IF NOT EXISTS `question_answer`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `question_id` int DEFAULT NULL,
    `answer_id`   int DEFAULT NULL,
    `outcome_id`  int DEFAULT NULL,
    `quiz_id`     int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `AA_FK_QUESTION_ID_idx` (`question_id`),
    KEY `AA_FK_ANSWER_ID_idx` (`answer_id`),
    KEY `AA_FK_OUTCOME_ID_idx` (`outcome_id`),
    KEY `AA_FK_QUIZ_ID_idx` (`quiz_id`),
    CONSTRAINT `AA_FK_ANSWER_ID` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`id`),
    CONSTRAINT `AA_FK_OUTCOME_ID` FOREIGN KEY (`outcome_id`) REFERENCES `outcomes` (`id`),
    CONSTRAINT `AA_FK_QUESTION_ID` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
    CONSTRAINT `AA_FK_QUIZ_ID` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users` (`username`, `password`)
VALUES ('admin', '$2a$10$3sLj0tznM4.BYXMQYFwlSuPvDHI0o8xoODuga22st2hDeANn/pvGK');
INSERT INTO `users` (`username`, `password`)
VALUES ('user', '$2a$10$3sLj0tznM4.BYXMQYFwlSuPvDHI0o8xoODuga22st2hDeANn/pvGK');
INSERT INTO `users` (`username`, `password`)
VALUES ('Incognita', '$2a$10$3sLj0tznM4.BYXMQYFwlSuPvDHI0o8xoODuga22st2hDeANn/pvGK');


INSERT INTO `roles` (`name`)
VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (`name`)
VALUES ('ROLE_USER');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES ('1', '1');
INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES ('2', '2');
INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES ('3', '2');


INSERT INTO `quiz_statuses` (`title`)
VALUES ('Formed');
INSERT INTO `quiz_statuses` (`title`)
VALUES ('Submitted');