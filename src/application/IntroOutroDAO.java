package application;

public class IntroOutroDAO extends DAO {

    String outroFilePath;

    public IntroOutroDAO(){
        defaultFileName = "intro.txt";
        defaultFilePath = "data_files/intro.txt";
        outroFilePath = "data_files/outro.txt";
        prepareFile();
        prepareFile("data_files/outro.txt");
    }
}
