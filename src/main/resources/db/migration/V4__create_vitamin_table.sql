CREATE TABLE public."vitamin"
(id           bigserial primary key,
 name         character varying not null,
 manufacturer character varying not null,
 dosage       character varying,
 description  character varying,
 quantity     integer,
 amount       decimal,
 amount_cy    character varying,
 link         character varying,
 created      timestamp without time zone DEFAULT now(),
 created_by   character varying,
 updated      timestamp without time zone DEFAULT now(),
 updated_by   character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "vitamin"(name, manufacturer, dosage, amount, amount_cy, quantity, link)
values ('Vitamin D3', 'Jarrow Formulas', '5000 UI', 65.87, 'SEK', 100, 'https://se.iherb.com/pr/Jarrow-Formulas-Vitamin-D3-Cholecalciferol-5-000-IU-100-Softgels/22926');

insert into "vitamin"(name, manufacturer, dosage, amount, amount_cy, quantity, link)
values ('Krill Oil', 'Jarrow Formulas', '600 mg', 364, 'SEK', 120, 'https://se.iherb.com/pr/Jarrow-Formulas-Krill-Oil-120-Softgels/74793');

insert into "vitamin"(name, manufacturer, dosage, amount, amount_cy, quantity, link)
values ('Magnesium Threonate', 'Jarrow Formulas', '600 mg', 364, 'SEK', 120, 'https://se.iherb.com/pr/Jarrow-Formulas-Krill-Oil-120-Softgels/74793');

insert into "vitamin"(name, manufacturer, dosage, amount, amount_cy, quantity, link)
values ('Bio-Gest', 'Thorne Research', '600 mg', 364, 'SEK', 180, 'https://se.iherb.com/pr/Jarrow-Formulas-Krill-Oil-120-Softgels/74793');

insert into "vitamin"(name, manufacturer, dosage, amount, amount_cy, quantity, link)
values ('Adrenal Optimizer', 'Jarrow Formulas', '600 mg', 364, 'SEK', 180, 'https://se.iherb.com/pr/Jarrow-Formulas-Krill-Oil-120-Softgels/74793');

insert into "vitamin"(name, manufacturer, dosage, amount, amount_cy, quantity, link)
values ('Super K', 'Life Extension', '2600 mcg', 364, 'SEK', 90, 'https://se.iherb.com/pr/Jarrow-Formulas-Krill-Oil-120-Softgels/74793');

