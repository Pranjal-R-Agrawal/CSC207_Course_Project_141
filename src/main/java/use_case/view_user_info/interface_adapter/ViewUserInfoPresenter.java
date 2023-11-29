package use_case.view_user_info.interface_adapter;

import use_case.view_user_info.application_business_rules.ViewUserInfoOutputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoOutputData;

import javax.swing.*;

public class ViewUserInfoPresenter implements ViewUserInfoOutputBoundary {

    @Override
    public void prepareCollabView(ViewUserInfoOutputData user) {
        String message = "<html>Name: </html>" + user.getName() +
                "<html><br/>Rating: </html>" + user.getRating() +
                "<html><br/>Email: </html>" + user.getEmail() +
                "<html><br/>Phone Number: </html>" + user.getPhoneNumber();

        JOptionPane.showMessageDialog(null, message, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void prepareGeneralView(ViewUserInfoOutputData user) {
        String message = "<html>Name: </html>" + user.getName() +
                "<html><br/>Rating: </html>" + user.getRating();

        JOptionPane.showMessageDialog(null, message, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
