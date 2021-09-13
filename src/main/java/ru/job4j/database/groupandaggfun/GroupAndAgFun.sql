create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Watch', 150), ('Mobile', 300), ('Notebook', 1500);
insert into people(name) values ('Alex'), ('Irina'), ('Maxim');
insert into devices_people(device_id, people_id) values (1, 1), (2, 2), (3, 3);
insert into devices_people(device_id, people_id) values (2, 1), (2, 2), (3, 3);
insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (2, 3);

select avg(price) from devices;
select p.name, avg(d.price) from devices_people as dp join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id group by p.name;
select p.name, avg(d.price) from devices_people as dp join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id group by p.name having avg(d.price) > 240;