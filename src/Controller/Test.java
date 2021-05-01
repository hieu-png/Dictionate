package Controller;

import Model.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Test extends SceneController {
    public static String TEST_PRONUNCIATION = "pronunciation";
    public static String TEST_WORD_MEANING = "word meaning";

    @FXML public Button backToMainMenu;
    @FXML
    protected void handleBackToMainMenu(ActionEvent event)   {
        switchTo("MainMenu", backToMainMenu.getScene());
    }
    @FXML
    protected void handleLaunchTestP(ActionEvent event)   {
        switchTo("TestPronunciation", backToMainMenu.getScene());
    }
}
