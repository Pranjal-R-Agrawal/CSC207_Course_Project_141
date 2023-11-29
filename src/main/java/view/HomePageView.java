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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}