package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Exam {
    //Date dateTaken, dateFinished;
    SimpleIntegerProperty  testID,userID,score;
    SimpleStringProperty  testType;
    SimpleStringProperty timeTakeTest, examLength;

    public Exam(Integer testID, String testType, Timestamp dateTaken, Timestamp dateFinished, Integer userID, Integer score) {
        this.testID = new SimpleIntegerProperty (testID);
        this.testType = new SimpleStringProperty(testType);
        //this.dateTaken = dateTaken;
       // this.dateFinished = dateFinished;
        this.userID = new SimpleIntegerProperty ( userID);
        this.score = new SimpleIntegerProperty (score);
        this.timeTakeTest = new SimpleStringProperty(dateTaken.toString());
        //examLength = new SimpleStringProperty("");
        LocalTime tempTakenTime = dateTaken.toLocalDateTime().toLocalTime();
        LocalTime tempFinishedTime = dateFinished.toLocalDateTime().toLocalTime();

        Long hours = tempTakenTime.until(tempFinishedTime, ChronoUnit.HOURS);
        tempTakenTime.plusHours(hours);
        Long minutes = tempTakenTime.until(tempFinishedTime, ChronoUnit.MINUTES);
        tempTakenTime.plusMinutes(minutes);
        Long seconds = tempTakenTime.until(tempFinishedTime, ChronoUnit.SECONDS);
       this.examLength = new SimpleStringProperty(hours.toString() +"h"+
               minutes.toString() +"m"+
               seconds.toString() +"s"


       );
    }

    public String getExamLength() {
        return examLength.get();
    }

    public SimpleStringProperty examLengthProperty() {
        return examLength;
    }

    public void setExamLength(String examLength) {
        this.examLength.set(examLength);
    }

    public int getTestID() {
        return testID.get();
    }

    public SimpleIntegerProperty testIDProperty() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID.set(testID);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getUserID() {
        return userID.get();
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public SimpleIntegerProperty userIDProperty() {
        return userID;
    }

    public String getTestType() {
        return testType.get();
    }

    public SimpleStringProperty testTypeProperty() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType.set(testType);
    }

    public String getTimeTakeTest() {
        return timeTakeTest.get();
    }

    public SimpleStringProperty timeTakeTestProperty() {
        return timeTakeTest;
    }

    public void setTimeTakeTest(String timeTakeTest) {
        this.timeTakeTest.set(timeTakeTest);
    }
}
