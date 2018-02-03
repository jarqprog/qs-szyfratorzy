package school;

import java.util.List;

public class SchoolController {

    private SchoolModel school;

    public SchoolController(SchoolModel school){
        this.school = school;
    }

    public SchoolController(){
        this.school = new SchoolModel();
    }

    public List<GroupModel> getGroups(){
        return school.getGroups();
    }

    public List<TeamModel> getTeams(){
        return school.getTeams();
    }

    public ExperienceLevels getExperienceLevels(){
        return school.getExperienceLevels();
    }

    public SchoolModel getSchool(){
        return school;
    }

}
