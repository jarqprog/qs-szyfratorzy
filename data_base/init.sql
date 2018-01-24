PRAGMA foreign_keys = OFF;
BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS mentors(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text);
CREATE TABLE IF NOT EXISTS students(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text,
  team_id integer,
  group_id integer,
  wallet integer,
  expirence integer);
CREATE TABLE IF NOT EXISTS admins(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text);
INSERT OR IGNORE INTO admins VALUES(1,'admin','admin','admin@email.com','admin');
COMMIT;