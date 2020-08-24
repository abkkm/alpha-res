CREATE TABLE public."nootropic"
(id           bigserial primary key,
 name         character varying not null,
 manufacturer character varying not null,
 ingredients  character varying not null,
 link         character varying,
 created      timestamp without time zone DEFAULT now(),
 created_by   character varying,
 updated      timestamp without time zone DEFAULT now(),
 updated_by   character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "nootropic"(name, manufacturer, ingredients, link)
values ('Alpha Brain', 'Onnit', 'Vitamin B6(10 mg), L-Tyrosine L-Theanine Oat extract Phosphatidylserine (650mg)', 'https://www.onnit.com/alphabrain/');
