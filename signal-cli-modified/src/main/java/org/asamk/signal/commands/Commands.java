package org.asamk.signal.commands;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        addCommand("addDevice", new AddDeviceCommand());
        addCommand("daemon", new DaemonCommand());
        addCommand("link", new LinkCommand());
        addCommand("listDevices", new ListDevicesCommand());
        addCommand("listGroups", new ListGroupsCommand());
        addCommand("listIdentities", new ListIdentitiesCommand());
        addCommand("quitGroup", new QuitGroupCommand());
        addCommand("receive", new ReceiveCommand());
        addCommand("register", new RegisterCommand());
        addCommand("removeDevice", new RemoveDeviceCommand());
        addCommand("removePin", new RemovePinCommand());
        addCommand("send", new SendCommand());
        addCommand("sendContacts", new SendContactsCommand());
        addCommand("updateContact", new UpdateContactCommand());
        addCommand("setPin", new SetPinCommand());
        addCommand("trust", new TrustCommand());
        addCommand("unregister", new UnregisterCommand());
        addCommand("updateAccount", new UpdateAccountCommand());
        addCommand("updateGroup", new UpdateGroupCommand());
        addCommand("updateProfile", new UpdateProfileCommand());
        addCommand("verify", new VerifyCommand());

        addCommand("epoch", new EpochCommand());
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }

    private static void addCommand(String name, Command command) {
        commands.put(name, command);
    }
}
