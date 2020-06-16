package org.thoughtcrime.securesms;

import org.whispersystems.libsignal.IdentityKey;

import java.util.HashMap;
import java.util.Map;

public class IdentityKeyVerifier {
    public static Map<String, Integer> friendKeyStore = new HashMap();

    public static void storeKey (String address, IdentityKey key) {
        friendKeyStore.put(address, key.hashCode());

    }

    public static boolean verifyKey (String address, IdentityKey key) {
        return friendKeyStore.get(address).intValue() == key.hashCode();
    }

}
