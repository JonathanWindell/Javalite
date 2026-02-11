package logger.repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import logger.logic.LogEntry;

/**
* LogRepository contains the database connection. 
* @param message Log message content
* @param message Log message content
* @param type Log entry type
*/
public class LogRepository {

    private String url = "jdbc:sqlite:my.db";

    public void insertData(LogEntry entry) {
        String sql = "INSERT INTO messages(timeStamp, message, type) VALUES(?,?,?)";

        try (var conn = DriverManager.getConnection(url);
             var pstmt = conn.prepareStatement(sql)) {

            // JDBC-index börjar på 1, inte 0!
            // Vi hämtar datan från vårt entry-objekt
            pstmt.setString(1, entry.getTimeStamp().toString()); 
            pstmt.setString(2, entry.getMessage());
            pstmt.setString(3, entry.getType());

            pstmt.executeUpdate();
            System.out.println("Log saved to database!");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}

