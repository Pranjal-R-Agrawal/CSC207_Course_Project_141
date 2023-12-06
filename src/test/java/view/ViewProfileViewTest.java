package view;
import app.ViewProfileUseCaseFactory;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import data_access.ViewProfileDataAccessInterface;
import data_access.ViewProfileDataFileAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.TextMeasurer;

import static org.junit.Assert.assertEquals;

public class ViewProfileViewTest {
    private static String message_username="";
    private static String message_name="";

    private static String message_email="";

    private static ViewProfileView viewProfileView;
    private ViewProfileDataFileAccessObject viewProfileDataFileAccessObject;
    private ViewProfileViewModel viewProfileViewModel;
    private JButton backButton;
    private JButton logoutButton;



    @Before
    public void setUpTest() throws Exception {

        MongoDBDataAccessObject mongoDBDataAccessObject;
        try {mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
            User user = (User) new UserFactory().create("testuser1","testpass1", "test1","test1@email.com","123-456-781","testcity1", "t" +
                    "coding");
            mongoDBDataAccessObject.addUser(user);
            mongoDBDataAccessObject.setLoggedInUserID(user.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        viewProfileViewModel = new ViewProfileViewModel();
        viewProfileView = ViewProfileUseCaseFactory.create(new ViewManagerModel(), viewProfileViewModel, mongoDBDataAccessObject);
        backButton = (JButton) viewProfileView.getComponent(4);
        logoutButton = (JButton) viewProfileView.getComponent(5);

//new JButton();


        JFrame application = new JFrame("View Profile Page");
        application.add(viewProfileView);
        application.pack();
        application.setVisible(true);
    }


    @Test
    public void testViewProfileDisplayStatic() {
        viewProfileViewModel.getState().setUsername("testuser").setName("test").setEmail("test@email.com");
        viewProfileViewModel.firePropertyChanged("display_profile");
        createCloseTimer().start();
        // TODO: Finish implementing this test after main page is implemented

//        assertEquals("testuser", viewProfileViewModel.getState().getUsername());
//        assertEquals("test", viewProfileViewModel.getState().getName());
//        assertEquals("test@email.com", viewProfileViewModel.getState().getEmail());
//        assertEquals("0.0", viewProfileViewModel.getState().getRating());
        assertEquals("testuser", message_username);
        assertEquals("test", message_name);
        assertEquals("test@email.com", message_email);



    }
    @Test
    public void testViewProfileDisplayDynamic() {
        viewProfileViewModel.firePropertyChanged("display_profile");
        createCloseTimer().start();
        // TODO: Finish implementing this test after main page is implemented

//        assertEquals("testuser", viewProfileViewModel.getState().getUsername());
//        assertEquals("test", viewProfileViewModel.getState().getName());
//        assertEquals("test@email.com", viewProfileViewModel.getState().getEmail());
//        assertEquals("0.0", viewProfileViewModel.getState().getRating());
        assertEquals("testuser1", message_username);
        assertEquals("test1", message_name);
        assertEquals("test1@email.com", message_email);



    }

    private Timer createCloseTimer() {
     ActionListener close = e -> {

            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame jFrame) {
                    if (jFrame.isVisible()) {
                        //   String s = (GenerateIdeaView)(jFrame.getRootPane().getContentPane().getComponent(0)).getGenerateIdeaViewModel().getState().getBusinessModel();
                        message_username = ((ViewProfileView) ((BorderLayout) jFrame.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getViewProfileViewModel().getState().getUsername();
                        message_name = ((ViewProfileView) ((BorderLayout) jFrame.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getViewProfileViewModel().getState().getName();
                        message_email = ((ViewProfileView) ((BorderLayout) jFrame.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getViewProfileViewModel().getState().getEmail();


                        System.out.println(message_username);
                        System.out.println(message_name);
                        System.out.println(message_email);


                    }
                }
            }
            for (Window window : windows) {
                window.dispose();
            }
        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }



}
