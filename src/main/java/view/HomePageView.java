package view;

import data_access.MongoDBDataAccessObject;

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

    private final SignupViewModel signupViewModel;

    private final ViewProfileViewModel viewProfileViewModel;

    private final MongoDBDataAccessObject mongoDBDataAccessObject;

    private final JButton generateIdeaButton = new JButton();
    private final JButton postButton = new JButton();
    private final JButton viewProfileButton = new JButton();

    private final JButton logoutButton = new JButton();
    
    /**
     * Initializes the UI for the Home Page and provides functionality to its components.
     * @param viewManagerModel View manager for managing which view is displayed
     * @param homePageViewModel Observable that stores the state useful to the Home Page View.
     * @param generateIdeaViewModel Observable that stores the state of the Generate Idea View.
     * @param createPostViewModel Observable that stores the state of the Create Post View.
     * @param viewProfileViewModel Observable that stores the state of the View Profile View.
     * @param mongoDBDataAccessObject the DAO for accessing the database form HomePageView
     */
    public HomePageView(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel, GenerateIdeaViewModel generateIdeaViewModel, CreatePostViewModel createPostViewModel, SignupViewModel signupViewModel, ViewProfileViewModel viewProfileViewModel, MongoDBDataAccessObject mongoDBDataAccessObject) {
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
        this.generateIdeaViewModel = generateIdeaViewModel;
        this.createPostViewModel = createPostViewModel;
        this.signupViewModel = signupViewModel;
        this.viewProfileViewModel = viewProfileViewModel;
        this.mongoDBDataAccessObject = mongoDBDataAccessObject;

        viewName = homePageViewModel.getViewName();
        setName(viewName);
        generateIdeaButton.setText(HomePageViewModel.GENERATE_IDEA_BUTTON_LABEL);

        postButton.setText(HomePageViewModel.POST_BUTTON_LABEL);

        viewProfileButton.setText(HomePageViewModel.PROFILE_BUTTON_LABEL);

        logoutButton.setText(HomePageViewModel.LOGOUT_BUTTON_LABEL);

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
                        // System.out.println("Click View Profile"); - for testing
                    }
                }
        );

        logoutButton.addActionListener(
                e -> {
                    if (e.getSource().equals(logoutButton)) {
                        mongoDBDataAccessObject.setLoggedInUserID(null);
                        signupViewModel.firePropertyChanged("reset_input_fields");
                        viewManagerModel.setActiveView(signupViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        setLayout(new GridLayout(9, 5));

        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();
        JPanel emptyPanel3 = new JPanel();
        JPanel emptyPanel4 = new JPanel();
        JPanel emptyPanel5 = new JPanel();

        add(emptyPanel4);
        add(viewProfileButton);
        add(emptyPanel1);
        add(generateIdeaButton);
        add(emptyPanel2);
        add(postButton);
        add(emptyPanel3);
        add(logoutButton);
        add(emptyPanel5);

    }

    /**
     * Updates the UI when a change is made in the HomePageViewModel
     * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
