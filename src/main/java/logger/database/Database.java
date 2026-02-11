package logger.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    /**
     * Method for creating database. If database already exists. 
     * @param args
     */
    public static void createDatabase() {
        String url = "jdbc:sqlite:my.db";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Method for creating table. Checks first if table already exists. 
     */
    public static void createDatabaseTable() {
        // SQLite connection 
        var url = "jdbc:sqlite:my.db";

        // SQL statement for creating a new table
        var sql = "CREATE TABLE IF NOT EXISTS messages ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	timeStamp TEXT NOT NULL,"
                + "	message TEXT NOT NULL,"
                + "	type TEXT NOT NULL"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
