
---

# Step 3: Dockerization – StudentApp

This step explains how to **dockerize the StudentApp**, use **persistent H2 storage**, and run it using **Docker Compose**.

---

## **Overview**

Docker allows us to **package the application and all dependencies** into a container that can run anywhere.
We are using:

* **Dockerfile** – Defines how to build the StudentApp image.
* **Docker Compose** – Simplifies container management and enables persistent storage.
* **Persistent H2 database** – Keeps data even if the container stops.

---

## **Folder Structure**

```
studentapp/
├── .mvn/
├── src/
├── target/
│   ├── studentapp-0.0.1-SNAPSHOT.jar
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## **1. Dockerfile Explanation**

```dockerfile
# Base image: Java 21 JDK
FROM eclipse-temurin:21-jdk-alpine

# Install bash (required for mvnw)
RUN apk add --no-cache bash

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build application inside container
RUN ./mvnw clean package -DskipTests

# Expose the port (must match application.properties)
EXPOSE 2025

# Run the application
CMD ["java", "-jar", "target/studentapp-0.0.1-SNAPSHOT.jar"]
```

**Explanation:**

1. Uses lightweight **Alpine Linux** with Java 21.
2. Installs `bash` for Maven wrapper.
3. Copies Maven wrapper and project files.
4. Builds the `.jar` inside the container.
5. Exposes **port 2025**.
6. Runs the app using the generated JAR.

---

## **2. Docker Compose (docker-compose.yml)**

```yaml
services:
  studentapp:
    image: studentapp:latest
    container_name: studentapp
    ports:
      - "2025:2025"
    volumes:
      - studentapp-data:/app/data

volumes:
  studentapp-data:
```

**Explanation:**

* `studentapp-data` → Docker-managed volume for **persistent H2 DB**.
* Maps container port `2025` to host `2025`.
* Works on **Windows, Linux, and Mac**.

---

## **3. How to Build and Run**

## **Step 1: Build Docker Image (Optional if using Compose)**

```cmd
docker build -t studentapp:latest .
```

* Builds the container image from Dockerfile.

---

## **Step 2: Run Container via Docker Compose**

From the project root:

```cmd
docker-compose up -d
```

* Automatically uses local image.
* Creates persistent volume `studentapp-data`.
* Runs container in detached mode.

---

## **Step 3: Stop / Remove Container**

Stop container:

```cmd
docker-compose down
```

* Stops the container but **volume persists**, so DB data is safe.

---

## **Step 4: Check Logs**

```cmd
docker-compose logs -f
```

* Follow real-time logs of the app.

---

## **Step 5: Test Application**

* **API Base URL:** `http://localhost:2025/api/students`
* **H2 Console:** `http://localhost:2025/h2-console`

**H2 JDBC URL:**

```properties
jdbc:h2:file:./data/studentdb
```

* User: `sa`
* Password: *(leave empty)*

---

## **4. Tips**

* To **completely remove all data**, delete the Docker volume:

```cmd
docker volume rm studentapp-data
```

* To rebuild the container after changes:

```cmd
docker-compose down
docker build -t studentapp:latest .
docker-compose up -d
```

* Always use Docker Compose for **cross-platform compatibility**.

---

