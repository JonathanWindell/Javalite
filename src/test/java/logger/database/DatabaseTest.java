package logger.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.nio.file.Files;

public class DatabaseTest {

    private Database database = new Database();

    @Test
    void checkIfDatabaseTableIsCreated(@TempDir Path tempDir) {
        // Create file in temp-map
        Path dbFilePath = tempDir.resolve("testDatabase.db");

        // Create URL
        String testUrl = "jdbc:sqlite:" + dbFilePath.toAbsolutePath().toString().replace("\\", "/");

        // Act
        Database.createDatabaseTable(testUrl);

        // Assert
        assertTrue(Files.exists(dbFilePath), "File was not found at: " + dbFilePath.toAbsolutePath());
    }
}

