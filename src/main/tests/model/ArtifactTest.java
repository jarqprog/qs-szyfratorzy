package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtifactTest {

    @Test
    public void artifactShouldNotHaveMinusValue(){
            Artifact expected = new Artifact("Robienie kupy","wczoraj",0);
            Artifact actual = new Artifact("Robienie kupy", "wczoraj", -100);
            assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBeAbleToChangeToMinus(){
        Artifact expected = new Artifact("Robienie kupy","wczoraj",0);
        Artifact actual = new Artifact("Robienie kupy", "wczoraj", 0);
        actual.setPrice(-100);
        assertEquals(expected,actual);
    }


}