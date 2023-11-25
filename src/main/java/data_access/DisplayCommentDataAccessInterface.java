package data_access;

import entity.Comment;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface DisplayCommentDataAccessInterface {
    Comment getCommentByCommentID(ObjectId id);
    Post getPostByPostID(ObjectId id);
    List<Comment> getCommentsByParentPostID(ObjectId id);
    User getUserById(ObjectId id);
}
