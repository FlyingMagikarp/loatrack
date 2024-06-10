CREATE TABLE public."t_char_inv"
(
    "id" integer NOT NULL,
    "fk_item" integer NOT NULL,
    "amount" integer NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "C_CHAR_INV_ITEM" FOREIGN KEY ("fk_item")
        REFERENCES public."t_item" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."t_char_inv"
    OWNER to postgres;

ALTER TABLE IF EXISTS public.t_char_inv
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;