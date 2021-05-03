package Controller;

import Model.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class TestPronunciation extends SceneController {

    @FXML public Label wordToTest, questionNumber;
    @FXML public RadioButton answer0, answer1, answer2, answer3;
    @FXML public Button backToTest,submitButton,previousButton,nextButton,saveButton;
    @FXML public ToggleGroup Answer;
    @FXML public ListView listOfQuestion; int previousIndex = 0;
    @FXML public ProgressBar progressBar;
    int difficulty = 1;
    RadioButton[] answerArray= new RadioButton[4];
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    LocalDateTime dateTimeTaken, dateTimeFinished;
    Boolean submitted = false;
    ObservableList<String> observableListOfQuestion = FXCollections.observableArrayList();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ArrayList<TestQuestionP> questionList = new ArrayList<>();
    //Test Structure: ID, selectedWord, answerNumber
    //Cau truc cua mot cau hoi
    class TestQuestionP {
        int ID,selectedWordID,correctAnswerId,answeredID;
        public TestQuestionP(int ID, int selectedWordID, int correctAnswerId) {
            this.correctAnswerId = correctAnswerId;
            this.ID = ID;
            this.selectedWordID = selectedWordID;
            this.answeredID = -1;
        }
        public boolean isCorrect() {
            return correctAnswerId == answeredID;
        }

        public boolean isAnswered() {
            return !(answeredID == -1);
        }

        public void setAnsweredID(int answeredID) {
            this.answeredID = answeredID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getAnsweredID() {
            return answeredID;
        }

        public int getCorrectAnswerId() {
            return correctAnswerId;
        }

        public int getSelectedWordID() {
            return selectedWordID;
        }

        @Override
        public String toString() {
            return "TestQuestionP{" +
                    "ID=" + ID +
                    ", selectedWordID=" + selectedWordID +
                    ", correctAnswerId=" + correctAnswerId +
                    ", answeredID=" + answeredID +
                    '}';
        }
    }
    // chay luc duoc goi


    public void initSpecial(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void init() {
        super.init();
        answerArray[0] =  answer0;
        answerArray[1] =  answer1;
        answerArray[2] =  answer2;
        answerArray[3] =  answer3;

        generateTest(15+(difficulty - 1) * 10);
        listOfQuestion.setItems(observableListOfQuestion);
        listOfQuestion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleSelectItems();
            }
        });
        listOfQuestion.getSelectionModel().select(0);
        dateTimeTaken = LocalDateTime.now();

    }
    // khi chon danh sach
    protected void handleSelectItems() {
        int selectIndex = listOfQuestion.getSelectionModel().getSelectedIndex();

        if(!submitted) {
            questionList.get(previousIndex).setAnsweredID(getSelectedId());

            System.out.println(getSelectedId() + questionList.get(previousIndex).toString());
            progressBar.setProgress(getProgress());
        setQuestionPane(
                questionList.get(selectIndex).getID(),
                questionList.get(selectIndex).getSelectedWordID(),
                questionList.get(selectIndex).getCorrectAnswerId()
                );
            previousIndex = selectIndex;

        }
        else {
            setQuestionPane(
                    questionList.get(selectIndex).getID(),
                    questionList.get(selectIndex).getSelectedWordID(),
                    questionList.get(selectIndex).getCorrectAnswerId(),
                    questionList.get(selectIndex).getAnsweredID()
            );
        }
        //xoa cac select neu no la chua dc chon
        if(questionList.get(selectIndex).getAnsweredID() == -1) {
            answer0.setSelected(false);
            answer1.setSelected(false);
            answer2.setSelected(false);
            answer3.setSelected(false);
        } else
        {
            setSelectedId(questionList.get(selectIndex).getAnsweredID(),true);
        }

    }
    @FXML
    protected void handleBackToTest(ActionEvent event)   {

        if(submitted || showConfirmation("Stop test",
                "Do you really want to cancel the test?",
                "Information will not be saved."))
        switchTo("Test", backToTest.getScene());
    }
    @FXML
    protected void handleSummitButton(ActionEvent event) {
        submit();
    }
    @FXML protected void handleSaveButton(ActionEvent event) {
        handleSelectItems();
    }
    @FXML protected void handleNextButton(ActionEvent event) {
        selectItem(listOfQuestion.getSelectionModel().getSelectedIndex()+1);

    }
    @FXML protected void handlePreviousButton(ActionEvent event) {
        selectItem(listOfQuestion.getSelectionModel().getSelectedIndex()-1);
    }
    public void selectItem(int index) {
        if(index >= 0 && index < questionList.size()) {
        listOfQuestion.getSelectionModel().select(index);
        listOfQuestion.getFocusModel().focus(index);
        listOfQuestion.scrollTo(index);
        handleSelectItems();
        }
    }
    public void generateTest(int numberOfQuestion) {
        Random r = new Random();

        for(int i = 0; i < numberOfQuestion; i ++) {

            int randomDeviation = r.ints(
                    0,
                    4).findFirst().getAsInt();

            int randomWord = randomWordIndex();
            questionList.add(new TestQuestionP(
                    i+1,
                    randomWord,
                    randomDeviation));

            observableListOfQuestion.add(databaseFunction.getDictionaryData().getWordText(randomWord));
        }
    }

    public void submit() {
        handleSelectItems();
        int score = (int)(100*(float)checkTestScore()/(float)questionList.size());
        if(showConfirmation("Finish test",
                "Do you want to submit the test?",
                "You can't change the answer after submitting!")) {
            dateTimeFinished = LocalDateTime.now();


            databaseFunction.getDatabaseQueryTest().addRecord(Test.TEST_PRONUNCIATION,
                    Timestamp.valueOf(dateTimeTaken),
                    Timestamp.valueOf(dateTimeFinished),
                    databaseFunction.getCurrentUser().getUserID(),
                    score
            );
            submitted = true;
            if(showConfirmation("Test finished",
                    "Do you want to review the test?",
                    "Your score is: " + score + "/100!")) {
                answer0.setDisable(true);
                answer1.setDisable(true);
                answer2.setDisable(true);
                answer3.setDisable(true);
                handleSelectItems();
                submitButton.setDisable(true);

            }
        }
    }

    public int checkTestScore() {
        int score = 0;
        for(TestQuestionP tqp : questionList) {
            if(tqp.isCorrect())
                score++;
        }
        return score;
    }

    public double getProgress() {
        double progress = 0;
        for(TestQuestionP tqp : questionList) {
            if(tqp.isAnswered()) {
                progress++;
            }
        }
        return  progress/(double)questionList.size();
    }

    public void clearTest() {
        questionList.clear();
    }
    public void setQuestionPane(Integer id, Integer wordID,Integer correctAnswerID) {
        String pronunciation = databaseFunction.getDictionaryData().getWordPronounce(wordID);

        wordToTest.setText(databaseFunction.getDictionaryData().getWordText(wordID));

        questionNumber.setText(id.toString());

        for (int i = 0; i <= 3; i ++) {
            if(i == correctAnswerID) {
                (answerArray[i]).setText("/" +
                         pronunciation +
                        "/");
            } else {
                String pronunciationOther = pronunciation;
                int offset = 0;
                while(pronunciationOther.compareTo(pronunciation) == 0) {
                    offset++;
                    pronunciationOther = databaseFunction.getDictionaryData().getWordPronounce(wordID + i + offset);
                }
                ((RadioButton) answerArray[i]).setText("/" +
                        pronunciationOther
                        + "/");
            }
        }
    }
    public void setQuestionPane(Integer id, Integer wordID,int correctAnswerID, int answeredID) {
        String pronunciation = databaseFunction.getDictionaryData().getWordPronounce(wordID);
        wordToTest.setText(databaseFunction.getDictionaryData().getWordText(wordID));

        questionNumber.setText(id.toString());
        // int randomDeviation = r.ints(0,4).findFirst().getAsInt();

        for (int i = 0; i <= 3; i ++) {
            RadioButton button = (answerArray[i]);
            if(i == correctAnswerID) {
                button.setText("/" +
                        pronunciation +
                        "/");
                if(answeredID == correctAnswerID)
                    button.setStyle("-fx-text-fill: green; -fx-font-size: 13px;");
                else
                    button.setStyle("-fx-text-fill: red; -fx-font-size: 13px;");

            } else {
                String pronunciationOther = pronunciation;
                int offset = 0;
                while(pronunciationOther.compareTo(pronunciation) == 0) {
                    offset++;
                    pronunciationOther = databaseFunction.getDictionaryData().getWordPronounce(wordID + i + offset);
                }
                button.setText("/" +
                        pronunciationOther
                        + "/");
                button.setStyle("-fx-text-fill: black; -fx-font-size: 13px;");
            }
        }
    }

    public int randomWordIndex() {
        Random r = new Random();
        return r.ints(
                0,
                databaseFunction.getDictionaryData().getListOfWordText().size()-3)
                .findFirst().getAsInt();
    }
    public int getSelectedId() {
        if(answer0.isSelected())
            return 0;
        else if (answer1.isSelected())
            return 1;
        else if (answer2.isSelected())
            return 2;
        else if (answer3.isSelected())
            return 3;
        else return -1;
    }
    public void setSelectedId(int id,boolean value) {
        switch (id) {
            case 0:
                answer0.setSelected(value);
                break;
            case 1:
                answer1.setSelected(value);
                break;
            case 2:
                answer2.setSelected(value);
                break;
            case 3:
                answer3.setSelected(value);
                break;
        }
    }
}
