#!/bin/bash
set -e

echo "Starting database initialization..."

# Wait for PostgreSQL to be ready
until psql -U "postgres" -c '\q'; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

# Create databases
for db in patient_db appointment_db; do
  echo "Creating database: $db"
  psql -U "postgres" <<-EOSQL
    CREATE DATABASE $db;
EOSQL
done

echo "Database initialization complete!"