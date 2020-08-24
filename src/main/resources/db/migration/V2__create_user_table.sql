CREATE TABLE public."users"
(username   character varying primary key,
 password   character varying not null,
 enabled    boolean           not null,
 gender     character varying,
 weight     decimal,
 height     decimal,
 first_name character varying,
 last_name  character varying,
 created    timestamp without time zone DEFAULT now(),
 created_by character varying,
 updated    timestamp without time zone DEFAULT now(),
 updated_by character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;


insert into "users"(username, password, enabled)
values ('junhuhdev@gmail.com', '$2a$10$scqRYBBNaezhL9Gy10TcY.GNhA08hNspdvQb32oFAXWjBL9B/oOKG', true);


insert into "users"(username, password, enabled)
values ('test@gmail.com', 'test123', true);
