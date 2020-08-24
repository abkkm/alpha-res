CREATE TABLE public."food"
(id          bigserial primary key,
 name        character varying not null,
 description character varying,
 ingredients character varying,
 category    character varying,
 allergy     character varying,
 created     timestamp without time zone DEFAULT now(),
 created_by  character varying,
 updated     timestamp without time zone DEFAULT now(),
 updated_by  character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "food"(name, ingredients, category, allergy)
values ('gröt', 'havregryn,blåbär,havremjölk,honung', 'BREAKFAST', 'GLUTEN_FREE');

insert into "food"(name, ingredients, category, allergy)
values ('smoothie', 'kefir,l-glutamine,vatten,frysta bär,honung', 'SNACK', 'GLUTEN_FREE');