package use_case.collab_request.application_business_rules;

import data_access.CollabRequestDataAccessInterface;
import entity.*;
import org.bson.types.ObjectId;

public class CollabRequestInteractor implements CollabRequestInputBoundary{
    final CollabRequestDataAccessInterface collabRequestDataAccessObject;
    final CollabRequestOutputBoundary collabPresenter;
    final CollabRequestFactory collabRequestFactory;

    public CollabRequestInteractor(CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestOutputBoundary collabPresenter, CollabRequestFactory collabRequestFactory) {
        this.collabRequestDataAccessObject = collabRequestDataAccessObject;
        this.collabPresenter = collabPresenter;
        this.collabRequestFactory = collabRequestFactory;
    }
    @Override
    public void execute(CollabRequestInputData collabRequestInputData) {
        User commenter = collabRequestDataAccessObject.getUserById(
                collabRequestDataAccessObject.getCommentByCommentId(
                        collabRequestInputData.getCommentId()).getAuthorId());
        Comment comment = collabRequestDataAccessObject.getCommentByCommentId(
                collabRequestInputData.getCommentId());
        String author = collabRequestDataAccessObject.getUserById(
                collabRequestDataAccessObject.getPostByPostId(
                        comment.getParentPostId()
                ).getAuthorID()
        ).getUsername();
        String title = collabRequestDataAccessObject.getPostByPostId(
                comment.getParentPostId()
        ).getTitle();

        ObjectId collabId = collabRequestDataAccessObject.getCommentByCommentId(
                collabRequestInputData.getCommentId()
        ).getId();

        CollabRequest collabRequest = collabRequestFactory.create(collabId, author, title);
        collabRequestDataAccessObject.addCollabRequest(collabRequest);
        commenter.addCollaborationRequestId(collabId);








    }
}
