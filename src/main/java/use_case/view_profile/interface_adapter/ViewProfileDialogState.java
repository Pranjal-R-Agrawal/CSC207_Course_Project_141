package use_case.view_profile.interface_adapter;

/**
 * Stores the message string that is to be displayed
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileDialogState{
    private String message;

    /**
     * Getter for message
     * @return Returns the message string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for message
     * @param message the message that needs to be displayed
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
