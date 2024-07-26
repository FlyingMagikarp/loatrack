CREATE TABLE public.t_content
(
    id integer NOT NULL,
    "min_ilvl" integer NOT NULL,
    name character varying NOT NULL,
    hardmode boolean NOT NULL DEFAULT false,
    fk_type integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "C_CONTENT_CONTENT_TYPE" FOREIGN KEY (fk_type)
        REFERENCES public.t_content_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.t_content
    OWNER to postgres;