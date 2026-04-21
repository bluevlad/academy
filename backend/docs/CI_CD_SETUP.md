# CI/CD Architecture & Security Setup Guide

## 1. Overview
This project uses a **Self-hosted GitHub Actions Runner** to deploy the Spring Boot backend directly to a local Windows environment running Docker Desktop.

- **Source Code**: GitHub Repository
- **Runner**: Local Windows PC (Self-hosted)
- **Containerization**: Docker & Docker Compose
- **Target environment**: Local Docker container (`academy-backend`)

## 2. Security Configuration (CRITICAL)
To prevent credential leaks, the following security measures are enforced:

### 2.1. Git Ignore Rules
The following directories and files are excluded from version control via `.gitignore` and **must never be forcibly added**:
- `/actions-runner/`: Contains the runner's unique identity and authentication tokens (`.credentials`).
- `.env`: Contains runtime environment variables (DB passwords, etc.).

### 2.2. Secrets Management
Sensitive information is strictly managed using **GitHub Secrets**:
- **Settings Path**: `Settings` > `Security` > `Secrets and variables` > `Actions`
- **Required Secrets**:
  - `DB_URL`: JDBC URL for the database
  - `DB_USERNAME`: Database username
  - `DB_PASSWORD`: Database password

These secrets are injected into a `.env` file only during the workflow execution.

## 3. Self-hosted Runner Setup Instructions
If the runner needs to be re-installed or moved to a new machine:

1.  **Navigate to GitHub**:
    - Go to `Settings` > `Actions` > `Runners` > `New self-hosted runner`.
    - Select **Windows** architecture (x64).

2.  **Installation (PowerShell)**:
    ```powershell
    # Create a clean folder (e.g., C:\actions-runner) - Do NOT put this inside the Git repo if possible, 
    # or ensure it is ignored.
    mkdir actions-runner; cd actions-runner
    
    # Download the latest runner package (Check GitHub for exact URL)
    Invoke-WebRequest -Uri https://github.com/actions/runner/releases/download/v2.x.x/actions-runner-win-x64-2.x.x.zip -OutFile actions-runner.zip
    
    # Extract
    Add-Type -AssemblyName System.IO.Compression.FileSystem ; [System.IO.Compression.ZipFile]::ExtractToDirectory("$PWD/actions-runner.zip", "$PWD")
    ```

3.  **Configuration**:
    ```powershell
    # Run the config command provided by GitHub (Token is temporary)
    ./config.cmd --url https://github.com/bluevlad/academy-back-end-JavaSpring --token <DISPLAYED_TOKEN_ON_GITHUB>
    ```
    - Run as Service (Optional): `./run.cmd` to run interactively, or configure as a Windows Service to run in background.

## 4. Deployment Workflow
The pipeline is defined in `.github/workflows/deploy-prod.yml`.

**Trigger**: Push to `prod` branch.

**Job Steps**:
1.  **Checkout Code**: Pulls the latest code.
2.  **Generate Configuration**: Creates `.env` file using GitHub Secrets.
3.  **Deploy**:
    - `docker-compose down --remove-orphans`: Stops running containers.
    - `docker-compose up -d --build`: Rebuilds and starts the updated application.
