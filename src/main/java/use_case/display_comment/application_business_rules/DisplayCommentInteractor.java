package use_case.display_comment.application_business_rules;

import data_access.DisplayCommentDataAccessInterface;
import entity.Comment;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayCommentInteractor implements DisplayCommentInputBoundary {
    final DisplayCommentDataAccessInterface displayCommentDataAccessObject;
    final DisplayCommentOutputBoundary displayCommentPresenter;

    public DisplayCommentInteractor(DisplayCommentDataAccessInterface displayCommentDataAccessObject, DisplayCommentOutputBoundary displayCommentOutputBoundary) {
        this.displayCommentDataAccessObject = displayCommentDataAccessObject;
        this.displayCommentPresenter = displayCommentOutputBoundary;
    }

    public void execute(ObjectId parentPostID) {
        List<Comment> comments = displayCommentDataAccessObject.getCommentsByParentPostID(parentPostID);
        DisplayCommentOutputData outputData = new DisplayCommentOutputData(comments.size());
        for (Comment comment : comments) {
            outputData.getComments().put(comment.getId(), processComment(comment));
        }
        displayCommentPresenter.prepareSuccessView(outputData);
    }

    private Map<String, Object> processComment(Comment comment) {
        Map<String, Object> processedComment = new HashMap<>(8, 1);
        processedComment.put("id", comment.getId());
        processedComment.put("parentPostId", comment.getParentPostId());
        processedComment.put("parentId", comment.getParentId());
        processedComment.put("authorId", comment.getAuthorId());
        processedComment.put("authorName", displayCommentDataAccessObject.getUserById(comment.getAuthorId()).getUsername());
        processedComment.put("body", comment.getBody());
        processedComment.put("qualifications", comment.getQualifications());
        return processedComment;
    }
}
