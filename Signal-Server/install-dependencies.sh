#bin/bash

echo "This script follows the instructions laid out in 'Installing OWS Signal 1.88'"
echo "This script will install the following packages if and only if they haven't been installed already:"


# postgres  downgrade to 12
apt-get -y --purge autoremove postgresql*

curl -fsSL https://www.postgresql.org/media/keys/ACCC4CF8.asc|sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/postgresql.gpg
echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`-pgdg main" |sudo tee  /etc/apt/sources.list.d/pgdg.list
apt update
apt -y install postgresql-12 postgresql-client-12

#coturn
add-apt-repository ppa:ubuntuhandbook1/coturn


declare -a package_names=("openjdk-8-jdk" "postgresql-12" "postgresql-client-12" "postgresql-client-common-12" "redis-server" "memcached" "coturn")
for package_name in "${package_names[@]}"
do
  echo "$package_name"
done

read -p "Continue (Y/N)?: " confirm && [[ $confirm == [yY] || $confirm == [yY][eE][sS] ]] || exit 1

echo ""

# INSTALL DEPENDENCIES
for package_name in "${package_names[@]}"
do
  echo "== INSTALLING $package_name =="
  if [ $(dpkg-query -W -f='${Status}' $package_name 2>/dev/null | grep -c "ok installed") -eq 0 ];
  then
      apt-get install $package_name;
  else
      echo "$package_name is already installed"
  fi
done

echo ""

echo "== CONFIGURING Java 8 =="
update-java-alternatives --list
read -p "Please type the path as shown above to java-1.8: " JAVA_8_PATH
echo "Selecting Java 8 JDK via sudo update-java-alternatives --set $JAVA_8_PATH"
sudo update-java-alternatives --set $JAVA_8_PATH

echo "Please run ./run-background-services.sh to start redis-server, coturn, etc."
