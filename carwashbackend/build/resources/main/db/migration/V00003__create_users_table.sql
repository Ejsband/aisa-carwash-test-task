CREATE TABLE public.users
(
    id uuid NOT NULL,
    name character varying(20) NOT NULL,
    password character varying(30) NOT NULL,
    role character varying(50) NOT NULL,
    PRIMARY KEY (id)
);