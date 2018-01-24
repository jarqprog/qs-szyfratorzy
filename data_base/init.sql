
PRAGMA  FOREIGN_KEYS = ON;

BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS mentors(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text);

CREATE TABLE IF NOT EXISTS groups(
  id integer PRIMARY KEY AUTOINCREMENT,
  name text UNIQUE);
 
CREATE TABLE  IF NOT EXISTS teams(
  id integer PRIMARY KEY AUTOINCREMENT,
  name text UNIQUE);

CREATE TABLE IF NOT EXISTS students(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text,
  team_id integer,
  FOREIGN KEY (team_id) REFERENCES teams(id),
  group_id integer,
  FOREIGN KEY (group_id) REFERENCES groups(id),
  wallet integer,
  expirence integer);

CREATE TABLE IF NOT EXISTS admins(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text);
<<<<<<< HEAD

CREATE TABLE IF NOT EXISTS artifacts(
  id integer PRIMARY KEY AUTOINCREMENT ,
  name text,
  type text,
  description text,
  price integer);

CREATE TABLE IF NOT EXISTS quests(
  id integer PRIMARY KEY AUTOINCREMENT ,
  name text,
  type text,
  description text,
  reward integer);
  
CREATE TABLE IF NOT EXISTS teams_transactions(
  id integer PRIMARY KEY AUTOINCREMENT,
  team_id integer,
  FOREIGN KEY (team_id) REFERENCES teams(id),
  artefact_id integer,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id),
  date text);
  
CREATE TABLE IF NOT EXISTS students_transactions(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  FOREIGN KEY (student_id) REFERENCES students(id),
  artefact_id integer,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id),
  date text);
  
CREATE TABLE IF NOT EXISTS students_artifacts(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  FOREIGN KEY (student_id) REFERENCES students(id),
  artefact_id integer,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id));

INSERT OR IGNORE INTO admins VALUES(1,'admin','admin','admin@email.com','admin');

COMMIT;