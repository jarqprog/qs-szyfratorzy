package users;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MentorDAO extends UsersDAO
{

    public List<MentorModel> getTestMentors(){

        List<MentorModel> mentorsCollection = new ArrayList<MentorModel>();
        mentorsCollection.add(new MentorModel("Kuba", "Bigos", "123"));
        mentorsCollection.add(new MentorModel("Marcin", "Kot", "123"));
        mentorsCollection.add(new MentorModel("Lola", "Szafraniec", "123"));
        mentorsCollection.add(new MentorModel("Bolek", "Kowalski", "123"));
        mentorsCollection.add(new MentorModel("Aneta", "Fajna", "123"));

        return mentorsCollection;
    }
}
