CREATE TABLE public.appointments
(
    id         uuid NOT NULL,
    service_id uuid NOT NULL,
    user_id    uuid NOT NULL,
    day        character varying (200) NOT NULL,
    PRIMARY KEY (id)
--     CONSTRAINT "service_FK" FOREIGN KEY (service_id)
--         REFERENCES public.services (id) MATCH SIMPLE,
--     CONSTRAINT "user_FK" FOREIGN KEY (user_id)
--         REFERENCES public.users (id) MATCH SIMPLE
);