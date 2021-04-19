package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginScreen {
    @FXML private Text textMessage;
    @FXML private TextField username;
    @FXML private PasswordField passwordField;
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        if(username.getText().compareTo("")==0)
            textMessage.setText("Please enter a username!");
        else if(passwordField.getText().compareTo("")==0)
            textMessage.setText("Please enter a password!");
        else if(!login(username.getText(),passwordField.getText()))
            textMessage.setText("Either the username or password is incorrect");

    }
    @FXML protected void handleLoginAsGuestAction(ActionEvent event) {
        login("guest","1");
    }
    private Boolean login(String userName, String password){
        textMessage.setText("Logged in as "+userName);
        return true;
    }
}
