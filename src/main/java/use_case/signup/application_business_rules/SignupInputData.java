package use_case.signup.application_business_rules;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String name;
    final private String email;
    final private String phoneNumber;
    final private String city;
    final private String fieldOfExpertise;

    public SignupInputData(String username, String password, String repeatPassword, String name, String email, String phoneNumber, String city, String fieldOfExpertise) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.fieldOfExpertise = fieldOfExpertise;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }
}
