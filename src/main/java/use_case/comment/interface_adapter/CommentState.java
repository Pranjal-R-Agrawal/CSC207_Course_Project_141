package use_case.comment.interface_adapter;


public class CommentState {
    private String body;
    private String qualifications;
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
    public String getErrorMessage(){return errorMessage;}
    public CommentState setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }
}
