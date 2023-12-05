package data_access;

import entity.Post;
import entity.User;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewProfileDataFileAccessObject implements ViewProfileDataAccessInterface{
    private final File userFile;
    private final ArrayList<String> userContents;
    private User testUser = null;

    public ViewProfileDataFileAccessObject (String csvPath) throws Exception {
        this.userFile = new File(csvPath);
        this.userContents = new ArrayList<String>();
        this.testUser = null;
        try {
            loadUser();
        }
        catch(FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't find database");
        }

    }
    private void loadUser() throws Exception {
        try (Scanner scanner = new Scanner(userFile)) {

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] items = row.split(",");
                for (String item : items) {
                    userContents.add(item.trim());
                }
            }

            // Create the User object using the values from the CSV
            String username = userContents.get(0);
            String password = userContents.get(1);
            String name = userContents.get(2);
            String email = userContents.get(3);
            String phoneNumber = userContents.get(4);
            String city = userContents.get(5);
            String fieldOfExpertise = userContents.get(6);
            testUser = new User(username, password, name, email, phoneNumber, city, fieldOfExpertise);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't find database: " + userFile.getAbsolutePath());
        }
    }
    @Override
    public User getLoggedInUser() {
        return testUser;

    }

    @Override
    public Post getPostByPostID(ObjectId id) {
        return null;
    }

    @Override
    public CollabRequest getCollabRequestById(ObjectId id) {
        return null;
    }
}
