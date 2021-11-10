drop table if exists Category;
drop table if exists Artist;
drop table if exists CategoryArtist;
drop table if exists category_artist;
drop table if exists CategorySong;
drop table if exists category_song;
drop table if exists Song;

CREATE TABLE if not exists Category
(
    id_c     VARCHAR(255) PRIMARY KEY,
    name     VARCHAR(255),
    type     VARCHAR(255),
    capacity INTEGER,
    updates  INTEGER
);


CREATE TABLE if not exists Artist
(
    id_a    VARCHAR(255) PRIMARY KEY,
    name    VARCHAR(255),
    type    VARCHAR(255),
    updates INTEGER
);

CREATE TABLE if not exists Song
(
    id_g    VARCHAR(255) PRIMARY KEY,
    name    VARCHAR(255),
    type    VARCHAR(255),
    updates INTEGER
);

CREATE TABLE if not exists category_artist
(
    id_ca   VARCHAR(255) PRIMARY KEY,
    id_c    VARCHAR(255),
    id_a    VARCHAR(255),
    version BIGINT,
    votes   INTEGER,
    updates INTEGER

);

CREATE TABLE if not exists category_song
(
    id_cs   VARCHAR(255) PRIMARY KEY,
    id_c    VARCHAR(255),
    id_s    VARCHAR(255),
    version BIGINT,
    votes   INTEGER,
    updates INTEGER
);
