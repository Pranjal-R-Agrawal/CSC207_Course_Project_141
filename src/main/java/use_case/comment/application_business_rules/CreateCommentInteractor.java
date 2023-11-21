package use_case.comment.application_business_rules;

import data_access.CommentDataAccessInterface;
import entity.Comment;
import entity.CommentFactory;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

public class CreateCommentInteractor implements CreateCommentInputBoundary {
    final CommentDataAccessInterface commentDataAccessObject;
    final CreateCommentOutputBoundary commentPresenter;
    final CommentFactory commentFactory;

    public CreateCommentInteractor(CreateCommentOutputBoundary commentPresenter, CommentDataAccessInterface commentDataAccessObject, CommentFactory commentFactory){
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
        this.commentFactory = commentFactory;
    }

    public void execute(CreateCommentInputData createCommentInputData){
        if (createCommentInputData.getBody() == null){
            commentPresenter.prepareFailureView("Please enter text in body.");
        } else if (createCommentInputData.getQualifications().isEmpty()) {
            commentPresenter.prepareFailureView("Please enter at least 1 qualification.");
        } else {
            List<String> qualifications = Arrays.asList(createCommentInputData.getQualifications().split(";"));
            ObjectId authorId = commentDataAccessObject.getLoggedInUserId();
            Comment comment = commentFactory.create(createCommentInputData.getParentId(),
                    createCommentInputData.getParentPostId(),
                    authorId,
                    createCommentInputData.getBody(),
                    qualifications);
            commentDataAccessObject.addComment(comment);
            commentPresenter.prepareSuccessView();
        }
    }
}
