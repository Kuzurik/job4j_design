create table address(
    id serial primary key,
    name varchar(255),
);

create table person(
    id serial primary key,
    name varchar(255)
    address_id int references(id)
);