create table country(
    id serial primary key,
    name varchar(255)
);

create table capital(
    id serial primary key,
    name varchar(255)
);

create table country_capital(
    id serial primary key,
    country_id int references country(id) unique,
    capital_id int references capital(id) unique
);