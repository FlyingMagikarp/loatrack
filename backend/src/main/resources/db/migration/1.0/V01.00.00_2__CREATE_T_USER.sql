CREATE TABLE public."t_user"
(
    "id" uuid NOT NULL,
    "name" character varying(20) NOT NULL,
    "password" character varying(1024) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."t_user"
    OWNER to postgres;

INSERT INTO "t_user"
VALUES ('4a3574a2-b173-47b4-a08a-3e6c4f19cab5', 'Magikarp', '12345678');