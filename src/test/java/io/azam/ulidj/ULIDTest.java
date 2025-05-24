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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import static io.azam.ulidj.TestUtils.assertEntropyIsIncremented;
import static io.azam.ulidj.TestUtils.bytesToInitializer;
import static io.azam.ulidj.TestUtils.incrementBytes;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
    assertNotNull("Generated ULID must not be null", value);
    assertEquals("Generated ULID length must be 26", 26, value.length());
    assertTrue("Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]",
        value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
  }

  @Test
  public void testRandomExternalRandom() {
    String value = ULID.random(TEST_RANDOM);
    assertNotNull("Generated ULID must not be null", value);
    assertEquals("Generated ULID length must be 26", 26, value.length());
    assertTrue("Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]",
        value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
    assertArrayEquals("Generated ULID must use provided random instance", FILLED_ENTROPY,
        ULID.getEntropy(value));
  }

  @Test
  public void testRandomInvalid() {
    assertThrows("Null random instance should throw NullPointerException",
        NullPointerException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            Random random = null;
            String value = ULID.random(random);
          }
        });
  }

  @Test
  public void testRandomBinary() {
    byte[] value = ULID.randomBinary();
    assertNotNull("Generated ULID binary must not be null", value);
    assertEquals("Generated ULID binary length must be 16", 16, value.length);
    assertEquals("Generated binary ULID entropy must be of length 10", 10,
        ULID.getEntropyBinary(value).length);
  }

  @Test
  public void testRandomBinaryExternalRandom() {
    byte[] value = ULID.randomBinary(TEST_RANDOM);
    assertNotNull("Generated binary ULID must not be null", value);
    assertEquals("Generated binary ULID length must be 16", 16, value.length);
    assertEquals("Generated binary ULID entropy must be of length 10", 10,
        ULID.getEntropyBinary(value).length);
    assertArrayEquals("Generated binary ULID must use provided random instance", FILLED_ENTROPY,
        ULID.getEntropyBinary(value));
  }

  @Test
  public void testRandomBinaryInvalid() {
    assertThrows("Null random instance should throw NullPointerException",
        NullPointerException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            Random random = null;
            byte[] value = ULID.randomBinary(random);
          }
        });
  }


  @Test
  public void testRandomULID() {
    ULID value = ULID.randomULID();
    assertNotNull("Generated ULID instance must not be null", value);
    assertEquals("Generated ULID instance must be of type ULID", ULID.class, value.getClass());
    assertNotNull("Generated ULID instance string value must not be null", value.toString());
    assertTrue("Generated ULID instance string value must be valid",
        ULID.isValid(value.toString()));
    assertNotNull("Generated ULID instance binary value must not be null", value.toBinary());
    assertTrue("Generated ULID instance binary value must be valid",
        ULID.isValidBinary(value.toBinary()));
  }

  @Test
  public void testRandomULIDExternalRandom() {
    ULID value = ULID.randomULID(TEST_RANDOM);
    assertNotNull("Generated ULID instance must not be null", value);
    assertEquals("Generated ULID instance must be of type ULID", ULID.class, value.getClass());
    assertNotNull("Generated ULID instance string value must not be null", value.toString());
    assertTrue("Generated ULID instance string value must be valid",
        ULID.isValid(value.toString()));
    assertNotNull("Generated ULID instance binary value must not be null", value.toBinary());
    assertTrue("Generated ULID instance binary value must be valid",
        ULID.isValidBinary(value.toBinary()));
    assertArrayEquals("Generated ULID instance entropy must use provided random instance",
        FILLED_ENTROPY, value.getEntropy());
  }

  @Test
  public void testGenerateRandom() {
    byte[] entropy = new byte[10];
    Random random = new Random();
    random.nextBytes(entropy);
    String value = ULID.generate(TEST_TIMESTAMP, entropy);
    assertNotNull("Generated ULID must not be null", value);
    assertEquals("Generated ULID length must be 26, but returned " + value.length() + " instead",
        26, value.length());
    assertTrue(
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned "
            + value + " instead",
        value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
    assertEquals("Generated ULID timestamp must equal given timestamp", TEST_TIMESTAMP,
        ULID.getTimestamp(value));
    assertArrayEquals("Generated ULID entropy must equal given entropy", entropy,
        ULID.getEntropy(value));
  }

  @Test
  public void testGenerateInvalid() {
    Random random = new Random();
    assertThrows("Valid ULID timestamp must be greater than or equal to ULID.MIN_TIME("
        + ULID.MIN_TIME + ")", IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            String value = ULID.generate(ULID.MIN_TIME - 1, FILLED_ENTROPY);
          }
        });
    assertThrows(
        "Valid ULID timestamp must be lesser than or equal to ULID.MAX_TIME(" + ULID.MAX_TIME + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            String value = ULID.generate(ULID.MAX_TIME + 1, FILLED_ENTROPY);
          }
        });
    assertThrows("Valid ULID entropy must not be null", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            String value = ULID.generate(TEST_TIMESTAMP, null);
          }
        });
    assertThrows("Valid ULID entropy must be at least of length ULID.ENTROPY_LENGTH("
        + ULID.ENTROPY_LENGTH + ")", IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] entropy = new byte[9];
            Arrays.fill(entropy, (byte) 0x77);
            String value = ULID.generate(TEST_TIMESTAMP, entropy);
          }
        });
  }

  @Test
  public void testGenerateBinaryRandom() {
    byte[] entropy = new byte[10];
    Random random = new Random();
    random.nextBytes(entropy);
    byte[] value = ULID.generateBinary(TEST_TIMESTAMP, entropy);
    assertNotNull("Generated ULID binary must not be null", value);
    assertEquals(
        "Generated ULID binary length must be 16, but returned " + value.length + " instead", 16,
        value.length);
    assertEquals("Generated ULID binary timestamp must equal given timestamp", TEST_TIMESTAMP,
        ULID.getTimestampBinary(value));
    assertArrayEquals("Generated ULID binary entropy must equal given entropy", entropy,
        ULID.getEntropyBinary(value));
  }

  @Test
  public void testGenerateBinaryInvalid() {
    Random random = new Random();
    assertThrows("Valid ULID timestamp must be greater than or equal to ULID.MIN_TIME("
        + ULID.MIN_TIME + ")", IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] value = ULID.generateBinary(ULID.MIN_TIME - 1, FILLED_ENTROPY);
          }
        });
    assertThrows(
        "Valid ULID timestamp must be lesser than or equal to ULID.MAX_TIME(" + ULID.MAX_TIME + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] value = ULID.generateBinary(ULID.MAX_TIME + 1, FILLED_ENTROPY);
          }
        });
    assertThrows("Valid ULID entropy must not be null", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] value = ULID.generateBinary(TEST_TIMESTAMP, null);
          }
        });
    assertThrows("Valid ULID entropy must be of ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] entropy = new byte[9];
            Arrays.fill(entropy, (byte) 0xff);
            byte[] value = ULID.generateBinary(TEST_TIMESTAMP, entropy);
          }
        });
    assertThrows("Valid ULID entropy must be at least of length ULID.ENTROPY_LENGTH("
        + ULID.ENTROPY_LENGTH + ")", IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] entropy = new byte[9];
            Arrays.fill(entropy, (byte) 0xff);
            byte[] value = ULID.generateBinary(TEST_TIMESTAMP, entropy);
          }
        });
  }

  @Test
  public void testGenerateFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      String value = ULID.generate(params.timestamp, params.entropy);
      assertEquals("Generated ULID must be equal to \"" + params.value + "\" for "
          + params.reproducer + " , but returned \"" + value + "\" instead", params.value, value);
      assertNotNull("Generated ULID must not be null", value);
      assertEquals("Generated ULID length must be 26, but returned " + value.length() + " instead",
          26, value.length());
      assertTrue(
          "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned "
              + value + " instead",
          value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
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
      assertArrayEquals("ULID binary is different", bytes,
          ULID.generateBinary(params.timestamp, params.entropy));
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
      assertNotNull("ULID instance must not be null", value);
      assertEquals("ULID instance must be of type ULID", ULID.class, value.getClass());
      assertNotNull("ULID instance string value must not be null", value.toString());
      assertTrue("ULID instance string value must be valid", ULID.isValid(value.toString()));
      assertEquals("ULID instance string value must equal given string value", params.value,
          value.toString());
      assertTrue("ULID instance binary value must be valid", ULID.isValidBinary(value.toBinary()));
      assertArrayEquals("ULID instance binary value must equal given binary value", bytes,
          value.toBinary());
      assertEquals("ULID instance timestamp must equal given timestamp", params.timestamp,
          value.getTimestamp());
      assertArrayEquals("ULID instance entropy must equal given entropy", params.entropy,
          value.getEntropy());
    }
  }

  @Test
  public void testGenerateULIDTimeEntropyNegative() {
    Random random = new Random();
    assertThrows("Valid ULID timestamp must be greater than or equal to ULID.MIN_TIME("
        + ULID.MIN_TIME + ")", IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            ULID value = ULID.generateULID(ULID.MIN_TIME - 1, FILLED_ENTROPY);
          }
        });
    assertThrows(
        "Valid ULID timestamp must be lesser than or equal to ULID.MAX_TIME(" + ULID.MAX_TIME + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            ULID value = ULID.generateULID(ULID.MAX_TIME + 1, FILLED_ENTROPY);
          }
        });
    assertThrows("Valid ULID entropy must not be null", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID value = ULID.generateULID(TEST_TIMESTAMP, null);
          }
        });
    assertThrows(
        "Valid ULID entropy must be of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            ULID value = ULID.generateULID(TEST_TIMESTAMP, null);
          }
        });
    assertThrows(
        "Valid ULID entropy must be of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] entropy = new byte[9];
            Arrays.fill(entropy, (byte) 0x77);
            ULID value = ULID.generateULID(TEST_TIMESTAMP, entropy);
          }
        });
    assertThrows(
        "Valid ULID entropy must be of length ULID.ENTROPY_LENGTH(" + ULID.ENTROPY_LENGTH + ")",
        IllegalArgumentException.class, new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] entropy = new byte[11];
            Arrays.fill(entropy, (byte) 0x77);
            ULID value = ULID.generateULID(TEST_TIMESTAMP, entropy);
          }
        });
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
      assertFalse("ULID \"" + ulid + "\" should be invalid", ULID.isValid(ulid));
    }
  }

  @Test
  public void testIsValidFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      assertTrue("ULID string is valid", ULID.isValid(params.value));
    }
  }

  @Test
  public void testIsValidBinary() {
    byte[] ulid = new byte[16];
    Arrays.fill(ulid, (byte) 0x00);
    assertTrue("Any byte array of 16 bytes is a valid binary ULID", ULID.isValidBinary(ulid));
    Arrays.fill(ulid, (byte) 0xff);
    assertTrue("Any byte array of 16 bytes is a valid binary ULID", ULID.isValidBinary(ulid));
    Random random = new Random();
    random.nextBytes(ulid);
    assertTrue("Any byte array of 16 bytes is a valid binary ULID", ULID.isValidBinary(ulid));
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
      assertFalse("Binary ULID \"" + bytesToInitializer(ulid) + "\" should be invalid",
          ULID.isValidBinary(ulid));
    }
  }

  @Test
  public void testGetTimestampFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      assertEquals("ULID timestamp is different", params.timestamp,
          ULID.getTimestamp(params.value));
    }
  }

  @Test
  public void testGetTimestampNegative() {
    assertThrows("ULID string must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getTimestamp(null);
          }
        });
    assertThrows("ULID string must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getTimestamp("7ZZZZZZZZZ000000000000000");
          }
        });
    assertThrows("Timestamp overflows MAX_TIME", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getTimestamp("8ZZZZZZZZZ0000000000000000");
          }
        });
    assertThrows("Timestamp overflows MAX_TIME", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getTimestamp("ZZZZZZZZZZ0000000000000000");
          }
        });
  }

  @Test
  public void testGetTimestampBinaryNegative() {
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getTimestampBinary(null);
          }
        });
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] ulid = new byte[9];
            Arrays.fill(ulid, (byte) 0xff);
            ULID.getTimestampBinary(ulid);
          }
        });
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] ulid = new byte[11];
            Arrays.fill(ulid, (byte) 0xff);
            ULID.getTimestampBinary(ulid);
          }
        });
  }

  @Test
  public void testGetEntropyNegative() {
    assertThrows("ULID string must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getEntropy(null);
          }
        });
    assertThrows("ULID string must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getEntropy("7ZZZZZZZZZZZZZZZZZZZZZZZZ");
          }
        });
    assertThrows("ULID string must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getEntropy("7ZZZZZZZZZZZZZZZZZZZZZZZZZZ");
          }
        });
  }

  @Test
  public void testGetEntropyFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      assertArrayEquals("ULID entropy is different", params.entropy, ULID.getEntropy(params.value));
    }
  }

  @Test
  public void testGetEntropyBinaryNegative() {
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.getEntropyBinary(null);
          }
        });
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] ulid = new byte[9];
            Arrays.fill(ulid, (byte) 0xff);
            ULID.getEntropyBinary(ulid);
          }
        });
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] ulid = new byte[11];
            Arrays.fill(ulid, (byte) 0xff);
            ULID.getEntropyBinary(ulid);
          }
        });
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
      assertArrayEquals("ULID binary is different", bytes, ULID.toBinary(params.value));
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
      assertEquals("ULID string is different", params.value, ULID.fromBinary(bytes));
    }
  }

  @Test
  public void testFromBinaryNegative() {
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            ULID.fromBinary(null);
          }
        });
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] ulid = new byte[9];
            Arrays.fill(ulid, (byte) 0xff);
            ULID.fromBinary(ulid);
          }
        });
    assertThrows("ULID binary must be valid", IllegalArgumentException.class,
        new ThrowingRunnable() {
          @Override
          public void run() {
            byte[] ulid = new byte[11];
            Arrays.fill(ulid, (byte) 0xff);
            ULID.fromBinary(ulid);
          }
        });
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
      assertNotNull("ULID instance must not be null", value);
      assertEquals("ULID instance must be of type ULID", ULID.class, value.getClass());
      assertNotNull("ULID instance string value must not be null", value.toString());
      assertTrue("ULID instance string value must be valid", ULID.isValid(value.toString()));
      assertEquals("ULID instance string value must equal given string value", params.value,
          value.toString());
      assertTrue("ULID instance binary value must be valid", ULID.isValidBinary(value.toBinary()));
      assertArrayEquals("ULID instance binary value must equal given binary value", bytes,
          value.toBinary());
      assertEquals("ULID instance timestamp must equal given timestamp", params.timestamp,
          value.getTimestamp());
      assertArrayEquals("ULID instance entropy must equal given entropy", params.entropy,
          value.getEntropy());
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
      assertNotNull("ULID instance must not be null", value);
      assertEquals("ULID instance must be of type ULID", ULID.class, value.getClass());
      assertNotNull("ULID instance string value must not be null", value.toString());
      assertTrue("ULID instance string value must be valid", ULID.isValid(value.toString()));
      assertEquals("ULID instance string value must equal given string value", params.value,
          value.toString());
      assertTrue("ULID instance binary value must be valid", ULID.isValidBinary(value.toBinary()));
      assertArrayEquals("ULID instance binary value must equal given binary value", bytes,
          value.toBinary());
      assertEquals("ULID instance timestamp must equal given timestamp", params.timestamp,
          value.getTimestamp());
      assertArrayEquals("ULID instance entropy must equal given entropy", params.entropy,
          value.getEntropy());
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
      assertThrows("Invalid ULID string should throw IllegalArgumentException",
          IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() {
              ULID ulidObj = ULID.parseULID(ulid);
            }
          });
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
      assertThrows("Invalid ULID binary should throw IllegalArgumentException",
          IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() {
              ULID ulidObj = ULID.parseULID(ulid);
            }
          });
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
      assertTrue("ULID " + testValue[0] + " should be greater than " + testValue[1],
          ULID.compare(testValue[0], testValue[1]) > 0);
      assertTrue("ULID " + testValue[1] + " should be lesser than " + testValue[0],
          ULID.compare(testValue[1], testValue[0]) < 0);
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
      assertTrue("ULID " + testValue[0] + " should be greater than " + testValue[1],
          testValue[0].compareTo(testValue[1]) > 0);
      assertTrue("ULID " + testValue[1] + " should be lesser than " + testValue[0],
          testValue[1].compareTo(testValue[0]) < 0);
    }
  }

  @Test
  public void testULIDNew() {
    Constructor<?>[] ctors = ULID.class.getDeclaredConstructors();
    Constructor<?> noArgsCtor = null;
    for (Constructor<?> ctor : ctors) {
      if (ctor.getParameterTypes().length == 0) {
        noArgsCtor = ctor;
        break;
      }
    }
    assertNotNull(noArgsCtor);
    assertFalse(noArgsCtor.isAccessible());
    assertThrows("ULID constructor should throw IllegalAccessException",
        IllegalAccessException.class, new ThrowingRunnable() {
          @Override
          public void run()
              throws InvocationTargetException, InstantiationException, IllegalAccessException {
            Constructor<?> testCtor = null;
            for (Constructor<?> ctor : ULID.class.getDeclaredConstructors()) {
              if (ctor.getParameterTypes().length == 0) {
                testCtor = ctor;
                break;
              }
            }
            assert testCtor != null;
            ULID value = ((Constructor<ULID>) testCtor).newInstance();
          }
        });
    assertThrows("ULID constructor should throw UnsupportedOperationException if actually called",
        UnsupportedOperationException.class, new ThrowingRunnable() {
          @Override
          public void run() throws Throwable {
            Constructor<?> testCtor = null;
            for (Constructor<?> ctor : ULID.class.getDeclaredConstructors()) {
              if (ctor.getParameterTypes().length == 0) {
                testCtor = ctor;
                break;
              }
            }
            assert testCtor != null;
            testCtor.setAccessible(true);
            try {
              ULID value = ((Constructor<ULID>) testCtor).newInstance();
            } catch (InvocationTargetException e) {
              // unwrap the target exception
              throw e.getTargetException();
            }
          }
        });
  }

  @Test
  public void testULIDEquals() {
    assertTrue("ULID instances should be equal", ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)
        .equals(ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY)));
    byte[] bytes1 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes1, (byte) 0x00);
    ULID value1 = ULID.parseULID(bytes1);
    byte[] bytes2 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes2, (byte) 0x00);
    assertTrue("ULID instances should be equal", value1.equals(ULID.parseULID(bytes2)));
    byte[] bytes3 = value1.toBinary();
    assertTrue("ULID instances should be equal", value1.equals(ULID.parseULID(bytes3)));
    byte[] bytes4 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes4, (byte) 0x00);
    bytes4[0] = (byte) 0x01;
    assertFalse("ULID instances should not be equal", value1.equals(ULID.parseULID(bytes4)));
    assertFalse("ULID instances should not be equal to null", value1.equals(null));
    assertFalse("ULID instances should not be equal to non-ULID instance",
        value1.equals((Object) value1.toString()));
  }

  @Test
  public void testULIDHashCode() {
    assertEquals("ULID instances should have same hash code",
        ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY).hashCode(),
        ULID.generateULID(TEST_TIMESTAMP, ZERO_ENTROPY).hashCode());
    byte[] bytes1 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes1, (byte) 0x00);
    ULID value1 = ULID.parseULID(bytes1);
    byte[] bytes2 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes2, (byte) 0x00);
    assertEquals("ULID instances should have same hash code", value1.hashCode(),
        ULID.parseULID(bytes2).hashCode());
    byte[] bytes3 = value1.toBinary();
    assertEquals("ULID instances should have same hash code", value1.hashCode(),
        ULID.parseULID(bytes3).hashCode());
    byte[] bytes4 = new byte[ULID.ULID_BINARY_LENGTH];
    Arrays.fill(bytes4, (byte) 0x00);
    bytes4[0] = (byte) 0x01;
    assertNotEquals("ULID instances should not have same hash code", value1.hashCode(),
        ULID.parseULID(bytes4).hashCode());
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
      assertArrayEquals("ULID entropy is different for " + param.reproducer, entropy,
          ULID.getEntropy(value));
      assertEquals("ULID timestamp is different for " + param.reproducer, ULID.MIN_TIME,
          ULID.getTimestamp(value));
      previousValue = value;
    }
    long end = System.currentTimeMillis();
    System.out.println("testGeneratorCorrectness took " + (end - start) + " [ms]");
  }
}
