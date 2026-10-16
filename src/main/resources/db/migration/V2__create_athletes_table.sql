CREATE TABLE athletes (
                          id BIGSERIAL PRIMARY KEY,
                          full_name VARCHAR(100) NOT NULL,
                          gender VARCHAR(10) NOT NULL,
                          photo_url VARCHAR(500),
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);