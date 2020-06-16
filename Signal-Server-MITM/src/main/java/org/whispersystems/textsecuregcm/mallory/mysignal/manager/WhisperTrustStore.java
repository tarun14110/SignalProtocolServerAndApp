package org.whispersystems.textsecuregcm.mallory.mysignal.manager;

import org.whispersystems.signalservice.api.push.TrustStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class WhisperTrustStore implements TrustStore {

    @Override
    public InputStream getKeyStoreInputStream() {

        File initialFile = new File("src/main/resources/whisper.store");
        InputStream is = null;
        try {
            is = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return is;
        //return WhisperTrustStore.class.getResourceAsStream("whisper.store");
    }

    @Override
    public String getKeyStorePassword() {
        return "whisper";
    }
}
