package logger.contract;

import logger.logic.LogEntry;
import logger.logic.LogValidation;
import logger.repository.LogRepository;

import java.time.LocalDateTime;


public class Logger implements ILogger {
    
    private LogRepository repository;
    private LogValidation validator;

    public Logger() {
        this.repository = new LogRepository();
        this.validator = new LogValidation();
    }

    private void processLog(String message, String level) {
        LocalDateTime timeStamp = LocalDateTime.now();
        LogEntry entry = new LogEntry(timeStamp, message, level);

        if (validator.isValid(entry)) {
            repository.insertData(entry);
        } else {
            System.err.println("Log was not validated: " + message);
        }
    }

    @Override
    public void debug(String message, Object...args) {
        processLog(String.format(message, args), "DEBUG");
    }

    @Override
    public void info(String message, Object...args) {
        processLog(String.format(message, args), "INFO");
    }

    @Override
    public void warning(String message) {
        processLog(message, "WARNING");
    }

    @Override
    public void error(String message, Throwable throwable) {
        String fullMessage = message + " | Error: " + throwable.getMessage();
        processLog(fullMessage, "ERROR");
    }

    @Override
    public void critical(String message, Throwable throwable) {
        String fullMessage = message + " | Critical: " + throwable.getMessage();
        processLog(fullMessage, "CRITICAL");
    }
}
