
CREATE DATABASE arcadia;
\c arcadia
CREATE USER arcadia;
ALTER USER arcadia WITH ENCRYPTED PASSWORD 'password';
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON sequences TO arcadia;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON tables TO arcadia;

CREATE SEQUENCE IF NOT EXISTS hours_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS hours (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('hours_seq'),
    monday varchar (100) NOT NULL,
    tuesday varchar (100) NOT NULL,
    wednesday varchar (100) NOT NULL,
    thursday varchar (100) NOT NULL,
    friday varchar (100) NOT NULL,
    saturday varchar (100) NOT NULL,
    sunday varchar (100) NOT NULL
);
ALTER SEQUENCE hours_seq OWNED BY hours.id;

CREATE SEQUENCE IF NOT EXISTS service_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS service (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('service_seq'),
    name varchar (100) NOT NULL,
    description text NOT NULL
);
ALTER SEQUENCE service_seq OWNED BY service.id;

CREATE SEQUENCE IF NOT EXISTS view_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS view (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('view_seq'),
    pseudo varchar (100) NOT NULL,
    comment text NOT NULL,
    is_Visible bool NOT NULL
);
ALTER SEQUENCE view_seq OWNED BY view.id;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('users_seq'),
    email varchar (100) unique NOT NULL,
    password varchar (100) NOT NULL,
    lastname varchar (100) NOT NULL,
    firstname varchar (100) NOT NULL,
    role int NOT NULL
);
ALTER SEQUENCE users_seq OWNED BY users.id;

CREATE SEQUENCE IF NOT EXISTS breed_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS breed (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('breed_seq'),
    label varchar (100) NOT NULL
);
ALTER SEQUENCE breed_seq OWNED BY breed.id;

CREATE SEQUENCE IF NOT EXISTS food_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS food (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('food_seq'),
    name varchar (100) NOT NULL
);
ALTER SEQUENCE food_seq OWNED BY food.id;

CREATE SEQUENCE IF NOT EXISTS habitat_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS habitat (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('habitat_seq'),
    name varchar (100) NOT NULL,
    description text NOT NULL,
    comment text NOT NULL
);
ALTER SEQUENCE habitat_seq OWNED BY habitat.id;

CREATE SEQUENCE IF NOT EXISTS image_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS image (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('image_seq'),
    hash varchar (255) NOT NULL,
    image_name varchar (100) NOT NULL
);
ALTER SEQUENCE image_seq OWNED BY image.id;

CREATE SEQUENCE IF NOT EXISTS animal_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS animal (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('animal_seq'),
    firstname varchar (100) NOT NULL,
    health text NOT NULL,
    id_habitat int NOT NULL,
    id_breed int NOT NULL,
    FOREIGN KEY (id_habitat) REFERENCES habitat,
    FOREIGN KEY (id_breed) REFERENCES breed
);
ALTER SEQUENCE animal_seq OWNED BY animal.id;

CREATE SEQUENCE IF NOT EXISTS veterinary_report_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS veterinary_report (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('veterinary_report_seq'),
    grammage int NOT NULL,
    detail text NOT NULL,
    date date NOT NULL,
    id_animal int NOT NULL,
    id_food int NOT NULL,
    id_user int NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES animal,
    FOREIGN KEY (id_food) REFERENCES food,
    FOREIGN KEY (id_user) REFERENCES users
);
ALTER SEQUENCE veterinary_report_seq OWNED BY veterinary_report.id;

CREATE TABLE IF NOT EXISTS animal_image (
    id_animal int NOT NULL ,
    id_image int NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES animal,
    FOREIGN KEY (id_image) REFERENCES image,
    PRIMARY KEY (id_animal, id_image)
);

CREATE TABLE IF NOT EXISTS habitat_image (
    id_habitat int NOT NULL ,
    id_image int NOT NULL,
    FOREIGN KEY (id_habitat) REFERENCES habitat,
    FOREIGN KEY (id_image) REFERENCES image,
    PRIMARY KEY (id_habitat, id_image)
);

CREATE TABLE IF NOT EXISTS food_animal (
    id_food int NOT NULL,
    id_animal int NOT NULL,
    FOREIGN KEY (id_food) REFERENCES food,
    FOREIGN KEY (id_animal) REFERENCES animal,
    PRIMARY KEY (id_food, id_animal)
);

CREATE SEQUENCE IF NOT EXISTS token_seq START WITH 1 INCREMENT BY 50 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE TABLE IF NOT EXISTS token (
	id BIGINT PRIMARY KEY DEFAULT NEXTVAL('token_seq') NOT NULL,
	token varchar(255) unique NULL,
	expired bool NOT NULL,
	revoked bool NOT NULL,
	token_type int NULL,
	id_user BIGINT NULL,
	CONSTRAINT token_token_type_check CHECK (((token_type >= 0) AND (token_type <= 0)))
);
ALTER SEQUENCE token_seq OWNED BY token.id;

INSERT INTO users
("role", email, firstname, lastname, "password")
VALUES(2, 'admin@test.com', 'José', 'Duseau', '$2a$10$hTc/.Qpwe0I/hSwwmBXX1eQvHI8aqvGlxJ7VKXE5SKkkrYLibsYwa');
