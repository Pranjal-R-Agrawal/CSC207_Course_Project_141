package view;

import use_case.generate_idea.interface_adapter.GenerateIdeaState;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenerateIdeaViewModel extends ViewModel {
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String POST_BUTTON_LABEL = "Post";
    public static final String IDEA_BUTTON_LABEL = "Generate Idea!";
    private GenerateIdeaState state = new GenerateIdeaState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GenerateIdeaState getState() {
        return state;
    }

    public void setState(GenerateIdeaState state) {
        this.state = state;
    }

    public GenerateIdeaViewModel() {
        super("generate_idea");
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
