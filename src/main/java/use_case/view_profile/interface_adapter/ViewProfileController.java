package use_case.view_profile.interface_adapter;

import use_case.view_profile.application_business_rules.ViewProfileInputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileInputData;
/**
 * Routes the control to application logic to perform the view profile use case
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileController {
    final ViewProfileInputBoundary viewProfileUseCaseInteractor;
    /**
     * Initializes the controller configured with the ViewProfileInputBoundary to route to.
     * @param  viewProfileUseCaseInteractor the interactor for the ViewProfileUseCase
     */
    public ViewProfileController(ViewProfileInputBoundary viewProfileUseCaseInteractor) {
        this.viewProfileUseCaseInteractor = viewProfileUseCaseInteractor;
    }
    /**
     * Passes the control to the ViewProfileInputBoundary (in the form of the use case interactor) to perform view profile use case logic
     */
    public void execute() {
        viewProfileUseCaseInteractor.execute();
    }

}
