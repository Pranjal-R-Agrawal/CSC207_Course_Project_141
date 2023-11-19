package use_case.generate_idea.interface_adapter;

import use_case.generate_idea.application_business_rules.GenerateIdeaOutputBoundary;
import use_case.generate_idea.application_business_rules.GenerateIdeaOutputData;
import view.GenerateIdeaViewModel;
import view.ViewManagerModel;

public class GenerateIdeaPresenter implements GenerateIdeaOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GenerateIdeaViewModel generateIdeaViewModel;
    public GenerateIdeaPresenter(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.generateIdeaViewModel = generateIdeaViewModel;
    }

    @Override
    public void prepareSuccessView(GenerateIdeaOutputData idea) {

        generateIdeaViewModel.getState().setIdea(idea.getIdea()).setBusinessModel(idea.getBusinessModel());

        viewManagerModel.setActiveView(generateIdeaViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
