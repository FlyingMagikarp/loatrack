CREATE TABLE public."t_roster_inv"
(
    "id" integer NOT NULL,
    "fk_item" integer NOT NULL,
    "amount" integer NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_ROSTER_INV_ITEM" FOREIGN KEY ("fk_item")
        REFERENCES public."t_item" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."t_roster_inv"
    OWNER to postgres;

ALTER TABLE IF EXISTS public.t_roster_inv
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE IF EXISTS public.t_roster_inv
    ADD COLUMN fk_user uuid NOT NULL;
ALTER TABLE IF EXISTS public.t_roster_inv
    ADD CONSTRAINT fk_roster_inv_user FOREIGN KEY (fk_user)
    REFERENCES public.t_user (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;