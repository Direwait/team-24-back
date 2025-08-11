-- Создание таблицы Roles (роли пользователей)
CREATE TABLE roles (
    role_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    viewing_my_requests BOOLEAN DEFAULT FALSE,
    viewing_all_Requests BOOLEAN DEFAULT FALSE,
    creating_Requests BOOLEAN DEFAULT FALSE,
    creating_Admins BOOLEAN DEFAULT FALSE
);

-- Создание таблицы User (пользователи)
CREATE TABLE users (
    user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_id BIGINT NOT NULL REFERENCES roles(role_id),
    user_mail VARCHAR(100) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    user_first_name VARCHAR(50) NOT NULL,
    user_last_name VARCHAR(50) NOT NULL,
    user_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы Candidate (кандидаты)
CREATE TABLE candidate (
    candidate_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    candidate_first_name VARCHAR(50),
    candidate_last_name VARCHAR(50),
    candidate_father_name VARCHAR(50),
    candidate_mail VARCHAR(100),
    candidate_birth_date DATE,
    candidate_phone VARCHAR(20),
    candidate_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы Template (шаблоны писем и уведомленй,)
CREATE TABLE templates (
    template_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    template_name VARCHAR(100) NOT NULL UNIQUE,
    template_subject VARCHAR(255) NOT NULL,
    template_body TEXT NOT NULL,
    template_text TEXT NOT NULL
);

-- Создание таблицы Request (запросы)
CREATE TABLE request (
    request_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    candidate_id BIGINT NOT NULL REFERENCES candidate(candidate_id),
    template_id BIGINT NOT NULL REFERENCES templates(template_id),
    request_token VARCHAR(100) NOT NULL UNIQUE,
    request_state VARCHAR(20), --CHECK (request_state IN ('PENDING', 'APPROVED', 'REJECTED', 'EXPIRED')) DEFAULT 'PENDING',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы Notification (уведомления)
CREATE TABLE notification (
    notification_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    request_id BIGINT NOT NULL REFERENCES request(request_id),
    notification_state VARCHAR(20), --CHECK (notification_state IN ('SENT', 'READ', 'FAILED')) DEFAULT 'SENT',
    notification_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notification_read_at TIMESTAMP NULL
);
