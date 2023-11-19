package data_access;

import entity.User;
<<<<<<<<< Temporary merge branch 1

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
=========
import org.junit.Before;
import org.junit.Test;

public class MongoDBDataAccessObjectTest {
    MongoDBDataAccessObject dataAccessObject;

    @Test
    public void testUsernameUsedEmptyCollection() {
        assert !dataAccessObject.usernameUsed("username");
>>>>>>>>> Temporary merge branch 2
    }

    @Test
    public void testAddUserOne() {
        User user = new User("username", "password", "", "", "");
<<<<<<<<< Temporary merge branch 1
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
=========
        dataAccessObject.addUser(user);
        assert dataAccessObject.usernameUsed("username");
    }

    @Before
    public void setUpTest() {
        dataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        dataAccessObject.resetDatabase();
    }
}
>>>>>>>>> Temporary merge branch 2
