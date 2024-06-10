CREATE TABLE public."T_CHARACTER"
(
    "ID" integer NOT NULL,
    "FK_USER" uuid NOT NULL,
    "NAME" character varying(20) NOT NULL,
    "ILVL" real NOT NULL,
    "NOTES" character varying(1024),
    "NOTES_OVERVIEW" character varying(256),
    "CLASS" character varying(100),
    PRIMARY KEY ("ID"),
    CONSTRAINT "C_CHARACTER_USER" FOREIGN KEY ("FK_USER")
        REFERENCES public."T_USER" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."T_CHARACTER"
    OWNER to postgres;