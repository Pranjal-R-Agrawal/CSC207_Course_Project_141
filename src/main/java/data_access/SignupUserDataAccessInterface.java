package data_access;

import entity.UserInterface;

public interface SignupUserDataAccessInterface {
    boolean usernameUsed(String username);
    void addUser(UserInterface user);
}
