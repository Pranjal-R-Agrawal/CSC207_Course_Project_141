package use_case.collab_request;

import data_access.CollabRequestDataAccessInterface;
import data_access.MockCollabRequestDataAccessObject;
import entity.CollabRequestFactory;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
import use_case.collab_request.application_business_rules.CollabRequestInteractor;
import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;

import javax.swing.*;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollabRequestInteractorTest {

    @Test
    public void successTest() {
        MockCollabRequestDataAccessObject collabRequestDataAccessObject = new MockCollabRequestDataAccessObject();
        CollabRequestFactory collabRequestFactory = new CollabRequestFactory();
        ObjectId postid = collabRequestDataAccessObject.getIdPost();
        ObjectId userid = collabRequestDataAccessObject.getIdUser();
        CollabRequestOutputBoundary successPresenter = new CollabRequestOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertEquals(postid, collabRequestDataAccessObject.getUserById(userid).getCollaborationRequestIDs().get(0));
            }
        };

        CollabRequestInputBoundary interactor = new CollabRequestInteractor(collabRequestDataAccessObject, successPresenter, collabRequestFactory);
        interactor.execute(postid, userid,null);

    }



}
