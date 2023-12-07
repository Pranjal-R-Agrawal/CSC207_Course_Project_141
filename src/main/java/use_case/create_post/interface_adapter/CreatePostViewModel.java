package use_case.create_post.interface_adapter;

import use_case.create_post.interface_adapter.CreatePostState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The observable that contains what the CreatePostView will observe
 * @author Yathusan Koneswararajah
 */
public class CreatePostViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Enter Title";
    public static final String BODY_LABEL = "Enter Project Description";
    public static final String COLLABORATOR_ROLES_LABEL = "Enter Suggested Collaborator Roles Seperated By Semi-Colons (;)";
    public static final String POST_BUTTON_LABEL = "Post";
    private CreatePostState state = new CreatePostState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Initializes the observable
     */
    public CreatePostViewModel() {super("post");}

    /**
     * @return Returns the current state
     */
    public CreatePostState getState() {return state;}

    /**
     * Updates the current state with the inputted state
     * @param state A CreatePostState
     */
    public void setState(CreatePostState state) {this.state = state;}

    /**
     * Instructs the listening views to change based on the property change
     * @param propertyName Name of property that was changed
     */
    public void firePropertyChanged(String propertyName){
        support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Adds a new observer of this ViewModel
     * @param listener A view that wants to observe the changes of this view model
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
