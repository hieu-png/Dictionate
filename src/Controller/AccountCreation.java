package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AccountCreation extends SceneController {

    @FXML protected PasswordField passwordField1, passwordField2;
    @FXML protected TextField username, fullName, email;
    @FXML protected Text textMessage,textMessageUserName,textMessageFullName,textMessageEmail,textMessagePassword1,textMessagePassword2;

    public boolean checkIfSatisfy() {
        Boolean success = true;

        if(fullName.getText().compareTo("")==0) {
            textMessageFullName.setText("This field is required");
            success = false;
        } else textMessageFullName.setText("✓");


        if(username.getText().compareTo("")==0) {
            textMessageUserName.setText("This field is required");
            success = false;
        } else if(false) { //If already exist
            textMessageUserName.setText("Account already exist");
            success = false;
        } else textMessageUserName.setText("✓");


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

        switchTo("LoginScreen",textMessage.getScene(),false);
    }
    @FXML protected void handleCreateButtonAction() {


        if(checkIfSatisfy()) {

        } else {

        }
    }
}
