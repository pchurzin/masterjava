DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;
DROP TYPE IF EXISTS user_flag;

DROP TABLE IF EXISTS cities;
DROP SEQUENCE IF EXISTS city_seq;

DROP TABLE IF EXISTS "groups";
DROP SEQUENCE IF EXISTS group_seq;
DROP TYPE IF EXISTS group_type;

DROP TABLE IF EXISTS projects;
DROP SEQUENCE IF EXISTS project_seq;

-- projects
CREATE SEQUENCE project_seq START 300000;

CREATE TABLE projects
(
  id INTEGER PRIMARY KEY DEFAULT nextval('project_seq'),
  name TEXT NOT NULL UNIQUE,
  description TEXT NOT NULL
);

-- groups
CREATE TYPE group_type AS ENUM ('REGISTERING', 'CURRENT', 'FINISHED');

CREATE SEQUENCE group_seq START 200000;

CREATE TABLE "groups"
(
  id INTEGER PRIMARY KEY DEFAULT nextval('group_seq'),
  name TEXT NOT NULL UNIQUE,
  type group_type NOT NULL,
  project INTEGER NOT NULL REFERENCES projects (id)
);

-- cities
CREATE SEQUENCE city_seq START 100000;

CREATE TABLE cities
(
  id    INTEGER PRIMARY KEY DEFAULT nextval('city_seq'),
  key   TEXT NOT NULL UNIQUE,
  title TEXT NOT NULL
);

-- users
CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');

CREATE SEQUENCE user_seq START 100000;

CREATE TABLE users
(
  id        INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  full_name TEXT      NOT NULL,
  email     TEXT      NOT NULL,
  flag      user_flag NOT NULL,
--   city      INTEGER   NOT NULL REFERENCES cities (id)
  city INTEGER
);

CREATE UNIQUE INDEX email_idx ON users (email);