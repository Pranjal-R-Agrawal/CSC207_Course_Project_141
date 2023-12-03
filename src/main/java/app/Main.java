package app;

import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;
import view.*;
import view.display_post.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);

        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();

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

        PostAndCommentsViewModel postAndCommentsViewModel = new PostAndCommentsViewModel();
        CreateCommentUseCaseBuilder createCommentUseCaseBuilder = new CreateCommentUseCaseBuilder(postAndCommentsViewModel, mongoDBDataAccessObject);
        PostAndCommentsView postAndCommentsView = DisplayPostUseCaseFactory.create(postAndCommentsViewModel, viewManagerModel, mongoDBDataAccessObject, createCommentUseCaseBuilder);
        viewManager.setupDisplayComments(postAndCommentsViewModel, postAndCommentsView);
        NewWindow newPostAndCommentsWindow = new NewWindow(true, postAndCommentsView.viewName);
        NewWindow newCreateCommentWindow = new NewWindow(false, "Reply");
        NewWindow newCreatePostWindow = new NewWindow(false, "Post");
        viewManager.setupNewWindows(newPostAndCommentsWindow, newCreateCommentWindow, newCreatePostWindow);

        CreatePostViewModel createPostViewModel = new CreatePostViewModel();
        CreatePostView createPostView = CreatePostUseCaseFactory.create(viewManagerModel,createPostViewModel,mongoDBDataAccessObject);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
