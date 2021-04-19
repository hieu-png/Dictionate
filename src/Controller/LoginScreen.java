package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class LoginScreen {
    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
    @FXML protected void handleLoginAsGuestAction(ActionEvent event) {
        actiontarget.setText("Logged in as guest");
    }
}
