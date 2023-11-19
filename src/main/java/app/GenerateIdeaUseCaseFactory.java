package app;

import api.GenerativeAIAPI;
import data_access.GenerateIdeaDataAccessInterface;
import use_case.generate_idea.application_business_rules.GenerateIdeaInputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaInteractor;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputBoundary;
import use_case.generate_idea.interface_adapter.GenerateIdeaController;
import use_case.generate_idea.interface_adapter.GenerateIdeaPresenter;
import view.*;

public class GenerateIdeaUseCaseFactory {

    /** Prevents instantiation */
    private GenerateIdeaUseCaseFactory() {}

    public static GenerateIdeaView create(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel, GenerateIdeaDataAccessInterface generateIdeaDataAccessObject, GenerativeAIAPI generativeAIAPI, HomePageViewModel homePageViewModel) {
        GenerateIdeaController generateIdeaController = createGenerateIdeaUseCase(viewManagerModel, generateIdeaViewModel, generateIdeaDataAccessObject, generativeAIAPI);
        return new GenerateIdeaView(viewManagerModel, generateIdeaViewModel, generateIdeaController, homePageViewModel);
    }
    private static GenerateIdeaController createGenerateIdeaUseCase(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel, GenerateIdeaDataAccessInterface generateIdeaDataAccessObject, GenerativeAIAPI generativeAIAPI) {
        GenerateIdeaOutputBoundary generateIdeaPresenter = new GenerateIdeaPresenter(viewManagerModel, generateIdeaViewModel);
        GenerateIdeaInputBoundary generateIdeaInteractor = new GenerateIdeaInteractor(generateIdeaDataAccessObject, generateIdeaPresenter,generativeAIAPI);
        return new GenerateIdeaController(generateIdeaInteractor);
    }
}
