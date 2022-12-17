CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `image` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) 