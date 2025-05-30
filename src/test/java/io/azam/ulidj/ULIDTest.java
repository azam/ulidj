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

import java.lang.reflect.Constructor;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Random;

import static io.azam.ulidj.TestUtils.assertEntropyIsIncremented;
import static io.azam.ulidj.TestUtils.bytesToInitializer;
import static io.azam.ulidj.TestUtils.incrementBytes;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link io.azam.ulidj.ULID}
 *
 * @author azam
 * @since 0.0.1
 */
public class ULIDTest {
  public static final byte[] ZERO_ENTROPY = new byte[] { //
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 //
  };
  public static final byte[] FILLED_ENTROPY = new byte[] { //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
  };
  public static final long TEST_TIMESTAMP = 946652400000L; // 2000-01-01T00:00:00Z
  public static final Clock TEST_CLOCK =
      Clock.fixed(Instant.ofEpochMilli(TEST_TIMESTAMP), ZoneOffset.UTC);
  public static final Random TEST_RANDOM = new FixedRandom(FILLED_ENTROPY);

  private static class TestParam {
    public final long timestamp;
    public final byte[] entropy;
    public final String value;
    public final String reproducer;

    public TestParam(long timestamp, byte[] entropy, String value) {
      this.timestamp = timestamp;
      this.entropy = entropy;
      this.value = value;
      StringBuilder sb = new StringBuilder();
      sb.append("ULID.generate(").append(Long.toString(timestamp)).append("L,")
          .append(bytesToInitializer(entropy)).append(")");
      this.reproducer = sb.toString();
    }
  }

  private static final TestParam[] TEST_PARAMETERS = new TestParam[] { //
      new TestParam(ULID.MIN_TIME, ZERO_ENTROPY, "00000000000000000000000000"), //
      new TestParam(ULID.MIN_TIME, FILLED_ENTROPY, "0000000000ZZZZZZZZZZZZZZZZ"), //
      new TestParam(ULID.MAX_TIME, ZERO_ENTROPY, "7ZZZZZZZZZ0000000000000000"), //
      new TestParam(ULID.MIN_TIME, FILLED_ENTROPY, "0000000000ZZZZZZZZZZZZZZZZ"), //
      new TestParam(ULID.MAX_TIME, FILLED_ENTROPY, "7ZZZZZZZZZZZZZZZZZZZZZZZZZ"), //
      new TestParam(0x0000000000000001L, ZERO_ENTROPY, "00000000010000000000000000"), //
      new TestParam(0x000000000000000fL, ZERO_ENTROPY, "000000000F0000000000000000"), //
      new TestParam(0x0000000000000010L, ZERO_ENTROPY, "000000000G0000000000000000"), //
      new TestParam(0x0000000000000011L, ZERO_ENTROPY, "000000000H0000000000000000"), //
      new TestParam(0x000000000000001fL, ZERO_ENTROPY, "000000000Z0000000000000000"), //
      new TestParam(0x0000000000000020L, ZERO_ENTROPY, "00000000100000000000000000"), //
      new TestParam(0x0000000000000021L, ZERO_ENTROPY, "00000000110000000000000000"), //
      new TestParam(0x000000000000002fL, ZERO_ENTROPY, "000000001F0000000000000000"), //
      new TestParam(0x0000000000000030L, ZERO_ENTROPY, "000000001G0000000000000000"), //
      new TestParam(0x0000000000000031L, ZERO_ENTROPY, "000000001H0000000000000000"), //
      new TestParam(0x000000000000003fL, ZERO_ENTROPY, "000000001Z0000000000000000"), //
      new TestParam(0x0000000000000040L, ZERO_ENTROPY, "00000000200000000000000000"), //
      new TestParam(0x00000000000000f0L, ZERO_ENTROPY, "000000007G0000000000000000"), //
      new TestParam(0x00000000000000ffL, ZERO_ENTROPY, "000000007Z0000000000000000"), //
      new TestParam(0x0000000000000100L, ZERO_ENTROPY, "00000000800000000000000000"), //
      new TestParam(0x0000000000000101L, ZERO_ENTROPY, "00000000810000000000000000"), //
      new TestParam(0x00000000000001ffL, ZERO_ENTROPY, "00000000FZ0000000000000000"), //
      new TestParam(0x0000000000000200L, ZERO_ENTROPY, "00000000G00000000000000000"), //
      new TestParam(0x0000000000000201L, ZERO_ENTROPY, "00000000G10000000000000000"), //
      new TestParam(0x00000000000002ffL, ZERO_ENTROPY, "00000000QZ0000000000000000"), //
      new TestParam(0x0000000000000300L, ZERO_ENTROPY, "00000000R00000000000000000"), //
      new TestParam(0x0000000000000301L, ZERO_ENTROPY, "00000000R10000000000000000"), //
      new TestParam(0x00000000000003ffL, ZERO_ENTROPY, "00000000ZZ0000000000000000"), //
      new TestParam(0x0000000000000400L, ZERO_ENTROPY, "00000001000000000000000000"), //
      new TestParam(0x0000000000000401L, ZERO_ENTROPY, "00000001010000000000000000"), //
      new TestParam(0x00000000000007ffL, ZERO_ENTROPY, "00000001ZZ0000000000000000"), //
      new TestParam(0x0000000000000800L, ZERO_ENTROPY, "00000002000000000000000000"), //
      new TestParam(0x0000000000007fffL, ZERO_ENTROPY, "0000000ZZZ0000000000000000"), //
      new TestParam(0x0000ffffffffffffL, ZERO_ENTROPY, "7ZZZZZZZZZ0000000000000000"), //
      // new TestParam(0x0000ffffffffffffL, ZERO_ENTROPY,
      // "ZZZZZZZZZZ0000000000000000"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x01},
          "00000000000000000000000001"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0f},
          "0000000000000000000000000F"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x10},
          "0000000000000000000000000G"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1f},
          "0000000000000000000000000Z"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x20},
          "00000000000000000000000010"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x21},
          "00000000000000000000000011"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x2f},
          "0000000000000000000000001F"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x30},
          "0000000000000000000000001G"), //
      new TestParam(ULID.MIN_TIME, new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3f},
          "0000000000000000000000001Z"), //
  };

  @Test
  public void testRandom() {
    String value = ULID.random();
    assertNotNull(value, "Generated ULID must not be null");
    assertEquals(26, value.length(), "Generated ULID length must be 26");
    assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]");
  }

  @Test
  public void testRandomExternalRandom() {
    String value = ULID.random(TEST_RANDOM);
    assertNotNull(value, "Generated ULID must not be null");
    assertEquals(26, value.length(), "Generated ULID length must be 26");
    assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropy(value),
        "Generated ULID must use provided random instance");
  }

  @Test
  public void testRandomExternalClock() {
    String value = ULID.random(TEST_CLOCK);
    assertNotNull(value, "Generated ULID must not be null");
    assertEquals(26, value.length(), "Generated ULID length must be 26");
    assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(value),
        "Generated ULID must use provided clock instance");
  }

  @Test
  public void testRandomExternalClockAndRandom() {
    String value = ULID.random(TEST_CLOCK, TEST_RANDOM);
    assertNotNull(value, "Generated ULID must not be null");
    assertEquals(26, value.length(), "Generated ULID length must be 26");
    assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(value),
        "Generated ULID must use provided clock instance");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropy(value),
        "Generated ULID must use provided random instance");
  }

  @Test
  public void testRandomInvalid() {
    assertThrows(NullPointerException.class, () -> {
      Random random = null;
      String value = ULID.random(random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      String value = ULID.random(clock);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      Random random = null;
      String value = ULID.random(clock, random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      Random random = new Random();
      String value = ULID.random(clock, random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = Clock.systemUTC();
      Random random = null;
      String value = ULID.random(clock, random);
    }, "Null random instance should throw NullPointerException");
  }

  @Test
  public void testRandomBinary() {
    byte[] value = ULID.randomBinary();
    assertNotNull(value, "Generated ULID binary must not be null");
    assertEquals(16, value.length, "Generated ULID binary length must be 16");
    assertEquals(10, ULID.getEntropyBinary(value).length,
        "Generated binary ULID entropy must be of length 10");
  }

  @Test
  public void testRandomBinaryExternalRandom() {
    byte[] value = ULID.randomBinary(TEST_RANDOM);
    assertNotNull(value, "Generated binary ULID must not be null");
    assertEquals(16, value.length, "Generated binary ULID length must be 16");
    assertEquals(10, ULID.getEntropyBinary(value).length,
        "Generated binary ULID entropy must be of length 10");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropyBinary(value),
        "Generated binary ULID must use provided random instance");
  }

  @Test
  public void testRandomBinaryExternalClock() {
    byte[] value = ULID.randomBinary(TEST_CLOCK);
    assertNotNull(value, "Generated binary ULID must not be null");
    assertEquals(16, value.length, "Generated binary ULID length must be 16");
    assertEquals(10, ULID.getEntropyBinary(value).length,
        "Generated binary ULID entropy must be of length 10");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(value),
        "Generated binary ULID must use provided clock instance");
  }

  @Test
  public void testRandomBinaryExternalClockAndRandom() {
    byte[] value = ULID.randomBinary(TEST_CLOCK, TEST_RANDOM);
    assertNotNull(value, "Generated binary ULID must not be null");
    assertEquals(16, value.length, "Generated binary ULID length must be 16");
    assertEquals(10, ULID.getEntropyBinary(value).length,
        "Generated binary ULID entropy must be of length 10");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(value),
        "Generated binary ULID must use provided clock instance");
    assertArrayEquals(FILLED_ENTROPY, ULID.getEntropyBinary(value),
        "Generated binary ULID must use provided random instance");
  }

  @Test
  public void testRandomBinaryInvalid() {
    assertThrows(NullPointerException.class, () -> {
      Random random = null;
      byte[] value = ULID.randomBinary(random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      byte[] value = ULID.randomBinary(clock);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      Random random = null;
      byte[] value = ULID.randomBinary(clock, random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      Random random = new Random();
      byte[] value = ULID.randomBinary(clock, random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = Clock.systemUTC();
      Random random = null;
      byte[] value = ULID.randomBinary(clock, random);
    }, "Null random instance should throw NullPointerException");
  }


  @Test
  public void testRandomULID() {
    ULID value = ULID.randomULID();
    assertNotNull(value, "Generated ULID instance must not be null");
    assertEquals(ULID.class, value.getClass(), "Generated ULID instance must be of type ULID");
    assertNotNull(value.toString(), "Generated ULID instance string value must not be null");
    assertTrue(ULID.isValid(value.toString()),
        "Generated ULID instance string value must be valid");
    assertNotNull(value.toBinary(), "Generated ULID instance binary value must not be null");
    assertTrue(ULID.isValidBinary(value.toBinary()),
        "Generated ULID instance binary value must be valid");
  }

  @Test
  public void testRandomULIDExternalRandom() {
    ULID value = ULID.randomULID(TEST_RANDOM);
    assertNotNull(value, "Generated ULID instance must not be null");
    assertEquals(ULID.class, value.getClass(), "Generated ULID instance must be of type ULID");
    assertNotNull(value.toString(), "Generated ULID instance string value must not be null");
    assertTrue(ULID.isValid(value.toString()),
        "Generated ULID instance string value must be valid");
    assertNotNull(value.toBinary(), "Generated ULID instance binary value must not be null");
    assertTrue(ULID.isValidBinary(value.toBinary()),
        "Generated ULID instance binary value must be valid");
    assertArrayEquals(FILLED_ENTROPY, value.getEntropy(),
        "Generated ULID instance entropy must use provided random instance");
  }

  @Test
  public void testRandomULIDExternalClock() {
    ULID value = ULID.randomULID(TEST_CLOCK);
    assertNotNull(value, "Generated ULID instance must not be null");
    assertEquals(ULID.class, value.getClass(), "Generated ULID instance must be of type ULID");
    assertNotNull(value.toString(), "Generated ULID instance string value must not be null");
    assertTrue(ULID.isValid(value.toString()),
        "Generated ULID instance string value must be valid");
    assertNotNull(value.toBinary(), "Generated ULID instance binary value must not be null");
    assertTrue(ULID.isValidBinary(value.toBinary()),
        "Generated ULID instance binary value must be valid");
    assertEquals(TEST_TIMESTAMP, value.getTimestamp(),
        "Generated ULID instance must use provided clock instance");
  }

  @Test
  public void testRandomULIDExternalClockAndRandom() {
    ULID value = ULID.randomULID(TEST_CLOCK, TEST_RANDOM);
    assertNotNull(value, "Generated ULID instance must not be null");
    assertEquals(ULID.class, value.getClass(), "Generated ULID instance must be of type ULID");
    assertNotNull(value.toString(), "Generated ULID instance string value must not be null");
    assertTrue(ULID.isValid(value.toString()),
        "Generated ULID instance string value must be valid");
    assertNotNull(value.toBinary(), "Generated ULID instance binary value must not be null");
    assertTrue(ULID.isValidBinary(value.toBinary()),
        "Generated ULID instance binary value must be valid");
    assertEquals(TEST_TIMESTAMP, value.getTimestamp(),
        "Generated ULID instance must use provided clock instance");
    assertArrayEquals(FILLED_ENTROPY, value.getEntropy(),
        "Generated ULID instance entropy must use provided random instance");
  }

  @Test
  public void testRandomULIDInvalid() {
    assertThrows(NullPointerException.class, () -> {
      Random random = null;
      ULID value = ULID.randomULID(random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      ULID value = ULID.randomULID(clock);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      Random random = null;
      ULID value = ULID.randomULID(clock, random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = null;
      Random random = new Random();
      ULID value = ULID.randomULID(clock, random);
    }, "Null random instance should throw NullPointerException");
    assertThrows(NullPointerException.class, () -> {
      Clock clock = Clock.systemUTC();
      Random random = null;
      ULID value = ULID.randomULID(clock, random);
    }, "Null random instance should throw NullPointerException");
  }

  @Test
  public void testGenerateRandom() {
    byte[] entropy = new byte[10];
    Random random = new Random();
    random.nextBytes(entropy);
    String value = ULID.generate(TEST_TIMESTAMP, entropy);
    assertNotNull(value, "Generated ULID must not be null");
    assertEquals(26, value.length(),
        "Generated ULID length must be 26, but returned " + value.length() + " instead");
    assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned "
            + value + " instead");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestamp(value),
        "Generated ULID timestamp must equal given timestamp");
    assertArrayEquals(entropy, ULID.getEntropy(value),
        "Generated ULID entropy must equal given entropy");
  }

  @Test
  public void testGenerateInvalid() {
    Random random = new Random();
    assertThrows(IllegalArgumentException.class, () -> {
      String value = ULID.generate(ULID.MIN_TIME - 1, FILLED_ENTROPY);
    }, "Valid ULID timestamp must be greater than or equal to ULID.MIN_TIME(" + ULID.MIN_TIME
        + ")");
    assertThrows(

        IllegalArgumentException.class, () -> {
          String value = ULID.generate(ULID.MAX_TIME + 1, FILLED_ENTROPY);
        }, "Valid ULID timestamp must be lesser than or equal to ULID.MAX_TIME(" + ULID.MAX_TIME
            + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      String value = ULID.generate(TEST_TIMESTAMP, null);
    }, "Valid ULID entropy must not be null");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] entropy = new byte[9];
      Arrays.fill(entropy, (byte) 0x77);
      String value = ULID.generate(TEST_TIMESTAMP, entropy);
    }, "Valid ULID entropy must be at least of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH
        + ")");
  }

  @Test
  public void testGenerateBinaryRandom() {
    byte[] entropy = new byte[10];
    Random random = new Random();
    random.nextBytes(entropy);
    byte[] value = ULID.generateBinary(TEST_TIMESTAMP, entropy);
    assertNotNull(value, "Generated ULID binary must not be null");
    assertEquals(16, value.length,
        "Generated ULID binary length must be 16, but returned " + value.length + " instead");
    assertEquals(TEST_TIMESTAMP, ULID.getTimestampBinary(value),
        "Generated ULID binary timestamp must equal given timestamp");
    assertArrayEquals(entropy, ULID.getEntropyBinary(value),
        "Generated ULID binary entropy must equal given entropy");
  }

  @Test
  public void testGenerateBinaryInvalid() {
    Random random = new Random();
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] value = ULID.generateBinary(ULID.MIN_TIME - 1, FILLED_ENTROPY);
    }, "Valid ULID timestamp must be greater than or equal to ULID.MIN_TIME(" + ULID.MIN_TIME
        + ")");
    assertThrows(

        IllegalArgumentException.class, () -> {
          byte[] value = ULID.generateBinary(ULID.MAX_TIME + 1, FILLED_ENTROPY);
        }, "Valid ULID timestamp must be lesser than or equal to ULID.MAX_TIME(" + ULID.MAX_TIME
            + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] value = ULID.generateBinary(TEST_TIMESTAMP, null);
    }, "Valid ULID entropy must not be null");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] entropy = new byte[9];
      Arrays.fill(entropy, (byte) 0xff);
      byte[] value = ULID.generateBinary(TEST_TIMESTAMP, entropy);
    }, "Valid ULID entropy must be of ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] entropy = new byte[9];
      Arrays.fill(entropy, (byte) 0xff);
      byte[] value = ULID.generateBinary(TEST_TIMESTAMP, entropy);
    }, "Valid ULID entropy must be at least of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH
        + ")");
  }

  @Test
  public void testGenerateFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      String value = ULID.generate(params.timestamp, params.entropy);
      assertEquals(params.value, value, "Generated ULID must be equal to \"" + params.value
          + "\" for " + params.reproducer + " , but returned \"" + value + "\" instead");
      assertNotNull(value, "Generated ULID must not be null");
      assertEquals(26, value.length(),
          "Generated ULID length must be 26, but returned " + value.length() + " instead");
      assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
          "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned "
              + value + " instead");
    }
  }

  @Test
  public void testGenerateBinaryFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      byte[] bytes = new byte[ULID.ULID_BINARY_LENGTH];
      long ts = params.timestamp;
      for (int i = 5; i >= 0; i--) {
        bytes[i] = (byte) (ts & 0xff);
        ts = ts >>> 8;
      }
      System.arraycopy(params.entropy, 0, bytes, 6, 10);
      assertArrayEquals(bytes, ULID.generateBinary(params.timestamp, params.entropy),
          "ULID binary is different");
    }
  }

  @Test
  public void testGenerateULIDTimeEntropy() {
    for (TestParam params : TEST_PARAMETERS) {
      byte[] bytes = new byte[ULID.ULID_BINARY_LENGTH];
      long ts = params.timestamp;
      for (int i = 5; i >= 0; i--) {
        bytes[i] = (byte) (ts & 0xff);
        ts = ts >>> 8;
      }
      System.arraycopy(params.entropy, 0, bytes, 6, 10);
      ULID value = ULID.generateULID(params.timestamp, params.entropy);
      assertNotNull(value, "ULID instance must not be null");
      assertEquals(ULID.class, value.getClass(), "ULID instance must be of type ULID");
      assertNotNull(value.toString(), "ULID instance string value must not be null");
      assertTrue(ULID.isValid(value.toString()), "ULID instance string value must be valid");
      assertEquals(params.value, value.toString(),
          "ULID instance string value must equal given string value");
      assertTrue(ULID.isValidBinary(value.toBinary()), "ULID instance binary value must be valid");
      assertArrayEquals(bytes, value.toBinary(),
          "ULID instance binary value must equal given binary value");
      assertEquals(params.timestamp, value.getTimestamp(),
          "ULID instance timestamp must equal given timestamp");
      assertArrayEquals(params.entropy, value.getEntropy(),
          "ULID instance entropy must equal given entropy");
    }
  }

  @Test
  public void testGenerateULIDTimeEntropyNegative() {
    Random random = new Random();
    assertThrows(IllegalArgumentException.class, () -> {
      ULID value = ULID.generateULID(ULID.MIN_TIME - 1, FILLED_ENTROPY);
    }, "Valid ULID timestamp must be greater than or equal to ULID.MIN_TIME(" + ULID.MIN_TIME
        + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID value = ULID.generateULID(ULID.MAX_TIME + 1, FILLED_ENTROPY);
    }, "Valid ULID timestamp must be lesser than or equal to ULID.MAX_TIME(" + ULID.MAX_TIME + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID value = ULID.generateULID(TEST_TIMESTAMP, null);
    }, "Valid ULID entropy must not be null");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID value = ULID.generateULID(TEST_TIMESTAMP, null);
    }, "Valid ULID entropy must be of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] entropy = new byte[9];
      Arrays.fill(entropy, (byte) 0x77);
      ULID value = ULID.generateULID(TEST_TIMESTAMP, entropy);
    }, "Valid ULID entropy must be of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] entropy = new byte[11];
      Arrays.fill(entropy, (byte) 0x77);
      ULID value = ULID.generateULID(TEST_TIMESTAMP, entropy);
    }, "Valid ULID entropy must be of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")");
  }

  @Test
  public void testIsValidNegative() {
    String[] invalidUlids = new String[] { //
        null, //
        "", //
        "0", //
        "000000000000000000000000000", //
        "-0000000000000000000000000", //
        "0000000000000000000000000U", //
        "U0000000000000000000000000", //
        "0000000000000U000000000000", //
        "000000000000000000000000\u30420", //
        "\u30420000000000000000000000000", //
        "0000000000000000000000000#", //
        "80000000000000000000000000", // timestamp overflow
        "Z0000000000000000000000000", // timestamp overflow
    };
    for (String ulid : invalidUlids) {
      assertFalse(ULID.isValid(ulid), "ULID \"" + ulid + "\" should be invalid");
    }
  }

  @Test
  public void testIsValidFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      assertTrue(ULID.isValid(params.value), "ULID string is valid");
    }
  }

  @Test
  public void testIsValidBinary() {
    byte[] ulid = new byte[16];
    Arrays.fill(ulid, (byte) 0x00);
    assertTrue(ULID.isValidBinary(ulid), "Any byte array of 16 bytes is a valid binary ULID");
    Arrays.fill(ulid, (byte) 0xff);
    assertTrue(ULID.isValidBinary(ulid), "Any byte array of 16 bytes is a valid binary ULID");
    Random random = new Random();
    random.nextBytes(ulid);
    assertTrue(ULID.isValidBinary(ulid), "Any byte array of 16 bytes is a valid binary ULID");
  }

  @Test
  public void testIsValidBinaryNegative() {
    byte[][] invalidUlids = new byte[][] { //
        null, //
        new byte[] {}, //
        new byte[] {(byte) 0xff}, //
        new byte[] { //
            (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
            (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        } //
    };
    for (byte[] ulid : invalidUlids) {
      assertFalse(ULID.isValidBinary(ulid),
          "Binary ULID \"" + bytesToInitializer(ulid) + "\" should be invalid");
    }
  }

  @Test
  public void testGetTimestampFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      assertEquals(params.timestamp, ULID.getTimestamp(params.value),
          "ULID timestamp is different");
    }
  }

  @Test
  public void testGetTimestampNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getTimestamp(null);
    }, "ULID string must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getTimestamp("7ZZZZZZZZZ000000000000000");
    }, "ULID string must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getTimestamp("8ZZZZZZZZZ0000000000000000");
    }, "Timestamp overflows MAX_TIME");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getTimestamp("ZZZZZZZZZZ0000000000000000");
    }, "Timestamp overflows MAX_TIME");
  }

  @Test
  public void testGetTimestampBinaryNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getTimestampBinary(null);
    }, "ULID binary must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] ulid = new byte[9];
      Arrays.fill(ulid, (byte) 0xff);
      ULID.getTimestampBinary(ulid);
    }, "ULID binary must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] ulid = new byte[11];
      Arrays.fill(ulid, (byte) 0xff);
      ULID.getTimestampBinary(ulid);
    }, "ULID binary must be valid");
  }

  @Test
  public void testGetEntropyNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getEntropy(null);
    }, "ULID string must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getEntropy("7ZZZZZZZZZZZZZZZZZZZZZZZZ");
    }, "ULID string must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getEntropy("7ZZZZZZZZZZZZZZZZZZZZZZZZZZ");
    }, "ULID string must be valid");
  }

  @Test
  public void testGetEntropyFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      assertArrayEquals(params.entropy, ULID.getEntropy(params.value), "ULID entropy is different");
    }
  }

  @Test
  public void testGetEntropyBinaryNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.getEntropyBinary(null);
    }, "ULID binary must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] ulid = new byte[9];
      Arrays.fill(ulid, (byte) 0xff);
      ULID.getEntropyBinary(ulid);
    }, "ULID binary must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] ulid = new byte[11];
      Arrays.fill(ulid, (byte) 0xff);
      ULID.getEntropyBinary(ulid);
    }, "ULID binary must be valid");
  }

  @Test
  public void testToBinaryFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      byte[] bytes = new byte[ULID.ULID_BINARY_LENGTH];
      long ts = params.timestamp;
      for (int i = 5; i >= 0; i--) {
        bytes[i] = (byte) (ts & 0xff);
        ts = ts >>> 8;
      }
      System.arraycopy(params.entropy, 0, bytes, 6, 10);
      assertArrayEquals(bytes, ULID.toBinary(params.value), "ULID binary is different");
    }
  }

  @Test
  public void testFromBinaryFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      byte[] bytes = new byte[ULID.ULID_BINARY_LENGTH];
      long ts = params.timestamp;
      for (int i = 5; i >= 0; i--) {
        bytes[i] = (byte) (ts & 0xff);
        ts = ts >>> 8;
      }
      System.arraycopy(params.entropy, 0, bytes, 6, 10);
      assertEquals(params.value, ULID.fromBinary(bytes), "ULID string is different");
    }
  }

  @Test
  public void testFromBinaryNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      ULID.fromBinary(null);
    }, "ULID binary must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] ulid = new byte[9];
      Arrays.fill(ulid, (byte) 0xff);
      ULID.fromBinary(ulid);
    }, "ULID binary must be valid");
    assertThrows(IllegalArgumentException.class, () -> {
      byte[] ulid = new byte[11];
      Arrays.fill(ulid, (byte) 0xff);
      ULID.fromBinary(ulid);
    }, "ULID binary must be valid");
  }

  @Test
  public void testParseULIDString() {
    for (TestParam params : TEST_PARAMETERS) {
      byte[] bytes = new byte[ULID.ULID_BINARY_LENGTH];
      long ts = params.timestamp;
      for (int i = 5; i >= 0; i--) {
        bytes[i] = (byte) (ts & 0xff);
        ts = ts >>> 8;
      }
      System.arraycopy(params.entropy, 0, bytes, 6, 10);
      ULID value = ULID.parseULID(params.value);
      assertNotNull(value, "ULID instance must not be null");
      assertEquals(ULID.class, value.getClass(), "ULID instance must be of type ULID");
      assertNotNull(value.toString(), "ULID instance string value must not be null");
      assertTrue(ULID.isValid(value.toString()), "ULID instance string value must be valid");
      assertEquals(params.value, value.toString(),
          "ULID instance string value must equal given string value");
      assertTrue(ULID.isValidBinary(value.toBinary()), "ULID instance binary value must be valid");
      assertArrayEquals(bytes, value.toBinary(),
          "ULID instance binary value must equal given binary value");
      assertEquals(params.timestamp, value.getTimestamp(),
          "ULID instance timestamp must equal given timestamp");
      assertArrayEquals(params.entropy, value.getEntropy(),
          "ULID instance entropy must equal given entropy");
    }
  }

  @Test
  public void testParseULIDBinary() {
    for (TestParam params : TEST_PARAMETERS) {
      byte[] bytes = new byte[ULID.ULID_BINARY_LENGTH];
      long ts = params.timestamp;
      for (int i = 5; i >= 0; i--) {
        bytes[i] = (byte) (ts & 0xff);
        ts = ts >>> 8;
      }
      System.arraycopy(params.entropy, 0, bytes, 6, 10);
      ULID value = ULID.parseULID(bytes);
      assertNotNull(value, "ULID instance must not be null");
      assertEquals(ULID.class, value.getClass(), "ULID instance must be of type ULID");
      assertNotNull(value.toString(), "ULID instance string value must not be null");
      assertTrue(ULID.isValid(value.toString()), "ULID instance string value must be valid");
      assertEquals(params.value, value.toString(),
          "ULID instance string value must equal given string value");
      assertTrue(ULID.isValidBinary(value.toBinary()), "ULID instance binary value must be valid");
      assertArrayEquals(bytes, value.toBinary(),
          "ULID instance binary value must equal given binary value");
      assertEquals(params.timestamp, value.getTimestamp(),
          "ULID instance timestamp must equal given timestamp");
      assertArrayEquals(params.entropy, value.getEntropy(),
          "ULID instance entropy must equal given entropy");
    }
  }

  @Test
  public void testParseULIDStringNegative() {
    String[] invalidUlids = new String[] { //
        null, //
        "", //
        "0", //
        "000000000000000000000000000", //
        "-0000000000000000000000000", //
        "0000000000000000000000000U", //
        "U0000000000000000000000000", //
        "0000000000000U000000000000", //
        "000000000000000000000000\u30420", //
        "\u30420000000000000000000000000", //
        "0000000000000000000000000#", //
        "80000000000000000000000000", // timestamp overflow
        "Z0000000000000000000000000", // timestamp overflow
    };
    for (final String ulid : invalidUlids) {
      assertThrows(IllegalArgumentException.class, () -> {
        ULID ulidObj = ULID.parseULID(ulid);
      }, "Invalid ULID string should throw IllegalArgumentException");
    }
  }

  @Test
  public void testParseULIDBinaryNegative() {
    byte[][] invalidUlids = new byte[][] { //
        null, //
        new byte[] {}, //
        new byte[] {(byte) 0xff}, //
        new byte[] { //
            (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
            (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        } //
    };
    for (final byte[] ulid : invalidUlids) {
      assertThrows(IllegalArgumentException.class, () -> {
        ULID ulidObj = ULID.parseULID(ulid);
      }, "Invalid ULID binary should throw IllegalArgumentException");
    }
  }

  @Test
  public void testCompare() {
    // nulls
    assertEquals(0, ULID.compare(null, null));
    assertEquals(1, ULID.compare(ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY), null));
    assertEquals(-1, ULID.compare(null, ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)));
    // l == r
    assertEquals(0, ULID.compare(ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY),
        ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)));
    assertEquals(0, ULID.compare(ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY),
        ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)));
    assertEquals(0, ULID.compare(ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
        ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY)));
    assertEquals(0, ULID.compare(ULID.generateULID(ULID.MAX_TIME, FILLED_ENTROPY),
        ULID.generateULID(ULID.MAX_TIME, FILLED_ENTROPY)));
    byte[] zeroMSBFull = Arrays.copyOf(ZERO_ENTROPY, ULID.ENTROPY_LENGTH);
    zeroMSBFull[0] = (byte) 0xff;
    byte[] zeroLSBFull = Arrays.copyOf(ZERO_ENTROPY, ULID.ENTROPY_LENGTH);
    zeroLSBFull[9] = (byte) 0xff;
    byte[] filledMSBZero = Arrays.copyOf(FILLED_ENTROPY, ULID.ENTROPY_LENGTH);
    filledMSBZero[0] = (byte) 0x00;
    byte[] filledLSBZero = Arrays.copyOf(FILLED_ENTROPY, ULID.ENTROPY_LENGTH);
    filledLSBZero[9] = (byte) 0x00;
    // testValues[i][0] greater than testValues[i][1]
    ULID[][] testValues = new ULID[][] { //
        // entropy is greater
        {ULID.generateULID(TEST_TIMESTAMP, FILLED_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, incrementBytes(ZERO_ENTROPY)),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, zeroMSBFull),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, zeroLSBFull),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, zeroMSBFull),
            ULID.generateULID(TEST_TIMESTAMP, zeroLSBFull)},
        {ULID.generateULID(TEST_TIMESTAMP, FILLED_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, filledMSBZero)},
        {ULID.generateULID(TEST_TIMESTAMP, FILLED_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, filledLSBZero)},
        {ULID.generateULID(TEST_TIMESTAMP, filledLSBZero),
            ULID.generateULID(TEST_TIMESTAMP, filledMSBZero)},
        // timestamp is greater
        {ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP - 1, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP + 1, ZERO_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MIN_TIME + 1, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MIN_TIME + 1, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MAX_TIME - 1, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MAX_TIME - 1, FILLED_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)},};
    for (ULID[] testValue : testValues) {
      assertTrue(ULID.compare(testValue[0], testValue[1]) > 0,
          "ULID " + testValue[0] + " should be greater than " + testValue[1]);
      assertTrue(ULID.compare(testValue[1], testValue[0]) < 0,
          "ULID " + testValue[1] + " should be lesser than " + testValue[0]);
    }
  }

  @Test
  public void testULIDCompareTo() {
    // nulls
    assertEquals(1, ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY).compareTo(null));
    // l == r
    assertEquals(0, ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)
        .compareTo(ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)));
    assertEquals(0, ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)
        .compareTo(ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)));
    assertEquals(0, ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY)
        .compareTo(ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY)));
    assertEquals(0, ULID.generateULID(ULID.MAX_TIME, FILLED_ENTROPY)
        .compareTo(ULID.generateULID(ULID.MAX_TIME, FILLED_ENTROPY)));
    byte[] zeroMSBFull = Arrays.copyOf(ZERO_ENTROPY, ULID.ENTROPY_LENGTH);
    zeroMSBFull[0] = (byte) 0xff;
    byte[] zeroLSBFull = Arrays.copyOf(ZERO_ENTROPY, ULID.ENTROPY_LENGTH);
    zeroLSBFull[9] = (byte) 0xff;
    byte[] filledMSBZero = Arrays.copyOf(FILLED_ENTROPY, ULID.ENTROPY_LENGTH);
    filledMSBZero[0] = (byte) 0x00;
    byte[] filledLSBZero = Arrays.copyOf(FILLED_ENTROPY, ULID.ENTROPY_LENGTH);
    filledLSBZero[9] = (byte) 0x00;
    // testValues[i][0] greater than testValues[i][1]
    ULID[][] testValues = new ULID[][] { //
        // entropy is greater
        {ULID.generateULID(TEST_TIMESTAMP, FILLED_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, incrementBytes(ZERO_ENTROPY)),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, zeroMSBFull),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, zeroLSBFull),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, zeroMSBFull),
            ULID.generateULID(TEST_TIMESTAMP, zeroLSBFull)},
        {ULID.generateULID(TEST_TIMESTAMP, FILLED_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, filledMSBZero)},
        {ULID.generateULID(TEST_TIMESTAMP, FILLED_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, filledLSBZero)},
        {ULID.generateULID(TEST_TIMESTAMP, filledLSBZero),
            ULID.generateULID(TEST_TIMESTAMP, filledMSBZero)},
        // timestamp is greater
        {ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP - 1, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP + 1, ZERO_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MIN_TIME + 1, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MIN_TIME + 1, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MAX_TIME - 1, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MAX_TIME - 1, FILLED_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, ZERO_ENTROPY)},
        {ULID.generateULID(ULID.MAX_TIME, ZERO_ENTROPY),
            ULID.generateULID(ULID.MIN_TIME, FILLED_ENTROPY)},};
    for (ULID[] testValue : testValues) {
      assertTrue(testValue[0].compareTo(testValue[1]) > 0,
          "ULID " + testValue[0] + " should be greater than " + testValue[1]);
      assertTrue(testValue[1].compareTo(testValue[0]) < 0,
          "ULID " + testValue[1] + " should be lesser than " + testValue[0]);
    }
  }

  @Test
  public void testULIDSort() {
    ULID[] ulids = new ULID[TEST_PARAMETERS.length];
    for (int i = 0; i < TEST_PARAMETERS.length; i++) {
      ulids[i] = ULID.generateULID(TEST_PARAMETERS[i].timestamp, TEST_PARAMETERS[i].entropy);
    }
    Arrays.sort(ulids);
    for (int i = 1; i < ulids.length; i++) {
      if (ulids[i - 1].equals(ulids[i])) {
        assertEquals(0, ulids[i - 1].compareTo(ulids[i]),
            "ULID " + ulids[i - 1] + " should be equal to " + ulids[i]);
      } else {
        assertTrue(ulids[i - 1].compareTo(ulids[i]) < 0,
            "ULID " + ulids[i - 1] + " should be lesser than " + ulids[i]);
      }
    }
  }

  @Test
  public void testULIDNew() {
    Constructor<?>[] ctors = ULID.class.getConstructors();
    Constructor<?> noArgsCtor = null;
    for (Constructor<?> ctor : ctors) {
      if (ctor.getParameterTypes().length == 0) {
        noArgsCtor = ctor;
        break;
      }
    }
    assertNull(noArgsCtor, "ULID class should not have a no-args constructor");
  }

  @Test
  public void testULIDEquals() {
    assertTrue(ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)
        .equals(ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)), "ULID instances should be equal");
    byte[] bytes1 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes1, (byte) 0x00);
    ULID value1 = ULID.parseULID(bytes1);
    byte[] bytes2 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes2, (byte) 0x00);
    assertTrue(value1.equals(ULID.parseULID(bytes2)), "ULID instances should be equal");
    byte[] bytes3 = value1.toBinary();
    assertTrue(value1.equals(ULID.parseULID(bytes3)), "ULID instances should be equal");
    byte[] bytes4 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes4, (byte) 0x00);
    bytes4[0] = (byte) 0x01;
    assertFalse(value1.equals(ULID.parseULID(bytes4)), "ULID instances should not be equal");
    assertFalse(value1.equals(null), "ULID instances should not be equal to null");
    assertFalse(value1.equals((Object) value1.toString()),
        "ULID instances should not be equal to non-ULID instance");
  }

  @Test
  public void testULIDHashCode() {
    assertEquals(ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY).hashCode(),
        ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY).hashCode(),
        "ULID instances should have same hash code");
    byte[] bytes1 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes1, (byte) 0x00);
    ULID value1 = ULID.parseULID(bytes1);
    byte[] bytes2 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes2, (byte) 0x00);
    assertEquals(value1.hashCode(), ULID.parseULID(bytes2).hashCode(),
        "ULID instances should have same hash code");
    byte[] bytes3 = value1.toBinary();
    assertEquals(value1.hashCode(), ULID.parseULID(bytes3).hashCode(),
        "ULID instances should have same hash code");
    byte[] bytes4 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes4, (byte) 0x00);
    bytes4[0] = (byte) 0x01;
    assertNotEquals(value1.hashCode(), ULID.parseULID(bytes4).hashCode(),
        "ULID instances should not have same hash code");
  }

  /**
   * This test increments entropy and checks the ULID value correctness. This takes approx 45mins on
   * a Intel® Core™ i7-7820X CPU @ 3.60GHz × 16, so only run this locally.
   */
  // @Disabled("Very expensive test")
  // @Test
  public void testGeneratorCorrectness() {
    long start = System.currentTimeMillis();
    String previousValue = null;
    final byte[] endEntropy = new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    };
    for (byte[] entropy = new byte[10]; !Arrays.equals(endEntropy, entropy); entropy =
        incrementBytes(entropy)) {
      String value = ULID.generate(ULID.MIN_TIME, entropy);
      TestParam param = new TestParam(ULID.MIN_TIME, entropy, value);
      // Skip first value (previousValue==null)
      if (previousValue != null) {
        assertEntropyIsIncremented(previousValue, value);
      }
      // Progress logging
      if (entropy[7] == 0x0 && entropy[8] == 0x0 && entropy[9] == 0x0) {
        System.out.println(value);
      }
      assertArrayEquals(entropy, ULID.getEntropy(value),
          "ULID entropy is different for " + param.reproducer);
      assertEquals(ULID.MIN_TIME, ULID.getTimestamp(value),
          "ULID timestamp is different for " + param.reproducer);
      previousValue = value;
    }
    long end = System.currentTimeMillis();
    System.out.println("testGeneratorCorrectness took " + (end - start) + " [ms]");
  }
}
