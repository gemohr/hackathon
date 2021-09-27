DROP TABLE IF EXISTS game;

CREATE TABLE game
(
    id   BIGINT PRIMARY KEY,
    pos  INT,
    time BIGINT,
    date DATE
);