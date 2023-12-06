package use_case.view_user_info;

import data_access.MockViewUserInfoDataAccessObject;
import data_access.ViewUserInfoDataAccessInterface;
import org.bson.types.ObjectId;
import use_case.view_user_info.application_business_rules.*;

import org.junit.Test;

import static org.junit.Assert.*;


public class ViewUserInfoInteractorTest {

    @Test
    public void testIsCollaboratorCase() {
        ViewUserInfoInputData inputData = new ViewUserInfoInputData(new ObjectId(), true);
        ViewUserInfoDataAccessInterface mockDataAccessObject = new MockViewUserInfoDataAccessObject();

        ViewUserInfoOutputBoundary viewUserInfoPresenter = new ViewUserInfoOutputBoundary() {
            @Override
            public void prepareCollabView(ViewUserInfoOutputData user) {
                // "testname", "test@email.com", "0000000000", "testcity", "testexpertise"
                assertEquals("testname", user.getName());
                assertEquals("testexpertise", user.getFieldOfExpertise());
                assertEquals("testcity", user.getCity());
                assertEquals("test@email.com", user.getEmail());
                assertEquals("0000000000", user.getPhoneNumber());
            }

            @Override
            public void prepareGeneralView(ViewUserInfoOutputData user) {

                fail("General View is not expected.");

            }

            @Override
            public void prepareFailView(String error) {

                fail("Use case fail is unexpected.");

            }
        };

        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(mockDataAccessObject, viewUserInfoPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testIsGeneralUserCase() {
        ViewUserInfoInputData inputData = new ViewUserInfoInputData(new ObjectId(), false);
        ViewUserInfoDataAccessInterface mockDataAccessObject = new MockViewUserInfoDataAccessObject();

        ViewUserInfoOutputBoundary viewUserInfoPresenter = new ViewUserInfoOutputBoundary() {
            @Override
            public void prepareCollabView(ViewUserInfoOutputData user) {

                fail("Collaborator View is not expected");

            }

            @Override
            public void prepareGeneralView(ViewUserInfoOutputData user) {
                // "testname", "test@email.com", "0000000000", "testcity", "testexpertise"
                assertEquals("testname", user.getName());
                assertEquals("testexpertise", user.getFieldOfExpertise());
                assertEquals("", user.getCity());
                assertEquals("", user.getEmail());
                assertEquals("", user.getPhoneNumber());
            }

            @Override
            public void prepareFailView(String error) {

                fail("Use case fail is unexpected.");

            }
        };

        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(mockDataAccessObject, viewUserInfoPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testFailView() {
        ViewUserInfoInputData inputData = new ViewUserInfoInputData(new ObjectId(), false);
        ViewUserInfoDataAccessInterface mockDataAccessObject = new MockViewUserInfoDataAccessObject();

        ViewUserInfoOutputBoundary viewUserInfoPresenter = new ViewUserInfoOutputBoundary() {
            @Override
            public void prepareCollabView(ViewUserInfoOutputData user) {

                fail("Collaborator View is not expected");

            }

            @Override
            public void prepareGeneralView(ViewUserInfoOutputData user) {

                fail("General View is not expected.");

            }

            @Override
            public void prepareFailView(String error) {

                assertEquals("Failed to retreive User", error);

            }
        };

        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(mockDataAccessObject, viewUserInfoPresenter);
        interactor.execute(inputData);
    }
}
