# API Shell

[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
[![JUnit5](https://img.shields.io/badge/JUnit5-C21325?logo=junit5&logoColor=fff)](#)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)](#)
![ReadMe](https://img.shields.io/badge/ReadMe-018EF5?logo=readme&logoColor=fff)
![License](https://img.shields.io/badge/License-MIT-green)

# Highlights

This project was created as an easier way to handle all logs that usually gets lost or gets ignored but which might hold value further on during later development cycles. 

# Overview

# Author

I'm Jonathan and I develop projects in my sparetime that help myself and others become better and more efficient developers!
- [Linkedin](https://www.linkedin.com/in/jonathan-windell-418a55232/)
- [Portfolio](https://portfolio.jonathans-labb.org/)


# Project Structure

```text
├── .github/
│   └── workflows/      # CI/CD Pipelines (GitHub Actions)
├── src/java/logger
│       ├── contract/      # Authentication logic & User Manager
│       ├── database/      # Core infrastructure (Config, Database, Exceptions)
│       ├── logic/         # Pydantic Schemas & Data Models
│       ├── repository/    # API Routes/Endpoints
|   ├── src/test/   # Test suite (JUnit & Cucumber - Gherkin)

|   ├── database/      # Authentication logic & User Manager
    ├── runners/      # Core infrastructure (Config, Database, Exceptions)
    ├── steps/
|
|
|   
├── .env                # Environment variables (Configuration)
├── docker-compose.yml  # Docker services setup
├── Dockerfile          # Container definition
├── main.py             # Application entry point
└── requirements.txt    # Python dependencies
```

# Usage Instructions

# Installation Instructions

### Prerequisites
- Docker Desktop (Recommended)
- Java SDK

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

