/*
  Copyright (c) 2015-16, Princeton University.
  All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:
  * Redistributions of source code must retain the above copyright
  notice, this list of conditions and the following disclaimer.
  * Redistributions in binary form must reproduce the above
  copyright notice, this list of conditions and the following disclaimer
  in the documentation and/or other materials provided with the
  distribution.
  * Neither the name of Princeton University nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
  CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
  BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
  POSSIBILITY OF SUCH DAMAGE.
 */

package org.coniks.coniks_test_client;

import java.security.*;
import java.security.interfaces.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.lang.NumberFormatException;

import com.google.protobuf.*;

// coniks-java imports
import org.coniks.coniks_common.UtilProtos;
import org.coniks.crypto.Signing;
import org.coniks.crypto.Keys;
import org.coniks.util.Logging;
import org.coniks.coniks_common.C2SProtos.*;
import org.coniks.coniks_common.UtilProtos.ServerResp;
import org.coniks.coniks_common.ServerErr;

/** Implementation of a simple CONIKS test client
 * that simply displays how each component of the
 * protocol works.
 * The client is completely agnostic to the underlying format
 * of the data sent to the server (it only needs to know
 * whether it's using protobufs)).
 *
 *@author Marcela S. Melara (melara@cs.princeton.edu)
 *@author Aaron Blankstein
 *@author Michael Rochlin
 */
public class TestClient {

    // Must be passed in as args to the client
    private static String configFileName;
    private static String logPath;
    private static String server;
    private static boolean isFullOp;
    private static final int NUM_ARGS = 4; // ha, don't forget to set this to the right number

    // since we're not using the public key for actual encrypted communication, use whatever string
    private static final String FAKE_PK_DATA = "fake key data";

    /** List of coniks users - for now used for testing */
    private static HashMap<String,ClientUser> users = new HashMap<>();

    private static int changeCtr = 0; // this is just used to change the key data

    /** Sets the default truststore according to the {@link ClientConfig}.
     * This is needed to set up SSL connections with a CONIKS server.
     */
    private static void setDefaultTruststore () {
        System.setProperty("javax.net.ssl.trustStore",
                           ClientConfig.getTruststorePath());
        System.setProperty("javax.net.ssl.trustStorePassword",
                           ClientConfig.getTruststorePassword());
        System.setProperty("javax.net.ssl.keyStore", ClientConfig.getKeystorePath());
        System.setProperty("javax.net.ssl.keyStorePassword", ClientConfig.getKeystorePassword());
    }

    /** Returns the server error code corresponding to the
     * given server response proto message.
     *
     *@param serverMsg the simple server message
     *@return the error code corresponding to the simple server message
     */
    private static int getServerErr (ServerResp serverResp) {

        ServerResp.Message respType = serverResp.getMessage();

        int serverErr = ServerErr.SUCCESS;

        switch(respType) {
        case SUCCESS:
            serverErr = ServerErr.SUCCESS;
            break;
        case NAME_EXISTS_ERR:
            serverErr = ServerErr.NAME_EXISTS_ERR;
            break;
        case NAME_NOT_FOUND_ERR:
            serverErr = ServerErr.NAME_NOT_FOUND_ERR;
            break;
        case MALFORMED_ERR:
            serverErr = ServerErr.MALFORMED_CLIENT_MSG_ERR;
            break;
        case VERIFICATION_ERR:
            serverErr = ServerErr.SIGNED_CHANGE_VERIF_ERR;;
            break;
        default:
            serverErr = ServerErr.SERVER_ERR;
            break;
        }

        return serverErr;

    }

    /** Perfoms the CONIKS registration protocol with {@code server}
     * for the user {@code uname}.
     *
     *@param uname the username of the client user to register
     *@param server the CONIKS key server with which to register the name
     *@return whether the registration succeeded or an error code
     */
    private static int register(String uname, String server) {
        String fakePubKeyData = uname + " " + FAKE_PK_DATA;
        return register(uname, fakePubKeyData, server);
    }

    public static int register(String uname, String pubKeyData, String server) {
        KeyPair kp = null;

        try {
            kp = Keys.generateDSAKeyPair();
        }
        catch(NoSuchAlgorithmException e) {
            Logging.error("[TestClient] "+e.getMessage());
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        ClientUser user = new ClientUser(uname, pubKeyData, kp);
        users.put(uname, user);

        ClientMessaging.sendRegistrationProto(user, server);

        AbstractMessage serverMsg = ClientMessaging.receiveRegistrationRespProto();

        if (serverMsg == null) {
            return ServerErr.MALFORMED_SERVER_MSG_ERR;
        }
        else if (serverMsg instanceof ServerResp) {
            return getServerErr((ServerResp) serverMsg);
        }
        else {
            // TODO: for now just return SUCCESS
            // eventually we want to process the registration response
            return ServerErr.SUCCESS;
        }
    }

    /** Looks up the public key for the given {@code uname}
     * at {@code server}, and verifies the returned proof of inclusion
     * (authentication path)  if the name exists.
     *
     *@param uname the username of the client user whose key to look up
     *@param server the CONIKS key server at which to lookup the key
     *@return whether the lookup succeeded or an error code
     */
    private static int verifyUser(String uname, String server) {
        ConiksUser user = users.get(uname);
        return verifyUser(user, server);
    }

    public static int verifyUserKey(String uname, String keyData, String server) {
        ConiksUser dummy = new ConiksUser(uname, keyData, null);
        return verifyUser(dummy, server);
    }

    public static byte[] getCommitment(String server) {
        return getCommitment(System.currentTimeMillis(), server);
    }

    public static byte[] getCommitment(long epoch, String server) {
        ClientMessaging.sendCommitmentReqProto(CommitmentReq.CommitmentType.SELF, epoch, server, server);
        AbstractMessage serverMsg = ClientMessaging.receiveCommitmentProto();

        if (serverMsg instanceof UtilProtos.Commitment) {
            return serverMsg.toByteArray();
        }
        else {
            return null;
        }
    }

    public static long getCommitmentEpoch(byte[] commBytes) {
        try {
            UtilProtos.Commitment comm = UtilProtos.Commitment.parseFrom(commBytes);
            if (comm.hasEpoch()) {
                return comm.getEpoch();
            }
            else {
                return -1;
            }
        } catch (InvalidProtocolBufferException e) {
            System.err.println("Error parsing commitment from bytes");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // Checks that these commitments are, for the sake of a PoM, equivalent.
    // Meaning if we have two STRs for the same epoch, there's a problem.
    // Note that it intentionally does not check signatures
    public static boolean isMisbehavior(byte[] a, byte[] b) {
        UtilProtos.Commitment aComm, bComm;
        try {
            aComm = UtilProtos.Commitment.parseFrom(a);
            bComm = UtilProtos.Commitment.parseFrom(b);
        } catch (InvalidProtocolBufferException e) {
            System.err.println("Error parsing commitment from bytes");
            e.printStackTrace();
            return false;
        }

        if (!aComm.hasEpoch() || !bComm.hasEpoch())
            return false; // If it doesn't have an epoch, it's not misbehavior

        if (aComm.getEpoch() != bComm.getEpoch())
            return false; // If the epochs are the same, we can't say it's misbehavior

        if (!aComm.hasRootHash()  || !bComm.hasRootHash())
            return false; // If we don't have a root hash, it's not misbehavior

        return !Arrays.equals(aComm.getRootHash().toByteArray(), bComm.getRootHash().toByteArray());
    }

    public static int verifyEpochsSinceCommitment(byte[] verifiedBytes, String server) {
        UtilProtos.Commitment verifiedComm = null;
        if (verifiedBytes == null)
            return ConsistencyErr.CHECK_PASSED;

        try {
             verifiedComm = UtilProtos.Commitment.parseFrom(verifiedBytes);
        } catch (InvalidProtocolBufferException e) {
            System.err.println("Invalid commitment bytes passed!");
            e.printStackTrace();
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        long epoch = System.currentTimeMillis();

        // TODO: Rewrite to work. Currently doesn't really check anything of note.
        // This would need to be reworked, since the hash of the previous is needed in the next.
        // We don't have that, but the idea is there.
        // I would probably get all the commitments going back to our last known, then work back up
        // Also, probably a good idea to store them, for comparing received epochs.
        while (true) {
            ClientMessaging.sendCommitmentReqProto(CommitmentReq.CommitmentType.SELF, epoch, server, server);
            AbstractMessage serverMsg = ClientMessaging.receiveCommitmentProto();

            if (serverMsg instanceof UtilProtos.Commitment) {
                UtilProtos.Commitment toVerify = (UtilProtos.Commitment) serverMsg;
                if (toVerify.getEpoch() <= verifiedComm.getEpoch()) {
                    return ConsistencyErr.CHECK_PASSED;
                }

                int respCode = verifyReceivedCommitment(toVerify);
                if (respCode != ConsistencyErr.CHECK_PASSED) {
                    return respCode;
                }

                epoch = toVerify.getEpoch() - 1;
            }
            else if (serverMsg == null) {
                return ServerErr.MALFORMED_SERVER_MSG_ERR;
            }
            else if (serverMsg instanceof ServerResp) {
                return getServerErr((ServerResp) serverMsg);
            }
        }
    }

    private static int verifyReceivedCommitment(UtilProtos.Commitment commitment) {
        // TODO (Low, Hard): Verify signature of received commitment
        // TODO (Med, Med): Verify hash of received commitment
        // Will need way to pass in previous hash, or something.
        return ConsistencyErr.CHECK_PASSED;
    }

    private static int verifyUser(ConiksUser user, String server) {
        long epoch = System.currentTimeMillis();

        ClientMessaging.sendKeyLookupProto(user.getUsername(), epoch, server);

        AbstractMessage serverMsg = ClientMessaging.receiveAuthPathProto();

        if (serverMsg == null) {
            return ServerErr.MALFORMED_SERVER_MSG_ERR;
        }
        else if (serverMsg instanceof ServerResp) {
            return getServerErr((ServerResp)serverMsg);
        }
        else if (serverMsg instanceof AuthPath) {
            AuthPath authPath = (AuthPath)serverMsg;

            // check if the key we got is the same as the stored key
            int result = ConsistencyChecks.verifyPubKeyProto(user, authPath);

            if (result == ConsistencyErr.CHECK_PASSED) {
                // Ideally, we don't simple retrieve the latest commitment.
                // There should be an intermediate phase of storing each epoch's commitment,
                // so that we can ensure that doesn't change.
                // Further, this would allow for verification that each STR includes the last.
                ClientMessaging.sendCommitmentReqProto(CommitmentReq.CommitmentType.SELF, epoch, server, server);
                serverMsg = ClientMessaging.receiveCommitmentProto();

                if (serverMsg instanceof UtilProtos.Commitment) {
                    UtilProtos.Commitment comm = (UtilProtos.Commitment) serverMsg;

                    // verify the auth path is consistent with the root
                    result = ConsistencyChecks.verifyMappingProto(authPath, comm);

                    // TODO: store the looked up key if it checks out and we don't have it yet
                }

            }

            return result;
        }
        else {
            // we received some unexpected server message
            // receiveAuthPathProto gave us back something bad
            Logging.error("Got bad protobuf type from receiveAuthPath()");
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }
    }



    /** Performs a key change by generating a new key pair for the user
     * and signing and sending the new key.
     *
     *@param uname the username of the client user whose key to change
     *@param server the CONIKS key server
     *@return whether the key change succeeded or an error code
     */
    private static int signedKeyChange(String uname, String server) {
        ClientUser user = users.get(uname);

        if (user.isAllowsUnsignedChanges()) {
            System.out.println("user "+uname+" allows unsigned key changes");
        }

        // ugh, maybe this isn't the best.
        // we're testing the signing, mostly, so the key data doesn't really matter right now
        String newKeyData = user.getKeyData()+changeCtr;

        DSAPrivateKey prKey = user.loadChangePrivKey();

        if (prKey == null) {
            System.out.println("no private key for "+uname);
            return ConsistencyErr.KEYSTORE_ERR;
        }

        // update the change key for good measure
        KeyPair newCk = null;

        try {
            newCk = Keys.generateDSAKeyPair();
        }
        catch(NoSuchAlgorithmException e) {
            Logging.error("[TestClient] "+e.getMessage());
            user.unloadChangePrivKey();
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        // sign the whole key change request (including all unchanged data)
        byte[] sig = null;
        try {
            ULNChangeReq changeReq =
                ClientMessaging.buildULNChangeReqMsgProto(user.getUsername(),
                                                          newKeyData,
                                                          (DSAPublicKey)newCk.getPublic(),
                                                          user.isAllowsUnsignedChanges(),
                                                          user.isAllowsPublicVisibility());


            sig = Signing.dsaSign(prKey, changeReq.toByteArray());
        }
        catch (NoSuchAlgorithmException e) {
            Logging.error("[TestClient] "+e.getMessage());
            user.unloadChangePrivKey();
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        // we're done using the private key so clear its memory
        user.unloadChangePrivKey();

         // double check that we actually got a signature
        if (sig == null) {
            Logging.error("Couldn't get a signature for the new key data");
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        // now we can update the user's data internally
        user.setKeyData(newKeyData);
        user.saveChangeKeyPair(newCk);

        ClientMessaging.sendSignedULNChangeReqProto(user, sig, server);

        // for good measure, cut the pointer to the key pair
        newCk = null;

        AbstractMessage serverMsg = ClientMessaging.receiveRegistrationRespProto();

        if (serverMsg == null) {
            return ServerErr.MALFORMED_SERVER_MSG_ERR;
        }
        else if (serverMsg instanceof ServerResp) {
            return getServerErr((ServerResp)serverMsg);
        }
        else {
            // TODO: verify registration resp and save the new key data to disk
            changeCtr++;

            return ConsistencyErr.CHECK_PASSED;
        }

    }

    /** Performs an unsigned key change by generating a new key pair
     * for the user, and sends the new key to the server. Fails if the user
     * requires signed key changes.
     *
     *@param uname the username of the client user whose key to change
     *@param server the CONIKS key server
     *@return whether the key change succeeded or an error code
     */
    private static int unsignedKeyChange(String uname, String server) {
        ClientUser user = users.get(uname);

        if (!user.isAllowsUnsignedChanges()) {
            System.out.println("user "+uname+" doesn't allow unsigned key changes");
            return ConsistencyErr.DISALLOWED_OP_ERR;
        }

        // ugh, maybe this isn't the best.
        // we're testing the signing, mostly, so the key data doesn't really matter right now
        String newKeyData = user.getKeyData()+changeCtr;
        user.setKeyData(newKeyData);

        ClientMessaging.sendULNChangeReqProto(user, server);

        AbstractMessage serverMsg = ClientMessaging.receiveRegistrationRespProto();

        if (serverMsg == null) {
            return ServerErr.MALFORMED_SERVER_MSG_ERR;
        }
        else if (serverMsg instanceof ServerResp) {
            return getServerErr((ServerResp)serverMsg);
        }
        else {
            // TODO: check the registration resp and save the new key data to disk
            changeCtr++;

            return ConsistencyErr.CHECK_PASSED;
        }
    }

    /** Changes a user's key change policy, signs the change if required
     * and sends the new policy to the server.
     *
     *@param uname the username of the client user whose key change policy to change
     *@param server the CONIKS key server
     *@return whether the key change succeeded or an error code
     */
    private static int changeKeyChangePolicy(String uname, String server) {
        ClientUser user = users.get(uname);

        if (!user.isAllowsUnsignedChanges()) {
            System.out.println("Allowing unsigned key changes for user "+uname);
            user.allowUnsignedChanges();
        }
        else {
            System.out.println("Disallowing unsigned key changes for user "+uname);
            user.disallowUnsignedChanges();
        }

        // default is to always sign the changes no matter what
        DSAPrivateKey prKey = user.loadChangePrivKey();

        if (prKey == null) {
            System.out.println("no private key for "+uname);
            return ConsistencyErr.KEYSTORE_ERR;
        }

        // update the change key for good measure
        KeyPair newCk = null;

        try {
            newCk = Keys.generateDSAKeyPair();
        }
        catch(NoSuchAlgorithmException e) {
            Logging.error("[TestClient] "+e.getMessage());
            user.unloadChangePrivKey();
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        // sign the whole policy change request (including all unchanged data)
        byte[] sig = null;
        try {
            ULNChangeReq changeReq = ClientMessaging.buildULNChangeReqMsgProto(user.getUsername(), user.getKeyData(),
                                                                               (DSAPublicKey)newCk.getPublic(),
                                                                               user.isAllowsUnsignedChanges(),
                                                                               user.isAllowsPublicVisibility());


            sig = Signing.dsaSign(prKey, changeReq.toByteArray());
        }
        catch (NoSuchAlgorithmException e) {
            Logging.error("[TestClient] "+e.getMessage());
            user.unloadChangePrivKey();
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        // we're done using the private key so clear its memory
        user.unloadChangePrivKey();

        // double check that we actually got a signature
        if (sig == null) {
            Logging.error("Couldn't get a signature for the new policy");
            return ClientUtils.INTERNAL_CLIENT_ERR;
        }

        // now we can update the user's data internally
        user.saveChangeKeyPair(newCk);

        ClientMessaging.sendSignedULNChangeReqProto(user, sig, server);

        // for good measure, cut the pointer to the key pair
        newCk = null;

        AbstractMessage serverMsg = ClientMessaging.receiveRegistrationRespProto();

        if (serverMsg == null) {
            return ServerErr.MALFORMED_SERVER_MSG_ERR;
        }
        else if (serverMsg instanceof ServerResp) {
            return getServerErr((ServerResp)serverMsg);
        }
        else {
            // TODO: save the new policy to disk
            return ConsistencyErr.CHECK_PASSED;
        }

    }

    /** Prints the usage of the TestClient.
     */
    private static void usage() {
        System.out.println("valid operations: REGISTER, LOOKUP, SIGNED, UNSIGNED, POLICY");
    }

    /** Template for an error message.
     *
     *@param msg The error message to print.
     */
    private static void printErr (String msg) {
        System.out.println("Error: "+msg);
    }

    /** Prints an error message for the given user and the given error code.
     *
     *@param err the error code for which to print the error message
     *@param uname the username for which the error occurred
     */
    public static void printErrMsg (int err, String uname) {

        switch(err) {
        case ServerErr.SUCCESS:
            // don't print anything for a successful operation
            break;
        case ConsistencyErr.CHECK_PASSED:
            // don't print anything here either
            break;
        case ClientUtils.INTERNAL_CLIENT_ERR:
            printErr("Some internal client error occurred while processing user "+uname+". Check the logs for details.");
            break;
        case ServerErr.INTERNAL_SERVER_ERR:
            printErr("The server experienced some internal error. Current user: "+uname);
            break;
        case ServerErr.NAME_EXISTS_ERR:
            printErr("Couldn't register the name "+uname+" because it already exists.");
            break;
        case ServerErr.NAME_NOT_FOUND_ERR:
            printErr("Couldn't find the name "+uname+".");
            break;
        case ServerErr.MALFORMED_CLIENT_MSG_ERR:
            printErr("The server received a malformed message for user "+uname);
            break;
        case ServerErr.MALFORMED_SERVER_MSG_ERR:
            printErr("Received a malformed server message. Current user: "+uname);
            break;
        case ServerErr.SIGNED_CHANGE_VERIF_ERR:
            printErr("The server could not verify the signed data change for user "+uname+".");
            break;
        case ServerErr.SERVER_ERR:
            printErr("Some other server error occurred. Current user: "+uname);
            break;
        case ConsistencyErr.BAD_MAPPING_ERR:
            printErr("Mapping inconsistent with tree root for user "+uname+".");
            break;
        case ConsistencyErr.UNEXPECTED_KEY_ERR:
            printErr("Unexpected key for user "+uname+".");
            break;
        case ConsistencyErr.BAD_STR_ERR:
            printErr("Inconsistent signed tree roots. Current user: "+uname);
            break;
        case ConsistencyErr.BAD_SERVER_SIG_ERR:
            printErr("Could not verify the server's identity. Current user: "+uname);
            break;
        case ConsistencyErr.KEYSTORE_ERR:
            printErr("Could not find the private or public key. Current user: "+uname);
            break;
        case ConsistencyErr.DISALLOWED_OP_ERR:
            printErr("Client "+uname+" tried to perform a forbidden operation");
            break;
        default:
            printErr("Some unknown server error occurred: "+err);
            break;
        }

    }

    /** Checks whether the given operation is valid.
     *
     *@param op the operation to be checked.
     *@return {@code true} if it's valid, {@code false} otherwise.
     */
    private static boolean isValidOperation (String op) {
         if (op.equalsIgnoreCase("LOOKUP") ||
            op.equalsIgnoreCase("REGISTER") ||
            op.equalsIgnoreCase("SIGNED") ||
            op.equalsIgnoreCase("UNSIGNED") ||
            op.equalsIgnoreCase("POLICY")) {
             return true;
         }
         else {
             return false;
         }
    }

    /** Performs the given operation {@code numUsers} times, starting
     * at user number {@code offset}.
     *
     *@param op the operation to perform
     *@param numUsers the number of users for which to do the operation
     *@param offset the user number at which to start
     */
    private static void doOperation (String op, int numUsers, int offset) {
        // print the status
        if (numUsers == 1) {
            System.out.print("Performing "+op+" for user test-"+offset);
        }
        else if (numUsers > 1) {
            System.out.print("Performing "+op+" for users test-"+offset+" thru test-"+(offset+numUsers-1));
        }

        for (int i = 0; i < numUsers; i++){
            // this is just a nicety to give the user some sense of progress
            if (numUsers <= 5 && i == 0) {
                System.out.print("...");
            }
            else if (numUsers >= 6  && numUsers <= 49 && i % (1+ (numUsers / 6)) == 0) {
                System.out.print(".");
            }
            if (numUsers >= 50  && numUsers <= 99 && i % (1 + (numUsers / 25)) == 0) {
                System.out.print(".");
            }
            else if (numUsers >= 100 && i % (1+ (numUsers / 50)) == 0) {
                System.out.print(".");
            }

            String uname = "test-"+(offset+i);

            int error = 0;

            if(op.equalsIgnoreCase("LOOKUP")){
                error = verifyUser(uname, server);
            }
            else if (op.equalsIgnoreCase("REGISTER")){
                error = register(uname, server);
            }
            else if (op.equalsIgnoreCase("SIGNED")) {
                error = signedKeyChange(uname, server);
            }
            else if (op.equalsIgnoreCase("UNSIGNED")) {
                error = unsignedKeyChange(uname, server);
            }
            else if (op.equalsIgnoreCase("POLICY")) {
                error = changeKeyChangePolicy(uname, server);
            }

            // if we got an error, print a new line so the error msg doesn't
            // appear next to the progress dots
            if (error != ServerErr.SUCCESS && error != ConsistencyErr.CHECK_PASSED) {
                System.out.println();
            }

            printErrMsg(error, uname);
        }
        System.out.println(" done!");
    }

    public static void loadConfigFile(String logPath) {
        File configFile = new File(logPath);
        if (!configFile.exists()) {
            System.err.println("Config file does not exist!");
            System.exit(1);
        }

        if (!configFile.canRead()) {
            System.err.println("Cannot read config file!");
            System.exit(2);
        }

        ClientConfig.readClientConfig(configFile, isFullOp);
    }

    public static void setupLogging(String logDirPath) {
        File logDir = new File(logDirPath);
        if (!logDir.isDirectory()) {
            if (!logDir.mkdirs()) {
                System.err.println("Log directory does not exist, and cannot be created");
                System.exit(3);
            }
        }

        // Actually do the setup
        Logging.setup(logDirPath+"/client-%g", "Client");
    }

    public static void setIsFullOp(boolean fullOp) {
        isFullOp = fullOp;
    }

    private static void parseOpMode(String opMode) {
        if (opMode.equalsIgnoreCase("full")) {
            isFullOp = true;
        }
        else if (opMode.equalsIgnoreCase("test")) {
            isFullOp = false;
        }
        else {
            System.out.println("Unknown operation mode: "+opMode);
            System.exit(-1);
        }
    }

    /** Prompts the user to perform a CONIKS operation for one or more users.
     */
    public static void main(String[] args){
        if (args.length != NUM_ARGS) {
            System.out.println("Need "+(NUM_ARGS-1)+" arguments: CONIKS_CLIENTCONFIG CONIKS_CLIENTLOGS and SERVER");
            System.out.println("Check run script for more info.");
            System.exit(-1);
        }

        configFileName = args[0];
        logPath = args[1];
        server = args[2];
        String opMode = args[NUM_ARGS-1];

        parseOpMode(opMode);
        setupLogging(logPath);
        loadConfigFile(configFileName);

        // set the operation mode in the messaging module
        ClientMessaging.setIsFullOp(isFullOp);

        String cont = "y";

        Scanner scanner = new Scanner(System.in);

        if (isFullOp) {
            // this is needed to enable the client to communicate using SSL
            setDefaultTruststore();
        }

        // initialize the set of users
        users = new HashMap<String,ClientUser>();

        // this loops prompts the users to enter the command, the number of users to run it for
        // and the offset
        while (!cont.equalsIgnoreCase("n")) {

            //  prompt for the user's name
            System.out.print("Enter the next operation (or h for help): ");

            // get their input as a String
            String op = scanner.next();

            if (op.equalsIgnoreCase("h")) {
                usage();
            }
            else if (isValidOperation(op)) {
                System.out.print("Enter the number of users for this operation: ");

                int numUsers = 1;

                try {
                    numUsers = Integer.parseInt(scanner.next());

                    while (numUsers < 1) {
                        System.out.println("the number of users must be a positive integer.");
                        System.out.print("Enter the number of users for this operation: ");
                        numUsers = Integer.parseInt(scanner.next());
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("malformed number.");
                    break;
                }

                System.out.print("Enter the first user number for this operation: ");

                int offset = 0;

                try {
                    offset = Integer.parseInt(scanner.next());

                    while (offset < 0) {
                        System.out.println("the user number can't be a negative number.");
                        System.out.print("Enter the first user number for this operation: ");
                        offset = Integer.parseInt(scanner.next());
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("malformed number.");
                    break;
                }

                // do the operation
                doOperation(op, numUsers, offset);

                System.out.print("Would you like to perform another operation? [y/n]: ");

                cont = scanner.next();

                while (!cont.equalsIgnoreCase("y")  && !cont.equalsIgnoreCase("n")) {
                    System.out.print("Please enter y or n: ");
                    cont = scanner.next();
                }
            }
            else {
                System.out.println("Unknown operation: "+op);
                usage();
                break;
            }

        }

        System.out.println("Goodbye.");

    }

}
