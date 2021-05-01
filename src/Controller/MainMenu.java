package Controller;
import Model.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MainMenu extends SceneController {


    @FXML protected Button buttonTest;
    @FXML protected void goToTest(ActionEvent event) {
        if(databaseFunction.getCurrentUser().getUsername().compareTo("") == 0) {
            showAlert("Take test","Guest user can't take test","Please login or create an account to use.");
        } else
        switchTo("Test",buttonTest.getScene());


    }
    @FXML protected void goToSettings(ActionEvent event) {

        //switchTo("Settings",buttonTest.getScene());

    }
    @FXML protected void goToDictionary(ActionEvent event) {

        switchTo("Dictionary",buttonTest.getScene());

    }
    @FXML protected void goToUserInfoView(ActionEvent event) {
        if(databaseFunction.getCurrentUser().getUsername().compareTo("") == 0) {
            if(showConfirmation("User info",
                    "Guest user can't use this!",
                    "Do you want to log out?")){
                databaseFunction.clearUserData();
                switchTo("LoginScreen", buttonTest.getScene());
            }
        } else
        switchTo("UserInfoView",buttonTest.getScene());

    }

}
