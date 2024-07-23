CREATE TABLE public.t_content_type
(
    id integer NOT NULL,
    type character varying NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.t_content_type
    OWNER to postgres;