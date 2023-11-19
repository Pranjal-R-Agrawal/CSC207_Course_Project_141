package view;

import use_case.comment.interface_adapter.CommentState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CommentViewModel extends ViewModel{
    public static final String BODY_LABEL = "Enter Comment";
    public static final String QUALIFICATIONS_LABEL = "Enter Qualifications Seperated By Semi-Colons (;)";
    public static final String COMMENT_BUTTON_LABEL = "Comment";
    private CommentState state = new CommentState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CommentViewModel() {super("comment");}

    public CommentState getState() {return state;}

    public void setState(CommentState state){this.state = state;}

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
