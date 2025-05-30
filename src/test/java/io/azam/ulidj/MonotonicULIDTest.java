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

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import static io.azam.ulidj.TestUtils.incrementBytes;
import static io.azam.ulidj.ULIDTest.FILLED_ENTROPY;
import static io.azam.ulidj.ULIDTest.TEST_RANDOM;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link io.azam.ulidj.MonotonicULID}
 *
 * @author azam
 * @since 1.0.3
 */
public class MonotonicULIDTest {
  @Test
  public void testConstructor() {
    assertNotNull(new MonotonicULID());
    assertNotNull(new MonotonicULID(new Random()));
    assertNotNull(new MonotonicULID(new SecureRandom()));
    Random nullRandom = null;
    assertNotNull(new MonotonicULID(nullRandom));
  }

  @Test
  public void testGenerate() {
    MonotonicULID ulid = new MonotonicULID();
    String value = ulid.generate();
    assertNotNull(value);
    assertTrue(ULID.isValid(value));
  }

  @Test
  public void testGenerateBinary() {
    MonotonicULID ulid = new MonotonicULID();
    byte[] value = ulid.generateBinary();
    assertNotNull(value);
    assertTrue(ULID.isValidBinary(value));
  }

  @Test
  public void testGenerateULID() {
    MonotonicULID ulid = new MonotonicULID();
    ULID value = ulid.generateULID();
    assertNotNull(value);
    assertEquals(ULID.class, value.getClass());
    assertNotNull(value.toString(), "ULID instance string value should not be null");
    assertTrue(ULID.isValid(value.toString()), "ULID instance string value must be valid");
    assertNotNull(value.toBinary(), "ULID instance binary value should not be null");
    assertTrue(ULID.isValidBinary(value.toBinary()), "ULID instance binary value must be valid");
  }

  @Test
  public void testGenerateExternalRandom() {
    MonotonicULID ulid = new MonotonicULID(TEST_RANDOM);
    String value = ulid.generate();
    assertNotNull(value, "ULID value should not be null");
    assertTrue(ULID.isValid(value), "ULID value must be valid");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropy(value),
        "ULID entropy should be filled with the provided random");
  }

  @Test
  public void testGenerateBinaryExternalRandom() {
    MonotonicULID ulid = new MonotonicULID(TEST_RANDOM);
    byte[] value = ulid.generateBinary();
    assertNotNull(value, "Binary ULID value should not be null");
    assertTrue(ULID.isValidBinary(value), "Binary ULID value must be valid");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropyBinary(value),
        "Binary ULID entropy should be filled with the provided random");
  }

  @Test
  public void testGenerateConcurrentNonDeterministic() {
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
        assertNotNull(value);
        assertTrue(ULID.isValid(value));
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
            assertArrayEquals(incrementBytes(prev), curr);
            prev = curr;
          }
        }
      }
    }
  }

  @Test
  public void testGenerateBinaryConcurrentNonDeterministic() {
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
        assertNotNull(value);
        assertTrue(ULID.isValidBinary(value));
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
            assertArrayEquals(incrementBytes(prev), curr);
            prev = curr;
          }
        }
      }
    }
  }

  @Test
  public void testGenerateOverflowNonDeterministic() {
    // Using a random generator that always return 0xff... so that next increment on
    // the same timestamp will throw exception
    final MonotonicULID ulid = new MonotonicULID(new FixedRandom(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));
    assertThrows(IllegalStateException.class, () -> {
      List<String> values = new ArrayList<String>();
      for (int i = 0; i < 1000000; i++) {
        values.add(ulid.generate());
      }
    });
  }

  @Test
  public void testGenerateBinaryOverflowNonDeterministic() {
    // Using a random generator that always return 0xff... so that next increment on
    // the same timestamp will throw exception
    final MonotonicULID ulid = new MonotonicULID(new FixedRandom(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));
    assertThrows(IllegalStateException.class, () -> {
      List<byte[]> values = new ArrayList<byte[]>();
      for (int i = 0; i < 1000000; i++) {
        values.add(ulid.generateBinary());
      }
    });
  }

  @Test
  public void testRandom() {
    String value = MonotonicULID.random();
    assertNotNull(value);
    assertTrue(ULID.isValid(value));
  }

  @Test
  public void testRandomBinary() {
    byte[] value = MonotonicULID.randomBinary();
    assertNotNull(value);
    assertTrue(ULID.isValidBinary(value));
  }

  @Test
  public void testRandomULID() {
    ULID value = MonotonicULID.randomULID();
    assertNotNull(value);
    assertTrue(ULID.isValid(value.toString()));
    assertTrue(ULID.isValidBinary(value.toBinary()));
  }
}
