-- Заполнение таблицы Roles тестовыми данными
INSERT INTO roles (role_name, viewing_my_requests, viewing_all_requests, creating_requests, creating_admins)
VALUES
    ('Администратор', TRUE, TRUE, TRUE, TRUE),
    ('Менеджер', TRUE, FALSE, TRUE, FALSE),
    ('Рекрутер', TRUE, FALSE, TRUE, FALSE);

-- Заполнение таблицы Users тестовыми данными
-- Пароли: 'admin123' и 'manager123' (захешированные BCrypt)
INSERT INTO users (role_id, user_mail, user_password, user_first_name, user_last_name)
VALUES
    (1, 'admin@example.com', '{noop}12345', 'Иван', 'Петров'),
    (2, 'manager@example.com', '$2a$10$qCQY8X3t6SYsMlz5vJ4NUe8YgZRb0N5DdQ2XUzYJg7cV1hQ9LbK0O', 'Анна', 'Сидорова');

-- Заполнение таблицы Templates тестовыми данными
INSERT INTO templates (template_name, template_subject, template_body, template_text)
VALUES
    ('Приглашение на собеседование', 'Приглашение на позицию {{position}}', '<html><body><h1>Уважаемый {{name}}!</h1><p>Мы рады пригласить вас...</p></body></html>', 'Уважаемый {{name}}! Мы рады пригласить вас...'),
    ('Отказ после собеседования', 'Результаты собеседования', '<html><body><h1>Уважаемый {{name}}</h1><p>К сожалению...</p></body></html>', 'Уважаемый {{name}} К сожалению...');

INSERT INTO candidate (candidate_first_name, candidate_last_name, candidate_father_name, candidate_mail, candidate_birth_date, candidate_phone)
VALUES
(NULL, NULL, NULL, 'ivan@example.com', NULL, NULL);