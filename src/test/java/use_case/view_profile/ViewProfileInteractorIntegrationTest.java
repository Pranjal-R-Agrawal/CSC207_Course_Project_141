package use_case.view_profile;
import data_access.ViewProfileDataAccessInterface;
import data_access.ViewProfileDataFileAccessObject;
import org.junit.Test;
import use_case.view_profile.application_business_rules.ViewProfileInputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileInteractor;
import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileOutputData;
import use_case.view_profile.interface_adapter.ViewProfileController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewProfileInteractorIntegrationTest {
    @Test
    public void successTest() throws Exception {
        ViewProfileDataAccessInterface viewProfileDataAccessInterface = new ViewProfileDataFileAccessObject(
                "src/main/java/data_access/users.csv");
        ViewProfileOutputBoundary successPresenter = new ViewProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewProfileOutputData viewProfileOutputData) {
                assertTrue(viewProfileOutputData.getUsername() instanceof String);
                assertTrue(viewProfileOutputData.getName() instanceof String);
                assertTrue(viewProfileOutputData.getEmail() instanceof String);

                assertEquals("testuser1", viewProfileOutputData.getUsername());
                assertEquals("test1", viewProfileOutputData.getName());
                assertEquals("test1@email.com", viewProfileOutputData.getEmail());

            }
        };
        ViewProfileInputBoundary interactor = new ViewProfileInteractor(viewProfileDataAccessInterface,
                successPresenter);
        interactor.execute();

    }
}
