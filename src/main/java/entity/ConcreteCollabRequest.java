package entity;

import org.bson.types.ObjectId;

public class ConcreteCollabRequest implements CollabRequest {
    private ObjectId postId;
    private String commenter;
    private String author;
    private String title;

    public ConcreteCollabRequest() {}

    public ConcreteCollabRequest(ObjectId postId, String commenter, String author, String title) {
        this.postId = postId;
        this.commenter = commenter;
        this.author = author;
        this.title = title;
    }
    @Override
    public ObjectId getPostId() {
        return postId;
    }
    @Override
    public void setPostId(ObjectId postId) {
        this.postId =  postId;
    }
    @Override
    public String getAuthor() {
        return author;
    }
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String getCommenter() {
        return commenter;
    }
    @Override
    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

}
