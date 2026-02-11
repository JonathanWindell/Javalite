package logger.repository;


public class LogConfig {

    // Letting user decide path and file name
    private String logFilePath = "";
    private String logFileName = "";

    public String getLogFilePath() {
        return logFilePath;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
}
