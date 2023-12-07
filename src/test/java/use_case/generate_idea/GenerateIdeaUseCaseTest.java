package use_case.generate_idea;

import api.GenerativeAIAPI;
import api.MistralCodegenAIAPI;
import data_access.IdeaDataFileDataAccessObject;
import entity.ConcreteIdeaFactory;
import entity.IdeaFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.generate_idea.application_business_rules.GenerateIdeaInteractor;
import use_case.generate_idea.interface_adapter.GenerateIdeaController;
import static org.junit.Assert.*;
import use_case.generate_idea.interface_adapter.GenerateIdeaPresenter;
import use_case.generate_idea.interface_adapter.GenerateIdeaViewModel;
import view.ViewManagerModel;

public class GenerateIdeaUseCaseTest {
    GenerateIdeaViewModel generateIdeaViewModel;
    IdeaDataFileDataAccessObject ideaDataFileDataAccessObject;
    GenerateIdeaController generateIdeaController;

    @Before
    public void setUpTest() throws Exception {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        IdeaFactory ideaFactory = new ConcreteIdeaFactory();
        generateIdeaViewModel = new GenerateIdeaViewModel();
        GenerativeAIAPI generativeAIAPI = new MistralCodegenAIAPI();
        ideaDataFileDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv", ideaFactory);
        generateIdeaController = new GenerateIdeaController(
                new GenerateIdeaInteractor(ideaDataFileDataAccessObject, new GenerateIdeaPresenter(viewManagerModel,generateIdeaViewModel), generativeAIAPI));
    }

    @Test
    public void testRealBusinessModel() {
        generateIdeaController.execute(false);
        assertNotEquals("Unable to produce Business Model",generateIdeaViewModel.getState().getBusinessModel());
    }
    @Test
    public void testErrorOutput() {
        generateIdeaController.execute(true);
        assertEquals("Unable to produce Business Model",generateIdeaViewModel.getState().getBusinessModel());
    }

}
