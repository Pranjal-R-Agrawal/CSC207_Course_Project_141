package app;

import api.GenerativeAIAPI;
import data_access.GenerateIdeaDataAccessInterface;
import use_case.generate_idea.application_business_rules.GenerateIdeaInputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaInteractor;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputBoundary;
import use_case.generate_idea.interface_adapter.GenerateIdeaController;
import use_case.generate_idea.interface_adapter.GenerateIdeaPresenter;
import view.*;

/**
 * Static Factory class for assembling the required components for the Generate Idea Use Case
 * @author Sidharth Sawhney
 */
public class GenerateIdeaUseCaseFactory {

    /** Prevents instantiation */
    private GenerateIdeaUseCaseFactory() {}


    /**
     * Creates the View for the Generate Idea Use Case
     * @param viewManagerModel Manages which view is displayed
     * @param generateIdeaViewModel Observable that stores the state useful to the Generate Idea View.
     * @param generateIdeaDataAccessObject Helps us interact appropriately with the Database
     * @param generativeAIAPI the API AI Model used to generate business model
     * @param createPostViewModel Observable that stores the state useful to Create Post View.
     * @param homePageViewModel Observable that stores the state useful to Home Page View.
     * @return the View for the Generate Idea Use Case
     */
    public static GenerateIdeaView create(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel, CreatePostViewModel createPostViewModel, GenerateIdeaDataAccessInterface generateIdeaDataAccessObject, GenerativeAIAPI generativeAIAPI, HomePageViewModel homePageViewModel) {
        GenerateIdeaController generateIdeaController = createGenerateIdeaUseCase(viewManagerModel, generateIdeaViewModel, generateIdeaDataAccessObject, generativeAIAPI);
        return new GenerateIdeaView(viewManagerModel, generateIdeaViewModel, createPostViewModel, generateIdeaController, homePageViewModel);
    }
    private static GenerateIdeaController createGenerateIdeaUseCase(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel, GenerateIdeaDataAccessInterface generateIdeaDataAccessObject, GenerativeAIAPI generativeAIAPI) {
        GenerateIdeaOutputBoundary generateIdeaPresenter = new GenerateIdeaPresenter(viewManagerModel, generateIdeaViewModel);
        GenerateIdeaInputBoundary generateIdeaInteractor = new GenerateIdeaInteractor(generateIdeaDataAccessObject, generateIdeaPresenter,generativeAIAPI);
        return new GenerateIdeaController(generateIdeaInteractor);
    }
}
