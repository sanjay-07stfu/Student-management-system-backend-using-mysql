# Student Management REST API

FSJP Assignment 2 implementation using Java 21, Spring Boot, Spring Security, JWT, Spring Data JPA, MySQL, and Postman.

## Features
- Register and login APIs
- JWT generated on successful login
- Passwords hashed using BCrypt
- All `/api/students` routes require a valid Bearer token
- Add, list, retrieve, update, and delete students
- Validation for required fields, email, and year
- Postman collection included

## 1. Prerequisites
- JDK 21
- Maven 3.6+
- MySQL Server
- Postman

Check:
```bash
java -version
mvn -version
```

## 2. Create database
Log in to MySQL and run:
```sql
CREATE DATABASE student_management;
```
You can also run `database/schema.sql`. Hibernate creates the tables at startup.

## 3. Configure database password
Open `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your MySQL root password.
Alternatively set environment variables:
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET` (use a private random secret of at least 32 bytes)

Do not commit real passwords or JWT secrets to GitHub.

## 4. Run
From the folder containing `pom.xml`:
```bash
mvn spring-boot:run
```
The API starts at `http://localhost:8080`.

## 5. Test in Postman
Import `postman/Student-Management-API.postman_collection.json`.

### Register
`POST http://localhost:8080/api/auth/register`
```json
{"username":"sanjay","password":"password123"}
```
Expected: `201 Created`.

### Login
`POST http://localhost:8080/api/auth/login`
```json
{"username":"sanjay","password":"password123"}
```
Expected: `200 OK` and a `token`. The included Postman collection saves the token automatically after a successful login.

### Student endpoints (include Authorization: Bearer <token>)
| Method | URL | Purpose |
|---|---|---|
| GET | `/api/students` | List all students |
| GET | `/api/students/1` | Get student by ID |
| POST | `/api/students` | Add student |
| PUT | `/api/students/1` | Update student |
| DELETE | `/api/students/1` | Delete student |

Add student JSON:
```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "branch": "Information Technology",
  "year": 2
}
```

Update uses the same JSON shape. `year` must be a positive integer.

## 6. Verify JWT protection
1. Call `GET /api/students` without an Authorization header: expect `401 Unauthorized`.
2. Login and copy the returned token (or use the collection's saved `{{token}}` variable).
3. Send `Authorization: Bearer <token>` and retry: expect `200 OK`.

## 7. Suggested screenshots for submission
Capture:
1. Register response (`201 Created`)
2. Login response with token (hide the token if sharing publicly)
3. Get all students (`200 OK`)
4. Add student (`201 Created`)
5. Get student by ID (`200 OK`)
6. Update student (`200 OK`)
7. Delete student (`204 No Content`)
8. Request without token (`401 Unauthorized`)

## 8. GitHub
Create a new repository, then from this folder:
```bash
git init
git add .
git commit -m "Build Student Management REST API with JWT"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/student-management-api.git
git push -u origin main
```
Replace the URL with your actual repository URL. Ensure your actual DB password is not committed.

## Common errors
- **Access denied for user**: check MySQL username/password.
- **Unknown database**: create `student_management`.
- **Port 8080 already in use**: change `server.port` in `application.properties`.
- **401 Unauthorized**: login again and send `Authorization: Bearer <token>`.
- **Email already exists**: use a unique email for each student.
- **JWT secret too short**: use at least 32 bytes.
