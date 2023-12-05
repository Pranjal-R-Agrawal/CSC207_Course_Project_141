package use_case.view_user_info.application_business_rules;

public class ViewUserInfoOutputData {

    private final String name;

    private final String fieldOfExpertise;


    private final String city;

    private final String email;

    private final String phoneNumber;

    public ViewUserInfoOutputData(String name, String fieldOfExpertise, String city, String email, String phoneNumber) {
        this.name = name;
        this.fieldOfExpertise = fieldOfExpertise;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }

    public String getCity() {
        return city;
    }
}
