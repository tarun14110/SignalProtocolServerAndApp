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

import javax.net.ssl.*;
import java.net.*;
import java.io.*;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

// coniks-java imports
import org.coniks.util.Logging;
import org.coniks.coniks_common.MsgType;
import org.coniks.coniks_common.CommonMessaging;

import org.coniks.coniks_common.C2SProtos.Registration;
import org.coniks.coniks_common.C2SProtos.CommitmentReq;
import org.coniks.coniks_common.C2SProtos.KeyLookup;
import org.coniks.coniks_common.C2SProtos.RegistrationResp;
import org.coniks.coniks_common.C2SProtos.AuthPath;
import org.coniks.coniks_common.C2SProtos.*;

import org.coniks.coniks_common.UtilProtos.Hash;
import org.coniks.coniks_common.UtilProtos.Commitment;
import org.coniks.coniks_common.UtilProtos.ServerResp;
import org.coniks.coniks_common.UtilProtos.*;

import java.security.spec.*;
import java.security.interfaces.*;

/** Implements all functions for exchanging CONIKS messages with a CONIKS
 * server.
 *
 *@author Marcela S. Melara (melara@cs.princeton.edu)
 *@author Michael Rochlin
*/
public class ClientMessaging {

    private static DataOutputStream dout;
    private static DataInputStream din;
    private static Socket sock;

    // don't want to have to pass this value from the TestClient
    // in every single function
    private static boolean isFullOp;

    /** Sets the isFullOp flag
     *
     *@param isFull indicates whether the client is operating in
     * full operating mode or in test mode
     */
    public static void setIsFullOp (boolean isFull) {
        isFullOp = isFull;
    }

    /** Sends a Registration protobuf message for the given
     * {@code user} to the {@code server}.
     */
    public static void sendRegistrationProto (ClientUser user,
                                              String server) {

        Registration reg = buildRegistrationMsgProto(user.getUsername(), user.getKeyData(),
                                                     user.getChangePubKey(), user.isAllowsUnsignedChanges(),
                                                     user.isAllowsPublicVisibility());
        sendMsgProto(MsgType.REGISTRATION, reg, server);

    }

    /** Sends a KeyLookup protobuf message with the given
     *   {@code username} and {@code epoch} to
     *  to the {@code server}.
     */
    public static void sendKeyLookupProto (String username, long epoch,
                                              String server) {

        KeyLookup lookup = buildKeyLookupMsgProto(username, epoch);
        sendMsgProto(MsgType.KEY_LOOKUP, lookup, server);

    }

    /** Sends a CommitmentReq protobuf message requesting {@code provider}'s
     *  signed tree root for {@code epoch} from {@code server}.
     *  If the server and provider are the same, {@code commitmentType}
     *  is {@code SELF}, otherwise it is {@code WITNESSED}.
     * (Unused)
    */
    public static void sendCommitmentReqProto (
                                               CommitmentReq.CommitmentType commitmentType,
                                               long epoch, String provider,
                                               String server) {

        CommitmentReq commReq = buildCommitmentReqMsgProto(commitmentType, epoch, provider);
        sendMsgProto(MsgType.COMMITMENT_REQ, commReq, server);

    }

    /** Sends a ULNChangeReq protobuf message for the given {@code user}
     * to the {@code server}.
     */
    public static void sendULNChangeReqProto(ClientUser user, String server) {

        ULNChangeReq changeReq = buildULNChangeReqMsgProto(user.getUsername(), user.getKeyData(),
                                                           user.getChangePubKey(), user.isAllowsUnsignedChanges(),
                                                           user.isAllowsPublicVisibility());
        sendMsgProto(MsgType.ULNCHANGE_REQ, changeReq, server);
    }

    /** Sends a SignedULNChangeReq protobuf for the given user {@code user}
     * including the signature on the the change message {@code sig} to
     * the {@code server}.
     */
    public static void sendSignedULNChangeReqProto(ClientUser user,
                                                   byte[] sig,
                                                   String server) {
        DSAPublicKey changePk = user.getChangePubKey();

        // let's not assume the key is in memory
        if (changePk == null) {
            user.loadChangePubKey();

            // make sure we actually got a key from disk
            if (user.getChangePubKey() == null) {
                Logging.error("ClientMessaging: Could not load change public key");
            }
        }

        ULNChangeReq changeReq = buildULNChangeReqMsgProto(user.getUsername(),
                                                           user.getKeyData(), changePk,
                                                           user.isAllowsUnsignedChanges(), user.isAllowsPublicVisibility());
        SignedULNChangeReq signed = buildSignedULNChangeReqMsgProto(changeReq, sig);
        sendMsgProto(MsgType.SIGNED_ULNCHANGE_REQ, signed, server);
    }

    /* Helper functions for implementing the sending functions */

    /** Sends any protobuf message {@code msg} of type {@code msgType}
     * to the given {@code server}.
     */
    private static void sendMsgProto (int msgType, AbstractMessage msg,
                                String server) {

        try {
            connect(server);

            // now send the message
            dout.writeByte(msgType);
            msg.writeDelimitedTo(dout);
            dout.flush();
        }
        catch (IOException e) {
            Logging.error("Sending msg proto "+msg.toString());
            Logging.error("Error: "+e.getMessage());
        }

    }

    /** Builds the Registration protobuf message with a given
        {@code username}, {@code publicKey} blob, {@code changeKey}, and unsigned key
        changes flag.
    */
    private static Registration buildRegistrationMsgProto(String username, String keyData,
                                                          DSAPublicKey changeKey, boolean allowsUnsignedKeyChange,
                                                          boolean allowsPublicVisibility) {
        Registration.Builder regBuild = Registration.newBuilder();
        regBuild.setName(username);
        regBuild.setBlob(keyData);
        DSAPublicKeyProto ckProto = ClientUtils.buildDSAPublicKeyProto(changeKey);
        regBuild.setChangeKey(ckProto);
        regBuild.setAllowsUnsignedKeychange(allowsUnsignedKeyChange);
        regBuild.setAllowsPublicLookup(allowsPublicVisibility);
        return regBuild.build();
    }

    /** Builds the KeyLookup protobuf message with a given
        {@code username} and {@code epoch}.
    */
    private static KeyLookup buildKeyLookupMsgProto(String username, long epoch) {
        KeyLookup.Builder keyLookupBuild = KeyLookup.newBuilder();
        keyLookupBuild.setName(username);
        keyLookupBuild.setEpoch(epoch);

        return keyLookupBuild.build();
    }

    /** Builds the CommitmentReq protobuf message with a given
        {@code commType}, {@code epoch}, and {@code server}.
    */
    private static CommitmentReq buildCommitmentReqMsgProto (
                                                            CommitmentReq.CommitmentType commType,
                                                            long epoch, String server) {
        CommitmentReq.Builder commReqBuild = CommitmentReq.newBuilder();
        commReqBuild.setType(commType);
        commReqBuild.setEpoch(epoch);
        commReqBuild.setProvider(server);

        return commReqBuild.build();
    }

    /** Builds the ULNChangeReq protobuf message with a given
     * {@code username} {@code blob} {@code changeKey}
     * {@code allowsUnsignedKeychange} {@code allowsPublicLookup}
     *  The blob and changeKey fields may be null if no change is requested
    */
    public static ULNChangeReq buildULNChangeReqMsgProto(String username,
                                                          String blob,
                                                          DSAPublicKey dsa,
                                                          boolean allowsUnsignedKeychange,
                                                          boolean allowsPublicLookup) {
        ULNChangeReq.Builder ulnChangeBuilder = ULNChangeReq.newBuilder();
        ulnChangeBuilder.setName(username);
        if (blob != null) {
            ulnChangeBuilder.setNewBlob(blob);
        }
        if (dsa != null) {
            ulnChangeBuilder.setNewChangeKey(ClientUtils.buildDSAPublicKeyProto(dsa));
        }
        ulnChangeBuilder.setAllowsUnsignedKeychange(allowsUnsignedKeychange);
        ulnChangeBuilder.setAllowsPublicLookup(allowsPublicLookup);
        return ulnChangeBuilder.build();
    }

    /** Builds the SignedULNChangeReq protobuf message with a given
        {@code changeReq} is a ULNChangeReq that was built previously
        {@code sig}.
    */
    private static SignedULNChangeReq buildSignedULNChangeReqMsgProto(ULNChangeReq changeReq,
                                                                      byte[] sig) {

        SignedULNChangeReq.Builder ulnChangeBuilder = SignedULNChangeReq.newBuilder();
        ulnChangeBuilder.setReq(changeReq);
        ulnChangeBuilder.setSig(ByteString.copyFrom(sig));

        return ulnChangeBuilder.build();
    }

    /* Functions for handling messages received from the server */

    /** Receives and parses a RegistrationResp protobuf message
     * from the server.
     *
     *@return The AbstractMessage (either of type ServerResp or RegistrationResp)
     *upon success. {@code null} otherwise.
     */
    public static AbstractMessage receiveRegistrationRespProto() {

        // first receive the generic message from the server
        AbstractMessage serverMsg = receiveMsgProto();

        AbstractMessage regResp = null;

        if (serverMsg != null && serverMsg instanceof ServerResp) {
            regResp = (ServerResp)serverMsg;
        }
        else if (serverMsg != null && serverMsg instanceof RegistrationResp) {
            regResp = (RegistrationResp)serverMsg;
        }

        return regResp;

    }

     /** Receives and parses an AuthPath protobuf message
     * from the server.
     *
     *@return The AbstractMessage (either of type ServerResp or AuthPath)
     *upon success. {@code null} otherwise.
     */
    public static AbstractMessage receiveAuthPathProto() {

        // first receive the generic message from the server
        AbstractMessage serverMsg = receiveMsgProto();

        AbstractMessage authPath = null;

        if (serverMsg != null && serverMsg instanceof ServerResp) {
            authPath = (ServerResp)serverMsg;
        }
        else if (serverMsg != null && serverMsg instanceof AuthPath) {
            authPath = (AuthPath)serverMsg;
        }

        return authPath;

    }

     /** Receives and parses a Commitment protobuf message
     * from the server. (Unused)
     *
     *@return The AbstractMessage (either of type ServerResp or Commitment)
     *upon success. {@code null} otherwise.
     */
    public static AbstractMessage receiveCommitmentProto() {

        // first receive the generic message from the server
        AbstractMessage serverMsg = receiveMsgProto();

        AbstractMessage comm = null;

        if (serverMsg != null && serverMsg instanceof ServerResp) {
            comm = (ServerResp)serverMsg;
        }
        else if (serverMsg != null && serverMsg instanceof Commitment) {
            comm = (Commitment)serverMsg;
        }

        return comm;

    }

    /* Helper functions for implementing the receiving functions */

    /** Receives a protobuf message from the server the client is currently
     * connected to, and checks that the message is correctly formatted
     * for the expected message type.
     * The caller is responsible for handling the exact message type(s).
     *
     *@return The specific protobuf message according to the message type
     * indicated by the server.
     */
    private static AbstractMessage receiveMsgProto () {

        try {
            // get the message type of the message and read in the stream
            int msgType = din.readUnsignedByte();

            // TODO: this should be a signed promise/temporary binding
            if (msgType == MsgType.REGISTRATION_RESP){
                RegistrationResp regResp = RegistrationResp.parseDelimitedFrom(din);

                if(!regResp.hasInitEpoch() || !regResp.hasEpochInterval()){
                    Logging.error("Malformed registration response");
                }
                else {
                    return regResp;
                }
            }
            else if (msgType == MsgType.AUTH_PATH) {
                AuthPath authPath = AuthPath.parseDelimitedFrom(din);

                if (!authPath.hasLeaf() || !authPath.hasRoot()) {
                    Logging.error("Malformed auth path");
                }
                else {
                    return authPath;
                }
            }
            else if (msgType == MsgType.COMMITMENT) {
                Commitment comm = Commitment.parseDelimitedFrom(din);

                if (!comm.hasEpoch() || !comm.hasRootHash()) {
                    Logging.error("Malformed commitment");
                }
                else {
                    return comm;
                }
            }

            // Some error occurred so the server responded with a simple response
            else if (msgType == MsgType.SERVER_RESP) {
                ServerResp resp = ServerResp.parseDelimitedFrom(din);

                if (!resp.hasMessage()) {
                    Logging.error("Malformed simple server response");
                }
                else {
                    return resp;
                }
            }
        }
        catch (InvalidProtocolBufferException e) {
            Logging.error("parsing proto msg: "+e.getMessage());
        }
        catch (IOException e) {
            Logging.error("receiving data from the server: "+e.getMessage());
        }
        finally {
            CommonMessaging.close(din);
        }

        // unexpected message type from the server
        return null;

    }

    /* Functions for handling the lower-level communication with the server */

    /** Establishes an SSL connection to {@code server} if in full operating mode.
     *
     *@param server the CONIKS server to which send the message
     *@param isFullOp indicates whether the client is operating in full mode
     * or in testing mode
     *@throws an {@code IOException} if any of the socket operations fail.
     */
    private static void connect (String server)
        throws IOException {

        if (isFullOp) {
            SSLSocketFactory sslFact =
                (SSLSocketFactory)SSLSocketFactory.getDefault();
            sock = (SSLSocket)sslFact.createSocket(server, ClientConfig.getPort());
        }
        else {
            sock = new Socket(server, ClientConfig.getPort());
        }
        dout = new DataOutputStream(sock.getOutputStream());
        din = new DataInputStream(sock.getInputStream());

    }

}
