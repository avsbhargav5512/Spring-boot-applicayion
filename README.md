# Spring Boot CI/CD Practice Project

A lightweight Spring Boot 3.4 (Java 21) REST application designed for practicing CI/CD pipelines, specifically **Jenkins Freestyle Projects**.

---

## 🚀 Quick Overview

- **Java Version:** 21
- **Build Tool:** Apache Maven
- **Endpoints:**
  - `GET /` — Welcome message
  - `GET /api/hello` (optional query: `?name=YourName`) — Greeting JSON
  - `GET /api/health` — Application health check JSON (`{"status": "UP", ...}`)
- **Testing:** JUnit 5 + Spring Boot Test (`MockMvc`), producing standard surefire XML test reports.

---

## 🛠️ Local Build & Run

### 1. Run Tests:
```bash
mvn clean test
```
*Test reports are generated in `target/surefire-reports/*.xml`.*

### 2. Package the Application:
```bash
mvn clean package
```
*Creates the executable `.jar` in `target/spring-boot-cicd-practice-0.0.1-SNAPSHOT.jar`.*

### 3. Run the Application:
```bash
java -jar target/spring-boot-cicd-practice-0.0.1-SNAPSHOT.jar
```
*Note: Configured to port `8085` so it does not conflict with Jenkins running on default port `8080`.*

Access endpoints at:
- http://localhost:8085/
- http://localhost:8085/api/hello?name=Jenkins
- http://localhost:8085/api/health

---

## 🏗️ Jenkins Freestyle Project Step-by-Step Setup

### Step 1: Create the Job
1. In Jenkins dashboard, click **New Item**.
2. Enter a name (e.g. `spring-boot-cicd-practice`).
3. Select **Freestyle project** and click **OK**.

### Step 2: Source Code Management (SCM)
1. Select **Git**.
2. Enter your Repository URL (GitHub, GitLab, or local git path).
3. Under **Branches to build**, specify `*/main` or `*/master`.

### Step 3: Build Triggers (Optional)
- Check **GitHub hook trigger for GITScm polling** (for automated webhook builds), or
- Check **Poll SCM** with schedule (e.g., `H/5 * * * *` for every 5 minutes).

### Step 4: Build Steps
Click **Add build step**:

#### Option A: If using "Invoke top-level Maven targets" (Recommended if Maven is configured in Jenkins Tools):
- **Maven Version:** Select your configured Maven.
- **Goals:** `clean package`

#### Option B: If running on Windows Jenkins agent:
- Add build step: **Execute Windows batch command**
- Command:
  ```cmd
  mvn.cmd clean package
  ```

#### Option C: If running on Linux/Docker Jenkins agent:
- Add build step: **Execute shell**
- Command:
  ```bash
  mvn clean package
  ```

---

### Step 5: Post-build Actions

Click **Add post-build action**:

#### 1. Publish JUnit test result report
- **Test report XMLs:**
  ```
  **/target/surefire-reports/*.xml
  ```
- *Why:* Jenkins will automatically generate test result trend charts, test history, and alert on failures.

#### 2. Archive the artifacts
- **Files to archive:**
  ```
  target/*.jar
  ```
- *Why:* Keeps the generated runnable JAR on Jenkins so you can download or deploy it.

---

## 🐳 Optional: Docker Containerization

To build a container image directly from the workspace:
```bash
docker build -t spring-boot-cicd-practice:latest .
docker run -p 8080:8080 spring-boot-cicd-practice:latest
```
