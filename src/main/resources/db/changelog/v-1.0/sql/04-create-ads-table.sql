CREATE TABLE ads (
    id bigserial primary key,
    title varchar(255) not null,
    description text not null,
    price integer not null,
    FOREIGN KEY (image_id) REFERENCES images(id),
    image_id bigint default null,
    FOREIGN KEY (user_id) REFERENCES users(id),
    user_id bigint default null
)
GO