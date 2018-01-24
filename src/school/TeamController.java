package school;

import users.StudentModel;

    public class TeamController {

        TeamModel team;

        public TeamController(TeamModel team){

            this.team = team;

        }

        public void addStudent(StudentModel student){

            // implementation to update database!!!

            team.addStudent(student);
        }

        public void removeStudent(StudentModel student){

            // implementation to update database!!!

            team.removeStudent(student);
        }
    }
