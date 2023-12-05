package entity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Concrete implementation of UserFactoryInterface to construct User objects
 * @author Sidharth Sawhney
 */
public class UserFactory implements UserFactoryInterface {
    @Override
    public UserInterface create(String username, String password, String name, String email, String phoneNumber, String city, String fieldOfExpertise){
        return new User(username,password,name,email,phoneNumber,city,fieldOfExpertise);
    }
}
