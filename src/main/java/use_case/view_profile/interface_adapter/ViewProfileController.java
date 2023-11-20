package use_case.view_profile.interface_adapter;

import use_case.view_profile.application_business_rules.ViewProfileInputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileInputData;

public class ViewProfileController {
    final ViewProfileInputBoundary viewProfileUseCaseInteractor;

    public ViewProfileController(ViewProfileInputBoundary viewProfileUseCaseInteractor) {
        this.viewProfileUseCaseInteractor = viewProfileUseCaseInteractor;
    }

    public void execute() {
        viewProfileUseCaseInteractor.execute();
    }

}
