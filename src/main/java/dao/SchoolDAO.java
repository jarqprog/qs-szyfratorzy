package dao;

import java.util.List;

public interface SchoolDAO extends SpecialDAO {

    List<String> getGroupNames();
    List<String> getTeamNames();
}
