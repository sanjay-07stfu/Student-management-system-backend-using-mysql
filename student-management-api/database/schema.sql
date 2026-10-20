CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- Spring Data JPA creates/updates the users and students tables at application startup
-- because spring.jpa.hibernate.ddl-auto=update is set in application.properties.
-- For production, use versioned database migrations instead.
