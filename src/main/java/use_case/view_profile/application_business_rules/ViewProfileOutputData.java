package use_case.view_profile.application_business_rules;

public class ViewProfileOutputData {
    private final String username;

    private final String name;

    private final String email;

    private final double rating;

    public ViewProfileOutputData(String username, String name, String email, double rating) {
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

    public String getRating() {
        return (String.valueOf(rating));
    }
}
