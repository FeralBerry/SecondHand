CREATE TYPE "role_type_enum" AS ENUM('user', 'admin');
CREATE TABLE users (
    id bigserial primary key,
    FOREIGN KEY (avatar_id) REFERENCES images(id),
    avatar_id bigint,
    username varchar(255) not null unique,
    password varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone varchar(255),
    role role_type_enum not null
)
GO