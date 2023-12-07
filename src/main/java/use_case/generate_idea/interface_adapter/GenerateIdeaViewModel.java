package use_case.generate_idea.interface_adapter;

import use_case.generate_idea.interface_adapter.GenerateIdeaState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The Observable containing state which GenerateIdeaView will observe.
 * @author Sidharth Sawhney
 */
public class GenerateIdeaViewModel extends ViewModel {
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String POST_BUTTON_LABEL = "Post";
    public static final String IDEA_BUTTON_LABEL = "Generate Idea!";
    private GenerateIdeaState state = new GenerateIdeaState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Initializes GenerateIdeaViewModel setting the viewName for its view
     */
    public GenerateIdeaViewModel() {
        super("generate_idea");
    }

    /**
     * @return the current state
     */
    public GenerateIdeaState getState() {
        return state;
    }

    /**
     * Updates the current state of the GenerateIdeaViewModel with the new state
     */
    public void setState(GenerateIdeaState state) {
        this.state = state;
    }

    /**
     * Instructs the Views listening to GenerateIdeaViewModel to react based on the property change.
     * @param propertyName the programmatic name of the property that was changed
     */
    @Override
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Configure an observer (View) of the GenerateIdeaViewModel
     * @param listener the View willing to observe the changes in state in the GenerateIdeaViewModel
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
