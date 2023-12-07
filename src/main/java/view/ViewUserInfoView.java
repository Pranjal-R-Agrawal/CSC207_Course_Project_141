package view;

import use_case.view_user_info.interface_adapter.ViewUserInfoState;
import use_case.view_user_info.interface_adapter.ViewUserInfoViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View class that displays a dialog box containing the relevant user info
 * @author Tanmay Shinde
 */
public class ViewUserInfoView extends JPanel implements PropertyChangeListener {

    private final ViewUserInfoViewModel viewUserInfoViewModel;

    public ViewUserInfoView(ViewUserInfoViewModel viewUserInfoViewModel){
        this.viewUserInfoViewModel = viewUserInfoViewModel;
        viewUserInfoViewModel.addPropertyChangeListener(this);
    }
    /**
     * Displays a pop-up dialog box containing all relevant user information
     * @param message the message to be displayed in the dialog box
     */
    public void showUserInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Calls the showUserInfo method passing the appropriate message from ViewUserInfoState when a change is made in the GenerateIdeaViewModel
     * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("message")) {
            ViewUserInfoState currentState = viewUserInfoViewModel.getState();
            showUserInfo(currentState.getMessage());
        }
    }
}
