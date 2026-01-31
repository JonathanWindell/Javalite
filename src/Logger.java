import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintStream;
import java.nio.Buffer;

/**
 * Logger class for tracking application events and errors
 */
public class Logger {

    // Collection to store log entries
    private ArrayList<LogEntry> log = new ArrayList<>(); 
    private LoggerConfig config;

    /**
     * Constructor with existing log list
     * @param log List of log entries
     */
    public Logger(ArrayList<LogEntry> log, LoggerConfig config) {
        this.log = log;
        this.config = config;
    }

    /**
     * Returns the current log list
     * @return ArrayList of log entries
     */
    public ArrayList<LogEntry> getLog() {
        return log;
    }


    
   
    
    

    
    


    
    

    /**
     * Logs exceptions with appropriate log level based on exception type
     * @param e The exception to log
     * @param context Description of where the exception occurred
     */
    public void logImportantException(Exception e, String context, LoggerConfig config) {
        LogType logType;

        if (e instanceof NullPointerException || 
            e instanceof ArithmeticException || 
            e instanceof IllegalArgumentException) {
            logType = LogType.ERROR;
        }
        else if (e instanceof IOException) {
            logType = LogType.WARNING;
        }
        else {
            logType = LogType.INFO;
        }
        
        LogEntry entry = new LogEntry(
            LocalDateTime.now(),
            context + ": " + e.getClass().getSimpleName() + " - " + e.getMessage(),
            logType.toString() 
        );

        if (logType.ordinal() >= config.getLogImportantExceptions().ordinal()) {
            this.log.add(entry);
        }
    }
}

