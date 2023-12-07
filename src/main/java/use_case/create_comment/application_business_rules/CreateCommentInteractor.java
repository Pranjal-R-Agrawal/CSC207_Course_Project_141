package use_case.create_comment.application_business_rules;

import data_access.CreateCommentDataAccessInterface;
import entity.Comment;
import entity.CommentFactory;
import entity.CommentFactoryInterface;
import entity.CommentInterface;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

/**
 * A concrete implementation of the CreateCommentInputBoundary which adds a comment to the database
 * and passes the id of the comment to be displayed to the user.
 * @author Yathusan Koneswararajah
 */
public class CreateCommentInteractor implements CreateCommentInputBoundary {
    final CreateCommentDataAccessInterface commentDataAccessObject;
    final CreateCommentOutputBoundary commentPresenter;
    final CommentFactoryInterface commentFactory;

    /**
     *Initializes the CreateCommentInteractor object.
     * @param commentPresenter Recieves output data (id) to display the appropriate comment.
     * @param commentDataAccessObject An object that puts the created comment into the database.
     * @param commentFactory An object that generates a Post entity given the necessary information
     */
    public CreateCommentInteractor(CreateCommentOutputBoundary commentPresenter, CreateCommentDataAccessInterface commentDataAccessObject, CommentFactoryInterface commentFactory){
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
        this.commentFactory = commentFactory;
    }

    /**
     * Creates a Comment entity based on the InputData and puts it into the database
     * and passes the created comment's id to the presenter but if inputs are invalid, it sends an
     * error to be displayed by the Presenter.
     * @param createCommentInputData A data class that contains the inputs for the post
     */
    public void execute(CreateCommentInputData createCommentInputData){
        if (createCommentInputData.getBody() == null || createCommentInputData.getBody().trim().isEmpty()){
            commentPresenter.prepareFailureView("Please enter text in body.");
        } else if (createCommentInputData.getQualifications() == null || createCommentInputData.getQualifications().trim().isEmpty()) {
            commentPresenter.prepareFailureView("Please enter at least 1 qualification.");
        } else {
            List<String> qualifications = Arrays.asList(createCommentInputData.getQualifications().split(";"));
            qualifications.replaceAll(String::trim);
            qualifications.removeIf(String::isEmpty);
            ObjectId authorId = commentDataAccessObject.getLoggedInUserId();
            CommentInterface comment = commentFactory.create(createCommentInputData.getParentId(),
                    createCommentInputData.getParentPostId(),
                    authorId,
                    createCommentInputData.getBody(),
                    qualifications);
            commentDataAccessObject.addComment((Comment)comment);
            commentPresenter.prepareSuccessView(comment.getId());
        }
    }
}
