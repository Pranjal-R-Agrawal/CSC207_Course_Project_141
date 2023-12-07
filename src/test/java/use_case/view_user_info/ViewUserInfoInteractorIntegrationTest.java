package use_case.view_user_info;

import data_access.MockViewUserInfoDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import data_access.ViewUserInfoDataAccessInterface;
import entity.User;
import entity.UserFactory;
import org.bson.types.ObjectId;
import use_case.view_user_info.application_business_rules.*;

import org.junit.Test;

import static org.junit.Assert.*;


public class ViewUserInfoInteractorIntegrationTest {

    @Test
    public void testIsCollaboratorCase() {

        MongoDBDataAccessObject mongoDBDataAccessObject;

        try {

            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
            mongoDBDataAccessObject.resetDatabase();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

        UserFactory userFactory = new UserFactory();
        User user = (User) userFactory.create("testusername", "testpwd", "testname", "test@email.com", "0000000000", "testcity", "testexpertise");

        mongoDBDataAccessObject.addUser(user);

        User testUser = mongoDBDataAccessObject.getUserByUsername("testusername");
        ObjectId testUserId = testUser.getId();

        ViewUserInfoInputData inputData = new ViewUserInfoInputData(testUserId, true);

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

        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(mongoDBDataAccessObject, viewUserInfoPresenter);
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
        mockDataAccessObject.drop();

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
