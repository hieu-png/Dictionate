package Controller;

import Model.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Test extends SceneController {
    @FXML public Button backToMainMenu;
    @FXML
    protected void handleBackToMainMenu(ActionEvent event)   {
        switchTo("MainMenu", backToMainMenu.getScene());
    }

}
