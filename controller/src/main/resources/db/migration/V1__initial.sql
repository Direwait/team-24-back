-- Создание таблицы Roles (роли пользователей)
CREATE TABLE if not exists roles (
    role_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    viewing_my_requests BOOLEAN DEFAULT FALSE,
    viewing_all_Requests BOOLEAN DEFAULT FALSE,
    creating_Requests BOOLEAN DEFAULT FALSE,
    creating_Admins BOOLEAN DEFAULT FALSE
);

-- Создание таблицы User (пользователи)
CREATE TABLE if not exists users (
    user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_id BIGINT NOT NULL REFERENCES roles(role_id),
    user_mail VARCHAR(100) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    user_first_name VARCHAR(50) NOT NULL,
    user_last_name VARCHAR(50) NOT NULL,
    user_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_is_active BOOLEAN DEFAULT TRUE
);

-- Создание таблицы Candidate (кандидаты)
CREATE TABLE if not exists candidate (
    candidate_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    candidate_first_name VARCHAR(50),
    candidate_last_name VARCHAR(50),
    candidate_father_name VARCHAR(50),
    candidate_mail VARCHAR(100),
    candidate_birth_date DATE,
    candidate_phone VARCHAR(20),
    candidate_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы Template (шаблоны писем)
CREATE TABLE if not exists templates (
    template_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT REFERENCES users(user_id),
    template_subject VARCHAR(255) NOT NULL,
    template_body TEXT NOT NULL,
    template_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    template_updated_at TIMESTAMP,
    template_is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE if not exists sopd (
    sopd_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT REFERENCES users(user_id),
    sopd_text TEXT NOT NULL,
    sopd_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sopd_updated_at TIMESTAMP,
    sopd_last_review TIMESTAMP,
    sopd_is_active BOOLEAN DEFAULT TRUE
);

-- Создание таблицы Request (запросы)
CREATE TABLE if not exists request (
    request_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    candidate_id BIGINT NOT NULL REFERENCES candidate(candidate_id),
    template_id BIGINT NOT NULL REFERENCES templates(template_id),
    sopd_id BIGINT REFERENCES sopd(sopd_id),
    request_token VARCHAR(100) NOT NULL UNIQUE,
    request_state VARCHAR(20), --CHECK (request_state IN ('PENDING', 'APPROVED', 'REJECTED', 'EXPIRED')) DEFAULT 'PENDING',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    request_is_active BOOLEAN DEFAULT TRUE
);

-- Создание таблицы Notification (уведомления)
CREATE TABLE if not exists notification (
    notification_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    request_id BIGINT NOT NULL REFERENCES request(request_id),
    notification_text text NOT NULL,
    notification_state VARCHAR(20), --CHECK (notification_state IN ('SENT', 'READ', 'FAILED')) DEFAULT 'SENT',
    notification_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notification_read_at TIMESTAMP NULL
);
