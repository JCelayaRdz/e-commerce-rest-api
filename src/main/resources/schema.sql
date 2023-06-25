CREATE SCHEMA `e-commerce-rest-api`;

USE `e-commerce-rest-api`;

CREATE TABLE `admin`
(
    username  VARCHAR(25) UNIQUE NOT NULL,
    email     VARCHAR(50) UNIQUE NOT NULL,
    password  VARCHAR(70)        NOT NULL,
    adminType VARCHAR(6)         NOT NULL,
    CONSTRAINT CHK_ADMIN_TYPE CHECK (adminType IN ('STORE', 'SYSTEM')),
    PRIMARY KEY (username)
);

CREATE TABLE `store`
(
    name          VARCHAR(40) UNIQUE NOT NULL,
    description   VARCHAR(200),
    isVisible     TINYINT            NOT NULL DEFAULT 1,
    adminUsername VARCHAR(25) UNIQUE NOT NULL,
    FOREIGN KEY (adminUsername) REFERENCES `admin` (username),
    PRIMARY KEY (name)
);

CREATE TABLE `product`
(
    productId   INT UNIQUE  NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30) NOT NULL,
    price       DECIMAL(8, 2) UNSIGNED NOT NULL,
    description VARCHAR(100),
    isVisible   BOOLEAN     NOT NULL DEFAULT 1,
    storeName   VARCHAR(40) NOT NULL,
    FOREIGN KEY (storeName) REFERENCES `store` (name),
    PRIMARY KEY (productId)
);


CREATE TABLE `customer`
(
    username    VARCHAR(25) UNIQUE NOT NULL,
    email       VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(70)        NOT NULL,
    gender      VARCHAR(6),
    dateOfBirth DATE,
    CONSTRAINT CHK_GENDER CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
    PRIMARY KEY (username)
);

CREATE TABLE `address`
(
    addressId        INT UNIQUE  NOT NULL AUTO_INCREMENT,
    country          VARCHAR(56) NOT NULL,
    postalCode       VARCHAR(32) NOT NULL,
    city             VARCHAR(35) NOT NULL,
    street           VARCHAR(95) NOT NULL,
    details          VARCHAR(50) NOT NULL,
    isVisible        BOOLEAN     NOT NULL DEFAULT TRUE,
    customerUsername VARCHAR(25) NOT NULL,
    FOREIGN KEY (customerUsername) REFERENCES `customer` (username),
    PRIMARY KEY (addressId)
);

CREATE TABLE `order`
(
    orderId          INT UNIQUE  NOT NULL AUTO_INCREMENT,
    status           VARCHAR(10) NOT NULL DEFAULT 'PENDING',
    date             DATETIME    NOT NULL,
    deliveredAt      DATETIME,
    customerUsername VARCHAR(25) NOT NULL,
    addressId        INT         NOT NULL,
    CONSTRAINT CHK_STATUS CHECK (status IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'IN TRANSIT', 'DELIVERED')),
    FOREIGN KEY (customerUsername) REFERENCES `customer` (username),
    FOREIGN KEY (addressId) REFERENCES `address` (addressId),
    PRIMARY KEY (orderId)
);

CREATE TABLE `product_order`
(
    orderId   INT NOT NULL,
    productId INT NOT NULL,
    quantity  INT NOT NULL DEFAULT 1,
    FOREIGN KEY (orderId) REFERENCES `order` (orderId),
    FOREIGN KEY (productId) REFERENCES `product` (productId),
    PRIMARY KEY (orderId, productId)
);
