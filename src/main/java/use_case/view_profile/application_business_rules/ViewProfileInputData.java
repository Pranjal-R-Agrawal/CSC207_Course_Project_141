package use_case.view_profile.application_business_rules;

public class ViewProfileInputData {

    final private String username;

    final private String name;

    final private String email;

    final private double rating;

    public ViewProfileInputData(String username, String name, String email, double rating) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getRating() {
        return rating;
    }

}
