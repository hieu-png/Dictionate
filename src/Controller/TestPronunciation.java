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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TestPronunciation extends SceneController {

    @FXML public Label wordToTest, questionNumber;
    @FXML public RadioButton answer1,answer2,answer3,answer4;
    @FXML public Button backToTest,submitButton,previousButton,nextButton,saveButton;
    @FXML public ToggleGroup Answer;
    @FXML public ListView listOfQuestion; int previousIndex = 0;
    @FXML public ProgressBar progressBar;
    LocalDateTime dateTimeTaken, dateTimeFinished;
    Boolean summitted = false;
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
    @Override
    public void init() {
        super.init();
        generateTest(15);
        listOfQuestion.setItems(observableListOfQuestion);
        listOfQuestion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleSelectItems();
            }
        });
        listOfQuestion.getSelectionModel().select(0);
        dateTimeTaken = LocalDateTime.now();
        System.out.print(dateTimeTaken);

    }
    // khi chon danh sach
    protected void handleSelectItems() {
        System.out.println(getSelectedId() + questionList.get(previousIndex).toString());
        questionList.get(previousIndex).setAnsweredID(getSelectedId());

        progressBar.setProgress(getProgress());
        int selectIndex = listOfQuestion.getSelectionModel().getSelectedIndex();

        setQuestionPane(
                questionList.get(selectIndex).getID(),
                questionList.get(selectIndex).getSelectedWordID(),
                questionList.get(selectIndex).getCorrectAnswerId()
                );
        //xoa cac select neu no la chua dc chon
        if(questionList.get(selectIndex).getAnsweredID() == -1) {
            answer1.setSelected(false);
            answer2.setSelected(false);
            answer3.setSelected(false);
            answer4.setSelected(false);
        } else
        {
            setSelectedId(questionList.get(selectIndex).getAnsweredID(),true);
        }
        previousIndex = selectIndex;
    }
    @FXML
    protected void handleBackToTest(ActionEvent event)   {
        if(showConfirmation("Stop test",
                "Do you really want to cancel the test?",
                "Information will not be saved."))
        switchTo("Test", backToTest.getScene());
    }
    @FXML
    protected void handleSummitButton(ActionEvent event) {
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
            if(showConfirmation("Test finished",
                    "Do you want to review the test?",
                    "Your score is: " + score + "/100!")) {

            }
        }
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
        wordToTest.setText(databaseFunction.getDictionaryData().getWordText(wordID));
        String pronunciation = databaseFunction.getDictionaryData().getWordPronounce(wordID);

        questionNumber.setText(id.toString());
       // int randomDeviation = r.ints(0,4).findFirst().getAsInt();
        Object[] answerArray = Answer.getToggles().toArray();

        for (int i = 0; i <= 3; i ++) {
            if(i == correctAnswerID) {
                ((RadioButton) answerArray[i]).setText("/" +
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


    public int randomWordIndex() {
        Random r = new Random();
        return r.ints(
                0,
                databaseFunction.getDictionaryData().getListOfWordText().size()-3)
                .findFirst().getAsInt();
    }
    public int getSelectedId() {
        if(answer1.isSelected())
            return 0;
        else if (answer2.isSelected())
            return 1;
        else if (answer3.isSelected())
            return 2;
        else if (answer4.isSelected())
            return 3;
        else return -1;
    }
    public void setSelectedId(int id,boolean value) {
        switch (id) {
            case 0:
                answer1.setSelected(value);
                break;
            case 1:
                answer2.setSelected(value);

                break;
            case 2:
                answer3.setSelected(value);

                break;
            case 3:
                answer4.setSelected(value);

                break;
        }
    }
}
