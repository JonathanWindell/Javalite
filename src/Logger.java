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






    
   
    
    

    
    


    
    

    /**
     * Logs exceptions with appropriate log level based on exception type
     * @param e The exception to log
     * @param context Description of where the exception occurred
     */
    public void logImportantException(Exception e, String context, LogConfig config) {
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

