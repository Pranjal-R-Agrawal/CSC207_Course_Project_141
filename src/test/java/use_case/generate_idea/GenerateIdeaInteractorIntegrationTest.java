package use_case.generate_idea;

import api.MistralCodegenAIAPI;
import api.MockAPIBusinessModels;
import data_access.GenerateIdeaDataAccessInterface;
import data_access.IdeaDataFileDataAccessObject;
import data_access.MockIdeaDataAccessObject;
import entity.ConcreteIdeaFactory;
import org.junit.Test;
import use_case.generate_idea.application_business_rules.GenerateIdeaInputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaInteractor;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputData;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class GenerateIdeaInteractorIntegrationTest {
    @Test
    public void successTest() throws Exception {
        GenerateIdeaDataAccessInterface generateIdeaDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv", new ConcreteIdeaFactory()); // IdeaDataFileDataAccessObject() constructor exception were already tested for in IdeaDataFileDataAccessObjectTest.java

        GenerateIdeaOutputBoundary successPresenter = new GenerateIdeaOutputBoundary() {
            @Override
            public void prepareSuccessView(GenerateIdeaOutputData ideaOutputData) {
                assertTrue(ideaOutputData.getBusinessModel() instanceof String);
                assertTrue(ideaOutputData.getIdea() instanceof String);
                assertNotEquals("Business model successfully produced", "Unable to produce Business Model",ideaOutputData.getBusinessModel());
                assertNotEquals("Not the initial state of the business model","",ideaOutputData.getBusinessModel());
            }
        };

        GenerateIdeaInputBoundary interactor = new GenerateIdeaInteractor(generateIdeaDataAccessObject, successPresenter, new MistralCodegenAIAPI());
        interactor.execute(false);
    }
    @Test
    public void failTest() throws Exception {
        GenerateIdeaDataAccessInterface generateIdeaDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv", new ConcreteIdeaFactory());// IdeaDataFileDataAccessObject() constructor exception were already tested for in IdeaDataFileDataAccessObjectTest.java

        GenerateIdeaOutputBoundary successPresenter = new GenerateIdeaOutputBoundary() {
            @Override
            public void prepareSuccessView(GenerateIdeaOutputData ideaOutputData) {
                assertTrue(ideaOutputData.getBusinessModel() instanceof String);
                assertTrue(ideaOutputData.getIdea() instanceof String);
                System.out.println(ideaOutputData.getBusinessModel());
                assertEquals("Business model could not be generated", "Unable to produce Business Model",ideaOutputData.getBusinessModel());
                assertNotEquals("Not the initial state of the business model","",ideaOutputData.getBusinessModel());
            }
        };

        GenerateIdeaInputBoundary interactor = new GenerateIdeaInteractor(generateIdeaDataAccessObject, successPresenter, new MistralCodegenAIAPI());
        interactor.execute(true);
    }

}
