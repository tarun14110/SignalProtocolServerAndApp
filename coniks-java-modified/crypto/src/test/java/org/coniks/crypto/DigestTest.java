/*
  Copyright (c) 2016, Princeton University.
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

package org.coniks.crypto;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import static org.hamcrest.core.StringContains.containsString;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Unit tests for Digest.
 */
public class DigestTest
{

    @Test
    public void testDigest()
        throws NoSuchAlgorithmException {

        byte[] msg = "message".getBytes();

        byte[] hash = Digest.digest(msg);

        if(hash.length != Digest.HASH_SIZE_BYTES) {
            fail("Computation of hash failed - wrong length.");
        }

        assertNotNull("Computation of hash failed - null", hash);

        assertFalse("Computation of hash failed - hash is all zeros",
                    Arrays.equals(hash, new byte[Digest.HASH_SIZE_BYTES]));

    }

    @Test
    public void testMakeRand()
        throws NoSuchAlgorithmException {

        byte[] r = Digest.makeRand();

        assertFalse("makeRand failed - hash of all zeros",
                    Arrays.equals(r,
                    Digest.digest(new byte[Digest.HASH_SIZE_BYTES])));

        if (r.length != Digest.HASH_SIZE_BYTES) {
            fail("Digest of random number failed - wrong length.");
        }
    }
}
