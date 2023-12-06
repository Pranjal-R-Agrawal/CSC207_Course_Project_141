package use_case.view_profile;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import data_access.ViewProfileDataAccessInterface;
import data_access.ViewProfileDataFileAccessObject;
import entity.User;
import entity.UserFactory;
import org.bson.types.ObjectId;
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
        MongoDBDataAccessObject mongoDBDataAccessObject;
        try {mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
            User user = (User) new UserFactory().create("testuser1","testpass1", "test1","test1@email.com","123-456-781","testcity1", "t" +
                    "coding");
            mongoDBDataAccessObject.addUser(user);
            mongoDBDataAccessObject.setLoggedInUserID(user.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

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
        ViewProfileInputBoundary interactor = new ViewProfileInteractor(mongoDBDataAccessObject,
                successPresenter);
        interactor.execute();

    }
}
