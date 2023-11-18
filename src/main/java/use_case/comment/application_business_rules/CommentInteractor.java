package use_case.comment.application_business_rules;

import data_access.CommentDataAccessInterface;
import entity.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentInteractor implements CommentInputBoundary{
    final CommentDataAccessInterface commentDataAccessObject;
    final CommentOutputBoundary commentPresenter;

    public CommentInteractor(CommentOutputBoundary commentPresenter, CommentDataAccessInterface commentDataAccessObject){
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
    }

    public void execute(CommentInputData commentInputData){
        if (commentInputData.getBody() == null){
            commentPresenter.prepareFailureView("Please enter text in body.");
        } else if (commentInputData.getQualifications().isEmpty()) {
            commentPresenter.prepareFailureView("Please enter at least 1 qualification.");
        } else {
            List<String> qualifications = Arrays.asList(commentInputData.getQualifications().split(";"));
            Comment comment = new Comment(commentInputData.getParentId(),
                    commentInputData.getAuthorId(),
                    commentInputData.getBody(),
                    qualifications);
            commentDataAccessObject.addComment(comment);
            commentPresenter.prepareSuccessView();
        }
    }
}
