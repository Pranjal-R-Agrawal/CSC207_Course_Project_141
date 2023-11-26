package use_case.display_comment.application_business_rules;

import data_access.DisplayCommentDataAccessInterface;
import entity.Comment;
import entity.Post;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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

    public void execute(DisplayCommentInputData displayCommentInputData) {
        ObjectId id = displayCommentInputData.getId();
        int config = displayCommentInputData.getConfig();
        
        Post post = null;
        List<Comment> comments;

        if (config == 0) {
            comments = new ArrayList<>();
            comments.add(displayCommentDataAccessObject.getCommentByCommentID(id));
            if (comments.get(0) == null) comments.clear();
        } else {
            post = displayCommentDataAccessObject.getPostByPostID(id);
            comments = displayCommentDataAccessObject.getCommentsByParentPostID(id);
        }

        DisplayCommentOutputData outputData = new DisplayCommentOutputData(comments.size());

        if (!comments.isEmpty()) {
            post = displayCommentDataAccessObject.getPostByPostID(comments.get(0).getParentPostId());
            for (Comment comment : comments) {
                outputData.getComments().put(comment.getId(), processComment(post, comment));
            }
        } else if (config == 0) {
            displayCommentPresenter.prepareFailView("Comment not found");
            return;
        }

        if (post == null) {
            displayCommentPresenter.prepareFailView("Post not found");
        } else if (config == 1) {
            outputData.getPost().putAll(processPost(post));
        }
        displayCommentPresenter.prepareSuccessView(outputData);
    }

    private Map<String, Object> processComment(Post post, Comment comment) {
        Map<String, Object> processedComment = new HashMap<>(8, 1);
        processedComment.put("id", comment.getId());
        processedComment.put("parentPostId", comment.getParentPostId());
        processedComment.put("parentId", comment.getParentId());
        processedComment.put("authorId", comment.getAuthorId());
        processedComment.put("username", displayCommentDataAccessObject.getUserById(comment.getAuthorId()).getUsername());
        processedComment.put("body", comment.getBody());
        processedComment.put("qualifications", comment.getQualifications());

        boolean commentAuthorIsPostAuthor = post.getAuthorID().equals(comment.getAuthorId());
        boolean commentAuthorIsCollaborator = post.getCollaboratorIDs().contains(comment.getAuthorId());
        boolean loggedInUserIsPostAuthor = post.getAuthorID().equals(displayCommentDataAccessObject.getLoggedInUserId());
        boolean loggedInUserIsCollaborator = post.getCollaboratorIDs().contains(displayCommentDataAccessObject.getLoggedInUserId());
        boolean loggedInUserIsCommentAuthor = comment.getAuthorId().equals(displayCommentDataAccessObject.getLoggedInUserId());

        processedComment.put("comment_author_is_post_author", commentAuthorIsPostAuthor);
        processedComment.put("logged_in_user_is_comment_author", loggedInUserIsCommentAuthor);
        processedComment.put("logged_in_user_is_post_author", loggedInUserIsPostAuthor);
        processedComment.put("show_more_info_button", (commentAuthorIsPostAuthor || commentAuthorIsCollaborator) && (loggedInUserIsPostAuthor || loggedInUserIsCollaborator));

        return processedComment;
    }

    private Map<String, Object> processPost(Post post) {
        Map<String, Object> processedPost = new HashMap<>(8, 1);

        processedPost.put("id", post.getId());
        processedPost.put("authorId", post.getAuthorID());
        processedPost.put("username", displayCommentDataAccessObject.getUserById(post.getAuthorID()).getUsername());
        processedPost.put("title", post.getTitle());
        processedPost.put("body", post.getBody());
        processedPost.put("suggested_collaborator_qualifications", post.getSuggestedCollaboratorQualifications());

        boolean loggedInUserIsPostAuthor = post.getAuthorID().equals(displayCommentDataAccessObject.getLoggedInUserId());

        processedPost.put("logged_in_user_is_post_author", loggedInUserIsPostAuthor);

        return processedPost;
    }
}