-- initial table setup --
CREATE TABLE CFPLUS_PRODUCT(
id int GENERATED ALWAYS AS IDENTITY primary key,
name varchar(32) not null,
price decimal(8,2) check (
price > '0' and price < '5000000'));
COMMIT;

-- retrieving a specific product by id
SELECT id, name, price
FROM CFPLUS_PRODUCT
WHERE ID = ?;


-- retrieving all the table
SELECT *
FROM CFPLUS_PRODUCT;
