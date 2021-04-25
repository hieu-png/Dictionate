package Controller;

import Model.SceneController;
import Model.UserFunction;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountCreation extends SceneController {

    @FXML protected PasswordField passwordField1, passwordField2;
    @FXML protected TextField username, fullName, email;
    @FXML protected DatePicker birthdayPicker;
    @FXML public Text textMessage,textMessageUserName,textMessageFullName,textMessageBirthday,textMessageEmail,textMessagePassword1,textMessagePassword2;

    public boolean checkIfSatisfy() {
        Boolean success = true;

        if(fullName.getText().compareTo("")==0) {
            textMessageFullName.setText("This field is required");
            success = false;
        } else textMessageFullName.setText("✓");


        if(username.getText().compareTo("")==0) {
            textMessageUserName.setText("This field is required");
            success = false;
        } else if(!checkUserEligibility(username.getText())) { //If already exist
            textMessageUserName.setText("Account already exist");
            success = false;
        } else textMessageUserName.setText("✓");

        if(birthdayPicker.getValue() == null) {
            textMessageBirthday.setText("This field is required");
            success = false;
        }  else textMessageBirthday.setText("✓");

        if(email.getText().compareTo("")==0) {
            textMessageEmail.setText("This field is required");
            success = false;
        } else if(!email.getText().contains("@")) {
            textMessageEmail.setText("This is not a valid email");
            success = false;
        } else textMessageEmail.setText("✓");

        int passwordMinLength = 6;
        if(passwordField1.getText().compareTo("")==0) {
            textMessagePassword1.setText("This field is required");
            success = false;
        } else if(passwordField1.getText().length()<passwordMinLength) {
            textMessagePassword1.setText("Password length must be greater than " + passwordMinLength);
            success = false;
        } else textMessagePassword1.setText("✓");


        if(passwordField2.getText().compareTo("")==0) {
            textMessagePassword2.setText("This field is required");
            success = false;
        } else if(passwordField2.getText().compareTo(passwordField1.getText()) != 0) {

            textMessagePassword2.setText("This is not the same");
            success = false;

        } else textMessagePassword2.setText("✓");

        return success;
    }
    @FXML protected void handleCheckButtonAction() {

        checkIfSatisfy();
    }
    @FXML protected void handleToLoginScreen() {

        switchTo("LoginScreen",textMessage.getScene());
    }
    @FXML protected void handleCreateButtonAction() {


        if(checkIfSatisfy()) {
            PreparedStatement stmt = null;
            try {
                  stmt = databaseFunction.getConnection().prepareStatement(
                        "INSERT INTO userinfo " +
                                " VALUES (null,?,?,?,?,?);");
                        stmt.setString(1,username.getText());
                        stmt.setString(2,fullName.getText());
                        stmt.setDate(3,Date.valueOf(birthdayPicker.getValue()));
                        stmt.setString(4,email.getText());
                        stmt.setInt(5,UserFunction.HashPassword(passwordField1.getText()));
                System.out.println(stmt.toString());
                switchTo("LoginScreen",textMessage.getScene());
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{ if(stmt !=null) stmt.close();} catch (Exception e){};
            }
        } else {

        }
    }
    public boolean checkUserEligibility(String username) {
        Boolean eligibility = true;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt  = databaseFunction.getConnection().prepareStatement("SELECT userName FROM userinfo WHERE userName = ?");
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if(rs.next())
                eligibility = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};
            try{ if(rs !=null) rs.close();} catch (Exception e){};
        }
        return eligibility;
    }
    public boolean findUser(String username) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt  = databaseFunction.getConnection().prepareStatement("SELECT userName FROM userinfo WHERE userName = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};
            try{ if(rs !=null) rs.close();} catch (Exception e){};

        }
        return false;
    }
}
