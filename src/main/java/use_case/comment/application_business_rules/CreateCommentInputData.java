package use_case.comment.application_business_rules;

import org.bson.types.ObjectId;


public class CreateCommentInputData {
    final private ObjectId parentId;
    private final ObjectId parentPostId;
    final private String body;
    final private String qualifications;

    public CreateCommentInputData(ObjectId parentId, ObjectId parentPostId, String body, String qualifications){
        this.parentId = parentId;
        this.parentPostId = parentPostId;
        this.body = body;
        this.qualifications = qualifications;
    }
    public ObjectId getParentId(){return parentId;}
    public ObjectId getParentPostId() {return parentPostId;}
    public String getBody(){return body;}
    public String getQualifications(){return qualifications;}

}
