echo "Make sure to run ./install-dependencies.sh before you continue."
read -p "Continue (Y/N)?: " confirm && [[ $confirm == [yY] || $confirm == [yY][eE][sS] ]] || exit 1

echo "Setup TURN server"
turnserver -a -o -v -n  --no-dtls --no-tls -u test:test -r "someRealm"


echo "Starting redis-server"
redis-server &



echo "Setting up postgres dbs"
sudo sed -i 's/  peer/  trust/g' /etc/postgresql/12/main/pg_hba.conf
service postgresql restart
psql -U postgres -d postgres -c "alter user postgres with password 'password';"
sudo sed -i 's/  trust/  md5/g' /etc/postgresql/12/main/pg_hba.conf
service postgresql restart

touch ~/.pgpass > echo
echo "*:*:*:postgres:password" > ~/.pgpass
sudo chmod 600 ~/.pgpass
export PGPASSFILE='~/.pgpass'

service postgresql restart

echo "If asked for password, enter 'password'"

createdb -U postgres accountdb
createdb -U postgres messagedb
