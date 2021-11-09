drop table if exists Category;

CREATE TABLE if not exists Category
(
    id_c       VARCHAR(255) PRIMARY KEY,
    name     VARCHAR(255),
    capacity INTEGER
);

