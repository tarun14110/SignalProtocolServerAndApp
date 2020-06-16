#
# This script will refresh you Signal Server state before starting the server.
#

# Close any java processes using the 8080 port. This is usually leftovers from the server previously running.
# <netstat -pant> outputs the info and the python finds the process id from the output.

sudo netstat -pant | python -c 'import sys; import os; import signal; x = [[item.replace("/java", "") for item in line.split() if "/java" in item] for line in sys.stdin if "8080" in line]; [os.kill(int(pid), signal.SIGKILL) for pid in x[0]]'


# Delete and reinstall postgres databases.
# Postgress database password is <password> (no carrots), so be sure that is the password in your config file.
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

# Build the server!!!
mvn clean package -DskipTests

# Run the server.
# rm -r ~/.local/shareserver1  # This is where my MITM stores his keys!
java -jar target/TextSecureServer-1.88.jar server config/sample.yml