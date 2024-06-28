CREATE TABLE comments (
    id bigserial not null primary key,
    text text,
    created_at timestamp,
    updated_at timestamp,
    FOREIGN KEY (author_id) REFERENCES users(id),
    author_id bigint default null,
    FOREIGN KEY (ad_id) REFERENCES ads(id),
    ad_id bigint default null
)
GO