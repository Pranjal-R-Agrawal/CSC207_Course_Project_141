package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * The UI that displays the HomePageViewModel
 * @author Tanmay Shinde
 */
public class HomePageView extends JPanel implements PropertyChangeListener {
    public final String viewName;

    private final ViewManagerModel viewManagerModel;

    private final HomePageViewModel homePageViewModel;

    private final GenerateIdeaViewModel generateIdeaViewModel;

    private final CreatePostViewModel createPostViewModel;

    private final ViewProfileViewModel viewProfileViewModel;

    private final JButton generateIdeaButton = new JButton();
    private final JButton postButton = new JButton();
    private final JButton viewProfileButton = new JButton();
    
    /**
     * Initializes the UI for the Home Page and provides functionality to its components.
     * @param viewManagerModel View manager for managing which view is displayed
     * @param homePageViewModel Observable that stores the state useful to the Home Page View.
     * @param generateIdeaViewModel Observable that stores the state of the Generate Idea View.
     * @param createPostViewModel Observable that stores the state of the Create Post View.
     * @param viewProfileViewModel Observable that stores the state of the View Profile View.
     */
    public HomePageView(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel, GenerateIdeaViewModel generateIdeaViewModel, CreatePostViewModel createPostViewModel, ViewProfileViewModel viewProfileViewModel) {
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
        this.generateIdeaViewModel = generateIdeaViewModel;
        this.createPostViewModel = createPostViewModel;
        this.viewProfileViewModel = viewProfileViewModel;

        viewName = homePageViewModel.getViewName();
        setName(viewName);
        generateIdeaButton.setText(HomePageViewModel.GENERATE_IDEA_BUTTON_LABEL);

        postButton.setText(HomePageViewModel.POST_BUTTON_LABEL);

        viewProfileButton.setText(HomePageViewModel.PROFILE_BUTTON_LABEL);

        homePageViewModel.addPropertyChangeListener(this);

        generateIdeaButton.addActionListener(
                e -> {
                    if (e.getSource().equals(generateIdeaButton)) {
                        viewManagerModel.setActiveView(generateIdeaViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        postButton.addActionListener(
                e -> {
                    if (e.getSource().equals(postButton)) {
                        viewManagerModel.setActiveView(createPostViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        viewProfileButton.addActionListener(
                e -> {
                    if (e.getSource().equals(viewProfileButton)) {
                        viewManagerModel.setActiveView(viewProfileViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                        viewProfileViewModel.firePropertyChanged("display_user");
                    }
                }
        );

        setLayout(new GridLayout(7, 5));

        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();
        JPanel emptyPanel3 = new JPanel();
        JPanel emptyPanel4 = new JPanel();

        add(emptyPanel4);
        add(viewProfileButton);
        add(emptyPanel1);
        add(generateIdeaButton);
        add(emptyPanel2);
        add(postButton);
        add(emptyPanel3);
    }

    /**
     * Updates the UI when a change is made in the HomePageViewModel
     * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
