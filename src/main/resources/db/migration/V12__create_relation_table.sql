CREATE TABLE public."relation"
(diary_id      bigserial references diary (id) on delete cascade not null,
 relationship_id   bigserial                                         not null,
 relationship_type character varying                                 not null)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;
