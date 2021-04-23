package Controller;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneController {
    public static final int APP_WIDTH=850;
    public static final int APP_HEIGHT=600;
    /**
     *
     * @param stageName ten cua stage, scene... vd: Test, Settings
     * @param scene lay tu tat ca variable vd: passwordField.getScene()
     */
    public void switchTo(String stageName,Scene scene){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/"+stageName+".fxml"));
            Stage stage = (Stage) scene.getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
