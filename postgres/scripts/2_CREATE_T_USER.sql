CREATE TABLE public."T_USER"
(
    "ID" uuid NOT NULL,
    "NAME" character varying(20) NOT NULL,
    "PASSWORD" character varying(1024) NOT NULL,
    PRIMARY KEY ("ID")
);

ALTER TABLE IF EXISTS public."T_USER"
    OWNER to postgres;