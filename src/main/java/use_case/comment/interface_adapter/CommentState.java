package use_case.comment.interface_adapter;


import org.bson.types.ObjectId;

public class CommentState {
    private String body;
    private String qualifications;
    private ObjectId parentId;
    private ObjectId parentPostId;
    private String errorMessage;

    public String getBody(){return body;}

    public CommentState setBody(String body) {
        this.body = body;
        return this;
    }
    public String getQualifications(){return qualifications;}
    public CommentState setQualifications(String qualifications){
        this.qualifications = qualifications;
        return this;
    }

    public ObjectId getParentId() {return parentId;}

    public CommentState setParentId(ObjectId parentId) {
        this.parentId = parentId;
        return this;
    }

    public ObjectId getParentPostId() {return parentPostId;}

    public CommentState setParentPostId(ObjectId parentPostId) {
        this.parentPostId = parentPostId;
        return this;
    }

    public String getErrorMessage(){return errorMessage;}
    public CommentState setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }
}
