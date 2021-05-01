package Controller;

import Model.SceneController;
import Model.UserFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginScreen extends SceneController {
    @FXML
    private Text textMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

        if (username.getText().compareTo("") == 0)
            textMessage.setText("Please enter a username!");
        else if (passwordField.getText().compareTo("") == 0)
            textMessage.setText("Please enter a password!");
        else if(!login(username.getText(),passwordField.getText()))
            textMessage.setText("Either the username or password is incorrect");

    }
    @FXML protected void handleLoginAsGuestAction(ActionEvent event)   {
        login("guest","1");
    }
    @FXML protected void handleCreateNewAccountAction(ActionEvent event)   {
        switchTo("AccountCreation", textMessage.getScene());
    }
    private Boolean login(String userName, String password)  {
        if(userName == "guest") {
            switchTo("MainMenu", textMessage.getScene());
            return  true;
        } else
        if (databaseFunction.getUserData().getUserPassword(userName) == UserFunction.HashPassword(password)) { // Check for password here
            textMessage.setText("Logged in as " + userName);

            switchTo("MainMenu", textMessage.getScene());
          databaseFunction.getCurrentUser().setUser(
                  databaseFunction.getUserData().getUserID(userName),
                  userName,
                  databaseFunction.getUserData().getUserFullName(userName),
                  databaseFunction.getUserData().getUserBirthday(userName),
                  databaseFunction.getUserData().getUserEmail(userName),
                  password.length()
                  );

            return true;
        } else {
            return false;
        }
    }


}
