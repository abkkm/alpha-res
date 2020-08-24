CREATE TABLE public."bug"
(id          bigserial primary key,
 name        character varying not null,
 description character varying not null,
 severity    integer           not null,
 reason      character varying,
 created     timestamp without time zone DEFAULT now(),
 created_by  character varying,
 updated     timestamp without time zone DEFAULT now(),
 updated_by  character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "bug"(name, description, severity, reason)
values ('head tiredness', 'a distinct feeling of tiredness in my head, cannot work effectively', 5,
        'could be a combination of reasons wrong vitamins, too much snus, too much caffeine, poor sleep hygiene');

insert into "bug"(name, description, severity, reason)
values ('chest pain', 'chest pain in left side', 4,
        'too many stimulants');
