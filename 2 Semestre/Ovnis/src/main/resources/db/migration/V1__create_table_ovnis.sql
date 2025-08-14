CREATE TYPE shape_enum AS ENUM ('CIRCLE', 'SPHERE', 'TRIANGLE', 'OTHER');

CREATE TABLE ovnis (
                        id BIGSERIAL PRIMARY KEY,
                        local VARCHAR(255) NOT NULL,
                        date_time TIMESTAMP NOT NULL,
                        color VARCHAR(50) NOT NULL,
                        shape shape_enum NOT NULL,
                        quantity INTEGER NOT NULL
);
