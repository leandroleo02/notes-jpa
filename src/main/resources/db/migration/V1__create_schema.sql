CREATE TABLE notes
(
    id       UUID PRIMARY KEY,
    title    TEXT NOT NULL,
    category TEXT NOT NULL,
    text     TEXT NOT NULL
);
