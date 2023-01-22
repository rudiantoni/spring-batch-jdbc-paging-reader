-- Create table "clients"
-- Used as a application data source
CREATE TABLE public.clients (
	id int4 NOT NULL, --same as integer
	first_name VARCHAR(100) NULL,
	last_name VARCHAR(100) NULL,
	"age" int2 NOT NULL, --same as smallint
	email VARCHAR(100) NULL,
	CONSTRAINT pk_clients PRIMARY KEY (id),
	CONSTRAINT uk_clients_id UNIQUE (id)
);

-- Create sequence "clients_id_seq" for table "clients"
CREATE SEQUENCE public.clients_id_seq
AS int4 -- Same as the owner column type and the default set below
INCREMENT 1
MINVALUE 1
NO MAXVALUE
NO CYCLE
START WITH 1
OWNED BY public.clients.id;

ALTER TABLE public.clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);




