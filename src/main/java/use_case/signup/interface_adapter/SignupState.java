package use_case.signup.interface_adapter;

public class SignupState {
    private String username;
    private String password;
    private String repeatPassword;
    private String name;
    private String email;
    private String phoneNumber;
    private String errorMessage;

    public String getUsername() {
        return username;
    }

    public SignupState setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignupState setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public SignupState setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
        return this;
    }

    public String getName() {
        return name;
    }

    public SignupState setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SignupState setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SignupState setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }

    public void setFieldOfExpertise(String fieldOfExpertise) {
        this.fieldOfExpertise = fieldOfExpertise;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public SignupState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
