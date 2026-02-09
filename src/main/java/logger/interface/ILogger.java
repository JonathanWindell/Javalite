import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

}
