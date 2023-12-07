package use_case.collab_request.application_business_rules;

import data_access.CollabRequestDataAccessInterface;
import entity.*;
import org.bson.types.ObjectId;
/**
 * Represents a CollabRequestInteractor
 */
public class CollabRequestInteractor implements CollabRequestInputBoundary{
    final CollabRequestDataAccessInterface collabRequestDataAccessObject;
    final CollabRequestOutputBoundary collabRequestPresenter;
    final CollabRequestFactory collabRequestFactory;
    /**
     * Constructor for CollabRequestInteractor
     * @param collabRequestDataAccessObject
     * @param collabPresenter
     * @param collabRequestFactory
     */
    public CollabRequestInteractor(CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestOutputBoundary collabPresenter, CollabRequestFactory collabRequestFactory) {
        this.collabRequestDataAccessObject = collabRequestDataAccessObject;
        this.collabRequestPresenter = collabPresenter;
        this.collabRequestFactory = collabRequestFactory;
    }
    /**
     * Executes the CollabRequestInteractor
     * @param collabRequestInputData
     * @param actualCommenterForTests
     */
    @Override
    public void execute(CollabRequestInputData collabRequestInputData, User actualCommenterForTests) {
        ObjectId commenterId = collabRequestInputData.getCommenterId();
        ObjectId postId = collabRequestInputData.getPostId();
        User commenter = collabRequestDataAccessObject.getUserById(commenterId);
        String postTitle = collabRequestDataAccessObject.getPostByPostId(postId).getTitle();
        ObjectId authorId = collabRequestDataAccessObject.getPostByPostId(postId).getAuthorID();
        String author = collabRequestDataAccessObject.getUserById(authorId).getUsername();


       // ObjectId collabId = collabRequestDataAccessObject.getPostByPostId(postId).getId();
        CollabRequest collabRequest = collabRequestFactory.create(postId, commenter.getUsername(), author, postTitle);
        collabRequestDataAccessObject.addCollabRequest(collabRequest);
        if(actualCommenterForTests != null) {
            commenter = actualCommenterForTests;
        }
        collabRequestPresenter.prepareSuccessView();
    }
}
