package logger.logic;

public class LogRequest {
  // Instansiate
  private String message;
  private String level;

  // Empty constructor needed by Jackson to map object.
  public LogRequest() {}

  public LogRequest(String message, String level) {
    this.message = message;
    this.level = level;
  }

  // Getters & Setters (Important for JSON!)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
}
