package view;

import use_case.create_post.interface_adapter.CreatePostViewModel;
import use_case.generate_idea.interface_adapter.GenerateIdeaViewModel;
import use_case.search_post.interface_adapter.SearchPostViewModel;
import use_case.signup.interface_adapter.SignupViewModel;
import use_case.view_profile.interface_adapter.ViewProfileController;

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

    private final CreatePostView createPostView;
    private final SearchPostViewModel searchPostViewModel;
    private final ViewProfileController viewProfileController;

    private final JButton generateIdeaButton = new JButton();
    private final JButton postButton = new JButton();
    private final JButton viewProfileButton = new JButton();
    private final JButton logoutButton = new JButton();
    private final JButton searchButton = new JButton();
    
    /**
     * Initializes the UI for the Home Page and provides functionality to its components.
     *
     * @param viewManagerModel        View manager for managing which view is displayed
     * @param homePageViewModel       Observable that stores the state useful to the Home Page View.
     * @param generateIdeaViewModel   Observable that stores the state of the Generate Idea View.
     * @param createPostViewModel     Observable that stores the state of the Create Post View.
     * @param createPostView         UI for the Create Post View.
     */
    public HomePageView(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel, GenerateIdeaViewModel generateIdeaViewModel, CreatePostViewModel createPostViewModel, SignupViewModel signupViewModel, CreatePostView createPostView, SearchPostViewModel searchPostViewModel, ViewProfileController viewProfileController) {
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
        this.generateIdeaViewModel = generateIdeaViewModel;
        this.createPostViewModel = createPostViewModel;
        this.signupViewModel = signupViewModel;
        this.createPostView = createPostView;
        this.searchPostViewModel = searchPostViewModel;
        this.viewProfileController = viewProfileController;

        viewName = homePageViewModel.getViewName();
        setName(viewName);
        generateIdeaButton.setText(HomePageViewModel.GENERATE_IDEA_BUTTON_LABEL);

        postButton.setText(HomePageViewModel.POST_BUTTON_LABEL);

        viewProfileButton.setText(HomePageViewModel.PROFILE_BUTTON_LABEL);

        logoutButton.setText(HomePageViewModel.LOGOUT_BUTTON_LABEL);
        searchButton.setText("Search Posts");

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
                        viewManagerModel.displayCreatePost(createPostView);
                    }
                }
        );

        viewProfileButton.addActionListener(
                e -> {
                    if (e.getSource().equals(viewProfileButton)) {
                        viewProfileController.execute();
                    }
                }
        );

        logoutButton.addActionListener(
                e -> {
                    if (e.getSource().equals(logoutButton)) {
                        signupViewModel.firePropertyChanged("reset_input_fields");
                        viewManagerModel.setActiveView(signupViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        searchButton.addActionListener(
                e -> {
                    if (e.getSource().equals(searchButton)) {
                        viewManagerModel.setActiveView(searchPostViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0; constraints.gridy = GridBagConstraints.RELATIVE;
        add(viewProfileButton, constraints);
        add(generateIdeaButton, constraints);
        add(postButton, constraints);
        add(searchButton, constraints);
        add(logoutButton, constraints);
    }

    /**
     * Updates the UI when a change is made in the HomePageViewModel
     * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
