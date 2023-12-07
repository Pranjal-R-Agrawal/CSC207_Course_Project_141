package use_case.view_profile;

import data_access.MockViewProfileDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import data_access.ViewProfileDataAccessInterface;

import entity.User;
import entity.UserFactory;
import org.junit.Test;
import use_case.view_profile.application_business_rules.ViewProfileInputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileInteractor;
import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileOutputData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewProfileInteractorTest {

    public ViewProfileInteractorTest() {
    }



    @Test
    public void successTest() {
//        MongoDBDataAccessObject mongoDBDataAccessObject;
//        try {mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
//            User user = (User) new UserFactory().create("testuser1","testpass1", "test1","test1@email.com","123-456-781","testcity1", "t" +
//                    "coding");
//            mongoDBDataAccessObject.addUser(user);
//            mongoDBDataAccessObject.setLoggedInUserID(user.getId());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
        MockViewProfileDataAccessObject mockViewProfileDataAccessObject= new MockViewProfileDataAccessObject();

        ViewProfileOutputBoundary successPresenter = new ViewProfileOutputBoundary() {

            @Override
            public void prepareSuccessView(ViewProfileOutputData viewProfileOutputData) {


                assertTrue(viewProfileOutputData.getUsername() instanceof String);
                assertTrue(viewProfileOutputData.getName() instanceof String);
                assertTrue(viewProfileOutputData.getEmail() instanceof String);

                assertEquals("testuser", viewProfileOutputData.getUsername());
                assertEquals("test", viewProfileOutputData.getName());
                assertEquals("test@email.com", viewProfileOutputData.getEmail());


            }
        };
        ViewProfileInputBoundary interactor = new ViewProfileInteractor(mockViewProfileDataAccessObject, successPresenter);
        interactor.execute();

    }

}
