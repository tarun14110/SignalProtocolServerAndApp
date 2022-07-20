echo "Make sure to run ./install-dependencies.sh before you continue."
read -p "Continue (Y/N)?: " confirm && [[ $confirm == [yY] || $confirm == [yY][eE][sS] ]] || exit 1

echo "Setup TURN server"
sudo turnserver -a -o -v -n  --no-dtls --no-tls -u test:test -r "someRealm"


echo "Starting redis-server"
redis-server &
