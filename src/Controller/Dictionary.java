package Controller;

import Model.SceneController;
import Model.TextSpeech;
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

    private TextSpeech textspeech = new TextSpeech();

    @FXML
    public ListView wordList;
    @FXML
    public TextField searchBarText;
    @FXML
    public TextArea curWordMeaning;
    @FXML
    public Text curWordDef, curWordPronounce;
    @FXML
    protected Button toMainMenu, searchButton, soundButton;
    boolean loaded = false;

    @FXML
    protected void handleToMainMenu(ActionEvent event) {

        switchTo("MainMenu", toMainMenu.getScene());

    }

    @FXML
    protected void handleSearchButton(ActionEvent event) {
        selectWord(getWordNearest(searchBarText.getText()));
        System.out.print("Input" + searchBarText.getText());

    }

    @FXML
    protected void handleSoundButton(ActionEvent event) {
        String word = searchBarText.getText();
        textspeech.speakWord(word);
    }

    @FXML
    protected void handleSearchText(ActionEvent event) {
        selectWord(getWordNearest(searchBarText.getText()));
        System.out.print("Input" + searchBarText.getText());
    }
    public void selectWord(int i) {
        if (i >= 0 && i < databaseFunction.getDictionaryData().getListOfWordText().size()) {
            wordList.getSelectionModel().select(i);
            wordList.getFocusModel().focus(i);
            wordList.scrollTo(i);

        }

    }

    public int getWordNearest(String i) {
        int  counter = 0;
        boolean found = false;
        if(i.compareTo("") != 0)
        for (String s : databaseFunction.getDictionaryData().getListOfWordText()) {
            if (i.charAt(0) >= s.charAt(0)) {
                if (s.length() >= i.length()) {
                    if (s.substring(0, i.length()).compareTo(i) == 0) {
                        found = true;
                        break;

                    }
                }

            } else break;
            counter++;

        }
        if(!found)
            counter = -1;
        return counter;
    }

    protected void handleSelectItems() {
        int i = (getSelectedIndex());
        String word = getSelectedWord().replace("[", "").replace("]", "");

        curWordDef.setText(word);
        curWordMeaning.setText(databaseFunction.getDictionaryData().getListOfWordDescription().get(i));
        curWordPronounce.setText(
                "/" + databaseFunction.getDictionaryData().getListLOfWordPronounce().get(i) + "/");

    }

    public int getSelectedIndex() {
        return wordList.getSelectionModel().getSelectedIndex();
    }
    public String getSelectedWord() {
        return wordList.getSelectionModel().getSelectedItems().toString();

    }
    @Override
    public void init() {
        wordList.setItems(databaseFunction.getDictionaryData().getListOfWordText());
        wordList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleSelectItems();
            }
        });
        searchBarText.textProperty().addListener((obs, oldText, newText) -> {
            selectWord(getWordNearest(searchBarText.getText()));

            // ...
        });
    }
}
