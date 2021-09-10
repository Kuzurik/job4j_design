create table address(
    id serial primary key,
    name varchar(255)
);

create table person(
    id serial primary key,
    name varchar(255),
    address_id int references address(id)
);

insert into address(name) values ('zhukovskogo street');
insert into address(name) values ('voronyanskogo street');
insert into address(name) values ('moskovskaya street');

insert into person(name, address_id) values ('Alex', 1);
insert into person(name, address_id) values ('Irina', 2);
insert into person(name, address_id) values ('Artem', 3);
insert into person(name) values ('Vasil');
insert into person(name) values ('Tatiana');

select a.name, p.name from address as a join person as p on p.address_id = a.id;
select a.name as Адрес, p.name as Имя from address as a join person as p on p.address_id = a.id;
select p.name as Имя, a.name as Адрес from address as a join person as p on p.address_id = a.id;