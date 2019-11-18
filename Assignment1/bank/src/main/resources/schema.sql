CREATE TABLE customer (
    customerId int(11) NOT NULL AUTO_INCREMENT,
    firstName varchar(30) NOT NULL,
    lastName varchar(30) NOT NULL,
    PRIMARY KEY(customerId)
);

CREATE TABLE account (
    accountId int(11) NOT NULL AUTO_INCREMENT,
    balance double(30) NOT NULL,
    overdraftLimit double(30) NOT NULL,
    PRIMARY KEY(accountId)
);

CREATE TABLE customerAccount (
    customerId int(11) NOT NULL,
    accountId int(11) NOT NULL,
    PRIMARY KEY(customerId, accountId)
);
