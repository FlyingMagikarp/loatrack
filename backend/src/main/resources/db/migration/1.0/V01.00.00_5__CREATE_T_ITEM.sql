CREATE TABLE public."t_item"
(
    "id" integer NOT NULL,
    "name" character varying(64) NOT NULL,
    "fk_tier" integer NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "C_ITEM_TIER" FOREIGN KEY ("fk_tier")
        REFERENCES public."t_tier" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."t_item"
    OWNER to postgres;

ALTER TABLE IF EXISTS public.t_item
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;