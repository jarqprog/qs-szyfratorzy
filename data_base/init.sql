PRAGMA  FOREIGN_KEYS = ON;
BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS groups(
  group_id integer PRIMARY KEY AUTOINCREMENT,
  name text UNIQUE);

CREATE TABLE  IF NOT EXISTS teams(
  team_id integer PRIMARY KEY AUTOINCREMENT,
  name text UNIQUE);

CREATE TABLE IF NOT EXISTS mentors(
  mentor_id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text,
  group_id integer,
  FOREIGN KEY (group_id) REFERENCES groups(group_id));

CREATE TABLE IF NOT EXISTS students(
  student_id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text,
  wallet integer,
  experience integer,
  attendance float,
  team_id integer,
	group_id integer,
	FOREIGN KEY (team_id) REFERENCES teams(team_id),
	FOREIGN KEY (group_id) REFERENCES groups(group_id));

CREATE TABLE IF NOT EXISTS admins(
  admin_id integer PRIMARY KEY AUTOINCREMENT,
  first_name text,
  last_name text,
  email text,
  password text);

CREATE TABLE IF NOT EXISTS artifacts(
  artifact_id integer PRIMARY KEY AUTOINCREMENT ,
  name text,
  type text,
  description text,
  price integer);

CREATE TABLE IF NOT EXISTS quests(
  quest_id integer PRIMARY KEY AUTOINCREMENT ,
  name text,
  type text,
  description text,
  reward integer);

CREATE TABLE IF NOT EXISTS teams_transactions(
  team_transaction_id integer PRIMARY KEY AUTOINCREMENT,
  team_id integer,
  artefact_id integer,
  date text,
  FOREIGN KEY (team_id) REFERENCES teams(team_id),
  FOREIGN KEY (artefact_id) REFERENCES artifacts(artefact_id));

CREATE TABLE IF NOT EXISTS students_transactions(
  student_transaction_id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  artefact_id integer,
  date text,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

CREATE TABLE IF NOT EXISTS students_artifacts(
  student_artifact_id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  artefact_id integer,
  FOREIGN KEY (artefact_id) REFERENCES artifacts(artifact_id),
  FOREIGN KEY (student_id) REFERENCES students(student_id));

-- add data

  INSERT OR IGNORE INTO admins VALUES(1,'admin','admin','admin@email.com','admin');
  INSERT OR IGNORE INTO groups VALUES(1,'undefined');
  INSERT OR IGNORE INTO teams VALUES(1,'undefined');

DELETE FROM sqlite_sequence;

COMMIT;