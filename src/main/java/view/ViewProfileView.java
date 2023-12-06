package view;

import data_access.MongoDBDataAccessObject;
import data_access.ViewProfileDataAccessInterface;
import entity.CollabRequest;
import entity.User;
import use_case.view_profile.interface_adapter.ViewProfileController;
import use_case.view_profile.interface_adapter.ViewProfileState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ViewProfileView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final ViewManagerModel viewManagerModel;
    private final ViewProfileViewModel viewProfileViewModel;
    //private final HomePageViewModel homePageViewModel;
    private final ViewProfileController viewProfileController;
    private final ViewProfileDataAccessInterface viewProfileDataAccessObject;
    private final JTextField usernameDisplayField = new JTextField(20);
    private final JTextField nameDisplayField = new JTextField(20);
    private final JTextField emailDisplayField = new JTextField(20);
    private final JTextField projectsDisplayField = new JTextField(20);
    private final JTextField collabRequestsDisplayField = new JTextField(20);
    private final JButton backButton = new JButton();
    private final JButton logoutButton = new JButton();

    public ViewProfileView(ViewProfileViewModel viewProfileViewModel, ViewProfileController viewProfileController,
                           ViewManagerModel viewManagerModel, ViewProfileDataAccessInterface viewProfileDataAccessObject) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewProfileController = viewProfileController;
        this.viewManagerModel = viewManagerModel;
       this.viewProfileDataAccessObject = viewProfileDataAccessObject;

        viewName = viewProfileViewModel.getViewName();
        setName(viewName);
        logoutButton.setText(ViewProfileViewModel.LOGOUT_BUTTON_LABEL);
        backButton.setText(ViewProfileViewModel.BACK_BUTTON_LABEL);
        viewProfileViewModel.addPropertyChangeListener(this);

        JPanel usernamePanel = createEntry(ViewProfileViewModel.USERNAME_LABEL, usernameDisplayField);
        JPanel namePanel = createEntry(ViewProfileViewModel.NAME_LABEL, nameDisplayField);
        JPanel emailPanel = createEntry(ViewProfileViewModel.EMAIL_LABEL, emailDisplayField);
        JPanel projectsPanel = createEntry(ViewProfileViewModel.PROJECTS_LABEL, projectsDisplayField);
        JPanel collabRequestsPanel = createEntry(ViewProfileViewModel.COLLAB_REQUESTS_LABEL, collabRequestsDisplayField);
        collabRequestsPanel.setLayout(new FlowLayout());
        for (CollabRequest collabRequest : viewProfileViewModel.getState().getCollabRequests()) {
            JButton acceptButton = new JButton("Accept");
            JButton rejectButton = new JButton("Reject");

            acceptButton.addActionListener( e -> {
                if (e.getSource().equals(acceptButton)) {
                    collabRequestsDisplayField.setText(collabRequest.getTitle());
                    projectsPanel.add(collabRequestsDisplayField);
                    collabRequestsPanel.remove(collabRequestsDisplayField);
                   viewProfileDataAccessObject.addCollabRequest(collabRequest);
                   //TODO: remove collab request from database
                  User commenter = viewProfileDataAccessObject.getUserByUsername(viewProfileViewModel.getState().getUsername());
                  commenter.addCollabRequest(collabRequest.getPostId());
                  // TODO:remove from state
                    // TODO:remove from commenter

                }
            });

            rejectButton.addActionListener( e -> {
                if (e.getSource().equals(rejectButton)) {

                }
            });

            // Create a panel for each collab request with accept and reject buttons
            JPanel requestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout
            requestPanel.add(new JLabel(collabRequest.getTitle()));
            requestPanel.add(acceptButton);
            requestPanel.add(rejectButton);

            collabRequestsPanel.add(requestPanel);
        }

        // Repaint the panel
        collabRequestsPanel.revalidate();
        collabRequestsPanel.repaint();


        /*backButton.addActionListener(
                e -> {
                    if (e.getSource().equals(backButton)) {
                        viewManagerModel.setActiveView(homePageViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );*/

        /*logoutButton.addActionListener(
                e -> {
                    if (e.getSource().equals(logoutButton)) {
                        viewManagerModel.setActiveView(signupViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );*/
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel viewProfileOutputData = create();


        addComponent(usernamePanel, c, 0, 0, GridBagConstraints.HORIZONTAL);
        addComponent(namePanel, c, 0 , GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(emailPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(projectsPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(collabRequestsPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        add(logoutButton, c);
        add(backButton, c);

        addComponent(viewProfileOutputData, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
    }



    private JPanel createEntry(String name, JTextField textField) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.add(textField);
        return panel;
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
        this.usernameDisplayField.setText("username");
        this.nameDisplayField.setText("name");
        this.emailDisplayField.setText("email");
        this.projectsDisplayField.setText("projects");
        this.collabRequestsDisplayField.setText("collabRequests");
        return panel;
    }

    protected ViewProfileViewModel getViewProfileViewModel() {
        return this.viewProfileViewModel;
    }

    protected ViewProfileController getViewProfileController() {
        return this.viewProfileController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("display_profile")) {
            ViewProfileState currentState = viewProfileViewModel.getState();
            usernameDisplayField.setText(currentState.getUsername());
            nameDisplayField.setText(currentState.getName());
            emailDisplayField.setText(currentState.getEmail());
            projectsDisplayField.setText(String.valueOf(currentState.getProjects()));
            collabRequestsDisplayField.setText(String.valueOf(currentState.getCollabRequests()));
           // updateCollabRequestsPanel(collabRequestpanel);


        }
        else if (evt.getPropertyName().equals("error")) {
            ViewProfileState currentState = viewProfileViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        }

    }
}