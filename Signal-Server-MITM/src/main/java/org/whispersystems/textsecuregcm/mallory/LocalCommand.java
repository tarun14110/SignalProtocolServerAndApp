package org.whispersystems.textsecuregcm.mallory;

import org.whispersystems.textsecuregcm.mallory.mysignal.manager.Manager;

public interface LocalCommand extends Command {
    int handleCommand(Manager m, String from, String to);
}
