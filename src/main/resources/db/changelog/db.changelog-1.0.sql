--liquibase formatted sql

--changeset dmiit3iy:1

CREATE TABLE IF NOT EXISTS tasks
(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    deadline DATE NOT NULL

);
--rollback DROP TABLE tasks;

