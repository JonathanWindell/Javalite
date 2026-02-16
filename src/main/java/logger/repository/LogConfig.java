package logger.repository;

import io.github.cdimascio.dotenv.Dotenv;

public class LogConfig {

    private static String dbURL;

    public static void loadConfig() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        dbURL = dotenv.get("DATABASE_URL", "jdbc:sqlite:default_logger.db");
    }

    public static String getDbURL() {
        if (dbURL == null) {
            loadConfig(); // Auto-load
        }
        return dbURL;
    }
}
