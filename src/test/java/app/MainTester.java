package app;

import view.SignupViewModel;

public class MainTester extends Main {
    public static void main(String[] args) {
        Main.main(new String[] {
                "src/main/java/data_access/database_connection.txt",
                "Tests", "Users", "Posts", "Comments"
        });
    }

    public static SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }
}
