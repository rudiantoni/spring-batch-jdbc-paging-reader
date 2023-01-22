STD_PG_USER=$POSTGRES_USER
STD_PG_DB=$POSTGRES_DB
APP_DATA_DB=$DB_DATASOURCE_DATABASE
SQL_FILE_DB_CREATION=/docker-entrypoint-initdb.d/.sql/database_creation.sql
SQL_FILE_APP_DB_STRUCTURE=/docker-entrypoint-initdb.d/.sql/app_data_db_initial_structure.sql
SQL_FILE_APP_DB_DATA=/docker-entrypoint-initdb.d/.sql/app_data_db_initial_data.sql

echo
echo "Running SQL file ($SQL_FILE_DB_CREATION) to database ($STD_PG_DB) with user ($STD_PG_USER)."
psql --set=dbname=$APP_DATA_DB --echo-all --username $STD_PG_USER --dbname $STD_PG_DB --file $SQL_FILE_DB_CREATION

echo
echo "Running SQL file ($SQL_FILE_APP_DB_STRUCTURE) to database ($APP_DATA_DB) with user ($STD_PG_USER)."
psql --echo-all --username $STD_PG_USER --dbname $APP_DATA_DB --file $SQL_FILE_APP_DB_STRUCTURE

echo
echo "Running SQL file ($SQL_FILE_APP_DB_DATA) to database ($APP_DATA_DB) with user ($STD_PG_USER)."
psql --echo-all --username $STD_PG_USER --dbname $APP_DATA_DB --file $SQL_FILE_APP_DB_DATA
