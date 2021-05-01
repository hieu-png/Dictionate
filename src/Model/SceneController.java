package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class SceneController {
    public static final int APP_WIDTH=850;
    public static final int APP_HEIGHT=600;

    protected DatabaseQueryFunction databaseFunction;

    public void setDatabaseFunction(DatabaseQueryFunction databaseFunction) {
        this.databaseFunction = databaseFunction;
    }

    /**
    initialize function, avoid fxml problematic one.
     */
    public void init() {

    }

    /**
     *
     * @param stageName ten cua stage, scene... vd: Test, Settings
     * @param scene lay tu tat ca variable vd: passwordField.getScene()
     */



    public void switchTo(String stageName,Scene scene){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/"+stageName+".fxml"));

            Parent root = loader.load();

            SceneController s = (SceneController) loader.getController();
            s.setDatabaseFunction(databaseFunction);

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
            s.init();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showAlert(String title, String text, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(contentText);

        alert.showAndWait();
    }
    public Boolean showConfirmation(String title, String text, String contentText) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(contentText);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            return false;
        } else if (option.get() == ButtonType.OK) {
            return true;
        } else if (option.get() == ButtonType.CANCEL) {
            return false;
        } else {
            return false;
        }
    }





}
