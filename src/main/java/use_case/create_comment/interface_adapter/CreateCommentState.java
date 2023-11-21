package use_case.create_comment.interface_adapter;


import org.bson.types.ObjectId;

public class CreateCommentState {
    private String body;
    private String qualifications;
    private ObjectId parentId;
    private ObjectId parentPostId;
    private String errorMessage;

    public String getBody(){return body;}

    public CreateCommentState setBody(String body) {
        this.body = body;
        return this;
    }
    public String getQualifications(){return qualifications;}
    public CreateCommentState setQualifications(String qualifications){
        this.qualifications = qualifications;
        return this;
    }

    public ObjectId getParentId() {return parentId;}

    public CreateCommentState setParentId(ObjectId parentId) {
        this.parentId = parentId;
        return this;
    }

    public ObjectId getParentPostId() {return parentPostId;}

    public CreateCommentState setParentPostId(ObjectId parentPostId) {
        this.parentPostId = parentPostId;
        return this;
    }

    public String getErrorMessage(){return errorMessage;}
    public CreateCommentState setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }
}
