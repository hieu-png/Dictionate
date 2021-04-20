package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class UserInfoView extends SceneController{
    @FXML protected Button logOutButton,toMainMenu;
    @FXML protected void handleBackToMainMenu(ActionEvent event)   {
        switchTo("MainMenu", toMainMenu.getScene());
    }
    @FXML protected void handleBackToLogin(ActionEvent event)   {
        switchTo("LoginScreen", logOutButton.getScene(),false);
    }
}
