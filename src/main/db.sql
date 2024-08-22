CREATE TABLE games (
    id varchar(255) primary key NOT NULL,
    name TEXT NOT NULL,
    iconLink TEXT,
    bannerLink TEXT,
    views int,
    price DOUBLE,
    purchases int,
    discount int,
    rating DOUBLE
);