package use_case.create_comment.application_business_rules;

import org.bson.types.ObjectId;

/**
 * Bundles the inputs for a comment that is to be created
 * @author Yathusan Koneswararajah
 */
public class CreateCommentInputData {
    final private ObjectId parentId;
    private final ObjectId parentPostId;
    final private String body;
    final private String qualifications;

    /**
     * Initializes the data class that bundles the necessary data for creating comments
     * @param parentId ID of parent comment
     * @param parentPostId ID of parent post
     * @param body Body of comment
     * @param qualifications Qualifications the commenter listed
     */
    public CreateCommentInputData(ObjectId parentId, ObjectId parentPostId, String body, String qualifications){
        this.parentId = parentId;
        this.parentPostId = parentPostId;
        this.body = body;
        this.qualifications = qualifications;
    }

    /**
     * @return Returns parent comment's ID
     */
    public ObjectId getParentId(){return parentId;}

    /**
     * @return Returns parent post's ID
     */
    public ObjectId getParentPostId() {return parentPostId;}

    /**
     * @return Returns body of comment
     */
    public String getBody(){return body;}

    /**
     * @return Returns qualifications
     */
    public String getQualifications(){return qualifications;}

}
