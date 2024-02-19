CREATE TABLE IF NOT EXISTS groups (
    id VARCHAR(36),
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1020) NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX group_name_index ON groups USING HASH (name);