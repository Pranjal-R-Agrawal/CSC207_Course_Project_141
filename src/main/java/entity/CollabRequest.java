package entity;

import org.bson.types.ObjectId;

public class CollabRequest {
    private ObjectId postId;
    private String author;
    private String title;

    public CollabRequest() {}

    public CollabRequest(ObjectId postId, String author, String title) {
        this.postId = postId;
        this.author = author;
        this.title = title;
    }
    public ObjectId getPostId() {
        return postId;
    }
    public void setPostId(ObjectId postId) {
        this.postId =  postId;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
