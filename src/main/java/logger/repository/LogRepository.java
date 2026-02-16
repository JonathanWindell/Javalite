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

    // Real database url 
    private String url;

    // 
    public LogRepository() {
        this.url = LogConfig.getDbURL();
    }

    // Sets correct url. Needed to differntiate between fake(test) & real(production)
    public void setCorrectUrl(String correctUrl){
        this.url = correctUrl;
    }

    // Properties for testing purposes
    private boolean useFakeDatabase = false; 
    private String fakeDatabaseUrl;

    // Fakes a connection to a database. 
    public void setUseFakeDatabase(boolean useFake) {
        this.useFakeDatabase = useFake;
    }

    // Allows for fake url for testing purposes. 
    public void setFakeDatabaseUrl(String fakeUrl) {
        this.fakeDatabaseUrl = fakeUrl;
    }

    public boolean insertData(LogEntry entry) {
        // If executed in test mode (mvn test). Execute with useFakeDatabase. 
        if (useFakeDatabase) return true;
        if (entry == null) return false;

        String activeUrl = (this.url != null) ? this.url : LogConfig.getDbURL();

        String sql = "INSERT INTO messages(timeStamp, message, type) VALUES(?,?,?)";
        try (var conn = DriverManager.getConnection(url);
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entry.getTimeStamp().toString()); 
            pstmt.setString(2, entry.getMessage());
            pstmt.setString(3, entry.getType());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ensure that any old databases are deleted to ensure cleanliness. 
     */
    public void truncateTable() {
        String sql = "DELETE FROM messages";
        try (var conn = DriverManager.getConnection(url);
            var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Clear error: " + e.getMessage());
        }
    }

    /**
     * Counts number of messages. 
     * Used in tests to ensure that messages are created & deleted. 
     * 
     * @return count
     */
    public int getLogCount() {
        String sqlLogCount = "SELECT COUNT(*) FROM messages";
        int count = 0;

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sqlLogCount)) {

            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }
}

