package view;

import use_case.create_post.interface_adapter.CreatePostState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreatePostViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Enter Title";
    public static final String BODY_LABEL = "Enter Project Description";
    public static final String COLLABORATOR_ROLES_LABEL = "Enter Suggested Collaborator Roles Seperated By Semi-Colons (;)";
    public static final String POST_BUTTON_LABEL = "Post";
    private CreatePostState state = new CreatePostState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreatePostViewModel() {super("post");}

    public CreatePostState getState() {return state;}

    public void setState(CreatePostState state) {this.state = state;}

    public void firePropertyChanged(String propertyName){
        support.firePropertyChange(propertyName, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
