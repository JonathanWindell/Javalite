package logger.contract;


public interface ILogger {

    // Methods for deciding level of logger to store. 
    void info(String message);
    void warning(String message);
    void debug(String message);
    void error(String message);
    void critical(String message);

    // Throwable, log crashes
    void error(String message, Throwable throwable);
    void critical(String message, Throwable throwable);

    // Objects
    void info(String message, Object...args);
    void debug(String message, Object...args);
}
