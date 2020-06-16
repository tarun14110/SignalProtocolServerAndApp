package org.whispersystems.textsecuregcm.mallory.mysignal;

import org.whispersystems.signalservice.internal.util.Base64;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(byte[] groupId) {
        System.out.println("Group not found: " + Base64.encodeBytes(groupId));
    }
}
