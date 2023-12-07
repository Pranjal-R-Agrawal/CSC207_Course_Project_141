package app;

import use_case.signup.interface_adapter.SignupViewModel;

import javax.swing.*;
import java.awt.*;

public class MainTester extends Main {
    public static void main(String[] args) {
        Main.main(new String[] {
                "src/main/java/data_access/database_connection.txt",
                "forum_test", "users_test", "comments_test", "posts_test"
        });
    }

    public static SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }

    public static JPanel getCurrentView() {
        JFrame application = null;
        Window[] windows = Window.getWindows();

        for (Window window : windows) {
            if (window instanceof JFrame) {
                application = (JFrame) window;
            }
        }

        assert application != null;

        JPanel root = (JPanel) ((JRootPane) application.getComponent(0)).getContentPane();
        JPanel views = (JPanel) root.getComponent(0);

        Component panel = views.getComponent(0);

        for (Component component : views.getComponents()) {
            if ((panel = component).isVisible()) break;
        }

        return (panel instanceof JPanel)? (JPanel) panel : null;
    }
}
