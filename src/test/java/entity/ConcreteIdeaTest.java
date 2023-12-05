package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConcreteIdeaTest {
    private ConcreteIdea idea;
    private ConcreteIdeaFactory ideaFactory = new ConcreteIdeaFactory();

    @Before
    public void setup() {
        idea = (ConcreteIdea)ideaFactory.create("3D printing");
    }

    @Test
    public void testSetup() {
        assertEquals("", idea.getBusinessModel());
        assertTrue(idea.getIdea() instanceof String);
    }

    @Test
    public void testBusinessModelSetGet() {
        idea = (ConcreteIdea) idea.setBusinessModel("Business Model");
        assertEquals("Business Model", idea.getBusinessModel());
    }

    @Test
    public void testIdeaGet() {

        assertEquals("3D printing", idea.getIdea());
    }
}
