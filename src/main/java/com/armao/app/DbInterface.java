package com.armao.app;

import java.sql.*;
import java.io.*; /* Handle files */

/**
 * Interface for database.
 */
public interface DbInterface {

    static Connection connect(String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            if (conn != null) {
                disconnect(conn);
            }
        }
        return conn;
    }

    static boolean disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return true;
    }

    static int execUpdateCmd(String url, String cmd) {
        int result = 0;
        try (Connection conn = connect(url);
             Statement stmt = conn.createStatement()) {
             result = stmt.executeUpdate(cmd);
        }
        catch (SQLException exc) {
            System.out.println(exc.getMessage());
            assert false : "SQL Exception OCCURRED!";
        }
        return result;
    }

    static ResultSet executeQueryCmd(String url, String cmd) {
        ResultSet result = null;
        try (Connection conn = connect(url);
             Statement stmt = conn.createStatement()) {
             result = stmt.executeQuery(cmd);
        }
        catch (SQLException exc) {
            System.out.println(exc.getMessage());
            assert false : "SQL Exception OCCURRED!";
        }
        return result;
    }

    static boolean deleteDB(String url) {
        boolean result = true;
        File db = new File(url);
        for (int i = 0; i < 3; i++) {
            if (!db.exists()) {
                break; // Done
            }
            else {
                if (i == 3) {
                    result = false;
                }
                db.delete();
            }
        }
        return result;
    }
}
