echo "This script will drop the accountdb and messagedb databases, recreate them, then migrate to the TextSecure server."

# read -p "Continue (Y/N)?: " confirm && [[ $confirm == [yY] || $confirm == [yY][eE][sS] ]] || exit 1

# Remove postgres instances, and restart.
sudo pkill postgres
sudo service postgresql start

echo "Dropping accountsdb"
sudo PGPASSWORD='password' dropdb -U postgres accountsdb
echo "Dropping messagedb"
sudo PGPASSWORD='password' dropdb -U postgres messagedb

echo "Creating accountsdb"
sudo PGPASSWORD='password' createdb -U postgres accountsdb
echo "Creating messagedb"
sudo PGPASSWORD='password' createdb -U postgres messagedb

echo "=== BEGINNING MIGRATION ==="
java -jar target/TextSecureServer-1.88.jar accountdb migrate config/sample.yml
java -jar target/TextSecureServer-1.88.jar messagedb migrate config/sample.yml
echo "If you see a bunch of INFO tags database load was successful. If you see a dropwizard stacktrace database load was unsuccessful"

