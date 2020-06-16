package org.whispersystems.textsecuregcm.mallory.mysignal;

class JsonError {

    String message;

    JsonError(Throwable exception) {
        this.message = exception.getMessage();
    }
}
