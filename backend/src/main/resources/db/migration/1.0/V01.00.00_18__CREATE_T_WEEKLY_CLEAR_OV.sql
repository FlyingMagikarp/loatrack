CREATE TABLE public.t_weekly_clear_ov
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    "fk_char" integer NOT NULL,
    "fk_raid" integer NOT NULL,
    cleared boolean NOT NULL DEFAULT false,
    PRIMARY KEY (id),
    CONSTRAINT "C_WCLEAR_CHAR" FOREIGN KEY ("fk_char")
        REFERENCES public.t_character (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "C_WCLEAR_CONTENT" FOREIGN KEY ("fk_raid")
        REFERENCES public.t_content (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.t_weekly_clear_ov
    OWNER to postgres;