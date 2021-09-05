create table car(
    id serial primary key,
    name varchar(255)
);

create table showroom(
    id serial primary key,
    name varchar(255)
);

create table car_showroom(
    id serial primary key,
    car_id int references car(id),
    showroom_id references showroom(id)
);