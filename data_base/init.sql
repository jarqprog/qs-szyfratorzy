PRAGMA foreign_keys=ON;
BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS groups(
  id integer PRIMARY KEY AUTOINCREMENT,
  name text UNIQUE);

CREATE TABLE  IF NOT EXISTS teams(
  id integer PRIMARY KEY AUTOINCREMENT,
  name text UNIQUE);

CREATE TABLE IF NOT EXISTS mentors(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text,
  group_id integer,
  FOREIGN KEY (group_id) REFERENCES groups(id));

CREATE TABLE IF NOT EXISTS students(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text,
  wallet integer,
  experience integer,
  attendance float,
  team_id integer,
	group_id integer,
	FOREIGN KEY (team_id) REFERENCES teams(id),
	FOREIGN KEY (group_id) REFERENCES groups(id));

CREATE TABLE IF NOT EXISTS admins(
  id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text);

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
  artefact_id integer,
  date text,
  FOREIGN KEY (team_id) REFERENCES teams(id),
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id));

CREATE TABLE IF NOT EXISTS students_transactions(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  artefact_id integer,
  date text,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

CREATE TABLE IF NOT EXISTS students_artifacts(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  artefact_id integer,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

-- add data

  INSERT OR IGNORE INTO groups VALUES(1,'undefined');
  INSERT OR IGNORE INTO teams VALUES(1,'undefined');
  INSERT OR IGNORE INTO admins VALUES(1,'admin','admin','admin@email.com','admin');
  INSERT OR IGNORE INTO admins VALUES(6,'Piotr','Gryzlo','piotr@cc.com','12321');
  INSERT OR IGNORE INTO students VALUES(100,'Marcinek','Stasik','lolek@gmail.com','12321', 20, 30, 40, 1, 1);
  INSERT OR IGNORE INTO mentors VALUES(1,'Jarek','Kucharczyk','jarek@gmail.com','12321', 1);


DELETE FROM sqlite_sequence;

COMMIT;