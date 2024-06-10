CREATE TABLE public."T_ITEM"
(
    "ID" integer NOT NULL,
    "NAME" character varying(64) NOT NULL,
    "FK_TIER" integer NOT NULL,
    PRIMARY KEY ("ID"),
    CONSTRAINT "C_ITEM_TIER" FOREIGN KEY ("FK_TIER")
        REFERENCES public."T_TIER" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."T_ITEM"
    OWNER to postgres;