package use_case.view_profile;

import data_access.MockViewProfileDataAccessObject;
import data_access.ViewProfileDataAccessInterface;
import java.lang.Double;

import org.junit.Before;
import org.junit.Test;
import use_case.view_profile.application_business_rules.ViewProfileInputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileInteractor;
import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileOutputData;
import use_case.view_profile.interface_adapter.ViewProfileController;
import view.ViewManagerModel;
import view.ViewProfileView;
import view.ViewProfileViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewProfileInteractorTest {

    public ViewProfileInteractorTest() {
    }



    @Test
    public void successTest() {
        ViewProfileDataAccessInterface viewProfileDataAccessObject = new MockViewProfileDataAccessObject();

        ViewProfileOutputBoundary successPresenter = new ViewProfileOutputBoundary() {

            @Override
            public void prepareSuccessView(ViewProfileOutputData viewProfileOutputData) {


                assertTrue(viewProfileOutputData.getUsername() instanceof String);
                assertTrue(viewProfileOutputData.getName() instanceof String);
                assertTrue(viewProfileOutputData.getEmail() instanceof String);
                assertTrue(viewProfileOutputData.getRating() instanceof String);
                assertEquals("testuser", viewProfileOutputData.getUsername());
                assertEquals("test", viewProfileOutputData.getName());
                assertEquals("test@email.com", viewProfileOutputData.getEmail());
                assertEquals("0.0", viewProfileOutputData.getRating());

            }
        };
        ViewProfileInputBoundary interactor = new ViewProfileInteractor(viewProfileDataAccessObject, successPresenter);
        interactor.execute();

    }

}
