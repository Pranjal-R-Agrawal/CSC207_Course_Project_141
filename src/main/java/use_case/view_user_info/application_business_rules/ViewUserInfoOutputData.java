package use_case.view_user_info.application_business_rules;

public class ViewUserInfoOutputData {

    private final String name;

    private final double rating;

    private final String email;

    private final String phoneNumber;

    public ViewUserInfoOutputData(String name, double rating, String email, String phoneNumber) {
        this.name = name;
        this.rating = rating;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
