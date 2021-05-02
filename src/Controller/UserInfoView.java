package Controller;

import Model.Exam;
import Model.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserInfoView extends SceneController {
    @FXML protected Button logOutButton,toMainMenu;
    @FXML public TextField usernameText, fullNameText,emailText,passwordText;
    @FXML protected void handleBackToMainMenu(ActionEvent event)   {
        switchTo("MainMenu", toMainMenu.getScene());
    }
    @FXML protected void handleBackToLogin(ActionEvent event)   {
        logOut();
    }
    @FXML protected TableView<Exam> infoTable;
    @FXML protected TableColumn testIDColumn, testTypeColumn, scoreColumn, dateTakenColumn, timeTakenColumn;
    ObservableList<Exam> examData = FXCollections.observableArrayList();


    @Override
    public void init() {
        super.init();
        usernameText.setText(databaseFunction.getCurrentUser().getUsername());
        fullNameText.setText(databaseFunction.getCurrentUser().getFullName());
        emailText.setText(databaseFunction.getCurrentUser().getEmail());
        passwordText.setText(".".repeat(databaseFunction.getCurrentUser().getPasswordLength()));
        getExamData(databaseFunction.getCurrentUser().getUserIDString());
    }

    public void logOut() {

        databaseFunction.clearUserData();
        switchTo("LoginScreen", logOutButton.getScene());

    }
    public void getExamData(String userID) {
        ResultSet rs = null;
        PreparedStatement stmt;
        try {
            stmt = databaseFunction.getConnection().prepareStatement("SELECT * FROM tests WHERE userID = ?");
            stmt.setString(1,userID);
            rs = stmt.executeQuery();
            while(rs.next()) {
                examData.add(new Exam(
                        rs.getInt("testID"),
                        rs.getString("testType"),
                        rs.getTimestamp("dateTaken"),
                        rs.getTimestamp("timeTaken"),
                        rs.getInt("userID"),
                        rs.getInt("score")
                ));


            }
            testIDColumn.setCellValueFactory(new PropertyValueFactory<Exam,Integer>("testID"));
            testTypeColumn.setCellValueFactory(new PropertyValueFactory<Exam,String>("testType"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<Exam,Integer>("score"));
            dateTakenColumn.setCellValueFactory(new PropertyValueFactory<Exam,String>("timeTakeTest"));
            timeTakenColumn.setCellValueFactory(new PropertyValueFactory<Exam,String>("examLength"));

            infoTable.setItems(examData);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
