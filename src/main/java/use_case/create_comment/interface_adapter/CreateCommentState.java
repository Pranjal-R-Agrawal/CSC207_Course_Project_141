package use_case.create_comment.interface_adapter;


import org.bson.types.ObjectId;

/**
 * Stores the state of CreateCommentViewModel
 * @author Yathusan Koneswararajah
 */
public class CreateCommentState {
    private String body;
    private String qualifications;
    private ObjectId parentId;
    private ObjectId parentPostId;
    private String errorMessage;

    /**
     * @return Returns body of comment
     */
    public String getBody(){return body;}

    /**
     * @param body Body of comment
     * @return Returns this state with updated body
     */
    public CreateCommentState setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * @return Returns qualification of comment
     */
    public String getQualifications(){return qualifications;}

    /**
     * @param qualifications Qualifications of comment
     * @return Returns this state with updated qualifications
     */
    public CreateCommentState setQualifications(String qualifications){
        this.qualifications = qualifications;
        return this;
    }

    /**
     * @return Returns parent ID of comment
     */
    public ObjectId getParentId() {return parentId;}

    /**
     * @param parentId ID of parent
     * @return Returns this state with updated parentID
     */
    public CreateCommentState setParentId(ObjectId parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * @return Returns ID of parent post
     */
    public ObjectId getParentPostId() {return parentPostId;}

    /**
     * @param parentPostId ID of parent post
     * @return Returns this state with update parentPostId
     */
    public CreateCommentState setParentPostId(ObjectId parentPostId) {
        this.parentPostId = parentPostId;
        return this;
    }

    /**
     * @return Returns error message
     */
    public String getErrorMessage(){return errorMessage;}

    /**
     * @param errorMessage Error message
     * @return Returns state with updated error message
     */
    public CreateCommentState setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }
}
