package data_access;

import entity.Comment;
import entity.CommentInterface;
import entity.Post;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class MockMongoDBDataAccessObject implements CreatePostDataAccessInterface, CreateCommentDataAccessInterface{
    private Map<ObjectId, Post> postsMap = new HashMap<>();
    private Map<ObjectId, Comment> commentMap = new HashMap<>();
    private ObjectId loggedInUserID;
    @Override
    public void addPost(Post post) {
        postsMap.put(post.getId(),post);
    }

    @Override
    public void addComment(Comment comment) {
        commentMap.put(comment.getId(), comment);
    }

    @Override
    public CommentInterface getCommentByCommentID(ObjectId id) {
        return commentMap.get(id);
    }

    @Override
    public ObjectId getLoggedInUserId() {
        return loggedInUserID;
    }

    @Override
    public void setLoggedInUserID(ObjectId id) {
        loggedInUserID = id;
    }

    @Override
    public Post getPostByPostID(ObjectId id) {
        return postsMap.get(id);
    }
}
