package application;

public class IntroOutroDAO extends AbstractDAO{

    String outroFilePath;

    public IntroOutroDAO(){
        defaultFileName = "intro.txt";
        defaultFilePath = "DataFiles/intro.txt";
        outroFilePath = "DataFiles/outro.txt";
        prepareFile();
        prepareFile("DataFiles/outro.txt");
    }
}
