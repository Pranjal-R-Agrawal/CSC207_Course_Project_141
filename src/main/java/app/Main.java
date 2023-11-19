package app;

import api.GenerativeAIAPI;
import api.MistralAIAPI;
import data_access.IdeaDataFileDataAccessObject;
import data_access.MongoDBDataAccessObject;
import entity.ConcreteIdeaFactory;
import entity.IdeaFactory;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    protected static SignupViewModel signupViewModel;

    public static void main(String[] args) {
        JFrame application = new JFrame("Startup Generator");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Sign Up creation
        signupViewModel = new SignupViewModel();
        GenerateIdeaViewModel generateIdeaViewModel = new GenerateIdeaViewModel();

        MongoDBDataAccessObject mongoDBDataAccessObject;
        if (args != null && args.length == 5)
            mongoDBDataAccessObject = new MongoDBDataAccessObject(args[0], args[1], args[2], args[3], args[4]);
        else {
            mongoDBDataAccessObject = new MongoDBDataAccessObject();
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, mongoDBDataAccessObject);
        views.add(signupView, signupView.viewName);

        // GenerateIdea creation
        IdeaFactory ideaFactory = new ConcreteIdeaFactory();
        IdeaDataFileDataAccessObject ideaDataFileDataAccessObject = null;
        try {
            ideaDataFileDataAccessObject = new IdeaDataFileDataAccessObject("ideas.csv", ideaFactory);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        GenerativeAIAPI generativeAIAPI = new MistralAIAPI();
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        GenerateIdeaView generateIdeaView = GenerateIdeaUseCaseFactory.create(viewManagerModel, generateIdeaViewModel, ideaDataFileDataAccessObject,generativeAIAPI,homePageViewModel);
        views.add(generateIdeaView, generateIdeaView.viewName);

        // Display the SignUp view first
        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
