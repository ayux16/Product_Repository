CREATE TABLE category
(
    id                   INT          NOT NULL,
    created_at           datetime     NULL,
    updated_at           datetime     NULL,
    is_deleted           BIT(1)       NOT NULL,
    title                VARCHAR(255) NULL,
    created_by_user_name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE category_seq
(
    next_val BIGINT NULL
);

CREATE TABLE flyway_schema_history
(
    installed_rank INT                     NOT NULL,
    version        VARCHAR(50)             NULL,
    `description`  VARCHAR(200)            NOT NULL,
    type           VARCHAR(20)             NOT NULL,
    script         VARCHAR(1000)           NOT NULL,
    checksum       INT                     NULL,
    installed_by   VARCHAR(100)            NOT NULL,
    installed_on   timestamp DEFAULT NOW() NOT NULL,
    execution_time INT                     NOT NULL,
    success        TINYINT(1)              NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (installed_rank)
);

CREATE TABLE products
(
    id                   INT          NOT NULL,
    created_at           datetime     NULL,
    updated_at           datetime     NULL,
    is_deleted           BIT(1)       NOT NULL,
    title                VARCHAR(255) NOT NULL,
    `description`        VARCHAR(255) NULL,
    image_url            VARCHAR(255) NULL,
    category_id          INT          NULL,
    created_by_user_name VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE products_seq
(
    next_val BIGINT NULL
);

CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history (success);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION;

CREATE INDEX FK_PRODUCTS_ON_CATEGORY ON products (category_id);