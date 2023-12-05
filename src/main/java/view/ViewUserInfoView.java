package view;

import javax.swing.*;

public class ViewUserInfoView extends JPanel {

    public void showUserInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }

}
