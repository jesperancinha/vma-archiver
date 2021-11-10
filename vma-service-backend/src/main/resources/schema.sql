drop table if exists Category;
drop table if exists Artist;
drop table if exists CategoryArtist;

CREATE TABLE if not exists Category
(
    id_c     VARCHAR(255) PRIMARY KEY,
    name     VARCHAR(255),
    capacity INTEGER,
    updates  INTEGER
);


CREATE TABLE if not exists Artist
(
    id_a VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE if not exists CategoryArtist
(
    id_ca VARCHAR(255) PRIMARY KEY,
    id_c  VARCHAR(255),
    id_a  VARCHAR(255)
);
