SET
GLOBAL TIME_ZONE = "+3:00"; 
DROP
DATABASE IF EXISTS `cash_machine`;
CREATE
DATABASE `cash_machine`;
USE
`cash_machine`;

CREATE TABLE user
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(12)  NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role`     ENUM ('CASHIER', 'SENIOR_CASHIER', 'COMMODITY_EXPERT', 'ADMIN')
);

INSERT INTO user (username, password, role)
VALUES ('admin', 'pass', 'ADMIN');

CREATE TABLE product
(
    `id`       BIGINT        NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    `name`     VARCHAR(255)  NOT NULL UNIQUE,
    `price`    DECIMAL(8, 2) NOT NULL,
    `quantity` BIGINT UNSIGNED DEFAULT (0)
);



INSERT INTO product (price, quantity, name)
VALUES (100, 100, 'p1'),
       (200, 200, 'p2'),
       (300, 300, 'p3'),
       (400, 400, 'p4'),
       (500, 500, 'p5'),
       (600, 600, 'p6'),
       (700, 700, 'p7'),
       (800, 800, 'p8'),
       (900, 900, 'p9');

CREATE TABLE receipt
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    `total_price` BIGINT DEFAULT (0),
    `user_id`     BIGINT NOT NULL,
    `status`      ENUM ('NEW', 'CLOSED', 'DECLINED') NOT NULL DEFAULT ('NEW'),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE product_in_receipt
(
    `id`                  BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    `receipt_id`          BIGINT NOT NULL,
    `product_id`          BIGINT NOT NULL,
    `quantity_in_receipt` BIGINT DEFAULT (1),
    `price`               BIGINT NOT NULL,
    FOREIGN KEY (receipt_id) REFERENCES receipt (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id),
    UNIQUE (receipt_id, product_id)
);

INSERT INTO user (username, password, role)
VALUES ('senior1', 'pass', 'SENIOR_CASHIER'),
       ('expert1', 'pass', 'COMMODITY_EXPERT'),
       ('cashier1', 'pass', 'CASHIER');


INSERT INTO receipt(user_id)
VALUES (1),
       (2),
       (3),
       (1),
       (2),
       (2);

INSERT INTO product_in_receipt (receipt_id, product_id, quantity_in_receipt, price)
VALUES (1, 1, 1, 100),
       (1, 2, 2, 400),
       (2, 3, 1, 300),
       (3, 4, 3, 1200),
       (4, 5, 5, 2500),
       (4, 6, 1, 600),
       (4, 7, 1, 700),
       (4, 8, 2, 1600),
       (5, 9, 11, 9900);

UPDATE receipt
SET total_price = (SELECT SUM(price) FROM product_in_receipt WHERE receipt_id = 1),
    status      = 'CLOSED'
WHERE receipt.id = 1;
UPDATE receipt
SET total_price = (SELECT SUM(price) FROM product_in_receipt WHERE receipt_id = 2),
    status      = 'CLOSED'
WHERE receipt.id = 2;
UPDATE receipt
SET total_price = (SELECT SUM(price) FROM product_in_receipt WHERE receipt_id = 3),
    status      = 'CLOSED'
WHERE receipt.id = 3;
UPDATE receipt
SET total_price = (SELECT SUM(price) FROM product_in_receipt WHERE receipt_id = 4),
    status      = 'CLOSED'
WHERE receipt.id = 4;
UPDATE receipt
SET total_price = (SELECT SUM(price) FROM product_in_receipt WHERE receipt_id = 5),
    status      = 'CLOSED'
WHERE receipt.id = 5;
UPDATE receipt
SET total_price = (SELECT SUM(price) FROM product_in_receipt WHERE receipt_id = 6),
    status      = 'CLOSED'
WHERE receipt.id = 6;