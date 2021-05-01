import Model.DatabaseQueryFunction;
import Model.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {


    //xmlns="http://javafx.com/javafx" xmlns:fx="http://javafx.com/fxml"
    DatabaseQueryFunction databaseQueryFunction;
    @Override
    public void start(Stage primaryStage) throws Exception{
        databaseQueryFunction = new DatabaseQueryFunction();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginScreen.fxml"));
        Parent root = loader.load();

        SceneController s = loader.getController();
        s.setDatabaseFunction(databaseQueryFunction);

        primaryStage.setTitle("E-Training");
        primaryStage.getIcons().add(new Image("https://i.imgur.com/Qf0aOPo.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
