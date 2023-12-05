package data_access;

import entity.Post;
import org.bson.types.ObjectId;

public interface CreatePostDataAccessInterface {
    void addPost(Post post);

    ObjectId getLoggedInUserId();

    void setLoggedInUserID(ObjectId id);

    Post getPostByPostID(ObjectId id);
}
