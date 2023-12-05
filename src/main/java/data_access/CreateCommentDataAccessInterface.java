package data_access;

import entity.Comment;
import entity.CommentInterface;
import org.bson.types.ObjectId;

public interface CreateCommentDataAccessInterface {
    void addComment(Comment comment);
    CommentInterface getCommentByCommentID(ObjectId id);
    ObjectId getLoggedInUserId();
    void setLoggedInUserID(ObjectId id);
}
