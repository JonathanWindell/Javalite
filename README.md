# Java Logger

[![Java 21](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)](#)
[![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apache-maven&logoColor=white)](#)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)](#)
[![SQLite](https://img.shields.io/badge/SQLite-07405E?logo=sqlite&logoColor=white)](#)
[![Java CI with Maven](https://github.com/JonathanWindell/JavaLogger/actions/workflows/main.yml/badge.svg)](https://github.com/JonathanWindell/JavaLogger/actions/workflows/main.yml)
[![JUnit5](https://img.shields.io/badge/JUnit5-C21325?logo=junit5&logoColor=fff)](#)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?logo=cucumber&logoColor=white)](#)
![License](https://img.shields.io/badge/License-MIT-green)


# Highlights

- **Microservice Ready:** Fully containerized using Docker, allowing for a "plug-and-play" setup in any environment.
- **RESTful API:** Interact with the logger through simple HTTP requests, making it language-agnostic.
- **Persistent Storage:** Built-in SQLite support ensures your logs are saved locally and are easily queryable without complex database setups.
- **BDD Architecture:** Developed using Behavior Driven Development (BDD). Includes Gherkin feature files and JUnit tests to ensure reliability and clear logic.
- **Layered Design:** Follows professional Clean Code principles with a clear separation between API, Business Logic, and Data layers.

# Overview

This project provides a robust and scalable shell for a centralized logging service. In many development cycles, valuable log data is often scattered or lost due to the lack of a simple, dedicated system. This API-driven logger solves that by providing a lightweight, ready-to-use template for capturing system events, errors, and info logs.

By utilizing **Javalin** for the web layer and **SQLite** for persistence, the project balances performance with simplicity. It includes essential building blocks such as:
- **Entities & DTOs:** Clean data structures for log requests.
- **Validation:** Integrated logic to ensure data integrity before storage.
- **Database Orchestration:** Automated table management and connection handling.

Whether you use it as a standalone logging microservice or as a starting point for your own Java backend, this shell is designed to be easily extended and modified.

# Architecture
<details>
<summary><b>Click to expand architecture diagram<b></summary>

**Abstract Diagram**

![Abstract Diagram](./diagram/images/AbstractDiagram.png)

**Sequence Diagram**

![Sequence Diagram](./diagram/images/SequenceDiagram.png)

</details>
</br>

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
│   ├── images/
│   │   ├── AbstractDiagram.png
│   │   └── SequenceDiagram.png
│   ├── AbstractDiagram.md
│   └── SequenceDiagram.md
├── docker/                 # Container configuration
│   ├── data/               # Persistent database storage
│   │   ├── logs.db
│   │   └── test_logger.db
│   ├── docker-compose.yml
│   └── Dockerfile
├── src/
│   ├── main/java/logger/
│   │   ├── api/            # API Endpoints (LogController)
│   │   ├── contract/       # Interfaces and orchestration (ILogger, Logger)
│   │   ├── database/       # Database management (Database)
│   │   ├── logic/          # Data models and validation (LogEntry, LogRequest, LogValidation)
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
├── target/                 # Compiled bytecode and reports
├── .env                    # Environment variables (e.g., DATABASE_URL)
├── .gitattributes          # Line ending normalization (LF/CRLF)
├── .gitignore              # Files to be ignored by Git
├── commands.txt            # Cheat sheet for developer commands
├── LICENSE                 # MIT License file
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

### 3: Viewing the Logs
If running via Docker, your database file is persisted on your host machine at `./docker/data/logs.db`.

**Recommended Tools:**
- **VSCode Extension:** [SQLite Viewer](https://marketplace.visualstudio.com/items?itemName=qwtel.sqlite-viewer) (Quickest way to inspect tables)
- **DB Browser for SQLite:** [DB Browser](https://sqlitebrowser.org/) (Professional standalone viewer)

# BDD & Testing
Use Cucumber to ensure the logger behaves exactly as described in our feature files. 

**Sample Feature:**
```Gherkin
Scenario: Successfully log an info message
  Given the logger is initialized
  When I send a POST request to "/log" with message "Test" and level "INFO"
  Then the database should contain 1 entry
```

To run tests locally: `mvn clean test`

# Installation Instructions

### Prerequisites
- **Docker Desktop** (Recommended for easy setup)
- **Java Version 21** (Required for local development)

### Docker Compose

### Configuration
Create a `.env` file in the root directory. You can copy the structure below: 

```ini
# Database URL (Used for local 'mvn run' only)
DATABASE_URL=jdbc:sqlite:C:/[Path]/[To]/[Your]/[Database]/logs.db

# API Key for administrative action.
ADMINKEY=MYPRIVATEADMINKEY
```

**Security:** Don't forget to create `.gitignore` file and add .env! This ensures that you never push anything private to github. 

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



