package users;

public class MentorController extends AbstractUserController {
    MentorView mentorView;
    // MentorDao mentorDao;
    // StudentDao studentDao;
    // QuestDao questDao;
    // ArtefactDao artefactDao;

    public MentorController() {
        mentorView = new MentorView();
        // mentorDao = new MentorDao();
        // studentDao = new StudentDao();
        // questDao = new QuestDao();
        // artefactDao = new ArtefactDao();
    }

    public void createStudent() {
        String firstName = mentorView.getInput("Enter a first name:");
        String lastName = mentorView.getInput("Enter a last name:");
        String password = mentorView.getInput("Enter a password:");
        StudentModel newStudent = new StudentModel(firstName, lastName, password);
        // studentDao.exportData(newStudent);

    }

    public void createArtifact() {
        char itemType = getInput();
        String itemName = getInput("Enter an item name:");
        String itemDescription = getInput("Enter an item name:");
        int price = getPrice();

        new ArtefactModel(itemType, itemName, itemDescription, price);
    }
}
