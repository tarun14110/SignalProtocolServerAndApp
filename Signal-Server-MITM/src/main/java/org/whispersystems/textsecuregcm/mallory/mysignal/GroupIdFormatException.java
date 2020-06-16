package org.whispersystems.textsecuregcm.mallory.mysignal;

import java.io.IOException;

public class GroupIdFormatException extends RuntimeException {

    public GroupIdFormatException(String groupId, IOException e) {
        super("Failed to decode groupId (must be base64) \"" + groupId + "\": " + e.getMessage());
    }
}
