PRAGMA foreign_keys=ON;
BEGIN TRANSACTION;

DELETE FROM experience_levels;

  INSERT OR IGNORE INTO experience_levels VALUES(1,'novice',0);
  INSERT OR IGNORE INTO experience_levels VALUES(2,'apprentice',100);
  INSERT OR IGNORE INTO experience_levels VALUES(3,'junior programmer',1000);
  INSERT OR IGNORE INTO experience_levels VALUES(4,'programmer',10000);
  INSERT OR IGNORE INTO experience_levels VALUES(5,'senior programmer',100000);
  INSERT OR IGNORE INTO experience_levels VALUES(6,'almost mentor',1000000);

DELETE FROM sqlite_sequence;
COMMIT;