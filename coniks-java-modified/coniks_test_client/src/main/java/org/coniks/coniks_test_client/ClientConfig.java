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

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;

/** Sets various configuration parameters 
 * for a CONIKS client.
 *
 *@author Marcela Melara (melara@cs.princeton.edu)
 *@author Michael Rochlin
 */
public class ClientConfig{

    private static final int PORT_IDX = 0;
    private static final int USERKEYS_PATH_IDX = 1;
    private static final int KEYSTORE_PATH_IDX = 2; //unused
    private static final int KEYSTORE_PWD_IDX = 3; //unused
    private static final int TRUSTSTORE_PATH_IDX = 4; //unused
    private static final int TRUSTSTORE_PWD_IDX = 5; //unused

    /** The port number on which the CONIKS server is listening
     */
    private static int PORT = -1;

    /** The path to the saved user keys */
    private static String USER_KEYS_PATH = "";

    /** The path to the client's private DSA key */
    private static String KEYSTORE_PATH = "";

    /** The password to the client's private DSA key */
    private static String KEYSTORE_PWD = "";

    /** The path to the client's trusted certificate store
     */
    private static String TRUSTSTORE_PATH = "";
    
    /** The password to the client's trusted certificate store
     */
    private static String TRUSTSTORE_PWD = "";

    /** Returns the {@code PORT} number at which the CONIKS server is listening.
     */
    public static int getPort() {
        return PORT;
    }

    /** Returns the path to the client's stored user keys {@code USER_KEY_PATH}.
     */
    public static String getUserKeysPath() {
        return USER_KEYS_PATH;
    }

    /** Returns the path to the client's keystore {@code KEYSTORE_PATH}.
     * (Unused)
     */
    public static String getKeystorePath() {
        return KEYSTORE_PATH;
    }

    /** Returns the password to the client's keystore {@code KEYSTORE_PWD}.
     * (Unused)
     */
    public static String getKeystorePassword() {
        return KEYSTORE_PWD;
    }

    /** Returns the path to the client's truststore {@code TRUSTSTORE_PATH}.
     */
    public static String getTruststorePath() {
        return TRUSTSTORE_PATH;
    }

    /** Returns the password to the client's truststore {@code TRUSTSTORE_PWD}.
     */
    public static String getTruststorePassword() {
        return TRUSTSTORE_PWD;
    }

     /** Set a {@link TestClient}'s configuration according to the parameters in
     * {@code configFile}.
     *
     * The client has already checked that {@code configFile} exists, but the
     * parameters in the File may still be malformed. If the client is being tested, 
     * this will skip the truststore parameters.
     *
     *@param configFile the client configuration file
     *@param isFullOp indicates whether the client is being run in full operation mode or testing mode
     *
     *@return {@code true} if the config file can be read in successfully, {@code false}
     * if an exception occurs, which will cause the client to halt.
     */
    public static boolean readClientConfig(File configFile, boolean isFullOp) {
        
        try {
            Scanner in = new Scanner (new FileInputStream(configFile));

            // read in all configs from the file, assumes they are in the following order
            ArrayList<String> configs = new ArrayList<String>();
            while (in.hasNextLine()) {
                configs.add(in.nextLine());
            }

            in.close();
            
            PORT = Integer.parseInt(configs.get(PORT_IDX));
            USER_KEYS_PATH = configs.get(USERKEYS_PATH_IDX);
            //KEYSTORE_PATH = configs.get(KEYSTORE_PATH_IDX);
            //KEYSTORE_PWD = configs.get(KEYSTORE_PWD_IDX);

            // skip these if we're testing the client
            if (isFullOp) {
                TRUSTSTORE_PATH = configs.get(TRUSTSTORE_PATH_IDX);
                TRUSTSTORE_PWD = configs.get(TRUSTSTORE_PWD_IDX);
            }
            
            return true;
        }
        catch (Exception e) {
            // caution the configuration may still have default values at this point
            System.out.println("Error in ClientConfig: "+e.getMessage());
        }

        return false;
    }

    public static void useDefaultConfig() {
        PORT = 40012;
        USER_KEYS_PATH = "keys";
    }

}
