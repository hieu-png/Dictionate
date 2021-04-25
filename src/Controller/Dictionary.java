package Controller;

import Model.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Dictionary extends SceneController {

    @FXML protected Button toMainMenu,searchButton;
    @FXML public ListView wordList;
    @FXML public TextField searchBarText;
    @FXML public TextArea curWordMeaning;
    @FXML public Text curWordDef,curWordPronounce;
    boolean loaded = false;
    @FXML protected void handleToMainMenu(ActionEvent event)   {

       switchTo("MainMenu", toMainMenu.getScene());

    }
    protected void handleSelectItems()   {
        int i = (getSelectedIndex());
        String word = getSelectedWord().replace("[","").replace("]","");

        curWordDef.setText(word);
        curWordMeaning.setText(databaseFunction.getDictionaryData().getListOfWordDescription().get(i));
        curWordPronounce.setText(
                "/"+ databaseFunction.getDictionaryData().getListLOfWordPronounce().get(i)+"/");

    }
    public int getSelectedIndex() {
        return wordList.getSelectionModel().getSelectedIndex();
    }
    public String getSelectedWord() {
        return wordList.getSelectionModel().getSelectedItems().toString();

    }
    @Override
    public void init() {
        wordList.setItems(databaseFunction.getDictionaryData().getListOfWordCache());
        wordList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleSelectItems();
            }
        });

    }
}
