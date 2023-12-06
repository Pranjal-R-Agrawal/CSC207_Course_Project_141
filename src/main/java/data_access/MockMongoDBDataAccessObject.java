package data_access;

import entity.Comment;
import entity.CommentInterface;
import entity.Post;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

/**
 * Mock Database for testing interactors in isolation
 */
public class MockMongoDBDataAccessObject implements CreatePostDataAccessInterface, CreateCommentDataAccessInterface{
    private Map<ObjectId, Post> postsMap = new HashMap<>();
    private Map<ObjectId, Comment> commentMap = new HashMap<>();
    private ObjectId loggedInUserID;

    /**
     * Adds post to database
     * @param post Post entity
     */
    @Override
    public void addPost(Post post) {
        postsMap.put(post.getId(),post);
    }

    /**
     * Adds comment to database
     * @param comment Comment entity
     */
    @Override
    public void addComment(Comment comment) {
        commentMap.put(comment.getId(), comment);
    }

    /**
     * Returns comment based on provided id
     * @param id ObjectId of comment
     * @return Comment corresponding to id
     */
    @Override
    public CommentInterface getCommentByCommentID(ObjectId id) {
        return commentMap.get(id);
    }

    /**
     * Returns ID of user currently logged in
     * @return ObjectID of currently logged in user
     */
    @Override
    public ObjectId getLoggedInUserId() {
        return loggedInUserID;
    }

    /**
     * Set ID of currently logged in user
     * @param id ObjetID of currently logged in user
     */
    @Override
    public void setLoggedInUserID(ObjectId id) {
        loggedInUserID = id;
    }

    /**
     * Returns post based on provided id
     * @param id ObjectId of post
     * @return Post corresponding to id
     */
    @Override
    public Post getPostByPostID(ObjectId id) {
        return postsMap.get(id);
    }
}
