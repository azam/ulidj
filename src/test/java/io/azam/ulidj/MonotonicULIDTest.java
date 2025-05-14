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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link io.azam.ulidj.MonotonicULID}
 *
 * @author azam
 * @since 1.0.3
 */
public class MonotonicULIDTest {
  @Test
  public void testConstructor() {
    Assertions.assertNotNull(new MonotonicULID());
    Assertions.assertNotNull(new MonotonicULID(new Random()));
    Assertions.assertNotNull(new MonotonicULID(new SecureRandom()));
  }

  @Test
  public void testConstructorNullRandom() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new MonotonicULID(null);
    });
  }

  @Test
  public void testGenerate() {
    MonotonicULID ulid = new MonotonicULID();
    String value = ulid.generate();
    Assertions.assertNotNull(value);
    Assertions.assertTrue(ULID.isValid(value));
  }

  @Test
  public void testGenerateBinary() {
    MonotonicULID ulid = new MonotonicULID();
    byte[] value = ulid.generateBinary();
    Assertions.assertNotNull(value);
    Assertions.assertTrue(ULID.isValidBinary(value));
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
        Assertions.assertNotNull(value);
        Assertions.assertTrue(ULID.isValid(value));
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
            Assertions.assertArrayEquals(TestUtils.incrementBytes(prev), curr);
            prev = curr;
          }
        }
      }
    }
  }

  @Test
  public void testGenerateBinaryConcurrent() {
    MonotonicULID ulid = new MonotonicULID();
    boolean hasSameTimestamp = false;
    // This test might not end if we cannot generate multiple ULID in the same
    // milliseconds. Unless we are running on really slow CPU, we should be OK.
    while (!hasSameTimestamp) {
      List<byte[]> values = new ArrayList<byte[]>();
      // Generate a bunch of ULIDS
      // Values are inserted in order
      for (int i = 0; i < 10000; i++) {
        values.add(ulid.generateBinary());
      }
      // Group into timestamp bucket
      Map<Long, List<byte[]>> groups = new HashMap<Long, List<byte[]>>();
      for (byte[] value : values) {
        Assertions.assertNotNull(value);
        Assertions.assertTrue(ULID.isValidBinary(value));
        long ts = ULID.getTimestampBinary(value);
        byte[] entropy = ULID.getEntropyBinary(value);
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
            Assertions.assertArrayEquals(TestUtils.incrementBytes(prev), curr);
            prev = curr;
          }
        }
      }
    }
  }

  @Test
  public void testGenerateOverflow() {
    // Using a random generator that always return 0xff... so that next increment on
    // the same timestamp will throw exception
    MonotonicULID ulid = new MonotonicULID(new FixedRandom(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));

    Assertions.assertThrows(IllegalStateException.class, () -> {
      List<String> values = new ArrayList<String>();
      for (int i = 0; i < 1000000; i++) {
        values.add(ulid.generate());
      }
    });
  }

  @Test
  public void testGenerateBinaryOverflow() {
    // Using a random generator that always return 0xff... so that next increment on
    // the same timestamp will throw exception
    MonotonicULID ulid = new MonotonicULID(new FixedRandom(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));
    Assertions.assertThrows(IllegalStateException.class, () -> {
      List<byte[]> values = new ArrayList<byte[]>();
      for (int i = 0; i < 1000000; i++) {
        values.add(ulid.generateBinary());
      }
    });
  }

}
