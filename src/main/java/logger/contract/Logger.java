package logger.contract;

import logger.database.Database;
import logger.logic.LogEntry;
import logger.repository.LogConfig;
import java.time.LocalDateTime;


public class Logger implements ILogger {
    
    public static void main(String[] args) {

        

        // Gets local time and user message & log level. 
        LogEntry logEntry = new LogEntry(LocalDateTime now, userMessage, info);

        // Load config settings
        LogConfig.loadConfig();

        // Get url from config file. 
        String address = LogConfig.getDbURL();

        // Create database
        Database.createDatabaseTable(address);
    }
}
