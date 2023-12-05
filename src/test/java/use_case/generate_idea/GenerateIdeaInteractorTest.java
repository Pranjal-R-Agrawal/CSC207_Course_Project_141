package use_case.generate_idea;

import api.MockAPIBusinessModels;
import data_access.GenerateIdeaDataAccessInterface;
import data_access.MockIdeaDataAccessObject;
import org.junit.Test;
import use_case.generate_idea.application_business_rules.GenerateIdeaInputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaInteractor;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputData;

import static org.junit.Assert.*;

public class GenerateIdeaInteractorTest {
    
    @Test
    public void successTest() {
        GenerateIdeaDataAccessInterface generateIdeaDataAccessObject = new MockIdeaDataAccessObject();

        GenerateIdeaOutputBoundary successPresenter = new GenerateIdeaOutputBoundary() {
            @Override
            public void prepareSuccessView(GenerateIdeaOutputData ideaOutputData) {
                assertTrue(ideaOutputData.getBusinessModel() instanceof String);
                assertTrue(ideaOutputData.getIdea() instanceof String);
                assertNotEquals("Business model successfully produced", "Unable to produce Business Model",ideaOutputData.getBusinessModel());
                assertNotEquals("Not the initial state of the business model","",ideaOutputData.getBusinessModel());
            }
        };

        GenerateIdeaInputBoundary interactor = new GenerateIdeaInteractor(generateIdeaDataAccessObject, successPresenter, new MockAPIBusinessModels());
        interactor.execute(false);
    }
    @Test
    public void failTest()
    {
        GenerateIdeaDataAccessInterface generateIdeaDataAccessObject = new MockIdeaDataAccessObject();

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

        GenerateIdeaInputBoundary interactor = new GenerateIdeaInteractor(generateIdeaDataAccessObject, successPresenter, new MockAPIBusinessModels());
        interactor.execute(true);

    }
}
