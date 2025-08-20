-- V2__insert_test_data.sql
-- Вставка тестовых данных с соблюдением порядка зависимостей

-- 1. Сначала вставляем роли (нет зависимостей)
INSERT INTO roles (role_name, viewing_my_requests, viewing_all_requests, creating_requests, creating_admins)
VALUES
    ('ADMIN', TRUE, TRUE, TRUE, TRUE),
    ('MANAGER', TRUE, FALSE, TRUE, FALSE),
    ('SUPER_ADMIN', TRUE, TRUE, TRUE, TRUE);

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

INSERT INTO templates (template_subject, template_body, user_id)
VALUES
    ('Ссылка на заполнение формы',
    '
    <div style="
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        background: #f4f4f6;
        padding: 20px;
      ">
    <div style="
          margin: 30px;
          text-align: center;
        ">
      <svg width="83" height="36" viewBox="0 0 83 36" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M72.7612 8.86615H53.9588V13.0406H61.2759V27.7503H65.4472V13.0438H72.7643V8.86615H72.7612Z"
          fill="black"></path>
        <path d="M80.9811 8.80324H76.6871V27.7063H80.9811V8.80324Z" fill="black"></path>
        <path
          d="M20.0881 15.1294H32.5517V21.5028H20.0881V34.0293H13.7493V21.5028H1.28572V15.1294H13.7493V2.60288H20.0881V15.1294ZM38.8748 2.57143V34.0293H45.2135V2.57143H38.8748Z"
          fill="#12A5DF"></path>
        <defs>
          <clipPath id="clip0_555_597">
            <rect width="79.7143" height="31.4579" fill="white" transform="translate(1.28572 2.57143)">
            </rect>
          </clipPath>
        </defs>
      </svg>
    </div>
    <div style="
          background: #FFF;
          border-radius: 16px;
          box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
          padding: 40px;
          width: 100%;
          max-width: 700px;
        ">
      <div style="
            display: flex;
            flex-direction: column;
          ">
        <div style="
              display: flex;
              flex-direction: column;
              gap: 16px;
              line-height: 1.6;
              color: #333;
              ">
          <h1>Привет!</h1>

          <p>
            Мы рады, что вы приняли наше предложение о работе в Т1!
          </p>
          <p>
            Заполнить регистрационные данные в нашей системе по ссылке:
            <a href="{token}">ССЫЛКА</a>
          </p>

          <p>
            Если у вас возникнут вопросы, обращайтесь – с радостью поможем! С уважением, команда Холдинга
            Т1.
          </p>
        </div>
      </div>
    </div>
  </div>
    ',
    3);

-- 5. Затем SOPD (зависит от users)
INSERT INTO sopd (user_id, sopd_text)
VALUES
    (3, 'Текст стандартной операционной процедуры 1'),
    (3, '<h1 class="greeting" >Привет!</h1>

            <p style="
                        font-size: 16px;
  margin: 0;
  text-align: justify;
                      ">
              ООО «ГК Иннотех» является разработчиком программного обеспечения для собственных нужд и партнеров.
            </p>

            <p style="
                        font-size: 16px;
  margin: 0;
  text-align: justify;
                      ">
              При организации разработки программного обеспечения мы обрабатываем персональные данные лиц, приглашаемых к участию и/или участвующих в разработке ПО, включая как потенциальных кандидатов, так и собственных работников, и представителей сторонних подрядчиков.
            </p>

            <p style="
                        font-size: 16px;
  margin: 0;
  text-align: justify;
                      ">
              Обработка персональных данных осуществляется на условиях политики конфиденциальности, с которой можно ознакомиться <a href="https://inno.tech/ru/data/privacy_policy/#navigation-id9" className={styles.link}>здесь</a>.
            </p>

            <p style="
                        font-size: 16px;
  margin: 0;
  text-align: justify;
                      ">
              Для выполнения требований законодательства РФ в области обработки персональных данных, предлагаем Вам дать согласие на обработку персональных данных (<a href="https://air.inno.tech/docs/AIR_personal_data_agreement_20240904.docx" className={styles.link}>см. здесь</a>), нажав соответствующую кнопку ниже.
            </p>');

-- 6. Затем запросы (зависит от users, candidate, templates, sopd)
INSERT INTO request (user_id, candidate_id, template_id, sopd_id, request_token, request_state)
VALUES
    (4, 1, 1, 1, 'token123', 'PENDING'),
    (4, 2, 1, NULL, 'token456', 'APPROVED');

-- 7. Затем уведомления (зависит от request)
INSERT INTO notification (request_id, notification_text, notification_state)
VALUES
    (1, 'Ваш запрос получен', 'SENT'),
    (2, 'Ваш запрос одобрен', 'READ');