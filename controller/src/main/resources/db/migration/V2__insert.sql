-- V2__insert_test_data.sql
-- Вставка тестовых данных с соблюдением порядка зависимостей

-- 1. Сначала вставляем роли (нет зависимостей)
INSERT INTO roles (role_name, viewing_my_requests, viewing_all_requests, creating_requests, creating_admins)
VALUES
    ('ADMIN', TRUE, TRUE, TRUE, TRUE),
    ('MANAGER', TRUE, FALSE, TRUE, FALSE),
    ('Рекрутер', TRUE, FALSE, TRUE, FALSE);

-- 2. Затем пользователей (зависит от roles)
INSERT INTO users (role_id, user_mail, user_password, user_first_name, user_last_name, user_is_active)
VALUES
    (1, 'admin@example.com', '$2a$04$gDtS8LWULFmjx5L5Grcjn./ysrrZSLNqqmOiHe9EsYUZtsIJm0aXm', 'Иван', 'Петров', true),
    (2, 'manager@example.com', '$2a$10$qCQY8X3t6SYsMlz5vJ4NUe8YgZRb0N5DdQ2XUzYJg7cV1hQ9LbK0O', 'Анна', 'Сидорова', true),
    (1, 'admin@mail.ru','$2a$10$Yay7Hy2bSG2wk0h.52shielt.fYS0lXP9CnMwXnIgTJcXu3pnzs4y', 'admin_first_name','admin_last_name', true),
    (2, 'manager@mail.ru','$2a$10$jbO7qo9pRXkRapl6YVkO7.WHOnT9DxR/tbIrzb6M5.3l807Ln1TZ.', 'manager_first_name','manager_last_name', true),
    (1, 'admin2@mail.ru','$2a$10$Yay7Hy2bSG2wk0h.52shielt.fYS0lXP9CnMwXnIgTJcXu3pnzs4y', 'admin_first_name','admin_last_name', true),
    (2, 'manager2@mail.ru','$2a$10$jbO7qo9pRXkRapl6YVkO7.WHOnT9DxR/tbIrzb6M5.3l807Ln1TZ.', 'manager_first_name','manager_last_name', true);



-- 3. Затем кандидатов (нет зависимостей)
INSERT INTO candidate (candidate_first_name, candidate_last_name, candidate_father_name, candidate_mail, candidate_birth_date, candidate_phone)
VALUES
    ('Сергей', 'Иванов', 'Петрович', 'sergey@example.com', '1990-05-15', '+79161234567'),
    (NULL, NULL, NULL, 'ivan@example.com', NULL, NULL);

INSERT INTO templates (template_name, template_subject, template_body, user_id)
VALUES
    ('Приглашение на собеседование',
     'Приглашение на позицию Дворник',
     '
     <table width="100%" border="0" cellspacing="0" cellpadding="0" style="max-width: 600px; margin: 0 auto; font-family: Arial, sans-serif;">
             <tr>
                 <td style="padding: 40px; background: #ffffff; border-radius: 16px;">
                     <h1 style="color: #20222c; font-size: 32px; margin: 0 0 20px 0; line-height: 1.2;">
                         Привет!
                     </h1>
                     <p style="font-size: 16px; line-height: 1.5; margin: 0 0 16px 0; color: #333333;">
                         Мы рады, что вы приняли наше предложение о работе в Т1!
                     </p>
                     <p style="font-size: 16px; line-height: 1.5; margin: 0 0 16px 0; color: #333333;">
                         Заполнить регистрационные данные в нашей системе по ссылке:
                         <a href="{token}" style="color: #1281E2; text-decoration: none; font-weight: 600;">ССЫЛКА</a>
                     </p>
                     <p style="font-size: 16px; line-height: 1.5; margin: 0; color: red;">
                         Если у вас возникнут вопросы, обращайтесь – с радостью поможем!<br>
                         С уважением, команда Холдинга Т1.
                     </p>
                 </td>
             </tr>
         </table>
     ',
     1);

-- 5. Затем SOPD (зависит от users)
INSERT INTO sopd (user_id, sopd_text)
VALUES
    (1, 'Текст стандартной операционной процедуры 1'),
    (2, 'Текст стандартной операционной процедуры 2');

-- 6. Затем запросы (зависит от users, candidate, templates, sopd)
INSERT INTO request (user_id, candidate_id, template_id, sopd_id, request_token, request_state)
VALUES
    (1, 1, 1, 1, 'token123', 'PENDING'),
    (2, 2, 1, NULL, 'token456', 'APPROVED');

-- 7. Затем уведомления (зависит от request)
INSERT INTO notification (request_id, notification_text, notification_state)
VALUES
    (1, 'Ваш запрос получен', 'SENT'),
    (2, 'Ваш запрос одобрен', 'READ');