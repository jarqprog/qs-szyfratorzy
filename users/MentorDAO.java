package users;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MentorDAO extends UsersDAO
{

    public List<MentorModel> getTestMentors(){

        List<MentorModel> mentorsCollection = new ArrayList<MentorModel>();
        mentorsCollection.add(new MentorModel(2, "Kuba", "Bigos", "123",'0'));
        mentorsCollection.add(new MentorModel(4, "Marcin", "Kot", "123",'A'));
        mentorsCollection.add(new MentorModel(10, "Lola", "Szafraniec", "123", '0'));
        mentorsCollection.add(new MentorModel(22, "Bolek", "Kowalski", "123",'A'));
        mentorsCollection.add(new MentorModel(44, "Aneta", "Fajna", "123",'0'));

        return mentorsCollection;
    }
}
