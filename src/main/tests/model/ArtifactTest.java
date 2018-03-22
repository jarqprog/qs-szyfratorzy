package model;

import factory.ConnectionFactory;
import managers.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

public class ArtifactTest {

    @InjectMocks
    private Artifact artifact;

    @Mock
    private Connection connection;

    @Mock
    private ConnectionFactory connectionFactory;

    @Before
    public void setUp() {
        artifact = spy(new Artifact("tester", "testowyOpis", 100 ));
        connectionFactory = new ConnectionFactory();
    }

    @Test
    public void getPrice_ReturnsCorrectValue() {
        int expected = 100;
        assertEquals(expected, artifact.getPrice());
    }

    @Test
    public void setPrice_changesArtifactPriceToNewValue() {
        Artifact spy = spy(new Artifact("tester", "testowyOpis", 100 ));
        doNothing().when(spy).saveModel();
        int expected = 1000;
        spy.setPrice(1000);
        assertEquals(expected, spy.getPrice());
    }
}