package org.whispersystems.textsecuregcm.mallory;

import java.util.List;

public class Impersonation {

    private static final Impersonation MALLORY = new Impersonation();

    private String phoneMailbox2;
    private List<String> targetedConversations;
    private List<String> targetedUsers;
    private String impersonator;


    public Impersonation() {
        impersonator = "";
    }

    public static Impersonation getInstance() {
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

    public boolean isImperonator(String number) {
        return impersonator.equals(number);
    }

    public String getImpersonator() {
        return impersonator;
    }

    public void setImpersonator(String forwards) {
        this.impersonator = forwards;
    }

    public void setMailbox(String number) {
        this.phoneMailbox2 = number;
    }

    public String getMailbox() {
        return this.phoneMailbox2;
    }
}
