package Controller;
import Model.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MainMenu extends SceneController {


    @FXML protected Button buttonTest;
    @FXML protected void goToTest(ActionEvent event) {
        switchTo("Test",buttonTest.getScene());


    }
    @FXML protected void goToSettings(ActionEvent event) {

        //switchTo("Settings",buttonTest.getScene());

    }
    @FXML protected void goToDictionary(ActionEvent event) {

        switchTo("Dictionary",buttonTest.getScene());

    }
    @FXML protected void goToUserInfoView(ActionEvent event) {
        switchTo("UserInfoView",buttonTest.getScene());

    }
}
