package data_access;

import entity.User;
import org.junit.Before;
import org.junit.Test;

public class MongoDBDataAccessObjectTest {
    MongoDBDataAccessObject dataAccessObject;

    // TODO: Add test for addComment() after implementation for fetching comments method
    @Test
    public void testUsernameUsedEmptyCollection() {
        assert !dataAccessObject.usernameUsed("username");
    }

    @Test
    public void testAddUserOne() {
        User user = new User("username", "password", "", "", "", "", "");
        dataAccessObject.addUser(user);
        assert dataAccessObject.usernameUsed("username");
    }

    @Before
    public void setUpTest() {
        try {
            dataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        dataAccessObject.resetDatabase();
    }
}