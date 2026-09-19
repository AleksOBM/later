-- =========================================================
-- users
-- =========================================================
CREATE TABLE IF NOT EXISTS users (
    id                 BIGSERIAL PRIMARY KEY,
    email              VARCHAR(255),
    first_name         VARCHAR(255) NOT NULL,
    last_name          VARCHAR(255),
    registration_date  TIMESTAMP,
    state              VARCHAR(255)
);

-- =========================================================
-- items
-- =========================================================
CREATE TABLE IF NOT EXISTS items (
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT,
    url            VARCHAR(255),
    resolved_url   VARCHAR(255),
    mime_type      VARCHAR(255),
    title          VARCHAR(255),
    has_image      BOOLEAN NOT NULL DEFAULT FALSE,
    has_video      BOOLEAN NOT NULL DEFAULT FALSE,
    unread         BOOLEAN NOT NULL DEFAULT TRUE,
    date_resolved  TIMESTAMP,

    CONSTRAINT fk_items_user
        FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_items_user_id ON items (user_id);

-- =========================================================
-- tags (ElementCollection у Item)
-- =========================================================
CREATE TABLE IF NOT EXISTS tags (
    item_id  BIGINT       NOT NULL,
    name     VARCHAR(255),

    CONSTRAINT fk_tags_item
        FOREIGN KEY (item_id)
        REFERENCES items (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_tags_item_id ON tags (item_id);

-- =========================================================
-- item_notes
-- =========================================================
CREATE TABLE IF NOT EXISTS item_notes (
    id         BIGSERIAL PRIMARY KEY,
    item_id    BIGINT,
    text       VARCHAR(255),
    note_date  TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_item_notes_item
        FOREIGN KEY (item_id)
        REFERENCES items (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_item_notes_item_id ON item_notes (item_id);