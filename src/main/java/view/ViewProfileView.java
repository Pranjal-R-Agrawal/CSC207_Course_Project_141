package view;

import use_case.view_profile.interface_adapter.ViewProfileController;
import use_case.view_profile.interface_adapter.ViewProfileState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewProfileView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final ViewProfileViewModel viewProfileViewModel;
    private final ViewProfileController viewProfileController;
    private final JTextField usernameDisplayField = new JTextField(20);
    private final JTextField nameDisplayField = new JTextField(20);
    private final JTextField emailDisplayField = new JTextField(20);
    private final JTextField ratingsDisplayField = new JTextField(20);

    private final JButton backButton = new JButton();
    private final JButton logoutButton = new JButton();

    public ViewProfileView(ViewProfileViewModel viewProfileViewModel, ViewProfileController viewProfileController) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewProfileController = viewProfileController;

        viewName = viewProfileViewModel.getViewName();
        setName(viewName);
        logoutButton.setText(ViewProfileViewModel.LOGOUT_BUTTON_LABEL);
        backButton.setText(ViewProfileViewModel.BACK_BUTTON_LABEL);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("display_profile")) {
            ViewProfileState currentState = viewProfileViewModel.getState();
            usernameDisplayField.setText(currentState.getUsername());
            nameDisplayField.setText(currentState.getName());
            emailDisplayField.setText(currentState.getEmail());
            ratingsDisplayField.setText(String.valueOf(currentState.getRating()));
        }
        else if (evt.getPropertyName().equals("error")) {
            ViewProfileState currentState = viewProfileViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        }

    }
}
