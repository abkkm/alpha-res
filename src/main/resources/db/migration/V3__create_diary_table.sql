CREATE TABLE public."diary"
(id                bigserial primary key,
 sharpness         integer                                                         not null,
 mood              integer                                                         not null,
 energy            integer                                                         not null,
 productivity      integer                                                         not null,
 total_sleep_hours decimal,
 activities        character varying,
 medicines         character varying,
 vitamins          character varying,
 nootropics        character varying,
 tobaccos          character varying,
 drinks            character varying,
 symptoms          character varying,
 bugs              character varying,
 foods             character varying,
 comment           character varying,
 created           timestamp without time zone DEFAULT now(),
 created_by        character varying,
 updated           timestamp without time zone DEFAULT now(),
 updated_by        character varying,
 username          character varying references users (username) on delete cascade not null)
WITH (
    OIDS = FALSE
    )
TABLESPACE pg_default;

INSERT INTO diary (sharpness, mood, energy, productivity, total_sleep_hours, activities, medicines,
                   vitamins, nootropics, tobaccos, drinks, symptoms, bugs, foods, comment, created, created_by, updated, updated_by, username)
VALUES (1, 2, 3, 4, 6.8, 'SAUNA,GYM', 'Alvedon',
        'Vitamin D3,Krill Oil,Magnesium Threonate', 'Alpha Brain', 'XR', 'NOCCO Carnival,NOCCO Caribbean',
        null, 'head tiredness,chest pain', null, 'sucks day', '2019-08-10 16:50:56.701150', null, '2019-08-11 16:50:56.701150', null, 'junhuhdev@gmail.com');

INSERT INTO diary (sharpness, mood, energy, productivity, total_sleep_hours, activities, medicines,
                   vitamins, nootropics, tobaccos, drinks, symptoms, bugs, foods, comment, created, created_by, updated, updated_by, username)
VALUES (1, 2, 3, 4, 6.8, 'SAUNA,GYM,JOGGING', 'Alvedon',
        'Vitamin D3,Krill Oil,Magnesium Threonate', 'Alpha Brain', 'XR', 'NOCCO Carnival',
        null, 'head tiredness,chest pain', null, 'sucks day', '2019-08-11 16:50:56.701150', null, '2019-08-11 16:50:56.701150', null, 'junhuhdev@gmail.com');