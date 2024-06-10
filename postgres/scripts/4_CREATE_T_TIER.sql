CREATE TABLE public."T_TIER"
(
    "ID" integer NOT NULL,
    "NAME" character varying(20) NOT NULL,
    PRIMARY KEY ("ID")
);

ALTER TABLE IF EXISTS public."T_TIER"
    OWNER to postgres;