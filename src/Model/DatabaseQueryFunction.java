package Model;
import java.sql.*;
public class DatabaseQueryFunction {
    Connection connection;
    User currentUser;
    DatabaseQueryDictionary databaseQueryDictionary;
    DatabaseQueryUserData databaseQueryUserData;
    DatabaseQueryTest databaseQueryTest;
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public DatabaseQueryUserData getUserData() {
        return databaseQueryUserData;
    }
    public void clearUserData() {
        currentUser.setUser(0,"","",null,"",0);
    }

    public void getTimes() {

    }

    public DatabaseQueryTest getDatabaseQueryTest() {
        return databaseQueryTest;
    }

    public DatabaseQueryDictionary getDictionaryData() {
        return databaseQueryDictionary;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public DatabaseQueryFunction() {
        try {
            currentUser = new User(0,"","",null,"",0);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dictionary?characterEncoding=latin1&useConfigs=maxPerformance",
                    "root",
                    "123");
            System.out.println("ConnectionSuccess");
            databaseQueryDictionary = new DatabaseQueryDictionary(this);
            databaseQueryUserData = new DatabaseQueryUserData(this);
            databaseQueryTest = new DatabaseQueryTest(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
