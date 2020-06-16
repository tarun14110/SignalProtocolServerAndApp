This project has taken initially from https://github.com/signalapp/Signal-Android

# Android app Setup

In the build.gradle change the SIGNAL_URL and SIGNAL_SERVICE_STATUS_URLvalues with your Signal server domain address. Make sure you use the same domain name that you used while generating a certificate for your Signal server. To get your domain address, you can do `nslookup <your ip address>`. Like replace "\"https://textsecure-service.whispersystems.org\"" and "\"uptime.signal.org\"" with "\"https://johnvonneumann.cs.byu.edu:8080\"".
    

The Signal app uses certificate binding. So you need to copy the public certificate of the server in the app that you generated using `./create-certificates.sh` while setting up the server. Put the public certificate in `res/raw/whisper.store`. 
- Download Keystore Explorer 
- In the menu click: File -> Open and open your whisper.store file (Password is "whisper")
- Click import trusted certificate. (Red ribbon with blue down arrow)
- Find the certificate you created for your signal server and import. (It should be in Signal-Server/keygen/*.crt)
- Open up whisper.store in android studio and verify you see your entry.



# Signal Android 

Signal is a messaging app for simple private communication with friends.

Signal uses your phone's data connection (WiFi/3G/4G) to communicate securely, optionally supports plain SMS/MMS to function as a unified messenger, and can also encrypt the stored messages on your phone.

Currently available on the Play store.

<a href='https://play.google.com/store/apps/details?id=org.thoughtcrime.securesms&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height='80px'/></a>

## Contributing Bug reports
We use GitHub for bug tracking. Please search the existing issues for your bug and create a new one if the issue is not yet tracked!

https://github.com/signalapp/Signal-Android/issues

## Joining the Beta
Want to live life on the bleeding edge and help out with testing?

You can subscribe to Signal Android Beta releases here:
https://play.google.com/apps/testing/org.thoughtcrime.securesms
 
If you're interested in a life of peace and tranquility, stick with the standard releases.

## Contributing Translations
Interested in helping to translate Signal? Contribute here:

https://www.transifex.com/projects/p/signal-android/

## Contributing Code
Instructions on how to setup your development environment and build Signal can be found in  [BUILDING.md](https://github.com/signalapp/Signal-Android/blob/master/BUILDING.md).

If you're new to the Signal codebase, we recommend going through our issues and picking out a simple bug to fix (check the "easy" label in our issues) in order to get yourself familiar. Also please have a look at the [CONTRIBUTING.md](https://github.com/signalapp/Signal-Android/blob/master/CONTRIBUTING.md), that might answer some of your questions.

For larger changes and feature ideas, we ask that you propose it on the [unofficial Community Forum](https://whispersystems.discoursehosting.net) for a high-level discussion with the wider community before implementation.

## Contributing Ideas
Have something you want to say about Open Whisper Systems projects or want to be part of the conversation? Get involved in the [community forum](https://whispersystems.discoursehosting.net).

Help
====
## Support
For troubleshooting and questions, please visit our support center!

https://support.signal.org/

## Documentation
Looking for documentation? Check out the wiki!

https://github.com/signalapp/Signal-Android/wiki

# Legal things
## Cryptography Notice

This distribution includes cryptographic software. The country in which you currently reside may have restrictions on the import, possession, use, and/or re-export to another country, of encryption software.
BEFORE using any encryption software, please check your country's laws, regulations and policies concerning the import, possession, or use, and re-export of encryption software, to see if this is permitted.
See <http://www.wassenaar.org/> for more information.

The U.S. Government Department of Commerce, Bureau of Industry and Security (BIS), has classified this software as Export Commodity Control Number (ECCN) 5D002.C.1, which includes information security software using or performing cryptographic functions with asymmetric algorithms.
The form and manner of this distribution makes it eligible for export under the License Exception ENC Technology Software Unrestricted (TSU) exception (see the BIS Export Administration Regulations, Section 740.13) for both object code and source code.

## License

Copyright 2011 Whisper Systems

Copyright 2013-2017 Open Whisper Systems

Licensed under the GPLv3: http://www.gnu.org/licenses/gpl-3.0.html

Google Play and the Google Play logo are trademarks of Google Inc.
