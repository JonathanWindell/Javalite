package logger.logic;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents a single log entry with timestamp, message, and type
 */
public class LogEntry {
    
    @NotNull(message = "Timestamp cannot be null")
    @PastOrPresent(message = "Timestamp cannot be in the future")
    private LocalDateTime timeStamp;

    @NotBlank(message = "Message cannot be empty")
    @Size(min = 10, max = 100, message = "Message must be between 10 and 100 characters")
    private String message;

    @NotBlank(message = "Level is required")
    @Pattern(regexp = "^(DEBUG|INFO|WARNING|ERROR|CRITICAL)$", message = "Invalid log level")
    private String type;
    
    /**
     * Creates a new log entry
     * @param timeStamp Time when the log entry was created
     * @param message Content of the log message
     * @param type Category or severity of the log entry
     */
    public LogEntry(LocalDateTime timeStamp, String message, String type) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.type = type;
    }
    
    /**
     * Gets the type of this log entry
     * @return The log type as a string
     */
    public String getType() {
        return type;
    }
    
    /**
     * Gets the timestamp of this log entry
     * @return The timestamp when this log was created
     */
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    
    /**
     * Gets the message content
     * @return The log message text
     */
    public String getMessage() {
        return message;
    }
}