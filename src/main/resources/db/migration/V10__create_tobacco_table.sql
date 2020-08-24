CREATE TABLE public."tobacco"
(id           bigserial primary key,
 name         character varying not null,
 manufacturer character varying not null,
 nicotine     character varying not null,
 size         character varying,
 created      timestamp without time zone DEFAULT now(),
 created_by   character varying,
 updated      timestamp without time zone DEFAULT now(),
 updated_by   character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "tobacco"(name, manufacturer, nicotine, size)
values ('XR', 'Swedish Match', '8,5 mg/g', '16.8 g');
