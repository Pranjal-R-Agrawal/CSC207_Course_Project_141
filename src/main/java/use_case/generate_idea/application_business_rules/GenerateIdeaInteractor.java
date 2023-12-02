package use_case.generate_idea.application_business_rules;

import api.GenerativeAIAPI;
import data_access.GenerateIdeaDataAccessInterface;
import entity.Idea;

/**
 * Concrete implementation of GenerateIdeaInputBoundary responsible for generating an Idea and passing it to the GenerateIdeaOutputBoundary to display it
 * @author Sidharth Sawhney
 * */
public class GenerateIdeaInteractor implements GenerateIdeaInputBoundary{
    final GenerateIdeaDataAccessInterface generateIdeaDataAccessObject;
    final GenerateIdeaOutputBoundary generateIdeaPresenter;
    final GenerativeAIAPI generativeAIAPI;


    /**
     * Initializes the GenerateIdeaInteractor object to perform application logic
     * @param generateIdeaDataAccessObject Helps us interact appropriately with the Database
     * @param generateIdeaPresenter Receives the output data from GenerateIdeaInteractor to facilitate with the display of the output data
     * @param generativeAIAPI the AI API used to generate business model
     */
    public GenerateIdeaInteractor(GenerateIdeaDataAccessInterface generateIdeaDataAccessObject, GenerateIdeaOutputBoundary generateIdeaPresenter, GenerativeAIAPI generativeAIAPI) {
        this.generateIdeaDataAccessObject = generateIdeaDataAccessObject;
        this.generateIdeaPresenter = generateIdeaPresenter;
        this.generativeAIAPI = generativeAIAPI;
    }


    /**
     * Generates a random idea, obtains the business model from the API call, and passes the complete idea to the Presenter
     * @param testForException a flag variable useful for testing purposes (set to false for normal execution)
     */
    @Override
    public void execute(boolean testForException) {
        Idea idea = null;

       try {
           idea = generateIdeaDataAccessObject.generateRandomIdea();
           String businessModel = generativeAIAPI.generateBusinessModel(idea,testForException);
           idea.setBusinessModel(businessModel);
           GenerateIdeaOutputData ideaOutputData = new GenerateIdeaOutputData(idea.getIdea(),idea.getBusinessModel());
           generateIdeaPresenter.prepareSuccessView(ideaOutputData);
       }catch (Exception e) {
           GenerateIdeaOutputData ideaOutputData = new GenerateIdeaOutputData(idea.getIdea(),e.getMessage());
           generateIdeaPresenter.prepareSuccessView(ideaOutputData);}
        }
    }
