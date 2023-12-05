package view;

import javax.swing.*;

/**
 * View class that displays a dialog box containing the relevant user info
 * @author Tanmay Shinde
 */
public class ViewUserInfoView extends JPanel {

    /**
     * Displays a pop-up dialog box containing all relevant user information
     * @param message the message to be displayed in the dialog box
     */
    public void showUserInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
