CREATE TABLE public."daily_food"
(id         bigserial primary key,
 diary_id   bigint references diary (id) on delete cascade                  not null,
 category   character varying                                               not null,
 foods      character varying                                               not null,
 created    timestamp without time zone DEFAULT now(),
 created_by character varying,
 updated    timestamp without time zone DEFAULT now(),
 updated_by character varying,
 username   character varying references users (username) on delete cascade not null,
 unique (diary_id, category))
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;
