CREATE SCHEMA IF NOT EXISTS scheduling_platform;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SET SCHEMA 'scheduling_platform';

CREATE TABLE IF NOT EXISTS scheduling (
    id uuid not null primary key DEFAULT public.uuid_generate_v4(),
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null,
    despatch timestamp,
    beneficiary varchar(200),
    message varchar(200),
    status varchar(10)
);
insert into scheduling values
(public.uuid_generate_v4(), now() - interval '1 hour', now() - interval '1 hour', now() + interval '3 hour', 'test', 'teste', 'send');