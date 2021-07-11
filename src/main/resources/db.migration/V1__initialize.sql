SET
FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `quiz_db`.`users`;

CREATE TABLE `users`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `password` char(80)    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`roles`;

CREATE TABLE `roles`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`users_roles`;

CREATE TABLE `users_roles`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY       `FK_ROLE_ID_idx` (`role_id`),
    CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`quiz_statuses`;

CREATE TABLE `quiz_statuses`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`questions`;

CREATE TABLE `questions`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    `type`  varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`answers`;

CREATE TABLE `answers`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `title`       varchar(45) NOT NULL,
    `question_id` int         NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `FK_QUESTION_ID_idx` (`question_id`),
    CONSTRAINT `FK_QUESTION_ID` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `quiz_db`.`quiz`;

CREATE TABLE `quiz`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `quiz_name`   varchar(45) NOT NULL,
    `create_at`   timestamp   NOT NULL,
    `finished_at` timestamp   NOT NULL,
    `quiz_status` int         NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `FK_QUIZ_STATUS_idx` (`quiz_status`),
    CONSTRAINT `FK_QUIZ_STATUS` FOREIGN KEY (`quiz_status`) REFERENCES `quiz_statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`quiz_question`;

CREATE TABLE `quiz_question`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `quantity`    int NOT NULL,
    `quiz_id`     int NOT NULL,
    `question_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `FK_QUIZ_ID_2_idx` (`quiz_id`),
    KEY           `FK_QUESTION_ID_2_idx` (`question_id`),
    CONSTRAINT `FK_QUESTION_ID_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
    CONSTRAINT `FK_QUIZ_ID_2` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`question_answer`;

CREATE TABLE `question_answer`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `question_id` int DEFAULT NULL,
    `answer_id`   int DEFAULT NULL,
    `outcome_id`  int DEFAULT NULL,
    `quiz_id`     int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `AA_FK_QUESTION_ID_idx` (`question_id`),
    KEY           `AA_FK_ANSWER_ID_idx` (`answer_id`),
    KEY           `AA_FK_OUTCOME_ID_idx` (`outcome_id`),
    KEY           `AA_FK_QUIZ_ID_idx` (`quiz_id`),
    CONSTRAINT `AA_FK_ANSWER_ID` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`id`),
    CONSTRAINT `AA_FK_OUTCOME_ID` FOREIGN KEY (`outcome_id`) REFERENCES `outcomes` (`id`),
    CONSTRAINT `AA_FK_QUESTION_ID` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
    CONSTRAINT `AA_FK_QUIZ_ID` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `quiz_db`.`outcomes`;

CREATE TABLE `outcomes`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
