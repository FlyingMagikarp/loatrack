
CREATE TABLE public."t_user"
(
    "id" uuid NOT NULL,
    "name" character varying(20) NOT NULL,
    "password" character varying(1024) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."t_user"
    OWNER to postgres;

INSERT INTO "t_user"
VALUES ('4a3574a2-b173-47b4-a08a-3e6c4f19cab5', 'Magikarp', '12345678');


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


CREATE TABLE public."t_tier"
(
    "id" integer NOT NULL,
    "name" character varying(20) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."t_tier"
    OWNER to postgres;


CREATE TABLE public."t_item"
(
    "id" integer NOT NULL,
    "name" character varying(64) NOT NULL,
    "fk_tier" integer NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "C_ITEM_TIER" FOREIGN KEY ("fk_tier")
        REFERENCES public."t_tier" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."t_item"
    OWNER to postgres;


CREATE TABLE public."t_char_inv"
(
    "id" integer NOT NULL,
    "fk_item" integer NOT NULL,
    "amount" integer NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "C_CHAR_INV_ITEM" FOREIGN KEY ("fk_item")
        REFERENCES public."t_item" ("id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public."t_char_inv"
    OWNER to postgres;

ALTER TABLE IF EXISTS public.t_char_inv
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

ALTER TABLE IF EXISTS public.t_char_inv
    ADD COLUMN fk_character integer NOT NULL;
ALTER TABLE IF EXISTS public.t_char_inv
    ADD CONSTRAINT c_char_inv_character FOREIGN KEY (fk_character)
    REFERENCES public.t_character (id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;


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


DELETE FROM public.t_tier;

INSERT INTO public.t_tier(id, name) VALUES (0, 'tier0');
INSERT INTO public.t_tier(id, name) VALUES (1, 'tier1');
INSERT INTO public.t_tier(id, name) VALUES (2, 'tier3');
INSERT INTO public.t_tier(id, name) VALUES (3, 'tier3_1');
INSERT INTO public.t_tier(id, name) VALUES (4, 'tier3_2');
INSERT INTO public.t_tier(id, name) VALUES (5, 'tier3_3');
INSERT INTO public.t_tier(id, name) VALUES (6, 'tier4');
INSERT INTO public.t_tier(id, name) VALUES (7, 'tier4_1');


DELETE FROM public.t_item;
--Global
INSERT INTO public.t_item(id, name, fk_tier) VALUES (1, 'Gold', 0);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (2, 'Silver', 0);


--Tier1
INSERT INTO public.t_item(id, name, fk_tier) VALUES (3, 'Harmony Shard', 1);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (4, 'Harmony Leapstone', 1);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (5, 'Destruction Stone Fragment', 1);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (6, 'Guardian Stone Fragment', 1);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (7, 'Stars Breath', 1);


--Tier3
INSERT INTO public.t_item(id, name, fk_tier) VALUES (8, 'Honor Shard', 2);

--Tier3_1
INSERT INTO public.t_item(id, name, fk_tier) VALUES (9, 'Great Honor Leapstone', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (10, 'Crystallized Destruction Stone', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (11, 'Crystallized Guardian Stone', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (12, 'Oreha Fusion Material', 3);

--Tier3_2
INSERT INTO public.t_item(id, name, fk_tier) VALUES (13, 'Marvelous Honor Leapstone', 4);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (14, 'Obliteration Stone', 4);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (15, 'Protection Stone', 4);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (16, 'Superior Oreha Fusion Material', 4);

--Tier3_3
INSERT INTO public.t_item(id, name, fk_tier) VALUES (17, 'Radiant Honor Leapstone', 5);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (18, 'Refined Obliteration Stone', 5);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (19, 'Refined Protection Stone', 5);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (20, 'Prime Oreha Fusion Material', 5);

--Tier3 Enhance
INSERT INTO public.t_item(id, name, fk_tier) VALUES (21, 'Solar Grace', 2);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (22, 'Solar Blessing', 2);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (23, 'Solar Protection', 2);


--Books
INSERT INTO public.t_item(id, name, fk_tier) VALUES (100, 'Metallurgy: Basic Folding', 1);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (101, 'Tailoring: Basic Knots', 1);


INSERT INTO public.t_item(id, name, fk_tier) VALUES (102, 'Metallurgy: Basic Welding', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (103, 'Tailoring: Basic Mending', 3);

INSERT INTO public.t_item(id, name, fk_tier) VALUES (104, 'Metallurgy: Applied Welding', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (105, 'Tailoring: Applied Mending', 3);

INSERT INTO public.t_item(id, name, fk_tier) VALUES (106, 'Metallurgy: Advanced Welding', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (107, 'Tailoring: Advanced Mending', 3);


INSERT INTO public.t_item(id, name, fk_tier) VALUES (108, 'Metallurgy: Expert Welding', 4);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (109, 'Tailoring: Expert Mending', 4);

INSERT INTO public.t_item(id, name, fk_tier) VALUES (110, 'Metallurgy: Specialized Welding', 4);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (111, 'Tailoring: Specialized Mending', 4);


INSERT INTO public.t_item(id, name, fk_tier) VALUES (112, 'Metallurgy: Professional Welding', 5);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (113, 'Tailoring: Professional Mending', 5);

INSERT INTO public.t_item(id, name, fk_tier) VALUES (114, 'Metallurgy: Welding Compound', 5);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (115, 'Tailoring: Mending Compound', 5);


--Free Taps
INSERT INTO public.t_item(id, name, fk_tier) VALUES (150, 'Fused Leapstone', 3);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (151, 'Advanced Leapstone', 4);
INSERT INTO public.t_item(id, name, fk_tier) VALUES (152, 'Concentrated Leapstone', 5);



CREATE TABLE public.t_content_type
(
    id integer NOT NULL,
    type character varying NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.t_content_type
    OWNER to postgres;


INSERT INTO public.t_content_type(id, type)VALUES (1, 'Chaos Dungeon');
INSERT INTO public.t_content_type(id, type)VALUES (2, 'Guardian Raid');
INSERT INTO public.t_content_type(id, type)VALUES (3, 'Raid');


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


-- Chaos dungeon 1415 - 1630 T3
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (1, 1415, 'CD 1415', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (2, 1445, 'CD 1445', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (3, 1475, 'CD 1475', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (4, 1490, 'CD 1490', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (5, 1520, 'CD 1520', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (6, 1540, 'CD 1540', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (7, 1560, 'CD 1560', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (8, 1580, 'CD 1580', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (9, 1600, 'CD 1600', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (10, 1610, 'CD 1610', false, 1);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (11, 1630, 'CD 1630', false, 1);

-- Guardian Raids
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (100, 1490, 'Caliligos', false, 2);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (101, 1540, 'Hanumatan', false, 2);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (102, 1580, 'Sonavel', false, 2);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (103, 1610, 'Gargadeth', false, 2);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (104, 1630, 'Vescal', false, 2);

-- Raids
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (200, 1415, 'Valtan', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (201, 1445, 'Valtan', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (202, 1430, 'Vykas', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (203, 1460, 'Vykas', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (204, 1475, 'Kakul-Saydon', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (205, 1490, 'Brelshaza', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (206, 1520, 'Brelshaza G4', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (207, 1540, 'Brelshaza', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (208, 1560, 'Brelshaza G4', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (209, 1540, 'Kayangel', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (210, 1580, 'Kayangel', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (211, 1580, 'Akkan', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (212, 1600, 'Akkan', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (213, 1600, 'Ivory Tower', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (214, 1620, 'Ivory Tower', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (215, 1610, 'Thaemine', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (216, 1630, 'Thaemine', true, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (217, 1620, 'Echidna', false, 3);
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (218, 1630, 'Echidna', true, 3);



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


-- CD 1415
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (1, 2, 70380, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (1, 8, 2447, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (1, 9, 4, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (1, 10, 83, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (1, 11, 229, false); --blue

-- CD 1445
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (2, 2, 69470, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (2, 8, 2584, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (2, 9, 5, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (2, 10, 87, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (2, 11, 241, false); --blue

-- CD 1475
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (3, 2, 69184, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (3, 8, 2790, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (3, 9, 5, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (3, 10, 90, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (3, 11, 260, false); --blue

-- CD 1490
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (4, 2, 72372, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (4, 8, 5226, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (4, 13, 3, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (4, 14, 52, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (4, 15, 150, false); --blue

-- CD 1520
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (5, 2, 74186, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (5, 8, 6837, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (5, 13, 3, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (5, 14, 57, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (5, 15, 182, false); --blue

-- CD 1540
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (6, 2, 78166, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (6, 8, 7916, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (6, 13, 4, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (6, 14, 71, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (6, 15, 212, false); --blue

-- CD 1560
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (7, 2, 77951, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (7, 8, 9683, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (7, 13, 6, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (7, 14, 78, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (7, 15, 237, false); --blue

-- CD 1580
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (8, 2, 77091, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (8, 8, 9914, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (8, 17, 3, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (8, 18, 40, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (8, 19, 108, false); --blue

-- CD 1600
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (9, 2, 78908, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (9, 8, 9678, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (9, 17, 4, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (9, 18, 43, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (9, 19, 123, false); --blue

-- CD 1610
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (10, 2, 91704, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (10, 8, 10677, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (10, 17, 5, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (10, 18, 58, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (10, 19, 167, false); --blue

-- CD 1630
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (11, 2, 97820, false); --silver
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (11, 8, 14515, true); --shards
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (11, 17, 9, true); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (11, 18, 96, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (11, 19, 275, false); --blue


-- Caliligos
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (100, 13, 10, false); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (100, 14, 70, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (100, 15, 210, false); --blue

-- Hanumatan
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (101, 13, 14, false); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (101, 14, 100, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (101, 15, 300, false); --blue

-- Sonavel
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (102, 17, 8, false); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (102, 18, 60, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (102, 19, 190, false); --blue

-- Gargadeth
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (103, 17, 12, false); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (103, 18, 100, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (103, 19, 300, false); --blue

-- Vescal
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (104, 17, 24, false); --leaps
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (104, 18, 160, false); --red
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (104, 19, 500, false); --blue

-- Valtan NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (200, 1, 750, false); --gold
-- Valtan HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (201, 1, 1100, false); --gold

-- Vykas NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (202, 1, 1000, false); --gold
-- Vykas HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (203, 1, 1500, false); --gold

-- Kakul-Saydon
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (204, 1, 2000, false); --gold

-- Brelshaza NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (205, 1, 3000, false); --gold
-- Brelshaza NM G4
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (206, 1, 1600, false); --gold

-- Brelshaza HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (207, 1, 3600, false); --gold
-- Brelshaza HM G4
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (208, 1, 2000, false); --gold

-- Kayangel NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (209, 1, 3600, false); --gold
-- Kayangel HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (210, 1, 4800, false); --gold

-- Akkan NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (211, 1, 8500, false); --gold
-- Akkan HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (212, 1, 11000, false); --gold

-- Ivory Tower NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (213, 1, 9000, false); --gold
-- Ivory tower HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (214, 1, 14500, false); --gold

-- Thaemine NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (215, 1, 13000, false); --gold
-- Thaemine HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (216, 1, 41000, false); --gold

-- Echidna NM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (217, 1, 14500, false); --gold
-- Echidna HM
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (218, 1, 18500, false); --gold




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



CREATE TABLE public.t_char_content_settings
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    fk_char integer NOT NULL,
    "clear_cd" boolean NOT NULL,
    "rested_cd" boolean NOT NULL,
    "clear_gr" boolean NOT NULL,
    "rested_gr" boolean NOT NULL,
    "unas_ls" integer,
    PRIMARY KEY (id),
    CONSTRAINT "C_SETTINGS_CHAR" FOREIGN KEY (fk_char)
        REFERENCES public.t_character (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.t_char_content_settings
    OWNER to postgres;


