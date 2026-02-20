package logger.contract;

public interface ILogger {

  // Methods for deciding level of logger to store.
  void warning(String message);

  // Throwable, log crashes
  void error(String message, Throwable throwable);

  void critical(String message, Throwable throwable);

  // Objects
  void info(String message, Object... args);

  void debug(String message, Object... args);

  // Method for clearing logs
  void clearAllLogs();
}
