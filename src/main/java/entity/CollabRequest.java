package entity;

import org.bson.types.ObjectId;

public class CollabRequest {
    private ObjectId collabRequestId;
    private String author;
    private String title;

    public CollabRequest() {}

    public CollabRequest(ObjectId collabRequestId, String author, String title) {
        this.collabRequestId = collabRequestId;
        this.author = author;
        this.title = title;
    }

    public ObjectId getCollabRequestId() {
        return collabRequestId;
    }
    public void setCollabRequestId() {
        this.collabRequestId = collabRequestId;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor() {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle() {
        this.title = title;
    }

}
