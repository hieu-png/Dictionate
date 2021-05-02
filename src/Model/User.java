package Model;

import java.sql.Date;

public class User {
    String username;
    String fullName;
    String email;
    Date birthday;
    int userID;
    int passwordLength;
    int passwordHashed;

    public User(int userID, String username, String fullName, Date birthday, String email, int passwordLength) {
        this.userID = userID;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.passwordLength = passwordLength;

    }
    public String getUserIDString() {
        return ((Integer)userID).toString();
    }

    public int getUserID() {
        return userID;
    }

    public void setUser(int userID, String username, String fullName, Date birthday, String email, int passwordLength) {
        this.userID = userID;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.passwordLength = passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getPasswordLength() {
        return passwordLength;
    }
}
