package org.whispersystems.textsecuregcm.controllers;

import org.whispersystems.textsecuregcm.entities.IncomingMessageList;
import org.whispersystems.textsecuregcm.mallory.Impersonation;
import org.whispersystems.textsecuregcm.mallory.Mitm;
import org.whispersystems.textsecuregcm.storage.Account;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class was made by BYU ISRL. It is for handling many MITM messages at once.
 */
public class MessageDispatch implements Runnable {

    private static final MessageDispatch dispatch = new MessageDispatch();

    private MyLock mailboxLock;
    private MyLock impersonatorLock;
    private MessageController messageController;
    private static volatile Queue<Item> toMalloryQueue;
    private static volatile Queue<Item> fromMalloryQueue;
    private static volatile Queue<Item> toTrudyQueue;
    private static volatile Queue<Item> fromTrudyQueue;
    private Mitm mallory;
    private Impersonation trudy;

    private MessageDispatch() {
        this.toMalloryQueue = new LinkedList<Item>();
        this.fromMalloryQueue = new LinkedList<Item>();
        this.toTrudyQueue = new LinkedList<Item>();
        this.fromTrudyQueue = new LinkedList<Item>();
        this.mailboxLock = new MyLock();
        this.impersonatorLock = new MyLock();
        this.mallory = Mitm.getInstance();
        this.trudy = Impersonation.getInstance();
    }

    public static MessageDispatch getInstance() {
        return dispatch;
    }

    /**
     * Handles MITM when multiple messages are sent at once.
     *  - Scheduled to run every 0.5 seconds in WhisperServerService.java
     */
    @Override
    public void run() {
        // MITM
        if (fromMalloryQueue.peek() != null) {
            Item top = fromMalloryQueue.poll();
            Account source = top.getSource();

            // retrieve from mailbox, send message, unlock mutex.
            source.setNumber(mallory.getFrom());
            try {
                System.out.println("Forwarding to -> " + top.getDestinationName());
                messageController.sendMessageHelper(source, top.getDestinationName(), top.getMessages());
            } catch (Exception e) {
                // Ignore problems, and unlock regardless.
                e.printStackTrace();
            }

            mailboxLock.unlock();
        } else if (toMalloryQueue.peek() != null && mailboxLock.tryLock()) {
            System.out.println("Acquiring resource!");
            try {
                Item top = toMalloryQueue.poll();
                System.out.print("MTIM for: " + top.getSource().getNumber());

                // set mailbox, send message.
                mallory.setFrom(top.getOriginal());
                messageController.sendMessageHelper(top.getSource(), top.getDestinationName(), top.getMessages());
                mallory.observe(top.getSource().getNumber(), top.getTarget());
            } catch (Exception e) {
                e.printStackTrace();
                mailboxLock.unlock();
            }
        }

        // Impersonation
        if (fromTrudyQueue.peek() != null) {
            System.out.println("Pulling off from trudy's queue");
            Item top = fromTrudyQueue.poll();
            Account source = top.getSource();
            source.setNumber(trudy.getMailbox());

            try {
                System.out.println("destination name: " + top.getDestinationName());
                messageController.sendMessageHelper(source, top.getDestinationName(), top.getMessages());
            } catch (Exception e) {
                e.printStackTrace();
            }

            impersonatorLock.unlock();
        } else if (toTrudyQueue.peek() != null && impersonatorLock.tryLock()) {
            System.out.println("Pulling off to trudy's queue");
            Item top = toTrudyQueue.poll();
            trudy.setMailbox(top.getOriginal());

            try {
                messageController.sendMessageHelper(top.getSource(), top.getTarget(), top.getMessages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Puts required data on queue to be forwarded to the original intended recipient.
     *
     * @param source - Account of where the message really came from.
     * @param destinationName - Phone number of message destination.
     * @param messages - messages, just passing data from the key controller.
     */
    public void enqFromMallory(Account source, String destinationName, IncomingMessageList messages) {
        fromMalloryQueue.add(new Item(source, destinationName, "", "", messages));
    }

    /**
     * Puts required data on queue to be mitm'd.
     *
     * @param source - Account where the message really came from.
     * @param target - Phone number to forward the message after a MITM.
     * @param original - Phone number the message "came from"
     * @param messages - messages, just passing data from the key controller.
     */
    public void enqToMallory(Account source, String target, String original, IncomingMessageList messages) {
        toMalloryQueue.add(new Item(source, mallory.digits, target, original, messages));
    }

    /**
     * Return a message sent from the impersonator.
     *
     * @param source - Account where the message really came from.
     * @param destinationName - Where the message is suppose to go to.
     * @param forward - Phone number to forward the message in an impersonation.
     * @param messages - messages, just passing data from the key controller.
     */
    public void enqFromTrudy(Account source, String destinationName, String forward, IncomingMessageList messages) {
        fromTrudyQueue.add(new Item(source, destinationName, forward, "", messages));
    }

    /**
     * Adds a message to be sent to the impersonator.
     *
     * @param source - Account where the message really came from.
     * @param destinationName - Where the message is suppose to go to.
     * @param forward - Phone number to forward the message in an impersonation.
     * @param messages - messages, just passing data from the key controller.
     */
    public void enqToTrudy(Account source, String destinationName, String forward, IncomingMessageList messages) {
        toTrudyQueue.add(new Item(source, destinationName, forward, destinationName, messages));
    }

    /**
     * Dependency injection, we need the message controller to be able to send messages when they are ready
     * to be processed.
     */
    public void setMessageController(MessageController messageController) {
        this.messageController = messageController;
    }

    /**
     * Lock class for controlling the mutual exclusive resource.
     */
    private static final class MyLock {
        private int resources;

        public MyLock() {
            this.resources = 1;
        }

        public boolean tryLock() {
            if (resources == 1) {
                resources = 0;
                return true;
            } else {
                return false;
            }
        }

        public void unlock() {
            resources = 1;
        }
    }

    /**
     * Container for holding items in the message queues.
     */
    private static final class Item {
        private Account source;
        private String destinationName;
        private String target;
        private String original;
        private IncomingMessageList messages;

        public Item(Account source, String destinationName, String target, String original, IncomingMessageList messages) {
            this.source = source;
            this.destinationName = destinationName;
            this.target = target;
            this.messages = messages;
            this.original = original;
        }

        public Account getSource() {
            return source;
        }

        public String getDestinationName() {
            return destinationName;
        }

        public String getTarget() {
            return target;
        }

        public IncomingMessageList getMessages() {
            return messages;
        }

        public String getOriginal() {
            return original;
        }
    }
}