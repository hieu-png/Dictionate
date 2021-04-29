package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseQueryDictionary {
    DatabaseQueryFunction databaseQueryFunction;
    ObservableList<String> listOfWordText;
    ArrayList<String> listOfWordDescription;
    ArrayList<String> listLOfWordPronounce;
    public DatabaseQueryDictionary(DatabaseQueryFunction databaseQueryFunction) {
        this.databaseQueryFunction = databaseQueryFunction;
        initializes();
    }

    public ObservableList<String> getListOfWordText() {
        return listOfWordText;
    }

    public ArrayList<String> getListLOfWordPronounce() {
        return listLOfWordPronounce;
    }

    public String getWordText(int index) {
        return listOfWordText.get(index);
    }
    public String getWordPronounce(int index) {
        return listLOfWordPronounce.get(index);
    }
    public String getWordDescription(int index) {
        return listOfWordDescription.get(index);
    }
    public ArrayList<String> getListOfWordDescription() {
        return listOfWordDescription;
    }

    //word, html, description,pronounce
    public String getWordData(String wordToGet,String column) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT * FROM av WHERE word = ?");
            stmt.setString(1,wordToGet);
            rs = stmt.executeQuery();
            if(!rs.next()) {
                return "";
            } else return rs.getString(column);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){};
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};
        }
        return "";
    }
    /**
     * Load dictionary word list at the start.
     */
    public void initializes() {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT * FROM av WHERE NOT pronounce = '' ORDER BY word ASC");
            rs = stmt.executeQuery();
            listLOfWordPronounce = new ArrayList<>();
            listOfWordDescription = new ArrayList<>();
            listOfWordText = FXCollections.observableArrayList();
            while(rs.next()) {
                listOfWordText.add(rs.getString("word"));
                listLOfWordPronounce.add(rs.getString("pronounce"));
                listOfWordDescription.add(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){}
            try{ if(stmt !=null) stmt.close();} catch (Exception e){}

        }
    }
}
