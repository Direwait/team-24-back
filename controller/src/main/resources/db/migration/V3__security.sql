CREATE TABLE if not exists token_white_list (
    token_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    token text NOT NULL
);

CREATE TABLE if not exists refresh_tokens (
    token_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    refresh_token text NOT NULL,
    expires_at TIMESTAMP
);