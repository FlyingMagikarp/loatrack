-- Add Behemoth raid
INSERT INTO public.t_content(id, "min_ilvl", name, hardmode, fk_type)VALUES (219, 1620, 'Behemoth', false, 3);

-- Behemoth Loot
INSERT INTO public.t_loot(fk_content, fk_item, amount, bound)VALUES (219, 1, 18000, false); --gold
