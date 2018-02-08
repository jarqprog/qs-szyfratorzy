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
  reward integer,
  status text);

  CREATE TABLE IF NOT EXISTS students_quests(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  quests_id integer,
  date text,
  FOREIGN KEY (quests_id) REFERENCES quests(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

CREATE TABLE IF NOT EXISTS teams_transactions(
  id integer PRIMARY KEY AUTOINCREMENT,
  team_id integer,
  artifact_id integer,
  date text,
  FOREIGN KEY (team_id) REFERENCES teams(id),
  FOREIGN KEY (artifact_id) REFERENCES artifacts(id));

CREATE TABLE IF NOT EXISTS students_quests(
  id integer PRIMARY KEY AUTOINCREMENT,
  student_id integer,
  quests_id integer,
  date text,
  FOREIGN KEY (quests_id) REFERENCES artifacts(id),
  FOREIGN KEY (student_id) REFERENCES students(id));

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


  CREATE TABLE IF NOT EXISTS team_artifacts(
  id integer PRIMARY KEY AUTOINCREMENT,
  team_id integer,
  artifact_id integer,
  FOREIGN KEY (artifact_id) REFERENCES artifacts(id),
  FOREIGN KEY (team_id) REFERENCES teams(id));

CREATE TABLE IF NOT EXISTS attendance(
  id integer PRIMARY KEY AUTOINCREMENT,
  date text,
  attendance int,
  student_id int,
  FOREIGN KEY (student_id) REFERENCES students(id));


-- add data

  INSERT OR IGNORE INTO groups VALUES(1,'undefined');
  INSERT OR IGNORE INTO teams VALUES(1,'undefined');
  INSERT OR IGNORE INTO groups VALUES(2,'A');
  INSERT OR IGNORE INTO teams VALUES(2,'meduzy');
  INSERT OR IGNORE INTO groups VALUES(3,'B');
  INSERT OR IGNORE INTO teams VALUES(3,'koniki');
  INSERT OR IGNORE INTO admins VALUES(1,'admin','admin','admin@email.com','admin');
  INSERT OR IGNORE INTO admins VALUES(6,'Piotr','Gryzlo','piotr@cc.com','12321');
  INSERT OR IGNORE INTO students VALUES(100,'Marcinek','Stasik','lolek@gmail.com','12321', 200000, 300, 2, 2);
  INSERT OR IGNORE INTO students VALUES(101,'Jadzia','Piernik','jadzia@cc.com','12321', 2000, 101, 2, 2);
  INSERT OR IGNORE INTO students VALUES(102,'Maciek','Jankowicz','maciek99@cc.com','12321', 20, 3000, 2, 2);
  INSERT OR IGNORE INTO students VALUES(103,'Balbina','Karp','karpik007@cc.com','12321', 20, 0, 3, 3);
  INSERT OR IGNORE INTO students VALUES(104,'Alfred','Szlarski','pan.samochodzik@cc.com','12321', 200, 3000, 3, 3);
  INSERT OR IGNORE INTO students VALUES(105,'Jakub','Wedrowycz','bimbrownik@gmail.com','12321', 20, 40000, 3, 3);
  INSERT OR IGNORE INTO mentors VALUES(10,'Jarek','Kucharczyk','jarek@gmail.com','12321', 2);
  INSERT OR IGNORE INTO mentors VALUES(11,'Dominik','Strazak','pogromca@cc.com','12321', 3);
  INSERT OR IGNORE INTO mentors VALUES(12,'Konrad','Mazina','kondzio999999@cc.com','12321', 3);

  -- attendance:
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-01',1,100);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-02',1,100);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-03',0,100);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-04',1,100);

  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-01',1,101);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-02',1,101);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-03',1,101);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-04',0,101);

  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-01',1,102);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-02',1,102);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-03',1,102);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-04',0,102);

  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-01',1,103);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-02',1,103);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-03',1,103);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-04',1,103);

  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-01',1,104);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-02',0,104);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-03',0,104);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-04',0,104);

  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-01',0,105);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-02',1,105);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-03',0,105);
  INSERT OR IGNORE INTO attendance VALUES(null,'2018-02-04',1,105);

  -- items (quests and artifacts):
  INSERT OR IGNORE INTO quests VALUES(1,'Exploring a dungeon','B','Finishing a Teamwork week', 100,'Available');
  INSERT OR IGNORE INTO quests VALUES(2,'Solving the magic puzzle','B','Finishing an SI assignment', 100,'Available');
  INSERT OR IGNORE INTO quests VALUES(3,'Slaying a dragon','B','Passing a Checkpoint in the first attempt', 500,'Available');
  INSERT OR IGNORE INTO quests VALUES(4,'Spot trap','E','Spot a major mistake in the assignment', 50,'Available');
  INSERT OR IGNORE INTO quests VALUES(5,'Taming a pet','E','Doing a demo about a pet project', 100,'Available');
  INSERT OR IGNORE INTO quests VALUES(6,'Recruiting some n00bs','E','Taking part in the student screening process', 100,'Available');
  INSERT OR IGNORE INTO quests VALUES(7,'Forging weapons','E','Organizing a workshop for other students', 400,'Available');
  INSERT OR IGNORE INTO quests VALUES(8,'Master the mornings','E','Attend 1 months without being late', 300,'Available');
  INSERT OR IGNORE INTO quests VALUES(9,'Fast as an unicorn','E','deliver 4 consecutive SI week assignments on time', 500,'Available');
  INSERT OR IGNORE INTO quests VALUES(10,'Achiever','E','set up a SMART goal accepted by a mentor, then achieve it', 1000,'Available');
  INSERT OR IGNORE INTO quests VALUES(11,'Fortune','E','students choose the best project of the week. Selected team scores', 500,'Available');
  INSERT OR IGNORE INTO quests VALUES(12,'Creating an enchanteds scroll','E','Creating extra material for the current TW/SI topic (should be revised by mentors)', 500,'Available');
  INSERT OR IGNORE INTO quests VALUES(13,'Enter the arena','E','Do a presentation on a meet-up', 500,'Available');

  INSERT OR IGNORE INTO artifacts VALUES(1,'Combat training','B','Private mentoring', 50);
  INSERT OR IGNORE INTO artifacts VALUES(2,'Sanctuary','B','You can spend a day in home office', 1000);
  INSERT OR IGNORE INTO artifacts VALUES(3,'Time Travel','B','extend SI week assignment deadline by one day', 750);
  INSERT OR IGNORE INTO artifacts VALUES(4,'Circle of Sorcery','M','60 min workshop by a mentor(s) of the chosen topic', 6000);
  INSERT OR IGNORE INTO artifacts VALUES(5,'Summon Code Elemental','M','mentor joins a students team for a one hour', 2500);
  INSERT OR IGNORE INTO artifacts VALUES(6,'Tome of knowledge','M','Extra material for the current topic', 1500);
  INSERT OR IGNORE INTO artifacts VALUES(7,'Transform mentors','M','All mentors should dress up as pirates (or just funny) for the day', 5000);
  INSERT OR IGNORE INTO artifacts VALUES(8,'Teleport','M','The whole course goes to an off-school program instead for a day', 30000);
  INSERT OR IGNORE INTO artifacts VALUES(9,'Coffe','B','brain buff', 5);

DELETE FROM sqlite_sequence;

COMMIT;