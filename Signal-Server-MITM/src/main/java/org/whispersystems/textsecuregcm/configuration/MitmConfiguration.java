package org.whispersystems.textsecuregcm.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class was built by BYU's ISRL lab.
 */
public class MitmConfiguration {

    @JsonProperty
    private List<String> targetedConversations;

    @JsonProperty
    private List<String> targetedUsers;

    public List<String> getTargetedConversations() {
        return targetedConversations;
    }

    public List<String> getTargetedUsers() {
        return targetedUsers;
    }
}