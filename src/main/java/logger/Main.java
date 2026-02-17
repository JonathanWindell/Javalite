package logger;

import logger.contract.*;
import logger.database.Database;
import logger.repository.LogConfig;

public class Main {

  public static void main(String[] args) {

    // Load config settings
    LogConfig.loadConfig();

    // Get url from config file.
    String address = LogConfig.getDbURL();

    // Create database
    Database.createDatabaseTable(address);

    ILogger logger = new Logger();

    logger.warning("Test message from main");
  }
}
