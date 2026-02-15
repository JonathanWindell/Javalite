package logger.contract;


public interface ILogger {

    // 
    void log(String message);

    /*
     */
    public class ConsoleLogger implements ILogger {

        @Override
        public void log(String message) {
            System.out.println(message);
        }
    }

    // user has to enter databaseURl

    /**
     * Enum for standard log types
     */
    // public enum LogType {
        //ERROR, INFO, WARNING, DEBUG
    // }

    /**
     * private Logger.LogType minimumLogLevel = Logger.LogType.INFO;
     * 
     * public void setMinimumLogLevel(Logger.LogType minimumLogLevel) {
        this.minimumLogLevel = minimumLogLevel;
    }

     */


}
