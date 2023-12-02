package view;

import api.MistralCodegenAIAPI;
import app.GenerateIdeaUseCaseFactory;
import data_access.IdeaDataFileDataAccessObject;
import entity.ConcreteIdeaFactory;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.Assert.*;

public class GenerateIdeaViewTest {
    private static String message;
    private static GenerateIdeaView generateIdeaView;
    private IdeaDataFileDataAccessObject ideaDataFileDataAccessObject;
    private GenerateIdeaViewModel generateIdeaViewModel;
    private JButton ideaButton;

    @Before
    public void setUpTest() throws Exception {

        ideaDataFileDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv",new ConcreteIdeaFactory());

        generateIdeaViewModel = new GenerateIdeaViewModel();
        generateIdeaView = GenerateIdeaUseCaseFactory.create(new ViewManagerModel(),generateIdeaViewModel, ideaDataFileDataAccessObject, new MistralCodegenAIAPI(), new HomePageViewModel());

        ideaButton = (JButton) generateIdeaView.getComponent(0);

        message = "";

        JFrame application = new JFrame("Startup Generator");
        application.add(generateIdeaView);
        application.pack();
        application.setVisible(true);
    }
    @Test
    public void testBusinessModelDisplayDynamic() {
        ideaButton.doClick();// this is for the GenerateIdeaViewModel to get updated with a business model from the API
        createCloseTimer().start();
        ideaButton.doClick(); // this is to access the text in the GenerateIdeaView display
        assertTrue(message instanceof String);
        assertNotEquals("", message);
    }
    @Test
    public void testUnableToProduceBusinessModel() {
        generateIdeaView.getGenerateIdeaController().execute(true);
        createCloseTimer().start();
        ideaButton.doClick(); // this is to access the text in the GenerateIdeaView display
        assertEquals("Unable to produce Business Model", message);
    }
    @Test
    public void testBusinessModelDisplayStatic() {
        generateIdeaViewModel.getState().setBusinessModel("3D printing");
        generateIdeaViewModel.firePropertyChanged("business_model_display");
        createCloseTimer().start();
        ideaButton.doClick(); // this is to access the text in the GenerateIdeaView display
        assertEquals("3D printing", message);
    }

    private Timer createCloseTimer() {
        ActionListener close = e -> {

            Window[] windows = Window.getWindows();
            System.out.println(windows);
            for (Window window : windows) {
                if (window instanceof JFrame jFrame) {
                    if (jFrame.isVisible()) {
                     //   String s = (GenerateIdeaView)(jFrame.getRootPane().getContentPane().getComponent(0)).getGenerateIdeaViewModel().getState().getBusinessModel();
                        String s = ((GenerateIdeaView) ((BorderLayout) jFrame.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getGenerateIdeaViewModel().getState().getBusinessModel();

                        System.out.println("message = " + s);

                        GenerateIdeaViewTest.message = s;
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
