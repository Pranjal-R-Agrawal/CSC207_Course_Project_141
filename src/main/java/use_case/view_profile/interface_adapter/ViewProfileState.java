package use_case.view_profile.interface_adapter;

public class ViewProfileState {
    private String username;
    private String name;
    private String email;
    private double rating;
    private String errorMessage;

    public String getUsername() {
        return username;
    }
    public ViewProfileState setUsername() {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewProfileState setName() {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ViewProfileState setEmail() {
        this.email = email;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public ViewProfileState setRating() {
        this.rating = rating;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ViewProfileState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }


}
