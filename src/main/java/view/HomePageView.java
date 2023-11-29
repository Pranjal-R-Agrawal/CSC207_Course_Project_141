package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



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

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}