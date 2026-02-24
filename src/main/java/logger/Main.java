package logger;

import io.javalin.*;
import logger.api.LogController;
import logger.contract.*;
import logger.repository.LogConfig;

public class Main {
  public static void main(String[] args) {
    // 1. Initialize core services
    LogConfig.loadConfig();
    ILogger logger = new Logger();

    // 2. Start Javalin
    var app = Javalin.create().start(8080);

    // 3. Register API routes
    new LogController(logger).registerRoutes(app);

    System.out.println("Logger Service is running...");
  }
}
