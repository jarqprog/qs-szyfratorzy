package model;

public class ArtifactFactoryImpl implements ItemFactory {

    public Artifact create(String name, String description, int price) {

        Artifact artifact = new Artifact(name, description, price);
        int id =  ModelDaoFactory.getByType(ArtifactDAO.class).saveObjectAndGetId(artifact);
        artifact.setId(id);
        return artifact;
    }
}