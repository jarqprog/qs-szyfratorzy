package application;

public class IntroOutroDAO extends DAO {

    public IntroOutroDAO(){
        defaultFilePath = FilePath.INTRO.getPath();
        String outroFilePath = FilePath.OUTRO.getPath();
        prepareFile();
        prepareFile(outroFilePath);
    }
}
