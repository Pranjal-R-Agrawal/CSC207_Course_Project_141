package data_access;

import entity.Post;
import org.bson.types.ObjectId;

public interface CreatePostDataAccessInterface {
    void AddPost(Post post);

    ObjectId getLoggedInUserId();
}
