CREATE TABLE public."authorities"
(username  character varying references users (username) on delete cascade not null,
 authority character varying                                              not null,
 created   timestamp without time zone DEFAULT now())
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;


insert into "authorities"(username, authority) values ('junhuhdev@gmail.com', 'USER');