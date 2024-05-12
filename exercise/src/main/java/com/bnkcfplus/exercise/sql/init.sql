-- drop table if exists
DROP TABLE IF EXISTS CFPLUS_PRODUCT;

-- initial table setup --
-- the index is auto incremented
CREATE TABLE CFPLUS_PRODUCT(
id int GENERATED ALWAYS AS IDENTITY primary key,
name varchar(32) not null,
price numeric(9,2) check (
price > '0' and price <= '5000000.00'));
COMMIT;

-- retrieving a specific product by id
SELECT id, name, price
FROM CFPLUS_PRODUCT
WHERE ID = ?;

-- examples of inserts that could be done by psql
INSERT INTO TABLE CFPLUS_PRODUCT (name, price) values ('shortLoan', '4999999.99');
INSERT INTO TABLE CFPLUS_PRODUCT (name, price) values ('shortLoan', '4999999.99');
INSERT INTO TABLE CFPLUS_PRODUCT (name, price) values ('shortLoan', '4999999.99');
INSERT INTO TABLE CFPLUS_PRODUCT (name, price) values ('mortgage', '170000.99');
INSERT INTO TABLE CFPLUS_PRODUCT (name, price) values ('anticipoStipendio', '1500.00');
INSERT INTO TABLE CFPLUS_PRODUCT (name, price) values ('longLoan', '4999999.99');

-- retrieving all the table
SELECT *
FROM CFPLUS_PRODUCT;
