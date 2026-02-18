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

# Usage Instructions

### 1: Starting the Docker Container

### 2: How to Create own Log Messages

### 3: How to View Logs in Database


# Installation Instructions

### Prerequisites
- **Docker Desktop** (Recommended)
- **Java Version 21**
- **Javac Version 21**

### Docker Compose

### Configuration
Create a `.env` file in the root directory. You can copy the structure below:

```ini
DATABASE_URL=jdbc:sqlite:C:/[Path]/[To]/[Your]/[Database]/logs.db
```

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




/**
 * Create sqlite database resource & table
 * https://www.sqlitetutorial.net/sqlite-java/create-database/
 * https://www.sqlitetutorial.net/sqlite-java/create-table/
 * https://www.sqlitetutorial.net/sqlite-java/insert/
 */ 

