--stored procedure demo related work
--done in codingex dbase instead of northwind
--/*
create table procdemo (
    id serial primary key,
    firstname varchar (20),
    lastname varchar (20));

insert into procdemo (firstname, lastname)
values ('Robert', 'Mazenko');

--stored procedures. postgresql syntax quite different from others
create or replace procedure addrow_procdemo(fname text, lname text)
as $$
    insert into procdemo (firstname, lastname)
    values (fname, lname)
$$
language sql;

call addrow_procdemo('Greg', 'Mazenko');

select * from procdemo;
--*/
--truncate table procdemo;
--drop table procdemo;
