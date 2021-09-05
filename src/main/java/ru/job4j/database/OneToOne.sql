create table country(
    id serial primary key UNIQUE,
    name varchar(255)
);

create table capital(
    id serial primary key UNIQUE,
    name varchar(255)
)

create table country_capital(
    id serial primary key
    country_id int references country(id),
    capital_id int references capital(id)
)