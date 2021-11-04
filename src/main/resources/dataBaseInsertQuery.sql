CREATE TABLE `counts` (
                          `c_id` int NOT NULL,
                          `count` int DEFAULT NULL,
                          `literature_name` varchar(45) DEFAULT NULL,
                          PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `feedbacks` (
                             `f_id` int NOT NULL AUTO_INCREMENT,
                             `f_user_id` int DEFAULT NULL,
                             `f_literature_id` int DEFAULT NULL,
                             `rating` int DEFAULT NULL,
                             `comment` varchar(45) DEFAULT NULL,
                             PRIMARY KEY (`f_id`),
                             UNIQUE KEY `id_UNIQUE` (`f_id`),
                             KEY `user_id_idx` (`f_user_id`),
                             KEY `f_literature_id_idx` (`f_literature_id`),
                             CONSTRAINT `f_literature_id` FOREIGN KEY (`f_literature_id`) REFERENCES `literatures` (`l_id`),
                             CONSTRAINT `f_user_id` FOREIGN KEY (`f_user_id`) REFERENCES `users` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `issue_history` (
                                 `user_id` int NOT NULL,
                                 `issue_id` int DEFAULT NULL,
                                 PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `literature_managements` (
                                          `lm_id` int NOT NULL AUTO_INCREMENT,
                                          `user_id` int DEFAULT NULL,
                                          `literature_id` int DEFAULT NULL,
                                          `issue_status` varchar(10) DEFAULT NULL,
                                          `date_of_give` timestamp NULL DEFAULT '0000-00-00 00:00:00',
                                          `date_to_return` timestamp NULL DEFAULT '0000-00-00 00:00:00',
                                          PRIMARY KEY (`lm_id`),
                                          KEY `literature_id_idx` (`literature_id`),
                                          KEY `user_id_idx` (`user_id`),
                                          CONSTRAINT `literature_id` FOREIGN KEY (`literature_id`) REFERENCES `literatures` (`l_id`),
                                          CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `literatures` (
                               `l_id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(45) NOT NULL,
                               `author` varchar(40) DEFAULT NULL,
                               `genres` varchar(20) DEFAULT NULL,
                               `isAvailable` tinyint DEFAULT NULL,
                               `type` varchar(45) DEFAULT NULL,
                               `pages` int DEFAULT NULL,
                               `publisher` varchar(20) DEFAULT NULL,
                               PRIMARY KEY (`l_id`),
                               UNIQUE KEY `int_UNIQUE` (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roles` (
                         `r_id` int NOT NULL,
                         `usertype` varchar(10) DEFAULT NULL,
                         PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_roles` (
                              `user_id` int unsigned NOT NULL,
                              `role_id` int unsigned NOT NULL,
                              PRIMARY KEY (`user_id`),
                              UNIQUE KEY `user_id` (`user_id`,`role_id`),
                              KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `u_id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(20) DEFAULT NULL,
                         `lastname` varchar(45) DEFAULT NULL,
                         `email` varchar(45) DEFAULT NULL,
                         `username` varchar(45) DEFAULT NULL,
                         `password` varchar(45) DEFAULT NULL,
                         `role_id` int DEFAULT '2',
                         `status` varchar(10) DEFAULT NULL,
                         PRIMARY KEY (`u_id`),
                         KEY `role_idx` (`role_id`),
                         CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT into roles(r_id, usertype) VALUES (1,'ADMIN');
INSERT into roles(r_id, usertype) VALUES (2,'USER');



