package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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





}
