package org.whispersystems.textsecuregcm.mallory.mysignal.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.whispersystems.textsecuregcm.mallory.mysignal.GroupIdFormatException;
import org.whispersystems.signalservice.internal.util.Base64;

import java.io.IOException;
import java.io.InvalidObjectException;

public class Util {

    private Util() {
    }

    public static String formatSafetyNumber(String digits) {
        final int partCount = 12;
        int partSize = digits.length() / partCount;
        StringBuilder f = new StringBuilder(digits.length() + partCount);
        for (int i = 0; i < partCount; i++) {
            f.append(digits, i * partSize, (i * partSize) + partSize).append(" ");
        }
        return f.toString();
    }

    public static String join(CharSequence separator, Iterable<? extends CharSequence> list) {
        StringBuilder buf = new StringBuilder();
        for (CharSequence str : list) {
            if (buf.length() > 0) {
                buf.append(separator);
            }
            buf.append(str);
        }

        return buf.toString();
    }

    public static JsonNode getNotNullNode(JsonNode parent, String name) throws InvalidObjectException {
        JsonNode node = parent.get(name);
        if (node == null) {
            throw new InvalidObjectException(String.format("Incorrect file format: expected parameter %s not found ", name));
        }

        return node;
    }

    public static byte[] decodeGroupId(String groupId) throws GroupIdFormatException {
        try {
            return Base64.decode(groupId);
        } catch (IOException e) {
            throw new GroupIdFormatException(groupId, e);
        }
    }
}
