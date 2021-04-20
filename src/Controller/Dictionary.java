package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Dictionary extends SceneController{

    @FXML protected Button toMainMenu;
    @FXML protected void handleToMainMenu(ActionEvent event)   {
        switchTo("MainMenu", toMainMenu.getScene());
    }

}
