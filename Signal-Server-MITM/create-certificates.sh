#
echo -e "!!! DISCLAIMER !!! If you misspell your password this script will fail quietly. If you get an error that you are missing files. Retry and make sure you spell all your passwords correctly.\n\n"

domain=$(hostname)
# read -p "Enter your domain name (e.g. philzimmerman.cs.byu.edu): " domain
read -p "Enter the password for certificates you'll be filling in: " passphrase
echo "You will be prompted for this password, make sure you enter it in every time to keep things consistent"
read -p "Your domain is $domain.  Continue (Type Y/N)?: " confirm && [[ $confirm == [yY] || $confirm == [yY][eE][sS] ]] || exit 1

echo ""
echo "Generating myCA.key Certificate Authority (Step 1/7)"
openssl genrsa -des3 -out myCA.key 2048
openssl req -x509 -new -nodes -key myCA.key -sha256 -days 1825 -out myCA.pem

echo ""
echo "Generating ${domain}.key (Step 2/7)"
openssl genrsa -out ${domain}.key 2048
openssl req -new -key ${domain}.key -out ${domain}.csr

echo ""
echo "Generating .csr from inline ext file (Step 3/7)"
openssl x509 -req -in ${domain}.csr -CA myCA.pem -CAkey myCA.key -CAcreateserial -out ${domain}.crt -days 1825 -sha256 -extfile <(cat <<EOF
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names
[alt_names]
DNS.1 = ${domain}
EOF
)

# Export to PKCS12
echo ""
echo "Exporting to .crt, .key to .p12 (PKCS12) (Step 4/7)"
openssl pkcs12 -export -password pass:$passphrase -in ${domain}.crt -inkey ${domain}.key -out keystore.p12 -name example -CAfile myCA.pem

# Export to Java keytool
echo ""
echo "Exporting .p12 to Java keytool .jks (Step 5/7)"
keytool -importkeystore -srckeystore keystore.p12 -srcstoretype PKCS12 -destkeystore keystore.jks

if [ ! -d "keygen" ]; then
  echo "Creating keygen/ folder"
  mkdir keygen
fi

echo "Moving .jks, .key, .pem, .srl, .p12 to keygen/ (gitignored) (Step 6/7)"
mv keystore.jks keygen/
mv myCA.key keygen/
mv myCA.pem keygen/
mv myCA.srl keygen/
mv ${domain}.key keygen/
mv ${domain}.csr keygen/
mv ${domain}.crt keygen/
mv keystore.p12 keygen/

echo "Editing config/sample.yml by updating keystoreGen and keystorePass (Step 7/7)"
python3 - <<EOF
with open("config/sample.yml") as f:
  lines = [s.rstrip() for s in f.readlines()];

server_settings_line = lines.index("server:")
for i in range(server_settings_line, len(lines)):
  if "keyStorePath" in lines[i]:
    colon = lines[i].index(":")
    lines[i] = lines[i][0:colon + 1] + " keygen/keystore.jks"
  elif "keyStorePassword" in lines[i]:
    colon = lines[i].index(":")
    lines[i] = lines[i][0:colon + 1] + " ${passphrase}"

with open("config/sample.yml", "w") as f:
  f.write("\n".join(lines))
EOF

echo "Finished! Your .key, .csr, .crt, and keystore.jks (among other) files are located in keygen/." 
