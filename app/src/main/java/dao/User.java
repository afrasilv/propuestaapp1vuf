package dao;

/**
 * Created by alex on 18/06/15.
 */
public class User {
    private String username;
    private String pass;
    private String email;
    private boolean isTeacher;

    public User() {
    }

    public User(String username, String pass, String email, boolean isTeacher) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.isTeacher = isTeacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }
}
