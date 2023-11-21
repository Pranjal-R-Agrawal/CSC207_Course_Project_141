package view;

import use_case.comment.interface_adapter.CreateCommentState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateCommentViewModel extends ViewModel{
    public static final String BODY_LABEL = "Enter Comment";
    public static final String QUALIFICATIONS_LABEL = "Enter Qualifications Seperated By Semi-Colons (;)";
    public static final String COMMENT_BUTTON_LABEL = "Comment";
    private CreateCommentState state = new CreateCommentState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateCommentViewModel() {super("comment");}

    public CreateCommentState getState() {return state;}

    public void setState(CreateCommentState state){this.state = state;}

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
