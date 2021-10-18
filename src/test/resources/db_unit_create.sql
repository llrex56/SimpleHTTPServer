drop table if exists `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `age` INT DEFAULT 0,
  PRIMARY KEY (`id`)
);