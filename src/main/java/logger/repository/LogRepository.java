package logger.repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import logger.logic.LogEntry;

/**
* LogRepository contains the database connection. 
* @param timeStamp timestamp of message
* @param message Log message content
* @param type Log entry type
*/
public class LogRepository {

    private String url = "jdbc:sqlite:my.db";

    public void insertData(LogEntry entry) {
        String sql = "INSERT INTO messages(timeStamp, message, type) VALUES(?,?,?)";

        try (var conn = DriverManager.getConnection(url);
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entry.getTimeStamp().toString()); 
            pstmt.setString(2, entry.getMessage());
            pstmt.setString(3, entry.getType());

            pstmt.executeUpdate();
            System.out.println("Log saved to database!");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public int getLogCount() {
        var sql = "SELECT COUNT(*) FROM messages";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql) {

            while (rs.next()) {
                System.out.printf("%-5s%-25s%-10s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("capacity")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getLastEntryMessage() {
        var sql1 = "SELECT * FROM messages ORDER BY id DESC LIMIT 1";
    }
}

