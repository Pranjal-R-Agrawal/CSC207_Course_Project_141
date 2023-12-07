package use_case.generate_idea.interface_adapter;

import use_case.generate_idea.application_business_rules.GenerateIdeaOutputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputData;
import view.ViewManagerModel;

/**
 * Concrete implementation of the GenerateIdeaOutputBoundary.
 * Presenter updates state of the GenerateIdeaViewModel and triggers te GenerateIdeaView to observe and display updated GenerateIdeaViewModel.
 * @author Sidharth Sawhney
 */
public class GenerateIdeaPresenter implements GenerateIdeaOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GenerateIdeaViewModel generateIdeaViewModel;

    /**
     * Initializes the Presenter configured with the manager model to display appropriate screen and View Model to update its state
     * @param viewManagerModel Manages which view is displayed
     * @param generateIdeaViewModel Observable that stores the state useful to the Generate Idea View.
     */
    public GenerateIdeaPresenter(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.generateIdeaViewModel = generateIdeaViewModel;
    }

    /**
     * Updates state of the GenerateIdeaViewModel and instructs it to inform the GenerateIdeaView. Switches the display screen to GenerateIdeaView.
     * @param idea - Output data (the Idea object) to be displayed
     */
    @Override
    public void prepareSuccessView(GenerateIdeaOutputData idea) {

        generateIdeaViewModel.getState().setIdea(idea.getIdea()).setBusinessModel(idea.getBusinessModel());
        generateIdeaViewModel.firePropertyChanged("business_model_display");

        viewManagerModel.setActiveView(generateIdeaViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
