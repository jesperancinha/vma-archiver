drop table if exists Category;
drop table if exists Artist;
drop table if exists CategoryArtist;
drop table if exists category_artist;
drop table if exists CategorySong;
drop table if exists category_song;
drop table if exists Song;
drop table if exists vote_category_artist;
drop table if exists vote_category_song;

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
    id_ca      VARCHAR(255) PRIMARY KEY,
    id_c       VARCHAR(255),
    id_a       VARCHAR(255),
    version    BIGINT,
    votes      BIGINT,
    vote_count BIGINT,
    updates    INTEGER

);

CREATE TABLE if not exists category_song
(
    id_cs      VARCHAR(255) PRIMARY KEY,
    id_c       VARCHAR(255),
    id_s       VARCHAR(255),
    version    BIGINT,
    votes      BIGINT,
    vote_count BIGINT,
    updates    INTEGER
);

CREATE TABLE if not exists vote_category_artist
(
    id_vca  VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255),
    id_c    VARCHAR(255),
    id_a    VARCHAR(255),
    updates INTEGER,
    unique (user_id, id_c)
);

CREATE TABLE if not exists vote_category_song
(
    id_vcs  VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255),
    id_c    VARCHAR(255),
    id_s    VARCHAR(255),
    updates INTEGER,
    unique (user_id, id_c)
);
