package data_access;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean usernameUsed(String username);
    void addUser(User user);
}
