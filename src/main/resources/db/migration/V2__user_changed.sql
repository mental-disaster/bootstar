ALTER TABLE user ADD auth VARCHAR(32) NOT NULL DEFAULT 'USER';
ALTER TABLE user ADD UNIQUE (username);