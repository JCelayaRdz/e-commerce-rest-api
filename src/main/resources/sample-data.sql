USE `e-commerce-rest-api`;

INSERT INTO `admin` (username, email, password, adminType)
VALUES ('admin1', 'admin1@example.com', '$2a$12$iPD.zstzBGiMZ6MTQjmpZ.capT/LkJNRuTg.2JIkG7MrA5N1A53Mm', 'SYSTEM'),
       ('admin2', 'admin2@example.com', '$2a$12$fU23AtCW/ISBquqnWMydBOd1F4WiKtM8li6ZbU8rznZ0lZBk2uQe6', 'STORE'),
       ('admin3', 'admin3@example.com', '$2a$12$u5zNhZG0PCVWhRQ6Fizjlu88LvcqhZAFeNK3rqH3RXMCjgwET0BwK', 'STORE'),
       ('admin4', 'admin4@example.com', '$2a$12$YsFYrh8NU2pMT4wjTAWhCeImsfeKWtccj5UTCnpwO/dMUFn.G0s7q', 'STORE'),
       ('admin5', 'admin5@example.com', '$2a$12$58qjRvro4AayoIb5gXZcAuA0n6NfROroN/s5GAJ8Xz1.QTY/xS0Gy', 'SYSTEM');

INSERT INTO `store` (name, description, isVisible, adminUsername)
VALUES ('Store 1', 'This is store 1', 1, 'admin2'),
       ('Store 2', 'This is store 2', 1, 'admin3'),
       ('Store 3', 'This is store 3', 1, 'admin4');

INSERT INTO `product` (name, price, description, isVisible, storeName)
VALUES ('Iphone 14 Pro', 1319.00, 'Iphone 14 Pro 128GB Deep Purple', 1, 'Store 1'),
       ('Xiaomi 13 Ultra', 1499.99, 'Xiaomi 13 Ultra 12 GB + 512 GB Green', 1, 'Store 2'),
       ('Intel Core i5-12400F', 162.99, 'Intel Core i5-12400F 2.5Ghz', 1, 'Store 3');

INSERT INTO `customer` (username, email, password, gender, dateOfBirth)
VALUES ('customer1', 'customer1@example.com', '$2a$12$zwr7eChj7hZMhC2pb1moRuHD9M3IJx84smZAxswkCTmxntvXEqFmS', 'MALE', '1990-05-15'),
       ('customer2', 'customer2@example.com', '$2a$12$poQYpZ9z0cOOAi0E/wFHnuTkplwbtZykVD7DIz1ud9DjVG.ilE4Jq', 'FEMALE',
        '1985-09-22'),
       ('customer3', 'customer3@example.com', '$2a$12$1VJntwnj6EnOJRwQpi8sluNMXOqy6wBGw.RPh6CTTVGORd24VFG9G', 'OTHER', '1998-12-10'),
       ('customer4', 'customer4@example.com', '$2a$12$7Ir3ZayjIWLYKprsufQlLeCQEfRdD/7ILOaLoK8ATuTFDM73VG8cu', 'MALE', '1982-03-07'),
       ('customer5', 'customer5@example.com', '$2a$12$ME4kjPwgeZX5u9ZcD7gxC.snEKt7vbvHQDFwHiAWJzvnnC8j/O6fa', 'FEMALE',
        '1995-11-28');

INSERT INTO `address` (country, postalCode, city, street, details, isVisible, customerUsername)
VALUES ('Spain', '28010', 'Madrid', 'Calle de Eloy Gonzalo', '1F', 1, 'customer1'),
       ('United Kingdom', 'E6 2NP', 'London', 'Wall End Road', '32', 1, 'customer2'),
       ('France', '92500', 'Paris', 'Rue des Bons Raisins', '48 2A', 1, 'customer3'),
       ('Germany', '13351', 'Berlin', 'Kameruner Straße', '39 2B', 1, 'customer4'),
       ('United States of America', '94166', 'San Francisco', '47th Avenue', '1755', 1, 'customer5');

INSERT INTO `order` (status, date, deliveredAt, customerUsername, addressId)
VALUES ('PENDING', '2023-06-22 09:00:00', NULL, 'customer1', 1),
       ('CONFIRMED', '2023-06-21 14:30:00', NULL, 'customer2', 2),
       ('SHIPPED', '2023-06-20 17:45:00', NULL, 'customer3', 3),
       ('IN TRANSIT', '2023-06-19 10:20:00', NULL, 'customer4', 4),
       ('DELIVERED', '2023-06-18 15:10:00', '2023-06-22 15:45:00', 'customer5', 5);

INSERT INTO `product_order` (orderId, productId, quantity)
VALUES (1, 1, 2),
       (2, 3, 1),
       (3, 2, 3),
       (4, 3, 1),
       (5, 2, 3);