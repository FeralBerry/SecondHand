ALTER TABLE ads
    drop ads_image_id_fkey
GO

ALTER TABLE ads
    drop ads_user_id_fkey
GO

DROP TABLE ads;

GO