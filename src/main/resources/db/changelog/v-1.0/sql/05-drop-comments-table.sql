ALTER TABLE comments
    drop comments_ad_id_fkey
GO

ALTER TABLE comments
    drop comments_author_id_fkey
GO

DROP TABLE comments;

GO

