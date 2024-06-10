CREATE TABLE public."t_character"
(
    "id" integer NOT NULL,
    "fk_user" uuid NOT NULL,
    "name" character varying(20) NOT NULL,
    "ilvl" real NOT NULL,
    "notes" character varying(1024),
    "notes_overview" character varying(256),
    "classname" character varying(100),
    PRIMARY KEY ("id"),
    CONSTRAINT "C_CHARACTER_USER" FOREIGN KEY ("fk_user")
        REFERENCES public."t_user" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."t_character"
    OWNER to postgres;

ALTER TABLE IF EXISTS public.t_character
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;