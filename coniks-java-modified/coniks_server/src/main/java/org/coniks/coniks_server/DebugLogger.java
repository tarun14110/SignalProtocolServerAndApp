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

package org.coniks.coniks_server;

import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;

// coniks-java imports
import org.coniks.util.Logging;

/** Implements a logger used for debugging the CONIKS
 * server.
 *
 *@author Marcela S. Melara (melara@cs.princeton.edu)
 */
public class DebugLogger {

    private static Logger logger;

    /** Sets up a debug logger for the CONIKS server.
     *
     *@param debugLog the file name for this logger.
     *
     *@deprecated Replaced by
     * {@link org.coniks.util.Logging#setup(String, String)}.
     */
    @Deprecated
    public static void setup (String debugLog) {
        logger = Logger.getLogger("ConiksLogger-Debug");
        
        // suppress the logging output to the console
        logger.setUseParentHandlers(false);
        
        logger.setLevel(Level.INFO);

        try {
             FileHandler handler = new FileHandler(debugLog, 
                                                  Logging.MAX_BYTES_LOGGED_PER_FILE,
                                                  Logging.MAX_NUM_LOG_FILES, true);
        
            // create a TXT formatter
            SimpleFormatter fmt = new SimpleFormatter();
            handler.setFormatter(fmt);
            
            // add the handler
            logger.addHandler(handler);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /** Writes an information message {@code msg}
     * to the debug log.
     *
     *@deprecated Replaced by
     * {@link org.coniks.util.Logging#log(String)}.
     */
    @Deprecated
    public static void log (String msg) {
        logger.info(msg);
    }

    /** Writes a severe error message {@code msg}
     * to the debug log.
     *
     *@deprecated Replaced by
     * {@link org.coniks.util.Logging#error(String)}.
     */
    @Deprecated
    public static void error (String msg) {
        logger.severe(msg);
    }
    
}
