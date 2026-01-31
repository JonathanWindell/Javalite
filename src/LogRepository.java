import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.constantpool.LongEntry;

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
