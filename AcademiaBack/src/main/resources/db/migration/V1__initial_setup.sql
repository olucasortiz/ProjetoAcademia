-- Criação da tabela de usuários
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

-- Criação da tabela de exercícios
CREATE TABLE exercises (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           description TEXT
);

-- Criação da tabela de treinos
CREATE TABLE workouts (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          user_id BIGINT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Criação da tabela de relacionamento entre treino e exercícios
CREATE TABLE workout_exercise (
                                  workout_id INTEGER NOT NULL,
                                  exercise_id INTEGER NOT NULL,
                                  PRIMARY KEY (workout_id, exercise_id),
                                  FOREIGN KEY (workout_id) REFERENCES workouts(id),
                                  FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);
