
CREATE TABLE users (
    id varchar(255) default(uuid()) primary key,
    login TEXT NOT NULL UNIQUE REQUIRED,
    password TEXT NOT NULL REQUIRED,
    name TEXT NOT NULL REQUIRED,
    cpf TEXT,
    email TEXT,
    phone_number TEXT
);

CREATE TABLE games (
    id varchar(255) default(uuid()) primary key,
    userId varchar(255) NOT NULL NOT NULL REQUIRED,
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
