package data_access;

import entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MongoDBDataAccessObjectTest extends MongoDBDataAccessObject {
    public MongoDBDataAccessObjectTest() {
            super(
                    "src/main/java/data_access/database_connection.txt",
                    "Tests", "Users", "Posts", "Comments"
            );
    }

    @Test
    public void testUsernameUsedEmptyCollection() {
        assert !usernameUsed("username");
    }

    @Test
    public void testAddUserOne() {
        User user = new User("username", "password", "", "", "");
        addUser(user);
        assert usernameUsed("username");
    }

    @Before
    @After
    public void resetDatabase() {
        users.drop();
        posts.drop();
        comments.drop();
    }
}