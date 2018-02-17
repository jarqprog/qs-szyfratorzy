package factory;

import dao.ArtifactDAO;
import model.Artifact;

public class ArtifactFactoryImpl implements ItemFactory {

    public Artifact create(String name, String description, int price) {

        Artifact artifact = new Artifact(name, description, price);
        int id =  new ArtifactDAO().saveObjectAndGetId(artifact);
        artifact.setId(id);
        return artifact;
    }
}