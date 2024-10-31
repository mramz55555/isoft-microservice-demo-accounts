CREATE TABLE IF NOT EXISTS `customer`
(
    `id`            int AUTO_INCREMENT PRIMARY KEY,
    `name`          varchar(100) NOT NULL,
    `email`         varchar(100) NOT NULL,
    `mobile_number` varchar(20)  NOT NULL,
    `created_date`  date         NOT NULL,
    `created_user`  varchar(20)  NOT NULL,
    `updated_date`  date        DEFAULT NULL,
    `updated_user`  varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `account`
(
    `id`             int AUTO_INCREMENT PRIMARY KEY,
    `customer_id`    int          NOT NULL,
    `account_type`   varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `created_date`   date         NOT NULL,
    `created_user`   varchar(20)  NOT NULL,
    `updated_date`   date        DEFAULT NULL,
    `updated_user`   varchar(20) DEFAULT NULL
);