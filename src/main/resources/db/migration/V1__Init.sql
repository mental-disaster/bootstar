DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `user_id` INT NOT NULL AUTO_INCREMENT,
                        `username` VARCHAR(16) NOT NULL,
                        `password` VARCHAR(255) NOT NULL,
                        `nickname` VARCHAR(16) NOT NULL DEFAULT `username`,
                        `profileimage` BLOB NULL,
                        `email` VARCHAR(255) NULL,
                        `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`user_id`));

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
                        `post_id` INT NOT NULL AUTO_INCREMENT,
                        `author_id` INT NOT NULL,
                        `image` BLOB NULL,
                        `caption` BLOB NULL,
                        `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`post_id`),
                        FOREIGN KEY (`author_id`)
                            REFERENCES `user` (`user_id`)
                            ON DELETE NO ACTION
                            ON UPDATE NO ACTION);

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                           `comment_id` INT NOT NULL AUTO_INCREMENT,
                           `author_id` INT NOT NULL,
                           `post_id` INT NOT NULL,
                           `detail` BLOB NULL,
                           `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`comment_id`),
                           FOREIGN KEY (`post_id`)
                               REFERENCES `post` (`post_id`)
                               ON DELETE NO ACTION
                               ON UPDATE NO ACTION,
                           FOREIGN KEY (`author_id`)
                               REFERENCES `user` (`user_id`)
                               ON DELETE NO ACTION
                               ON UPDATE NO ACTION);