package logger.repository;

import io.github.cdimascio.dotenv.Dotenv;

public class LogConfig {

  private static String dbURL;
  private static String adminKey;

  public static void loadConfig() {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    dbURL = dotenv.get("DATABASE_URL", "jdbc:sqlite:default_logger.db");
  }

  public static String getDbURL() {
    if (dbURL == null) {
      loadConfig(); // Auto-load
    }
    return dbURL;
  }

  public static String getAdminSecret() {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    adminKey = dotenv.get("ADMINKEY");

    return adminKey;
  }
}
