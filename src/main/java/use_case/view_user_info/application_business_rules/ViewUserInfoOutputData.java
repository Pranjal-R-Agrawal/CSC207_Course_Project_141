package use_case.view_user_info.application_business_rules;

public class ViewUserInfoOutputData {

    private final String username;

    private final double rating;

    private final String email;

    private final String phoneNumber;

    public ViewUserInfoOutputData(String username, double rating, String email, String phoneNumber) {
        this.username = username;
        this.rating = rating;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
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
