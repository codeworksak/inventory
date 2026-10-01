create table if not exists inventory_items (
    id UUId primary key ,
    name varchar(50) not null ,
    quantity int
);