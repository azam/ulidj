/**
 * MIT License
 *
 * Copyright (c) 2016 Azamshul Azizy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.azam.ulidj;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link io.azam.ulidj.MonotonicULID}
 *
 * @author azam
 * @since 1.0.3
 */
public class MonotonicULIDTest {
  @Test
  public void testConstructor() {
    Assert.assertNotNull(new MonotonicULID());
    Assert.assertNotNull(new MonotonicULID(new Random()));
    Assert.assertNotNull(new MonotonicULID(new SecureRandom()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullRandom() {
    new MonotonicULID(null);
  }

  @Test
  public void testGenerate() {
    MonotonicULID ulid = new MonotonicULID();
    String id = ulid.generate();
    Assert.assertNotNull(id);
    Assert.assertTrue(ULID.isValid(id));
  }

  @Test
  public void testGenerateConcurrent() {
    MonotonicULID ulid = new MonotonicULID();
    boolean hasSameTimestamp = false;
    // This test might not end if we cannot generate multiple ULID in the same
    // milliseconds. Unless we are running on really slow CPU, we should be OK.
    while (!hasSameTimestamp) {
      List<String> values = new ArrayList<String>();
      // Generate a bunch of ULIDS
      // Values are inserted in order
      for (int i = 0; i < 10000; i++) {
        values.add(ulid.generate());
      }
      // Group into timestamp bucket
      Map<Long, List<byte[]>> groups = new HashMap<Long, List<byte[]>>();
      for (String value : values) {
        Assert.assertNotNull(value);
        Assert.assertTrue(ULID.isValid(value));
        long ts = ULID.getTimestamp(value);
        byte[] entropy = ULID.getEntropy(value);
        if (!groups.containsKey(ts)) {
          groups.put(ts, new ArrayList<byte[]>());
        }
        groups.get(ts).add(entropy);
      }
      // For each timestamp bucket check if entropy is monotonic
      for (long ts : groups.keySet()) {
        // Loop until we have a bucket of 5 ids on the same timestamp
        if (groups.get(ts).size() > 4) {
          // Escape loop on next while eval
          hasSameTimestamp = true;
          List<byte[]> bucketValues = groups.get(ts);
          // Values are inserted in order so we don't have to sort
          byte[] prev = bucketValues.get(0);
          for (int i = 1; i < bucketValues.size(); i++) {
            byte[] curr = bucketValues.get(i);
            // The next value on the same timestamp is an increment of 1-bit if the previous
            // value
            Assert.assertArrayEquals(incrementBytes(prev), curr);
            prev = curr;
          }
        }
      }
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testGenerateOverflow() {
    // Using a random generator that always return 0xff... so that next increment on
    // the same timestamp will throw exception
    MonotonicULID ulid = new MonotonicULID(new FixedRandom(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));
    List<String> values = new ArrayList<String>();
    //
    for (int i = 0; i < 1000000; i++) {
      values.add(ulid.generate());
    }
  }

  @Test
  public void testIncrementBytes() {
    Assert.assertArrayEquals(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01 //
    }, incrementBytes(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 //
    }));
    Assert.assertArrayEquals(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff //
    }, incrementBytes(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfe //
    }));
    Assert.assertArrayEquals(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00 //
    }, incrementBytes(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff //
    }));
    Assert.assertArrayEquals(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }, incrementBytes(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe //
    }));
    Assert.assertArrayEquals(null, incrementBytes(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));
  }

  byte[] incrementBytes(byte[] bytes) {
    if (bytes == null || bytes.length != ULID.ENTROPY_LENGTH)
      return null;
    byte[] value = new byte[ULID.ENTROPY_LENGTH];

    boolean carry = true;
    for (int i = ULID.ENTROPY_LENGTH - 1; i >= 0; i--) {
      byte work = bytes[i];
      if (carry) {
        work = (byte) (work + 0x01);
        carry = bytes[i] == (byte) 0xff && carry;
      }
      value[i] = work;
    }
    // Last byte has carry over
    if (carry) {
      return null;
    }

    return value;
  }

  class FixedRandom extends Random {
    private final byte[] bytes;

    public FixedRandom(byte[] bytes) {
      this.bytes = bytes;
    }

    @Override
    public void nextBytes(byte[] out) {
      for (int i = 0; i < out.length; i++) {
        if (i < this.bytes.length)
          out[i] = this.bytes[i];
        else
          out[i] = 0x00;
      }
    }
  }

}
