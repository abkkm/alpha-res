CREATE TABLE public."drink"
(id           bigserial primary key,
 name         character varying not null,
 size         character varying not null,
 manufacturer character varying not null,
 caffeine     character varying not null,
 ingredients  character varying,
 created      timestamp without time zone DEFAULT now(),
 created_by   character varying,
 updated      timestamp without time zone DEFAULT now(),
 updated_by   character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "drink"(name, size, manufacturer, caffeine, ingredients)
values ('NOCCO Carnival', '330 ml', 'NOCCO', '180 mg',
        'BCAA 4:1:1 (leucin, valin, isoleucin) 3000 mg|Vitamin D 100% DRI|Vitamin B6 100% DRI|Vitamin B12 100% DRI|Biotin 100% DRI|Folsyra 50% DRI|Niacin 80% DRI');

insert into "drink"(name, size, manufacturer, caffeine, ingredients)
values ('NOCCO Caribbean', '330 ml', 'NOCCO', '180 mg',
        'BCAA 4:1:1 (leucin, valin, isoleucin) 3000 mg|Vitamin D 100% DRI|Vitamin B6 100% DRI|Vitamin B12 100% DRI|Biotin 100% DRI|Folsyra 50% DRI|Niacin 80% DRI');

insert into "drink"(name, size, manufacturer, caffeine, ingredients)
values ('Coffee', '330 ml', 'Gevalia', '180 mg',
        'Coffee');

insert into "drink"(name, size, manufacturer, caffeine, ingredients)
values ('Black tea', '330 ml', 'Gevalia', '180 mg',
        'tea');
