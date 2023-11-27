package app;

import api.GenerativeAIAPI;
import api.MistralAIAPI;
import data_access.GenerateIdeaDataAccessInterface;
import data_access.IdeaDataFileDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.ConcreteIdeaFactory;
import entity.IdeaFactory;
import view.*;
import javax.swing.*;
import java.awt.*;


public class Main {
    protected static SignupViewModel signupViewModel;
    protected static LoginViewModel loginViewModel;
    protected static GenerateIdeaViewModel generateIdeaViewModel;
    protected static HomePageViewModel homePageViewModel;

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
        generateIdeaViewModel = new GenerateIdeaViewModel();
        homePageViewModel = new HomePageViewModel();

        MongoDBDataAccessObject mongoDBDataAccessObject;
        try {
            if (args != null && args.length == 5) {
                mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder()
                        .setDatabaseConnectionPath(args[0])
                        .setDatabaseName(args[1])
                        .setUsersCollectionName(args[2])
                        .setCommentsCollectionName(args[3])
                        .setPostsCollectionName(args[4])
                        .build();
            }
            else {
                mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setStandadParameters().build();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, mongoDBDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mongoDBDataAccessObject);
        views.add(loginView, loginView.viewName);

        GenerativeAIAPI generativeAIAPI = new MistralAIAPI();
        GenerateIdeaDataAccessInterface generateIdeaDataAccessObject = null;
        IdeaFactory ideaFactory = new ConcreteIdeaFactory();
        try
        {
            generateIdeaDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv",ideaFactory);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        GenerateIdeaView generateIdeaView = GenerateIdeaUseCaseFactory.create(viewManagerModel,generateIdeaViewModel,generateIdeaDataAccessObject,generativeAIAPI,homePageViewModel);
        views.add(generateIdeaView,generateIdeaView.viewName);

        viewManagerModel.setActiveView(generateIdeaView.viewName);
        viewManagerModel.firePropertyChanged();
//signupView.viewName
        application.pack();
        application.setVisible(true);
    }
}
