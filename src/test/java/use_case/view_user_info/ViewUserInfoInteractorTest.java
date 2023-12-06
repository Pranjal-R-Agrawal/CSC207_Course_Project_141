package use_case.view_user_info;

import data_access.MockViewUserInfoDataAccessObject;
import data_access.ViewUserInfoDataAccessInterface;
import org.bson.types.ObjectId;
import use_case.view_user_info.application_business_rules.*;

import org.junit.Test;
import use_case.view_user_info.interface_adapter.ViewUserInfoPresenter;

import static org.junit.Assert.*;


public class ViewUserInfoInteractorTest {

    public void testIsCollaboratorCase() {
        ViewUserInfoInputData inputData = new ViewUserInfoInputData(new ObjectId(), true);
        ViewUserInfoDataAccessInterface dataAccessObject = new MockViewUserInfoDataAccessObject();

        ViewUserInfoOutputBoundary viewUserInfoPresenter = new ViewUserInfoOutputBoundary() {
            @Override
            public void prepareCollabView(ViewUserInfoOutputData user) {
                assertEquals();
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

        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(dataAccessObject, viewUserInfoPresenter);
        interactor.execute(inputData);
    }
}
