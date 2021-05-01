package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class DatabaseQueryTest {
    DatabaseQueryFunction databaseQueryFunction;
    public DatabaseQueryTest(DatabaseQueryFunction databaseQueryFunction) {
        this.databaseQueryFunction = databaseQueryFunction;

    }
    public void addRecord(String testType, Timestamp timeTaken, Timestamp timeFinished, int userID, int score ) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt  = databaseQueryFunction.
                    getConnection().prepareStatement(
                            "INSERT INTO tests " +
                                    "VALUES (null,?,?,?,?,?);");
            stmt.setString(1,testType);
            stmt.setTimestamp(2,timeTaken);
            stmt.setTimestamp(3,timeFinished);
            stmt.setInt(4,userID);
            stmt.setInt(5,score);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};
            try{ if(rs !=null) rs.close();} catch (Exception e){};
        }

    }

}
