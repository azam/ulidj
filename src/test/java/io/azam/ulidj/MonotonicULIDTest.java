/*
 * MIT License
 *
 * Copyright (c) 2016-2025 Azamshul Azizy
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
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static io.azam.ulidj.TestUtils.assertEntropyIsIncremented;
import static io.azam.ulidj.TestUtils.incrementBytes;
import static io.azam.ulidj.ULIDTest.FILLED_ENTROPY;
import static io.azam.ulidj.ULIDTest.TEST_CLOCK;
import static io.azam.ulidj.ULIDTest.TEST_RANDOM;
import static io.azam.ulidj.ULIDTest.TEST_TIMESTAMP;
import static io.azam.ulidj.ULIDTest.ZERO_ENTROPY;
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
    assertNotNull(new MonotonicULID(ThreadLocalRandom.current()));
    assertThrows(IllegalArgumentException.class, () -> {
      Random random = null;
      new MonotonicULID(random);
    });
    assertNotNull(new MonotonicULID(Clock.systemDefaultZone()));
    assertNotNull(new MonotonicULID(Clock.fixed(Instant.ofEpochMilli(0L), ZoneId.systemDefault())));
    assertThrows(IllegalArgumentException.class, () -> {
      Clock clock = null;
      new MonotonicULID(clock);
    });
    assertNotNull(new MonotonicULID(Clock.systemDefaultZone(), new Random()));
    assertThrows(IllegalArgumentException.class, () -> {
      Random random = null;
      Clock clock = null;
      new MonotonicULID(clock, random);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      Random random = new Random();
      Clock clock = null;
      new MonotonicULID(clock, random);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      Random random = null;
      Clock clock = Clock.systemDefaultZone();
      new MonotonicULID(clock, random);
    });
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
  public void testGenerateULIDExternalRandom() {
    MonotonicULID ulid = new MonotonicULID(TEST_RANDOM);
    ULID value = ulid.generateULID();
    assertNotNull(value, "ULID value should not be null");
    assertArrayEquals(FILLED_ENTROPY, value.getEntropy(),
        "Binary ULID entropy should be filled with the provided random");
  }

  @Test
  public void testGenerateExternalClock() {
    MonotonicULID ulid = new MonotonicULID(TEST_CLOCK);
    String value = ulid.generate();
    assertNotNull(value, "ULID value should not be null");
    assertTrue(ULID.isValid(value), "ULID value must be valid");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(value),
        "ULID timestamp should be filled with the provided clock");
  }

  @Test
  public void testGenerateBinaryExternalClock() {
    MonotonicULID ulid = new MonotonicULID(TEST_CLOCK);
    byte[] value = ulid.generateBinary();
    assertNotNull(value, "Binary ULID value should not be null");
    assertTrue(ULID.isValidBinary(value), "Binary ULID value must be valid");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(value),
        "Binary ULID timestamp should be filled with the provided clock");
  }

  @Test
  public void testGenerateULIDExternalClock() {
    MonotonicULID ulid = new MonotonicULID(TEST_CLOCK);
    ULID value = ulid.generateULID();
    assertNotNull(value, "ULID value should not be null");
    assertEquals(TEST_TIMESTAMP, value.getTimestamp(),
        "Binary ULID timestamp should be filled with the provided clock");
  }

  @Test
  public void testGenerateExternalRandomAndClock() {
    MonotonicULID ulid = new MonotonicULID(TEST_CLOCK, TEST_RANDOM);
    String value = ulid.generate();
    assertNotNull(value, "ULID value should not be null");
    assertTrue(ULID.isValid(value), "ULID value must be valid");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropy(value),
        "ULID entropy should be filled with the provided random");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(value),
        "ULID timestamp should be filled with the provided clock");
  }

  @Test
  public void testGenerateBinaryExternalClockAndRandom() {
    MonotonicULID ulid = new MonotonicULID(TEST_CLOCK, TEST_RANDOM);
    byte[] value = ulid.generateBinary();
    assertNotNull(value, "Binary ULID value should not be null");
    assertTrue(ULID.isValidBinary(value), "Binary ULID value must be valid");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropyBinary(value),
        "Binary ULID entropy should be filled with the provided random");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(value),
        "Binary ULID timestamp should be filled with the provided clock");
  }

  @Test
  public void testGenerateULIDExternalClockAndRandom() {
    MonotonicULID ulid = new MonotonicULID(TEST_CLOCK, TEST_RANDOM);
    ULID value = ulid.generateULID();
    assertNotNull(value, "Binary ULID value should not be null");
    assertArrayEquals(FILLED_ENTROPY, value.getEntropy(),
        "Binary ULID entropy should be filled with the provided random");
    assertEquals(TEST_TIMESTAMP, value.getTimestamp(),
        "Binary ULID timestamp should be filled with the provided clock");
  }

  @Test
  public void testGenerateConcurrent() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    String ulid1 = ulid.generate();
    assertTrue(ULID.isValid(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropy(ulid1));
    // Same timestamp as base, entropy should be incremented
    String ulid2 = ulid.generate();
    assertTrue(ULID.isValid(ulid2));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid2));
    assertEntropyIsIncremented(ulid1, ulid2);
    // Fast forward clock 1ms to the future
    clock.tick(Duration.ofMillis(1));
    assertEquals(TEST_TIMESTAMP + 1, clock.instant().toEpochMilli());
    // Base timestamp + 1ms, entropy should reset
    String ulid3 = ulid.generate();
    assertTrue(ULID.isValid(ulid3));
    assertEquals(TEST_TIMESTAMP + 1, ULID.getTimestamp(ulid3));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropy(ulid3));
  }

  @Test
  public void testGenerateBinaryConcurrent() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    byte[] ulid1 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropyBinary(ulid1));
    // Same timestamp as base, entropy should be incremented
    byte[] ulid2 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid2));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid2));
    assertEntropyIsIncremented(ulid1, ulid2);
    // Fast forward clock 1ms to the future
    clock.tick(Duration.ofMillis(1));
    assertEquals(TEST_TIMESTAMP + 1, clock.instant().toEpochMilli());
    // Base timestamp + 1ms, entropy should reset
    byte[] ulid3 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid3));
    assertEquals(TEST_TIMESTAMP + 1, ULID.getTimestampBinary(ulid3));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropyBinary(ulid3));
  }

  @Test
  public void testGenerateConcurrentEntropyOverflow() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(FILLED_ENTROPY));
    // Base
    String ulid1 = ulid.generate();
    assertTrue(ULID.isValid(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid1));
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropy(ulid1));
    // Entropy overflow, should throw exception
    assertThrows(IllegalStateException.class, () -> {
      String ulid2 = ulid.generate();
    }, "Entropy is filled, so the next generation in the same timestamp should fail");
    // Entropy overflow, should throw exception
    assertThrows(IllegalStateException.class, () -> {
      String ulid2 = ulid.generate();
    }, "Subsequent generations should also fail");
  }

  @Test
  public void testGenerateBinaryConcurrentEntropyOverflow() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(FILLED_ENTROPY));
    // Base
    byte[] ulid1 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid1));
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropyBinary(ulid1));
    // Entropy overflow, should throw exception
    assertThrows(IllegalStateException.class, () -> {
      byte[] ulid2 = ulid.generateBinary();
    }, "Entropy is filled, so the next generation in the same timestamp should fail");
    // Entropy overflow, should throw exception
    assertThrows(IllegalStateException.class, () -> {
      byte[] ulid2 = ulid.generateBinary();
    }, "Subsequent generations should also fail");
  }

  @Test
  public void testGenerateTimestampUnderflow() {
    Instant instant = Instant.ofEpochMilli(ULID.MIN_TIME);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    String ulid1 = ulid.generate();
    assertTrue(ULID.isValid(ulid1));
    assertEquals(ULID.MIN_TIME, ULID.getTimestamp(ulid1));
    // Default last timestamp is ULID.MIN_TIME with last entropy of ULID.ZERO_ENTROPY
    // so this is should be incremented
    assertArrayEquals(incrementBytes(ZERO_ENTROPY), ULID.getEntropy(ulid1));
    // Reveerse clock 1ms to the past
    clock.tick(Duration.ofMillis(-1));
    assertEquals(ULID.MIN_TIME - 1, clock.instant().toEpochMilli());
    // Timestamp is out of range (lesser than ULID.MIN_TIME), should throw exception
    assertThrows(IllegalStateException.class, () -> {
      String ulid2 = ulid.generate();
    }, "Timestamp is lesser than ULID.MIN_TIME, so the next generation should fail");
    assertThrows(IllegalStateException.class, () -> {
      String ulid2 = ulid.generate();
    }, "Subsequent generations should also fail");
  }

  @Test
  public void testGenerateBinaryTimestampUnderflow() {
    Instant instant = Instant.ofEpochMilli(ULID.MIN_TIME);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    byte[] ulid1 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid1));
    assertEquals(ULID.MIN_TIME, ULID.getTimestampBinary(ulid1));
    // Default last timestamp is ULID.MIN_TIME with last entropy of ULID.ZERO_ENTROPY
    // so this is should be incremented
    assertArrayEquals(incrementBytes(ZERO_ENTROPY), ULID.getEntropyBinary(ulid1));
    // Reveerse clock 1ms to the past
    clock.tick(Duration.ofMillis(-1));
    assertEquals(ULID.MIN_TIME - 1, clock.instant().toEpochMilli());
    // Timestamp is out of range (lesser than ULID.MIN_TIME), should throw exception
    assertThrows(IllegalStateException.class, () -> {
      byte[] ulid2 = ulid.generateBinary();
    }, "Timestamp is lesser than ULID.MIN_TIME, so the next generation should fail");
    assertThrows(IllegalStateException.class, () -> {
      byte[] ulid2 = ulid.generateBinary();
    }, "Subsequent generations should also fail");
  }

  @Test
  public void testGenerateTimestampOverflow() {
    Instant instant = Instant.ofEpochMilli(ULID.MAX_TIME);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    String ulid1 = ulid.generate();
    assertTrue(ULID.isValid(ulid1));
    assertEquals(ULID.MAX_TIME, ULID.getTimestamp(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropy(ulid1));
    // Fast forward clock 1ms to the future
    clock.tick(Duration.ofMillis(1));
    assertEquals(ULID.MAX_TIME + 1, clock.instant().toEpochMilli());
    // Timestamp is out of range (greater than ULID.MAX_TIME), should throw exception
    assertThrows(IllegalStateException.class, () -> {
      String ulid2 = ulid.generate();
    }, "Timestamp is greater than ULID.MAX_TIME, so the next generation should fail");
    assertThrows(IllegalStateException.class, () -> {
      String ulid2 = ulid.generate();
    }, "Subsequent generations should also fail");
  }

  @Test
  public void testGenerateBinaryTimestampOverflow() {
    Instant instant = Instant.ofEpochMilli(ULID.MAX_TIME);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    byte[] ulid1 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid1));
    assertEquals(ULID.MAX_TIME, ULID.getTimestampBinary(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropyBinary(ulid1));
    // Fast forward clock 1ms to the future
    clock.tick(Duration.ofMillis(1));
    assertEquals(ULID.MAX_TIME + 1, clock.instant().toEpochMilli());
    // Timestamp is out of range (greater than ULID.MAX_TIME), should throw exception
    assertThrows(IllegalStateException.class, () -> {
      byte[] ulid2 = ulid.generateBinary();
    }, "Timestamp is greater than ULID.MAX_TIME, so the next generation should fail");
    assertThrows(IllegalStateException.class, () -> {
      byte[] ulid2 = ulid.generateBinary();
    }, "Subsequent generations should also fail");
  }

  @Test
  public void testGenerateClockMovedBackwards() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    String ulid1 = ulid.generate();
    assertTrue(ULID.isValid(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropy(ulid1));
    // Reverse clock 1ms to the past
    clock.tick(Duration.ofMillis(-1));
    assertEquals(TEST_TIMESTAMP - 1, clock.instant().toEpochMilli());
    // Timestamp is lesser than MonotonicULID.lastTimestamp,
    // should use MonotonicULID.lastTimestamp and increment entropy
    String ulid2 = ulid.generate();
    assertTrue(ULID.isValid(ulid2));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid2));
    assertEntropyIsIncremented(ulid1, ulid2);
    // Timestamp is still lesser than MonotonicULID.lastTimestamp,
    // should use MonotonicULID.lastTimestamp and increment entropy
    String ulid3 = ulid.generate();
    assertTrue(ULID.isValid(ulid3));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid3));
    assertEntropyIsIncremented(ulid2, ulid3);
  }

  @Test
  public void testGenerateBinaryClockMovedBackwards() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    byte[] ulid1 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropyBinary(ulid1));
    // Reverse clock 1ms to the past
    clock.tick(Duration.ofMillis(-1));
    assertEquals(TEST_TIMESTAMP - 1, clock.instant().toEpochMilli());
    // Timestamp is lesser than MonotonicULID.lastTimestamp,
    // should use MonotonicULID.lastTimestamp and increment entropy
    byte[] ulid2 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid2));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid2));
    assertEntropyIsIncremented(ulid1, ulid2);
    // Timestamp is still lesser than MonotonicULID.lastTimestamp,
    // should use MonotonicULID.lastTimestamp and increment entropy
    byte[] ulid3 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid3));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid3));
    assertEntropyIsIncremented(ulid2, ulid3);
  }

  @Test
  public void testGenerateClockMovedForwards() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    String ulid1 = ulid.generate();
    assertTrue(ULID.isValid(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropy(ulid1));
    // Fast forward clock 1ms to the future
    clock.tick(Duration.ofMillis(1));
    assertEquals(TEST_TIMESTAMP + 1, clock.instant().toEpochMilli());
    // Timestamp is greater than MonotonicULID.lastTimestamp,
    // should use generate new entropy
    String ulid2 = ulid.generate();
    assertTrue(ULID.isValid(ulid2));
    assertEquals(TEST_TIMESTAMP + 1, ULID.getTimestamp(ulid2));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropy(ulid2));
    // Timestamp is same MonotonicULID.lastTimestamp,
    // should increment entropy
    String ulid3 = ulid.generate();
    assertTrue(ULID.isValid(ulid3));
    assertEquals(TEST_TIMESTAMP + 1, ULID.getTimestamp(ulid3));
    assertEntropyIsIncremented(ulid2, ulid3);
  }

  @Test
  public void testGenerateBinaryClockMovedForwards() {
    Instant instant = Instant.ofEpochMilli(TEST_TIMESTAMP);
    MutableFixedClock clock = new MutableFixedClock(instant, ZoneOffset.UTC);
    MonotonicULID ulid = new MonotonicULID(clock, new FixedRandom(ZERO_ENTROPY));
    // Base
    byte[] ulid1 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid1));
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(ulid1));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropyBinary(ulid1));
    // Fast forward clock 1ms to the future
    clock.tick(Duration.ofMillis(1));
    assertEquals(TEST_TIMESTAMP + 1, clock.instant().toEpochMilli());
    // Timestamp is greater than MonotonicULID.lastTimestamp,
    // should use generate new entropy
    byte[] ulid2 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid2));
    assertEquals(TEST_TIMESTAMP + 1, ULID.getTimestampBinary(ulid2));
    assertArrayEquals(ZERO_ENTROPY, ULID.getEntropyBinary(ulid2));
    // Timestamp is same MonotonicULID.lastTimestamp,
    // should increment entropy
    byte[] ulid3 = ulid.generateBinary();
    assertTrue(ULID.isValidBinary(ulid3));
    assertEquals(TEST_TIMESTAMP + 1, ULID.getTimestampBinary(ulid3));
    assertEntropyIsIncremented(ulid2, ulid3);
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
