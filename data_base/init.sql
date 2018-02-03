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
  artifact_id integer,
  date text,
  FOREIGN KEY (team_id) REFERENCES teams(id),
  FOREIGN KEY (artifact_id) REFERENCES artifacts(id));

CREATE TABLE IF NOT EXISTS students_transactions(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  artifact_id integer,
  date text,
  FOREIGN KEY (artifact_id) REFERENCES artifacts(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

CREATE TABLE IF NOT EXISTS experience_levels(
  id integer PRIMARY KEY AUTOINCREMENT,
  level_name text UNIQUE,
  level_value integer);

CREATE TABLE IF NOT EXISTS students_artifacts(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  artifact_id integer,
  FOREIGN KEY (artifact_id) REFERENCES artifacts(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

-- add data

  INSERT OR IGNORE INTO groups VALUES(1,'undefined');
  INSERT OR IGNORE INTO teams VALUES(1,'undefined');
  INSERT OR IGNORE INTO groups VALUES(2,'A');
  INSERT OR IGNORE INTO teams VALUES(2,'meduzy');
  INSERT OR IGNORE INTO groups VALUES(3,'B');
  INSERT OR IGNORE INTO teams VALUES(3,'koniki');
  INSERT OR IGNORE INTO experience_levels VALUES(1,'novice',0);
  INSERT OR IGNORE INTO experience_levels VALUES(2,'apprentice',100);
  INSERT OR IGNORE INTO experience_levels VALUES(3,'junior programmer',1000);
  INSERT OR IGNORE INTO experience_levels VALUES(4,'programmer',10000);
  INSERT OR IGNORE INTO experience_levels VALUES(5,'senior programmer',100000);
  INSERT OR IGNORE INTO experience_levels VALUES(6,'almost mentor',1000000);
  INSERT OR IGNORE INTO admins VALUES(1,'admin','admin','admin@email.com','admin');
  INSERT OR IGNORE INTO admins VALUES(6,'Piotr','Gryzlo','piotr@cc.com','12321');
  INSERT OR IGNORE INTO students VALUES(100,'Marcinek','Stasik','lolek@gmail.com','12321', 20, 30, 40, 2, 2);
  INSERT OR IGNORE INTO students VALUES(101,'Jadzia','Piernik','jadzia@cc.com','12321', 20, 30, 40, 2, 2);
  INSERT OR IGNORE INTO students VALUES(102,'Maciek','Jankowicz','maciek99@cc.com','12321', 20, 30, 40, 2, 2);
  INSERT OR IGNORE INTO students VALUES(103,'Balbina','Karp','karpik007@cc.com','12321', 20, 30, 40, 3, 3);
  INSERT OR IGNORE INTO students VALUES(104,'Alfred','Szlarski','pan.samochodzik@cc.com','12321', 20, 30, 40, 3, 3);
  INSERT OR IGNORE INTO students VALUES(105,'Jakub','Wedrowycz','bimbrownik@gmail.com','12321', 20, 30, 40, 3, 3);
  INSERT OR IGNORE INTO mentors VALUES(10,'Jarek','Kucharczyk','jarek@gmail.com','12321', 2);
  INSERT OR IGNORE INTO mentors VALUES(11,'Dominik','Strazak','pogromca@cc.com','12321', 3);
  INSERT OR IGNORE INTO mentors VALUES(12,'Konrad','Mazina','kondzio999999@cc.com','12321', 3);


DELETE FROM sqlite_sequence;

COMMIT;