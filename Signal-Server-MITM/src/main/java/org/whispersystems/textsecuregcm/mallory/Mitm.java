package org.whispersystems.textsecuregcm.mallory;

import org.whispersystems.signalservice.internal.push.LockedException;
import org.whispersystems.textsecuregcm.mallory.mysignal.manager.Manager;
import org.whispersystems.textsecuregcm.mallory.mysignal.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class was built by BYU's ISRL lab.
 */
public class Mitm {

    public static final String digits = "+19999999999";

    private static final Mitm MALLORY = new Mitm();

    private Manager m;
    private String phoneMailbox1;
    private List<String> targetedConversations;
    private List<String> targetedUsers;

    public Mitm() {
        this.m = new Manager(digits, getDefaultDataPath());
        this.phoneMailbox1 = "";
    }

    public Manager getManager() {
        return m;
    }

    public static Mitm getInstance() {
        return MALLORY;
    }

    public void setTargetedConversations(List<String> targetedConversations) {
        this.targetedConversations = targetedConversations;
    }

    public void setTargetedUsers(List<String> targetedUsers) {
        this.targetedUsers = targetedUsers;
    }

    public boolean isTargetedConversation(String number1, String number2) {

        if (targetedConversations.contains(number1+'_'+number2)) {
            return true;
        } else if (targetedConversations.contains(number2+'_'+number1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTargetedUser(String number) {
        return targetedUsers.contains(number);
    }

    public void register() {

        try {
            Thread.sleep(2000); // breaks when we don't sleep.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            m.register(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Registered Mallory: " + m.getAccount().getUsername());
        if (!m.userHasKeys()) {
            System.err.println("User has no keys, first call register.");
        }
        if (m.isRegistered()) {
            System.err.println("User registration is already verified");
        }
        try {
            m.verifyAccount("111-111", null);
        } catch (LockedException e) {
            System.err.println("Verification failed! This number is locked with a pin. Hours remaining until reset: " + (e.getTimeRemaining() / 1000 / 60 / 60));
            System.err.println("Use '--pin PIN_CODE' to specify the registration lock PIN");
        } catch (IOException e) {
            System.err.println("Verify error: " + e.getMessage());
        }
    }

    /**
     * Called to watch a conversation. (MITM)
     */
    public void observe(String from, String to) {
        MitmCommand mitm = new MitmCommand();
        mitm.handleCommand(m, from, to);
    }

    /**
     * Mailbox, mutually exclusive resource.
     */
    public String getFrom() {
        return this.phoneMailbox1;
    }

    public void setFrom(String from) {
        this.phoneMailbox1 = from;
    }

    /**
     *
     * Uses $XDG_DATA_HOME/mysignal-cli if it exists, or if none of the legacy directories exist:
     * - $HOME/.config/mysignal
     * - $HOME/.config/textsecure
     *
     * @return the data directory to be used by mysignal-cli.
     */
    private static String getDefaultDataPath() {
        String dataPath = IOUtils.getDataHomeDir() + "server1/mysignal-cli";
        System.out.println("Datapath: " + dataPath);
        if (new File(dataPath).exists()) {
            return dataPath;
        }

        String legacySettingsPath = System.getProperty("user.home") + "server1/.config/mysignal";
        if (new File(legacySettingsPath).exists()) {
            return legacySettingsPath;
        }

        legacySettingsPath = System.getProperty("user.home") + "server1/.config/textsecure";
        if (new File(legacySettingsPath).exists()) {
            return legacySettingsPath;
        }

        return dataPath;
    }
}
