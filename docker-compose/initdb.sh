#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username="${POSTGRES_USER}" --dbname="${POSTGRES_DB}" <<-EOSQL
  CREATE TABLE profile (
    id serial PRIMARY KEY,
    name varchar(256)
  );

  CREATE USER username WITH PASSWORD 'password';
  GRANT CONNECT ON DATABASE ${POSTGRES_DB} TO username;
  GRANT USAGE ON SCHEMA public TO username;
  GRANT ALL ON ALL TABLES IN SCHEMA public TO username;
  GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO username;
EOSQL