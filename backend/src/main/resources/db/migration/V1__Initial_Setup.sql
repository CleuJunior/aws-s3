CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          name TEXT NOT NULL,
                          email TEXT NOT NULL,
                          password TEXT NOT NULL,
                          gender TEXT NOT NULL,
                          birthdate TIMESTAMP NOT NULL
);
