
CREATE TABLE users (
    id varchar(255) default(uuid()) primary key,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    name TEXT NOT NULL,
    cpf TEXT,
    email TEXT,
    phone_number TEXT
);

CREATE TABLE games (
    id varchar(255) default(uuid()) primary key,
    userId varchar(255) NOT NULL,
    name TEXT NOT NULL,
    iconLink TEXT,
    bannerLink TEXT,
    views int,
    price DOUBLE,
    purchases int,
    discount int,
    rating DOUBLE,

    FOREIGN KEY(userId) REFERENCES users(userId)
);
