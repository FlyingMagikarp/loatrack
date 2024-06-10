CREATE TABLE public."t_tier"
(
    "id" integer NOT NULL,
    "name" character varying(20) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."t_tier"
    OWNER to postgres;

ALTER TABLE IF EXISTS public.t_tier
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;