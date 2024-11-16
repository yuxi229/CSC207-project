package entity;

/**
 * Implementation of User.
 */
public class User {
    private String name;
    private String uofTEmail;
    private String passwordHash;
    private String username;

    public User(String name, String email, String password, String username) {
        this.name = name;
        this.uofTEmail = email;
        this.passwordHash = password;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return uofTEmail;
    }

    public void setEmail(String email) {
        this.uofTEmail = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
