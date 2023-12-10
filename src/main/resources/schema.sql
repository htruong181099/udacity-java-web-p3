CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(30) NOT NULL,
    notes NVARCHAR(255),
    phone_number VARCHAR(14)
);

CREATE TABLE IF NOT EXISTS employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(30) NOT NULL,
    phone_number VARCHAR(14)
);

CREATE TABLE IF NOT EXISTS pets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(30) NOT NULL,
    type VARCHAR(15) NOT NULL,
    birth_date DATE,
    notes NVARCHAR(255),
    owner_id BIGINT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS schedules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    schedule_date DATE NOT NULL,
    activities VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS schedule_pets (
    schedule_id BIGINT NOT NULL,
    pet_id BIGINT NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES schedules (id),
    FOREIGN KEY (pet_id)  REFERENCES pets (id)
);

CREATE TABLE IF NOT EXISTS schedule_employees (
    schedule_id BIGINT NOT NULL,
    employee_id BIGINT NOT NULL,
    FOREIGN KEY (schedule_id)  REFERENCES schedules (id),
    FOREIGN KEY (employee_id)  REFERENCES employees (id)
);

CREATE TABLE IF NOT EXISTS employee_availability (
    employee_id BIGINT NOT NULL,
    availability VARCHAR(100) NOT NULL,
    FOREIGN KEY (employee_id)  REFERENCES employees (id)
);


CREATE TABLE IF NOT EXISTS employee_skills (
    employee_id BIGINT NOT NULL,
    skill_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);