package data_access;

import entity.ConcreteIdeaFactory;
import entity.Idea;
import entity.IdeaFactory;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

// TODO: Think about how to test loadIdeas private function- test if it is not a FileNotFoundException but some other Exception

public class IdeaDataFileDataAccessObjectTest {
    private IdeaDataFileDataAccessObject ideaDataFileDataAccessObject;
    private IdeaFactory ideaFactory;

    @Before
    public void setUpTest() throws Exception {
        ideaFactory = new ConcreteIdeaFactory();
        ideaDataFileDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv",ideaFactory);

    }
    @Test
    public void testGenerateRandomIdea()
    {
        assertNotNull(ideaDataFileDataAccessObject.generateRandomIdea());
        assertTrue(ideaDataFileDataAccessObject.generateRandomIdea() instanceof Idea);
    }

    @Test
    public void constructorFileNotFoundExceptionTest(){

        FileNotFoundException exception = assertThrows(FileNotFoundException.class,()->
                 new IdeaDataFileDataAccessObject("src/main/java/data_access/bad.csv",ideaFactory));
        assertEquals("Couldn't find Database", exception.getMessage());
    }
}
