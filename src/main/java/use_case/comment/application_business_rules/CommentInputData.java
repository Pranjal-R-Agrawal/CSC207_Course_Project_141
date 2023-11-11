package use_case.comment.application_business_rules;

import org.bson.types.ObjectId;

import java.util.List;

public class CommentInputData {
    final private ObjectId parentId;
    final private ObjectId authorId;
    final private String body;
    final private List<String> qualifications;
    final private List<String> collaborationRoles;

    public CommentInputData(ObjectId parentId, ObjectId authorId, String body, List<String> qualifications, List<String> collaborationRoles){
        this.parentId = parentId;
        this.authorId = authorId;
        this.body = body;
        this.qualifications = qualifications;
        this.collaborationRoles = collaborationRoles;
    }
    public ObjectId getParentId(){return parentId;}
    public ObjectId getAuthorId(){return authorId;}
    public String getBody(){return body;}
    public List<String> getQualifications(){return qualifications;}
    public List<String> getCollaborationRoles(){return collaborationRoles;}

}
