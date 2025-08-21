CREATE TABLE character (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(40) NOT NULL CHECK (char_length(name) >= 3),
                           species_group VARCHAR(20) NOT NULL CHECK (species_group ~ '^(HUMAN|CYBORG|ALIEN|ANDROID)$'),
    starting_level BIGINT NOT NULL CHECK (starting_level BETWEEN 1 AND 100),
    bio VARCHAR(280),
    creation_date DATE NOT NULL CHECK (creation_date <= CURRENT_DATE)
);
