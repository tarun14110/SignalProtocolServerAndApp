package org.whispersystems.textsecuregcm.mallory.mysignal;

import org.whispersystems.signalservice.internal.util.Base64;

public class NotAGroupMemberException extends RuntimeException {

    public NotAGroupMemberException(byte[] groupId, String groupName) {
        super("User is not a member in group: " + groupName + " (" + Base64.encodeBytes(groupId) + ")");
    }
}
