package view;

import use_case.generate_idea.interface_adapter.GenerateIdeaController;
import use_case.generate_idea.interface_adapter.GenerateIdeaState;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The UI that displays changes in the GenerateIdeaViewModel
 * @author Sidharth Sawhney
 */
public class GenerateIdeaView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final GenerateIdeaViewModel generateIdeaViewModel;
    private final GenerateIdeaController generateIdeaController;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton ideaButton = new JButton();
    private final JButton backButton = new JButton();
    private final JButton postButton = new JButton();
    private final JTextArea textArea = new JTextArea(10,25);

    /**
     * Initializes the UI template and provides functionality to its components.
     * @param viewManagerModel Manages which view is displayed
     * @param generateIdeaViewModel Observable that stores the state useful to the Generate Idea View.
     * @param generateIdeaController Controller to trigger the GenerateIdeaInputBoundary to perform application logic
     * @param homePageViewModel Observable that stores the state useful to Home Page View.
     */
    public GenerateIdeaView(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel, GenerateIdeaController generateIdeaController, HomePageViewModel homePageViewModel) {
        this.generateIdeaViewModel = generateIdeaViewModel;
        this.generateIdeaController = generateIdeaController;
        this.viewManagerModel = viewManagerModel;
        this.homePageViewModel = homePageViewModel;

        viewName = generateIdeaViewModel.getViewName();
        setName(viewName);

        ideaButton.setText(GenerateIdeaViewModel.IDEA_BUTTON_LABEL);
        backButton.setText(GenerateIdeaViewModel.BACK_BUTTON_LABEL);
        postButton.setText(GenerateIdeaViewModel.POST_BUTTON_LABEL);

        generateIdeaViewModel.addPropertyChangeListener(this);

        ideaButton.addActionListener(
                e -> {
                    if (e.getSource().equals(ideaButton)) {
                        generateIdeaController.execute(false);
                    }
                }
        );
       //  ideaButton.doClick();


        backButton.addActionListener(
                e -> {
                    if (e.getSource().equals(backButton)) {
                        generateIdeaViewModel.setState(new GenerateIdeaState());
                        viewManagerModel.setActiveView(homePageViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );


        postButton.addActionListener(
                e -> {
                    if(e.getSource().equals(postButton)){

                    }
                }
        );


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

            JPanel ideaOutputData = create();
            addComponent(ideaButton, c, 0, 0, GridBagConstraints.HORIZONTAL);
            addComponent(ideaOutputData, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
            addComponent(postButton, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
            addComponent(backButton, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
}


    private void addComponent(JPanel panel, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        add(panel, c);
    }

    private void addComponent(JButton jButton, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        add(jButton, c);
    }

    private JPanel create() {
        JPanel panel = new JPanel();
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setEditable(false); // read only mode
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        panel.add(scrollPane);
        return panel;
    }

    /**
     * Updates the UI when a change is made in the GenerateIdeaViewModel
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("business_model_display")) { // sets the text of JTextArea with the new business model
        this.textArea.setText(generateIdeaViewModel.getState().getBusinessModel());
        }
    }
}
