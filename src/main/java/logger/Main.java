package logger;

import io.javalin.*;
import logger.contract.*;
import logger.database.Database;
import logger.logic.LogRequest;
import logger.repository.LogConfig;

public class Main {
  public static void main(String[] args) {
    LogConfig.loadConfig();
    Database.createDatabaseTable(LogConfig.getDbURL());
    ILogger logger = new Logger();

    // Start service port 8080
    var app = Javalin.create().start(8080);

    // Create endpoint: POST http://localhost:8080/log
    app.post(
        "/log",
        ctx -> {
          // Retreive data (Example { "message": "Something happened", "level": "INFO" })
          LogRequest req = ctx.bodyAsClass(LogRequest.class);

          logger.info(req.getMessage());

          ctx.status(201).result("Log saved");
        });

    // Create endpoint: POST http://localhost:8080/deletelogs
    app.delete(
        "/deletelogs",
        ctx -> {
          String apiKey = ctx.header("X-API-Key");
          String secret = LogConfig.getAdminSecret(); // From .env

          if (secret != null && secret.equals(apiKey)) {
            logger.clearAllLogs();
            ctx.status(204);
          } else {
            ctx.status(401).result("Unauthorized - Keep your hands off my logs!");
          }
        });
  }
}
