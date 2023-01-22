-- --------------------------------------------
-- WARNING: THIS IS A DATABASE ROLLBACK SCRIPT!
-- --------------------------------------------

-- Reset sequence "clients_id_seq" from table "clients"
ALTER SEQUENCE public.clients_id_seq RESTART WITH 1;

-- Remove all data from table "clients"
DELETE FROM public.clients WHERE 1=1;
