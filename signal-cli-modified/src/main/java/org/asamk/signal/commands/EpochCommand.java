package org.asamk.signal.commands;

import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.asamk.signal.manager.Manager;
import org.coniks.coniks_test_client.ConsistencyErr;
import org.coniks.coniks_test_client.TestClient;
import org.whispersystems.libsignal.util.Pair;

public class EpochCommand implements LocalCommand {

    @Override
    public void attachToSubparser(final Subparser subparser) {
    }

    @Override
    public int handleCommand(final Namespace ns, final Manager m) {
        // These have a format of (Importance, Difficult)
        // An '*' means value is dependent on completion of another thing
        // Further, "DONE" means I've checked completed the task,
        //      and "PASS" means I've opted not to implement it.
        // DONE (Med, Med-Hard): Get and verify previous STRs and PoIs
        m.updateAndCheckStr();

        // All of these functions that just return an `int` response code
        // should really return something more, usually some context about
        // whatever happened, like the number. But I'm opting not to add that.
        int respCode = m.checkUserInConiksStr();
        if (respCode != ConsistencyErr.CHECK_PASSED) {
            System.err.println("User in STR check failed!");
            TestClient.printErrMsg(respCode, m.getUsername());
            return 1;
        }

        Pair<Integer, String> contactResp = m.checkContactsInStr();
        if (contactResp.first() != ConsistencyErr.CHECK_PASSED) {
            System.err.println("Contacts in STR check failed!");
            TestClient.printErrMsg(contactResp.first(), contactResp.second());
            return 1;
        }

        m.sendLatestCommitmentToContacts();


        // DONE (High, Low): Send STR to contacts
        // DONE (Low, Easy*):   ^ Check PoIs of contacts

        // I have opted not to do the following, since receiving messages is difficult
        // It would be difficult to even modify the current message receive command, given
        //   the complexity of the receiving code. I attempted the simple modification
        //   of inserting into the DBus handler version, but that was never even run, it turns out
        // I would have to modify either `JsonReceiveMessageHandler` or `ReceiveMessageHandler`,
        //   both of which are quite complicated in nature. The latter is complicated on the face,
        //   but the former is relying on a lot of underlying architecture that would take time
        //   to understand.
        // PASS (Med-Hard, VHard):  ^ Receive STRs and compare them
        // PASS (Med, Hard):        ^ Verify signatures of received STRs
        // PASSS (Low, VEasy*):      ^ Forward PoM to contacts


        // Both of these are very low priority, as they're not a central part of the system.
        // Given their difficulty in implementation, I decided against implementing it.
        // PASS (Low, Hard): Monitor for key changes when sending or receiving
        // PASS (VLow, Easy*):   ^ Short-lived attack monitoring (two key-changes in one epoch)
        return 0;
    }

}
