CREATE TABLE public."oauth_client_details"
(client_id               character varying primary key,
 resource_ids            character varying,
 client_secret           character varying,
 scope                   character varying,
 authorized_grant_types  character varying,
 web_server_redirect_uri character varying,
 authorities             character varying,
 access_token_validity   integer,
 refresh_token_validity  integer,
 additional_information  character varying,
 autoapprove             character varying)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

insert into "oauth_client_details"(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
values ('22B6XJ', '', '1ff9d71e0c220a0c120f018e14a1e45d', 'activity,heartrate,location,nutrition,profile,settings,sleep,social,weight',
        'password,authorization_code,refresh_token', '', 'USER', 36000, 36000, '', 'true')