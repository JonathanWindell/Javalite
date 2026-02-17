package logger.database;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class DatabaseTest {

  private Database database = new Database();

  @Test
  void checkIfDatabaseTableIsCreated(@TempDir Path tempDir) {
    // Arrange
    // Create file in temp-map
    Path dbFilePath = tempDir.resolve("testDatabase.db");
    String testUrl = "jdbc:sqlite:" + dbFilePath.toAbsolutePath().toString().replace("\\", "/");

    // Act
    Database.createDatabaseTable(testUrl);

    // Assert
    assertTrue(Files.exists(dbFilePath), "File was not found at: " + dbFilePath.toAbsolutePath());
  }

  @Test
  void checkStructureIsCorrect(@TempDir Path tempDir) {
    // Arrange
    Path dbFilePath = tempDir.resolve("testDatabase.db");
    String testUrl = "jdbc:sqlite:" + dbFilePath.toAbsolutePath().toString().replace("\\", "/");

    // Act
    Database.createDatabaseTable(testUrl);

    HashMap<String, String> columnMap = new HashMap<>();

    try (Connection connection = DriverManager.getConnection(testUrl)) {
      DatabaseMetaData metadata = connection.getMetaData();
      // Get metadata for messages table.
      try (ResultSet columns = metadata.getColumns(null, null, "messages", null)) {
        while (columns.next()) {
          String name = columns.getString("COLUMN_NAME").toLowerCase();
          String type = columns.getString("TYPE_NAME");
          columnMap.put(name, type);
        }
      }
    } catch (SQLException e) {
      fail("Could not read database structure: " + e.getMessage());
    }

    // Assert
    assertEquals("INTEGER", columnMap.get("id"));
    assertEquals("TEXT", columnMap.get("timestamp"));
    assertEquals("TEXT", columnMap.get("message"));
    assertEquals("TEXT", columnMap.get("type"));
  }

  @Test
  void checkForIdempotence(@TempDir Path tempDir) {
    // Arrange
    Path dbFilePath = tempDir.resolve("testDatabase.db");
    String testUrl = "jdbc:sqlite:" + dbFilePath.toAbsolutePath().toString().replace("\\", "/");

    // Act
    Database.createDatabaseTable(testUrl);
    assertDoesNotThrow(
        () -> Database.createDatabaseTable(testUrl),
        "Method should be able to be executed multiple times. (t.ex. 'Table already exists')");

    // Assert
    assertTrue(Files.exists(dbFilePath), "Database file should exist after.");
  }

  @Test
  @DisplayName("Test if non existent folder is handled correctly")
  void notValidDatabaseString(@TempDir Path tempDir) {
    // Arrange
    Path invalidPath = tempDir.resolve("non_Existent_folder/database.db");
    String testUrl = "jdbc:sqlite:" + invalidPath.toString();

    // Act
    Database.createDatabaseTable(testUrl);

    // Assert
    assertFalse(Files.exists(invalidPath), "File Should not exist since format is not correct");
  }
}
