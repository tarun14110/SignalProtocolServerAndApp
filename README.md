# Automatic Detection and Prevention of Fake Key Attacks in Secure Messaging


## Fake Key Attacks
We modified the Signal key server to perform fake key attacks (both MITM and impersonation). 
We added a client module to the key server using the Signal client CLI\footnote{https://github.com/AsamK/signal-cli}. 
It performs the standard ratcheting encryption done in normal Signal clients. 
We modified the server to hand out fake keys to clients. 
When the clients send encrypted messages to each other through the server, the server redirects the messages of the victim(s) to the client CLI module where the message is decrypted for the MKS to access it, and then re-encrypted and sent to the victim in the case of a MITM attack.
Our experiment demonstrates that fake key attacks are possible in the current Signal server implementation.

## Trust Network and AKR defense
Signal-Android-modified implemenets protype of two defense Trust network and AKR.
