## Student Management App - H2 DB SQL Queries & Postman Guide

---

### **H2 DB Connection**
- **URL:** jdbc:h2:mem:studentdb
- **Username:** sa
- **Password:** (leave empty)
- **H2 Console:** http://localhost:2025/h2-console

---

### **Table Structure**
```sql
CREATE TABLE STUDENT (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(255),
  EMAIL VARCHAR(255),
  DEPARTMENT VARCHAR(255)
);
```

---

### **SQL Queries for CRUD Operations (can be run in H2 console)**

#### 1. Get All Students
```sql
SELECT * FROM STUDENT;
```

#### 2. Get Student by ID
```sql
SELECT * FROM STUDENT WHERE ID=1;
```

#### 3. Add Student
```sql
INSERT INTO STUDENT (NAME, EMAIL, DEPARTMENT) VALUES ('John Doe', 'john@mail.com', 'CS');
```

#### 4. Update Student
```sql
UPDATE STUDENT SET NAME='John Updated', EMAIL='johnupdated@mail.com', DEPARTMENT='IT' WHERE ID=1;
```

#### 5. Delete Student
```sql
DELETE FROM STUDENT WHERE ID=1;
```

#### 6. Delete All Students
```sql
DELETE FROM STUDENT;
```

#### 7. Update All Students (example: update department for all)
```sql
UPDATE STUDENT SET DEPARTMENT='Updated Dept';
```

---

### **Postman API URLs & JSON Body Examples**

**Base URL:** http://localhost:2025/api/students

| Operation        | Method | URL                    | Body Example (JSON) |
|-----------------|--------|------------------------|-------------------|
| Get all students | GET    | /api/students          | None              |
| Get by ID        | GET    | /api/students/1        | None              |
| Add student      | POST   | /api/students          | {"name":"John","email":"john@mail.com","department":"CS"} |
| Update student   | PUT    | /api/students/1        | {"name":"John Updated","email":"johnupdated@mail.com","department":"IT"} |
| Delete student   | DELETE | /api/students/1        | None              |
| Delete all       | DELETE | /api/students          | None              |
| Update all       | PUT    | /api/students          | {"department":"Updated Dept"} |

---

**Instructions:**
1. Start Spring Boot app.
2. Use H2 console to inspect DB or run SQL queries.
3. Use Postman to call APIs; changes reflect in H2 in real time.
4. Refresh H2 console to see updates made via Postman.

---

End of file.

