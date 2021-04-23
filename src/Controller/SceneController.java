package Controller;

import Model.DatabaseQueryFunction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    public static final int APP_WIDTH=850;
    public static final int APP_HEIGHT=600;
    DatabaseQueryFunction databaseQueryFunction;

    public DatabaseQueryFunction getDatabaseQueryFunction() {
        return databaseQueryFunction;
    }

    public void setDatabaseQueryFunction(DatabaseQueryFunction databaseQueryFunction) {
        this.databaseQueryFunction = databaseQueryFunction;
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
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
