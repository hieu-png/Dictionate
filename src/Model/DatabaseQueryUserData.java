package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseQueryUserData {
    DatabaseQueryFunction databaseQueryFunction;


    public DatabaseQueryUserData(DatabaseQueryFunction databaseQueryFunction) {
        this.databaseQueryFunction = databaseQueryFunction;
    }
    public int getUserPassword(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT passwordHashed FROM userinfo WHERE username = ?");
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return 0;
            } else
                return rs.getInt("passwordHashed");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){};
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};

        }
        return 0;
    }
    public String getUserEmail(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT email FROM userinfo WHERE username = ?");
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return "";
            } else
                return rs.getString("email");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){};
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};

        }
        return "";
    }
    public String getUserFullName   (String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT fullName FROM userinfo WHERE username = ?");
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return "";
            } else
                return rs.getString("fullName");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){};
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};

        }
        return "";
    }
    public Date getUserBirthday(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT birthday FROM userinfo WHERE username = ?");
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            } else
                return rs.getDate("birthday");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){};
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};

        }
        return null;
    }
    public int getUserID(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = databaseQueryFunction.getConnection().
                    prepareStatement("SELECT userID FROM userinfo WHERE username = ?");
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return 0;
            } else
                return rs.getInt("userID");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(rs !=null) rs.close();} catch (Exception e){};
            try{ if(stmt !=null) stmt.close();} catch (Exception e){};

        }
        return 0;
    }


}
