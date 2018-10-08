CREATE DATABASE accountDB;


CREATE TABLE Accounts (
    UID varchar(30) NOT NULL,
    CheckingAccount float,
    SavingAccount float,
	PRIMARY KEY (UID)
);

INSERT into Accounts VALUES(
	"user", 1000.00, 3000.00
);



