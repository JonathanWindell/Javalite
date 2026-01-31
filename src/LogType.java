

LogRepositoryeates a new log file or reports if it exists
     * @param errorOutput Stream where error messages should be written
     */
    public void createLogFile(PrintStream errorOutput) {
        try {

            String path = config.getLogFilePath().isEmpty() ? "." : config.getLogFilePath();
            String name = config.getLogFileName().isEmpty() ? "logs.txt" : config.getLogFileName();

            File myObj = new File(path, name);
            if (myObj.createNewFile()) {
                errorOutput.println("File created: " + myObj.getAbsolutePath());
                BufferedWriter buffWriter = new BufferedWriter(new FileWriter(myObj));
                buffWriter.write(log.toString());
                buffWriter.close();
            } else {
                errorOutput.println("File already exists.");
            }
        } catch (IOException e) {
            errorOutput.println("An error occurred.");
            e.printStackTrace(errorOutput);
        }
    }


    public void thresholdToLog(LoggerConfig config) {
        if (log.size() >= config.getLogThreshold()) {
            appendToLog(config);
            System.out.println("Logs appended to file given users threshold");
        } else {
            System.out.println("Logs not appended to file given users threshold: ");
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
    public enum LogType {
        ERROR, INFO, WARNING, DEBUG
    }
