package data_access;

import entity.User;
import org.junit.Before;
import org.junit.Test;

public class MongoDBDataAccessObjectTest {
    MongoDBDataAccessObject dataAccessObject;

    @Test
    public void testUsernameUsedEmptyCollection() {
        assert !dataAccessObject.usernameUsed("username");
    }

    @Test
    public void testAddUserOne() {
        User user = new User("username", "password", "", "", "");
        dataAccessObject.addUser(user);
        assert dataAccessObject.usernameUsed("username");
    }

    @Before
    public void setUpTest() {
        dataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        dataAccessObject.resetDatabase();
    }
}
