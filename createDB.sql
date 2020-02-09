CREATE TABLE app_user(
    user_id serial,
    username varchar(50) UNIQUE,
    email varchar(50) UNIQUE,
    cvu int UNIQUE,
    idCard int,
    password varchar(250),
    CONSTRAINT user_pk PRIMARY KEY (user_id)
    );

CREATE TABLE account(
	account_id serial,
	balance int,
	user_id int,
	isBlocked boolean,
	CONSTRAINT account_pk PRIMARY KEY(account_id),
    CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES users (user_id)
    );

CREATE TABLE transaction(
        transaction_id serial,
        account_to int,
        account_from int,
        amount int,
        CONSTRAINT transaction_pk PRIMARY KEY(transaction_id),
        CONSTRAINT account_to_FK FOREIGN KEY (account_to) REFERENCES accounts(account_id),
        CONSTRAINT account_from_FK FOREIGN KEY (account_from) REFERENCES accounts(account_id)
    );