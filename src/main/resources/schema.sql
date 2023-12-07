CREATE TABLE IF NOT EXISTS pets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(30) NOT NULL,
    type VARCHAR(15) NOT NULL,
    birthDate DATE,
    notes NVARCHAR(255),
    owner_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS schedules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    activities VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(30) NOT NULL,
    notes NVARCHAR(255),
    phone_number VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(30) NOT NULL,
    phone_number VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS employee_skills (
    skill_name VARCHAR(50) NOT NULL,
    employee_id BIGINT NOT NULL,

);

ALTER TABLE employee_skills
ADD FOREIGN KEY (employee_id) REFERENCES employees (employee_id);

ALTER TABLE pets
ADD FOREIGN KEY (owner_id) REFERENCES customers(id);