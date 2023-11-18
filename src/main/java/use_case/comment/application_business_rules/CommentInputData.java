package use_case.comment.application_business_rules;

import org.bson.types.ObjectId;


public class CommentInputData {
    final private ObjectId parentId;
    final private ObjectId authorId;
    final private String body;
    final private String qualifications;

    public CommentInputData(ObjectId parentId, ObjectId authorId, String body, String qualifications){
        this.parentId = parentId;
        this.authorId = authorId;
        this.body = body;
        this.qualifications = qualifications;
    }
    public ObjectId getParentId(){return parentId;}
    public ObjectId getAuthorId(){return authorId;}
    public String getBody(){return body;}
    public String getQualifications(){return qualifications;}

}
