CREATE TABLE public."medicine"
(id           bigserial primary key,
 name         character varying not null,
 manufacturer character varying,
 dosage       character varying,
 description  character varying,
 link         character varying,
 created      timestamp without time zone DEFAULT now(),
 created_by   character varying,
 updated      timestamp without time zone DEFAULT now(),
 updated_by   character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;
