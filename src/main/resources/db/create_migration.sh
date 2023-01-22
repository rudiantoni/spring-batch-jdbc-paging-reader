# This script creates files based in utc timestamp granting liabilty when creating database migrations.
# By default, the file will be empty and placed inside a directory called migration.
# Those must be in the same directory level as the script.
#
# If you want to create a rollback for this migration, use the create_migration_and_rollback.sh script instead.
# If you want to change the output directory, change OUTPUT_FOLDER.
#
# Just enter a name you want when asked, and it will be created with the following pattern:
# OUTPUT_FILE_PREFIX + CURRENT_UTC_TIMESTAMP + "__" + FILE_NAME + OUTPUT_FILE_SUFFIX
#
echo "--------------------------------------------------"
echo "CREATE MIGRATION FILE"
echo "--------------------------------------------------"
echo "Type a name for your file (blank spaces are not supported):"
echo "---- Use snake_case_in_your_name to separate words."
echo "---- SIGINT (CTRL + C) to cancel."
read FILE_NAME

OUTPUT_FOLDER="./migration"
OUTPUT_FILE_PREFIX="V"
CURRENT_UTC_TIMESTAMP=$(date -u +%s)
OUTPUT_FILE_SUFFIX=".sql"
FILE_NAME=$OUTPUT_FILE_PREFIX$CURRENT_UTC_TIMESTAMP"__"$FILE_NAME$OUTPUT_FILE_SUFFIX

echo "Creating migration output folder $OUTPUT_FOLDER (if not exists)"
mkdir -p $OUTPUT_FOLDER

echo "Creating migration file $FILE_NAME in $OUTPUT_FOLDER"
touch $OUTPUT_FOLDER/$FILE_NAME

echo "--------------------------------------------------"
echo "Finished creating migration file."
echo "--------------------------------------------------"
