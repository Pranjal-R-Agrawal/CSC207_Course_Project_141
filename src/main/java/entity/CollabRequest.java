package entity;

import org.bson.types.ObjectId;

public interface CollabRequest {

    ObjectId getPostId();
    void setPostId(ObjectId postId);
    String getAuthor();
    void setAuthor(String author);
    String getTitle();
    void setTitle(String title);
    String getCommenter();
    void setCommenter(String commenter);
}
