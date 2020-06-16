package org.thoughtcrime.securesms.isrlmods;

import android.util.Log;

import java.util.ArrayList;

public class TrustNetworkRecord {
    private ArrayList<Long> timestamps;
    private ArrayList<Integer> keyHashes;
    private boolean suspicious;
    private String id;

    public TrustNetworkRecord (String id) {
        this.id = id;
        this.suspicious = false;
        keyHashes = new ArrayList();
        timestamps = new ArrayList();
    }

    public void addRecordFromMessage (TrustNetworkKeyValidationMessage message) {
        keyHashes.add(message.getKeyHash());
        timestamps.add(message.getTimestamp());

        if (!keyHashes.stream().allMatch(h -> h == keyHashes.get(0))) {
            Log.i("AARON_TAG", "Seems suspicious");
            suspicious = true;
        }
    }

    public int getNumberOfMismatches () {
        return -1;
    }

    public boolean isSuspicious () {
        return suspicious;
    }

    public String getId () {
        return id;
    }
}
