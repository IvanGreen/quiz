SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `quiz_db`.`users`;

CREATE TABLE `quiz_db`.`users`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` CHAR(80)    NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `quiz_db`.`roles`;

CREATE TABLE `quiz_db`.`roles`
(
    `id`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `quiz_db`.`users_roles`;

CREATE TABLE `quiz_db`.`users_roles`
(
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX     `FK_ROLE_ID_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `FK_ROLE_ID`
        FOREIGN KEY (`role_id`)
            REFERENCES `quiz_db`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_USER_ID`
        FOREIGN KEY (`user_id`)
            REFERENCES `quiz_db`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `quiz_db`.`questions`;

CREATE TABLE `quiz_db`.`questions`
(
    `id`    INT         NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `type`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `quiz_db`.`answers`;

CREATE TABLE `quiz_db`.`answers`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(45) NOT NULL,
    `question_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX         `FK_QUESTION_ID_idx` (`question_id` ASC) VISIBLE,
    CONSTRAINT `FK_QUESTION_ID`
        FOREIGN KEY (`question_id`)
            REFERENCES `quiz_db`.`questions` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `quiz_db`.`quiz_statuses`;

CREATE TABLE `quiz_db`.`quiz_statuses`
(
    `id`    INT         NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `quiz_db`.`quiz`;

CREATE TABLE `quiz_db`.`quiz`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `quiz_name`   VARCHAR(45) NOT NULL,
    `create_at`   TIMESTAMP   NOT NULL,
    `finished_at` TIMESTAMP   NOT NULL,
    `quiz_status` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX         `FK_QUIZ_STATUS_idx` (`quiz_status` ASC) VISIBLE,
    CONSTRAINT `FK_QUIZ_STATUS`
        FOREIGN KEY (`quiz_status`)
            REFERENCES `quiz_db`.`quiz_statuses` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



DROP TABLE IF EXISTS `quiz_db`.`quiz_question`;

CREATE TABLE `quiz_db`.`quiz_question`
(
    `id`          INT NOT NULL AUTO_INCREMENT,
    `quantity`    INT NOT NULL,
    `quiz_id`     INT NOT NULL,
    `question_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX         `FK_QUIZ_ID_2_idx` (`quiz_id` ASC) VISIBLE,
    INDEX         `FK_QUESTION_ID_2_idx` (`question_id` ASC) VISIBLE,
    CONSTRAINT `FK_QUIZ_ID_2`
        FOREIGN KEY (`quiz_id`)
            REFERENCES `quiz_db`.`quiz` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_QUESTION_ID_2`
        FOREIGN KEY (`question_id`)
            REFERENCES `quiz_db`.`questions` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `quiz_db`.`question_answer`;

CREATE TABLE `quiz_db`.`question_answer` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `question_id` int DEFAULT NULL,
                                   `answer_id` int DEFAULT NULL,
                                   `outcome_id` int DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `AA_FK_QUESTION_ID_idx` (`question_id`),
                                   KEY `AA_FK_ANSWER_ID_idx` (`answer_id`),
                                   KEY `AA_FK_OUTCOME_ID_idx` (`outcome_id`),
                                   CONSTRAINT `AA_FK_ANSWER_ID` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`id`),
                                   CONSTRAINT `AA_FK_OUTCOME_ID` FOREIGN KEY (`outcome_id`) REFERENCES `outcomes` (`id`),
                                   CONSTRAINT `AA_FK_QUESTION_ID` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `quiz_db`.`outcomes`;

CREATE TABLE `quiz_db`.`outcomes` (
                                     `id` INT NOT NULL AUTO_INCREMENT,
                                     `user_id` INT NOT NULL,
                                     `quiz_id` INT NOT NULL,
                                     `question_id` INT NULL,
                                     `answer_id` INT NULL,
                                     PRIMARY KEY (`id`),
                                     INDEX `OUT_FK_USER_ID_idx` (`user_id` ASC) VISIBLE,
                                     INDEX `OUT_FK_QUIZ_ID_idx` (`quiz_id` ASC) VISIBLE,
                                     INDEX `OUT_FK_QUESTION_ID_idx` (`question_id` ASC) VISIBLE,
                                     INDEX `OUT_FK_ANSWER_ID_idx` (`answer_id` ASC) VISIBLE,
                                     CONSTRAINT `OUT_FK_USER_ID`
                                         FOREIGN KEY (`user_id`)
                                             REFERENCES `quiz_db`.`users` (`id`)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION,
                                     CONSTRAINT `OUT_FK_QUIZ_ID`
                                         FOREIGN KEY (`quiz_id`)
                                             REFERENCES `quiz_db`.`quiz` (`id`)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION,
                                     CONSTRAINT `OUT_FK_QUESTION_ID`
                                         FOREIGN KEY (`question_id`)
                                             REFERENCES `quiz_db`.`questions` (`id`)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION,
                                     CONSTRAINT `OUT_FK_ANSWER_ID`
                                         FOREIGN KEY (`answer_id`)
                                             REFERENCES `quiz_db`.`answers` (`id`)
                                             ON DELETE NO ACTION
                                             ON UPDATE NO ACTION);



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
