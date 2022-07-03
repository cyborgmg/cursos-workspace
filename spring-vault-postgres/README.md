# Preparando o ambiente

sudo rm -rf /var/lib/postgresql

docker run -d --name postgres --hostname postgres -v /var/lib/postgresql/data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=c6b94gmg -e POSTGRES_USER=postgres -e POSTGRES_DB=ada -p 5432:5432 postgres:latest

docker run -d --hostname vault --name vault -p 8200:8200 cyborgmg/vault:latest

docker exec postgres psql -U postgres -c "CREATE DATABASE dbtest";

docker exec postgres psql -U postgres dbtest -c "CREATE TABLE public.foo(id SERIAL PRIMARY KEY);"

docker exec vault vault secrets enable database

# coloque seu ip no logar de meu-ip

docker exec vault vault write database/config/dbtest plugin_name=postgresql-database-plugin allowed_roles="myrole" connection_url="postgresql://{{username}}:{{password}}@meu-ip:5432/dbtest?sslmode=disable" username="postgres" password="c6b94gmg"

docker exec vault vault write database/roles/myrole db_name=dbtest creation_statements="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO \"{{name}}\"; GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO \"{{name}}\";"  default_ttl="1h" max_ttl="24h"
