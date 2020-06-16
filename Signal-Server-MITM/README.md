Signal-Server-MITM
=================
The main module that implements MITM and impersonation attacks is at `src/main/java/org/whispersystems/textsecuregcm/mallory`. 
We used Signal CLI from https://github.com/AsamK/signal-cli that acts as Mallory at the Signal Server.

The attacks can be configured in `./config/sample.yml` 

In MITM attacks, targetedConversations execute MITM on a pair, and targetedUsers execute MitM on all user connections. 
In the impersonation attack, targetedConversations execute impersonation on a pair, and targetedUsers allow impersonating all user connections. The messages can be forwarded to an attacker owned account, and the attacker can reply impersonating as other parties.


Documentation
-------------

Looking for protocol documentation? Check out the website!

https://signal.org/docs/

Cryptography Notice
------------

This distribution includes cryptographic software. The country in which you currently reside may have restrictions on the import, possession, use, and/or re-export to another country, of encryption software.
BEFORE using any encryption software, please check your country's laws, regulations and policies concerning the import, possession, or use, and re-export of encryption software, to see if this is permitted.
See <http://www.wassenaar.org/> for more information.

The U.S. Government Department of Commerce, Bureau of Industry and Security (BIS), has classified this software as Export Commodity Control Number (ECCN) 5D002.C.1, which includes information security software using or performing cryptographic functions with asymmetric algorithms.
The form and manner of this distribution makes it eligible for export under the License Exception ENC Technology Software Unrestricted (TSU) exception (see the BIS Export Administration Regulations, Section 740.13) for both object code and source code.

License
---------------------

Copyright 2013-2016 Open Whisper Systems

Licensed under the AGPLv3: https://www.gnu.org/licenses/agpl-3.0.html
