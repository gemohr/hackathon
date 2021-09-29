DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS USER (
                                    id BIGINT PRIMARY KEY,
                                    USERNAME VARCHAR (255) not null,
                                    PASSWORD VARCHAR (255) not null
);

CREATE TABLE IF NOT EXISTS game
(
    id   BIGINT PRIMARY KEY,
    pos  INT,
    time BIGINT,
    date DATE,
    picture BLOB,
    picture_id BIGINT,
    full_name VARCHAR (255),
    username VARCHAR (255),
    foreign key (username) references user(username)
);


