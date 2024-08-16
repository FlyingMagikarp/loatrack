CREATE TABLE public.t_upgrade
(
    id integer NOT NULL,
    start_ilvl integer NOT NULL,
    end_ilvl integer NOT NULL,
    gold integer NOT NULL,
    silver integer NOT NULL,
    shards integer NOT NULL,
    oreha integer NOT NULL,
    leapstone integer NOT NULL,
    red integer NOT NULL,
    blue integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.t_upgrade
    OWNER to postgres;