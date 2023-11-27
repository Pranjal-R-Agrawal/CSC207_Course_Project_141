package use_case.generate_idea.interface_adapter;

import use_case.generate_idea.application_business_rules.GenerateIdeaInputBoundary;

public class GenerateIdeaController {
    final GenerateIdeaInputBoundary generateIdeaUseCaseInteractor;

    public GenerateIdeaController(GenerateIdeaInputBoundary generateIdeaUseCaseInteractor) {
        this.generateIdeaUseCaseInteractor= generateIdeaUseCaseInteractor;
    }

    public void execute(boolean testForError) {
       generateIdeaUseCaseInteractor.execute(testForError);
    }
}
