CREATE TABLE public.t_char_content_settings
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    fk_char integer NOT NULL,
    "clear_cd" boolean NOT NULL,
    "rested_cd" boolean NOT NULL,
    "clear_gr" boolean NOT NULL,
    "rested_gr" boolean NOT NULL,
    "unas_ls" boolean,
    PRIMARY KEY (id),
    CONSTRAINT "C_SETTINGS_CHAR" FOREIGN KEY (fk_char)
        REFERENCES public.t_character (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.t_char_content_settings
    OWNER to postgres;