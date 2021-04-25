package Controller;

import Model.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class UserInfoView extends SceneController {
    @FXML protected Button logOutButton,toMainMenu;
    @FXML public TextField usernameText, fullNameText,emailText,passwordText;
    @FXML protected void handleBackToMainMenu(ActionEvent event)   {
        switchTo("MainMenu", toMainMenu.getScene());
    }
    @FXML protected void handleBackToLogin(ActionEvent event)   {
        logOut();
    }
    //public UserInfoView() {
  //
   // }


    @Override
    public void init() {
        super.init();
        usernameText.setText(databaseFunction.getCurrentUser().getUsername());
        fullNameText.setText(databaseFunction.getCurrentUser().getFullName());
        emailText.setText(databaseFunction.getCurrentUser().getEmail());
        passwordText.setText(".".repeat(databaseFunction.getCurrentUser().getPasswordLength()));
    }

    public void logOut() {

        databaseFunction.clearUserData();
        switchTo("LoginScreen", logOutButton.getScene());

    }
}
