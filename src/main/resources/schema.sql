CREATE TABLE IF NOT EXISTS course (
    course_id VARCHAR(10) PRIMARY KEY,
    subject_id VARCHAR(10) UNIQUE,
    course_number VARCHAR(10) UNIQUE,
    title VARCHAR(10) NOT NULL,
    number_of_credit BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS student (
    ssn VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(25) NOT NULL,
    middle_initial CHAR(1),
    last_name VARCHAR(25) NOT NULL,
    phone VARCHAR(15) UNIQUE,
    birth_date DATE,
    street VARCHAR(225) NOT NULL,
    zip_code CHAR(5) NOT NULL,
    dept_id  CHAR(4) NOT NULL
);

CREATE TABLE IF NOT EXISTS enrollment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_registered TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    grade CHAR,
    ssn VARCHAR(20) REFERENCES student(ssn) ON DELETE CASCADE,
    course_id VARCHAR(10) REFERENCES course(course_id) ON DELETE CASCADE
);
