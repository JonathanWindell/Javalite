# API Shell

[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
[![JUnit5](https://img.shields.io/badge/JUnit5-C21325?logo=junit5&logoColor=fff)](#)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)](#)
![ReadMe](https://img.shields.io/badge/ReadMe-018EF5?logo=readme&logoColor=fff)
![License](https://img.shields.io/badge/License-MIT-green)

# Highlights

This project was created as an easier way to handle all logs that usually gets lost or gets ignored but which might hold value further on during later development cycles. 

- **Easy viewing of logs** Using SQLite it's easy to view the logs that are saved
- **BDD Architecture** Built from the ground up using Behavior Driven Development with Gherkin & JUnit tests to support database connection. 

# Overview

I created this project as a way to contain the logs that I want to save from my projects but is unable to due to an easy and smart way of doing so. 

This logger contains the essention building blocks such as entites, database & validation. 

**Architecture**
- Show picture of abstract diagram. 

# Author

I'm Jonathan and I develop projects in my sparetime that help myself and others become better and more efficient developers!
- [Linkedin](https://www.linkedin.com/in/jonathan-windell-418a55232/)
- [Portfolio](https://portfolio.jonathans-labb.org/)


# Project Structure

```text
├── .github/
│   └── workflows/          
│       └── main.yml        # CI/CD Pipeline (GitHub Actions)
├── diagram/                # Architecture documentation
│   ├── AbstractDiagram.md
│   └── SequenceDiagram.md
├── docker/                 # Container configuration
│   ├── .dockerignore
│   ├── docker-compose.yml
│   └── Dockerfile
├── src/
│   ├── main/java/logger/
│   │   ├── contract/       # Interfaces and orchestration (ILogger, Logger)
│   │   ├── database/       # Database management (SQLite setup)
│   │   ├── logic/          # Data models and validation (LogEntry, LogValidation)
│   │   ├── repository/     # Configuration and data access (LogConfig, LogRepository)
│   │   └── Main.java       # Application entry point
│   └── test/
│       ├── java/logger/
│       │   ├── database/   # Unit tests for the database layer
│       │   ├── runners/    # Cucumber test runner configuration
│       │   └── steps/      # Step definitions for Gherkin scenarios
│       └── resources/
│           ├── features/   # Cucumber feature files
│           └── simplelogger.properties
├── target/                 # Compiled bytecode and reports (e.g., JaCoCo)
├── .env                    # Environment variables (e.g., DATABASE_URL)
├── .gitattributes          # Line ending normalization (LF/CRLF)
├── .gitignore              # Files to be ignored by Git (e.g., target, .env)
├── pom.xml                 # Maven configuration (Dependencies & Plugins)
└── README.md               # Project documentation and setup guide
```

# Quick Start
- 1: Clone the project: `git clone https://github.com/JonathanWindell/JavaLogger.git`
- 2: Create `.env` file with `ADMINKEY` 
- 3: Run `docker-compose -f docker/docker-compose.yml up --build`.

# Usage Instructions

### 1: API Endpoints
The logger runs as a microservice on port `8080`. By design, these endpoints do not render HTML in a browser but respond to HTTP requests.

| Method  | Endpoint     | Description         | Payload & Headers                          |
| --------| ----------   | ------------------- | ------------------------------------------ |
| POST    | `/log`       | Add a new log entry | `{"message": "string", "level": "string"}` |
| DELETE  | `/deletelogs`| Clear all logs      | Header: `X-API-Key: <Your_Admin_Key>`      |

**Example: Add a Log (PowerShell)**
```PowerShell
Invoke-RestMethod -Uri http://localhost:8080/log -Method Post -ContentType "application/json" -Body '{"message": "System check complete", "level": "INFO"}'
```

**Example: Clear Logs (PowerShell)**
```PowerShell
$headers = @{"X-API-Key" = "Your_Admin_Key"}
Invoke-RestMethod -Uri http://localhost:8080/deletelogs -Method Delete -Headers $headers
```

### 2: Integration as a Library
```java
ILogger logger = new Logger();
logger.info("Internal system message");
```

### 1: Starting the Docker Container

Start & build the docker container using `docker-compose -f docker/docker-compose.yml up --build` <br>
To find more commands look in the `commands.txt` file

**Endpoints** 

**Note:** Endpoints do not show anything in browser by design.

- Add log: `http://localhost:8080/log` <br>
```json
// POST /log
{
  "message": "Application started",
  "level": "INFO"
}
```
- Delete logs: `http://localhost:8080/deletelogs` <br>
**Note:** Delete requires `X-API-Key: <Your_Admin_Key>`


### 2: How to Create own Log Messages
Using the provided methods by ILogger you can easily create messages and decide log level. 

You can test logging out through powershell: <br>
`Invoke-RestMethod -Uri http://localhost:8080/log -Method Post -ContentType "application/json" -Body '{"message": "<Enter your message>", "level": "<Decide level>"}'`

- `logger.[Level](req.getMessage());`

### 3: How to View Logs in Database
The easiest way to watch logs is by downloading. 

**VSCode Extensions** 
- SQLite Viewer - Florian Klampfer: [Link](https://marketplace.visualstudio.com/items?itemName=qwtel.sqlite-viewer) 

This turns any .db file built with SQLite to an easy to read table. 


# Installation Instructions

### Prerequisites
- **Docker Desktop** 
- **Java Version 21**
- **Javac Version 21**

### Docker Compose

### Configuration
Create a `.env` file in the root directory. You can copy the structure below: 

```ini
# Set url to your database. This is only if you use mvn run without docker. 
# Otherwise use docker-compose.yml to decide file path for database. 
DATABASE_URL=jdbc:sqlite:C:/[Path]/[To]/[Your]/[Database]/logs.db

# Key is used to access deletelogs endpoint. 
ADMINKEY=MYPRIVATEADMINKEY
```

Don't forget to create `.gitignore` file and add .env! This ensures that you never push anything private to github. 

# Contributions
Contributions are welcome! Since this project follows **BDD (Behavior Driven Development)**, please ensure you include tests for any new features.

1. **Fork** the project.
2. Create your Feature Branch (`git checkout -b feature/UserFeature`).
3. Commit your changes (`git commit -m 'Add some Feature'`).
4. **Run Tests** (`mvn test`). Ensure everything is green! 
5. Push to the Branch (`git push origin feature/UserFeature`).
6. Open a **Pull Request**.

# License

Distributed under the MIT License. See `LICENSE` file for more information.



