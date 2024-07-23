CREATE TABLE public.t_loot
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    fk_content integer NOT NULL,
    fk_item integer NOT NULL,
    amount integer NOT NULL,
    bound boolean NOT NULL DEFAULT false,
    PRIMARY KEY (id),
    CONSTRAINT "C_LOOT_CONTENT" FOREIGN KEY (fk_content)
        REFERENCES public.t_content (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "C_LOOT_ITEM" FOREIGN KEY (fk_item)
        REFERENCES public.t_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.t_loot
    OWNER to postgres;