package app;

import data_access.MongoDBDataAccessObject;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    protected static SignupViewModel signupViewModel;
    protected static LoginViewModel loginViewModel;

    public static void main(String[] args) {
        JFrame application = new JFrame("Startup Generator");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();

        MongoDBDataAccessObject mongoDBDataAccessObject;
        if (args != null && args.length == 5)
            mongoDBDataAccessObject = new MongoDBDataAccessObject(args[0], args[1], args[2], args[3], args[4]);
        else {
            mongoDBDataAccessObject = new MongoDBDataAccessObject();
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, mongoDBDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mongoDBDataAccessObject);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
