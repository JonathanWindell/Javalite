package logger.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static void createDatabaseTable(String url) {
        var sql = "CREATE TABLE IF NOT EXISTS messages ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " timeStamp TEXT NOT NULL,"
                + " message TEXT NOT NULL,"
                + " type TEXT NOT NULL"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }
}