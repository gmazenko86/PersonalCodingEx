-- connect to northwind dbase using: psql --host=localhost --dbname=northwind --username=gmazenko

select version();

select * from orders where ship_city = 'Oulu' limit 5;

select customer_id, order_date from orders where ship_city = 'Köln';

select  * from Customers limit 10;

--select distinct
-- this will list all Country entries, with duplicates
select all Country from customers limit 10;
-- lists distinct Country entries, no duplicates
select distinct Country from customers;

--where with and, or, not
select * from customers where country='Germany' and city='Berlin';

select * from customers where not country='Germany' limit 10;

--order by
select * from Customers order by Country desc limit 10;

--orders by multiple columns. Country first, then company_name
select * from Customers order by Country, company_name limit 10;

--insert into (can eliminate the column list if adding adding values for all columns
insert into categories (category_id, category_name, description)
values ('9', 'Junkfood', 'Mostly Chips');

select * from categories where category_id = '9';

select * from categories;

--udpate
update categories set category_name = 'Oceanfood' where category_name = 'Seafood';
update categories set category_name = 'Seafood' where category_name = 'Oceanfood';

--delete
delete from categories where category_name = 'Junkfood';

--Null values
select * from  categories where picture is null;

select * from  categories where  picture is not null;

--select top
--done differently for different databases 'LIMIT' used for PostgreSQL
select * from categories limit 5;

select * from customers where country = 'Germany' limit 5;

--min() and max() functions and AS keyword
select min(postal_code) from customers;
select max(unit_price * quantity) from order_details as LargestOrder;
select min(unit_price) from order_details as MinPrice;

--count(), avg(), and sum() functions
select count(ship_country) from orders where ship_country = 'Germany';
select avg(unit_price) from order_details;
select  sum(unit_price * quantity)  from order_details;

--LIKE operator
--following selects all records from customers where city is 4 characters long
--note wildcards: _ for any single character, % for any number of characters including zero
select * from customers where city like '____' ;
--next one does city is 4 or 5 characters long
select * from customers where city like '____' or  city like '_____' limit 10;
--where country begins with U, then where country does not begin with U
select * from customers where country like 'U%'limit 10;
select * from customers where country not like 'U%' limit 10;
--phone number in (503) area code
select company_name, contact_name, city, region, phone from customers where phone like '(503)%';

--other wildcards
-- [] is any single character within the brackets: h[oa]t finds hot, and hat but not hit
-- ^ any character not in the brackets: h[^oa]t finds hit but not hot or hat
-- - a range of characters c[a-b]t finds cat and cbt
-- [] does not seem to be working in PostgreSQL. Following returns nothing but should return same as what follows
--select company_name, contact_name, city, region, phone from customers where city like '[BSP]%';
select company_name, contact_name, city, region, phone from customers where city like 'B%'
or city like 'S%' or city like 'P%' limit 15;

--IN operator: specify multiple values in a WHERE clause, it's shorthand for multiple OR conditions
select company_name, country from customers where country in ('Germany', 'USA', 'UK') limit 10;
select company_name, country from customers where country not in ('Germany', 'USA', 'UK') limit 10;
--can 'nest' another SELECT statement inside the 'in' clause
--the following would select all customers in the same country as a supplier
select * from customers where country in (select country from suppliers) limit 10;

--between operator
select employee_id, last_name, first_name from employees where employee_id between 3 and 7;
select employee_id, last_name, first_name from employees where employee_id not between  3 and 7;
select category_name from categories where category_name between 'Condiments' and 'Grains/Cereals';
select category_name from categories where category_name between 'a' and 'e';
select * from orders where order_date between '08/01/1996' and '08/31/1996' limit 10;

--aliases using AS keyword. Only exists for the duration of the query
--below creates an alias that combines 4 columns
select company_name, concat(address, ', ', postal_code, ' ', city, ', ' , country) as Address
from Customers limit 10;
--below use of aliases just makes the code more readable
select o.order_id, o.order_date, c.company_name
from Customers as c, Orders as o
where c.company_name='Around the Horn' and c.customer_id =o.customer_id limit 10;

--inner join
--here we list out customer info and corresponding order info
--parentheses after from not necessary, but added for conceptual clarity
select customers.customer_id, customers.city, customers.country, orders.order_id,
orders.order_date
from (customers inner join orders on customers.customer_id = orders.customer_id)
order by customers.customer_id, orders.order_date limit 20;
-- and an inner join of 3 tables. Add Shipper ID to the above
select customers.customer_id, customers.city, customers.country, orders.order_id,
orders.order_date, shippers.company_name
from ((customers inner join orders on customers.customer_id = orders.customer_id)
       inner join shippers on orders.ship_via = shippers.shipper_id)
order by customers.customer_id, orders.order_date limit 20;

--left join : all records from the left table, and matched records from right table
select products.product_id, products.product_name, categories.category_name
from (products left join categories on products.category_id = categories.category_id)
order by categories.category_name, products.product_name limit 20;

--right join : all records from the right table, and matched records from left table
select employees.last_name, employees.first_name, orders.order_id
from (orders right join employees on orders.employee_id = employees.employee_id)
where last_name  not like 'B%'
order by employees.last_name limit 20;
--above query can also be rewritten with 'using' as shown below
select employees.last_name, employees.first_name, orders.order_id
from (orders right join employees using (employee_id))
where last_name  not like 'B%'
order by employees.last_name limit 20;


--full join : all records when there is a match on the left or the right
select categories.category_name, products.product_name
from (categories full join products on categories.category_id = products.category_id)
order by categories.category_name limit 20;

--self join : match employees from the same country
--have to create 2 different aliases for the same table
select Table1.last_name as lastname1, Table2.last_name as lastname2, Table1.country
from employees as Table1, employees as Table2
where Table1.last_name <> Table2.last_name and Table1.country = Table2.country
order by Table1.country limit 20;

--union and union all
--this results in 1 column entitled 'last_name' containing last names and company names
--union all allows duplicate values
select last_name from employees
union
select company_name from customers
limit 20;

--group by
select country  from employees
group by country;
--gives the same result as
select distinct country from employees;

--now get a count of employees per country
select count (country), country from employees
group by country;
--get a count of orders shipped by each shipper
select shippers.company_name, count(orders.order_id) as numberShipped
from (shippers left join orders on shippers.shipper_id = orders.ship_via)
group by shippers.company_name
order by numberShipped desc;

--having : WHERE keyword cannot be used with aggregate functions
--using numberShipped alias in having statement does not work
select shippers.company_name, count(orders.order_id) as numberShipped
from (shippers left join orders on shippers.shipper_id = orders.ship_via)
group by shippers.company_name
having count (orders.order_id) > 0
order by numberShipped desc;

--exists operator is used to test for the existence of any record in a subquery
--think of it as a boolean
--below query lists all employees since subquery returns a record (Andrew Fuller is an employee)
select last_name, first_name from employees
where exists (select last_name from employees where last_name = 'Fuller');
--but below query lists 0 employees since the subquery returns nothing (no employee named 'Muller'
select last_name, first_name from employees
where exists (select last_name from employees where last_name = 'Muller');

--any and all operators are used with a WHERE or HAVING clause
--note that 'some' is a synonym for 'any'
--The '=' ANY is equivalent to IN operator.
--note that the below queries do not work in Intellij for some reason
--but they work from the command line or when executing from a java program
select product_name
from products
where product_id = any (select product_id from order_details where Quantity = 10)
limit 12;
--following will not return any results, since not all
--records in order_details have quantity = 10
select product_name
from products
where product_id = all (select product_id from order_details where Quantity = 10)
limit 12;

--following query lists employee and customer info for orders with quantity >= 75
--don't have to use table names in the first line if there is no ambiguity
select employees.last_name, employees.first_name, customers.company_name
from (orders inner join employees using(employee_id)
      inner join customers using(customer_id))
where order_id = any (select order_id from order_details where quantity >= 75)
limit  15;

--select into statement : copies data from 1 table to a new table (potentially in a new dbase)
-- following copies table categories to table categories2, except Beverages
--  select * into categories2
--  from categories
--  where category_name != 'Beverages';
--now get rid of the new table
--  drop table categories2;

--insert into is for copying data from 1 table into another existing table
--INSERT INTO table2
--SELECT * FROM table1
--WHERE condition;

--sql case statement, returns true when first condition is met
select order_id, unit_price, quantity,
    case
        when quantity < 20 then 'quantity is less than 20'
        when quantity = 20 then 'quantity equals 20'
        else 'quantity is greater than 20'
    end as quantitytext
from order_details
limit 12;

--another example
select customer_id, City, Country
from customers
order by
(case
    when City is null then Country
    else City
end)
limit 10;

--null functions allow you to replace a null value. coalesce() works here
--may be IFNULL(), ISNULL(), COALESCE(), or NVL() depending on the database
--note since the alias "multiplied units" has a space, it is enclosed in quotes
select product_name , unit_price *
(units_in_stock + coalesce(units_on_order, 0)) as "multiplied units"
from products
limit 10;

--stored procedures. postgresql syntax quite different from others
create or replace procedure printinfo()
language sql
as $$
    select last_name, first_name, title from employees limit 10;
$$;

--postgresql documentation says to use call instead of exec
call printinfo;
--above 2 (create then call) worked from the command line, but did not return anything
--says "Unlike a user-defined function, a stored procedure does not have a return value"
--pgAdmin4 does show that the printinfo() procedure exists as defined above

--many ways to create a function. the one below works, along with the query below
--note that corresponding scripts can be created and run in pgAdmin4
create or replace function addints(integer, integer) returns integer
    as 'select $1 + $2;'
language sql;

select last_name
from employees
limit addints(1,2);

--in java, the 'addints' function can be executed with
--statement.executeQuery("select public.addints(5, 6);");
--the same as any other query string. It returns a java ResultSet
--from the command line, this will return 1 record with an 11 in a column entitled addints;
select public.addints(5, 6);

select version();

--other important non-query sql statements
create database tempdb;
drop database tempdb;

--sql server does backup as follows. postgresql is different
--BACKUP DATABASE databasename
--TO DISK = 'filepath'
--WITH DIFFERENTIAL;

create table gregtable(
    id serial primary key ,
    firstname varchar not null,
    lastname varchar not null);

alter table gregtable
add age integer ;

alter table gregtable
add constraint Chk_age check (age >= 21);

--below works based on table created above
insert into gregtable
values
(1, 'Greg', 'Mazenko', 55),
(2, 'Carolyn', 'Mazenko', 52);

--but below does not work since we added an age >= 21 constraint
--ERROR:  new row for relation "gregtable" violates check constraint "chk_age"
--DETAIL:  Failing row contains (3, Willoughby, Roadrunner, 6).
insert into gregtable
values
(3, 'Willoughby', 'Roadrunner', 6);

drop table gregtable;

--or to create a table from an existing table
create table employees2
as select *
from employees
where first_name not like 'Nancy';

alter table employees2
add favcolor varchar;

alter table employees2
drop column favcolor;

truncate table employees2;

select * from employees2;

--see discussion of primary and foreign keys
--note that the keys are named, and can consist of multiple columns
--can see relationships looking at table properties in pgAdmin4
--https://www.w3schools.com/sql/sql_foreignkey.asp

--to create a view, which shows data of interest, and is updated real time
create or replace view us_customers
as select company_name, contact_name, phone, country
from customers
where country = 'USA';

select *
from us_customers limit 5;

select public.addints(8, 9);
