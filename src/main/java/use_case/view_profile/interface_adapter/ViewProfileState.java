package use_case.view_profile.interface_adapter;

public class ViewProfileState {
    private String username;
    private String name;
    private String email;
    private String rating;
    private String errorMessage;

    public String getUsername() {
        return username;
    }
    public ViewProfileState setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewProfileState setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ViewProfileState setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRating() {
        return String.valueOf(rating);
    }

    public ViewProfileState setRating(String rating) {
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
