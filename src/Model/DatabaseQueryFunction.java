package Model;
import java.sql.*;
public class DatabaseQueryFunction {
    Connection connectionDictionaryDatabase;
    Connection connectionUserInfo;
    public DatabaseQueryFunction() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionDictionaryDatabase = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dictionary",
                    "root",
                    "123");


            Statement stmt = connectionDictionaryDatabase.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM av");
            while(rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
