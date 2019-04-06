DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;
DROP TYPE IF EXISTS user_flag;
DROP TYPE IF EXISTS group_type;

DROP TABLE IF EXISTS cities;
DROP SEQUENCE IF EXISTS city_seq;

CREATE TYPE group_type AS ENUM ('REGISTERING', 'CURRENT', 'FINISHED');

CREATE SEQUENCE city_seq START 100000;

CREATE TABLE cities
(
  id    INTEGER PRIMARY KEY DEFAULT nextval('city_seq'),
  key   TEXT NOT NULL UNIQUE,
  title TEXT NOT NULL
);

CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');

CREATE SEQUENCE user_seq START 100000;

CREATE TABLE users
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  full_name TEXT      NOT NULL,
  email     TEXT      NOT NULL,
  flag      user_flag NOT NULL,
  city      INTEGER   NOT NULL REFERENCES cities (id)
);

CREATE UNIQUE INDEX email_idx ON users (email);