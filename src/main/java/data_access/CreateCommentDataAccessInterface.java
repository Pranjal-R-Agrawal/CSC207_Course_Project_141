package data_access;

import entity.Comment;
import org.bson.types.ObjectId;

public interface CreateCommentDataAccessInterface {
    void addComment(Comment comment);

    ObjectId getLoggedInUserId();
}
