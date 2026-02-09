package main.java.logger.repository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.constantpool.LongEntry;

import Logger;
import main.java.logger.logic.LogEntry;

/**
* LogRepository contains the database connection. 
* @param message Log message content
* @param message Log message content
* @param type Log entry type
*/


/**
 * Create sqlite database resource & table
 * https://www.sqlitetutorial.net/sqlite-java/create-database/
 * https://www.sqlitetutorial.net/sqlite-java/create-table/
 * https://www.sqlitetutorial.net/sqlite-java/insert/
 */


public class LogRepository extends LogValidation {

    private String filePath;

    public LogRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
     * Creates and adds a new log entry
     * @param timeStamp Time of the log event
     * @param message Log message content
     * @param type Log entry type
     */
    public void logAppend(LocalDateTime timeStamp, String message, String type) {
        Logger.LogType currentType = Logger.LogType.valueOf(type);
    
        if (currentType.ordinal() >= config.getMinimumLogLevel().ordinal()) {
            LogEntry entry = new LogEntry(timeStamp, message, type);
            log.add(entry);
        }
    }

    /**
     * Enum for standard log types
     */
    // public enum LogType {
        //ERROR, INFO, WARNING, DEBUG
    // }
