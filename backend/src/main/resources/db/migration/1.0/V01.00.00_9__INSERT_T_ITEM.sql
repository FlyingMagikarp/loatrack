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

