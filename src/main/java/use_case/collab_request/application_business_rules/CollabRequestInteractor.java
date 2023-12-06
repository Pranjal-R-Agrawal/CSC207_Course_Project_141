package use_case.collab_request.application_business_rules;

import data_access.CollabRequestDataAccessInterface;
import entity.*;
import org.bson.types.ObjectId;

public class CollabRequestInteractor implements CollabRequestInputBoundary{
    final CollabRequestDataAccessInterface collabRequestDataAccessObject;
    final CollabRequestOutputBoundary collabRequestPresenter;
    final CollabRequestFactory collabRequestFactory;

    public CollabRequestInteractor(CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestOutputBoundary collabPresenter, CollabRequestFactory collabRequestFactory) {
        this.collabRequestDataAccessObject = collabRequestDataAccessObject;
        this.collabRequestPresenter = collabPresenter;
        this.collabRequestFactory = collabRequestFactory;
    }
    @Override
    public void execute(ObjectId postId , ObjectId userId, User actualCommenterForTests) {
        User commenter = collabRequestDataAccessObject.getUserById(userId);
        String postTitle = collabRequestDataAccessObject.getPostByPostId(postId).getTitle();
        ObjectId authorId = collabRequestDataAccessObject.getPostByPostId(postId).getAuthorID();
        String author = collabRequestDataAccessObject.getUserById(authorId).getUsername();


       // ObjectId collabId = collabRequestDataAccessObject.getPostByPostId(postId).getId();

        CollabRequest collabRequest = collabRequestFactory.create(postId, author, postTitle);
        collabRequestDataAccessObject.addCollabRequest(collabRequest);
        if(actualCommenterForTests != null) {
            commenter = actualCommenterForTests;
        }
        commenter.addCollabRequest(postId);
        collabRequestPresenter.prepareSuccessView();
    }
}
