package org.whispersystems.textsecuregcm.mallory.mysignal;

public class AttachmentInvalidException extends RuntimeException {

    public AttachmentInvalidException(String message) {
        System.out.println("DUMMY -> " + AttachmentInvalidException.class);
    }

    public AttachmentInvalidException(String attachment, Exception e) {
        System.out.println("Attachment: " + attachment + " " + e.getMessage());
    }
}
