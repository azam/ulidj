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

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link io.azam.ulidj.ULID}
 *
 * @author azam
 * @since 0.0.1
 */
public class ULIDTest {
  private static final byte[] ZERO_ENTROPY = new byte[] { //
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 //
  };
  private static final byte[] FILLED_ENTROPY = new byte[] { //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
  };

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
      sb.append("ULID.generate(").append(Long.toString(timestamp)).append("L,");
      if (entropy != null) {
        sb.append("new byte[]{");
        for (int i = 0; i < entropy.length; i++) {
          sb.append("0x").append(Integer.toHexString((entropy[i] & 0xFF) + 0x100).substring(1));
          if (i + 1 < entropy.length) {
            sb.append(",");
          }
        }
        sb.append("}");
      } else {
        sb.append("null");
      }
      sb.append(")");
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
    Assertions.assertNotNull(value, "Generated ULID must not be null");
    Assertions.assertEquals(26, value.length(), "Generated ULID length must be 26");
    Assertions.assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]");
  }

  @Test
  public void testRandomBinary() {
    byte[] value = ULID.randomBinary();
    Assertions.assertNotNull(value, "Generated ULID binary must not be null");
    Assertions.assertEquals(16, value.length, "Generated ULID binary length must be 16");
  }

  @Test
  public void testGenerateRandom() {
    byte[] entropy = new byte[10];
    Random random = new Random();
    random.nextBytes(entropy);
    long now = System.currentTimeMillis();
    String value = ULID.generate(now, entropy);
    Assertions.assertNotNull(value, "Generated ULID must not be null");
    Assertions.assertEquals(26, value.length(),
        "Generated ULID length must be 26, but returned " + value.length() + " instead");
    Assertions.assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
        "Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned "
            + value + " instead");
    Assertions.assertEquals(now, ULID.getTimestamp(value),
        "Generated ULID timestamp must equal given timestamp");
    Assertions.assertArrayEquals(entropy, ULID.getEntropy(value),
        "Generated ULID entropy must equal given entropy");
  }

  @Test
  public void testGenerateBinaryRandom() {
    byte[] entropy = new byte[10];
    Random random = new Random();
    random.nextBytes(entropy);
    long now = System.currentTimeMillis();
    byte[] value = ULID.generateBinary(now, entropy);
    Assertions.assertNotNull(value, "Generated ULID binary must not be null");
    Assertions.assertEquals(16, value.length,
        "Generated ULID binary length must be 16, but returned " + value.length + " instead");
    Assertions.assertEquals(now, ULID.getTimestampBinary(value),
        "Generated ULID binary timestamp must equal given timestamp");
    Assertions.assertArrayEquals(entropy, ULID.getEntropyBinary(value),
        "Generated ULID binary entropy must equal given entropy");
  }

  @Test
  public void testGenerateFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      String value = ULID.generate(params.timestamp, params.entropy);
      Assertions.assertEquals(params.value, value,
          "Generated ULID must be equal to \"" + params.value + "\" for " + params.reproducer
              + " , but returned \"" + value + "\" instead");
      Assertions.assertNotNull(value, "Generated ULID must not be null");
      Assertions.assertEquals(26, value.length(),
          "Generated ULID length must be 26, but returned " + value.length() + " instead");
      Assertions.assertTrue(value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"),
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
      Assertions.assertArrayEquals(bytes, ULID.generateBinary(params.timestamp, params.entropy),
          "ULID binary is different");
    }
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
        "0000000000000000000000000/u3042", //
        "0000000000000000000000000#", //
    };
    for (String ulid : invalidUlids) {
      Assertions.assertFalse(ULID.isValid(ulid), "ULID \"" + ulid + "\" should be invalid");
    }
  }

  @Test
  public void testIsValidFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      Assertions.assertTrue(ULID.isValid(params.value), "ULID string is valid");
    }
  }

  @Test
  public void testGetTimestampFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      Assertions.assertEquals(params.timestamp, ULID.getTimestamp(params.value),
          "ULID timestamp is different");
    }
  }

  @Test
  public void testGetTimestamp() {
    Assertions.assertEquals(0x0003ffffffffffffL, ULID.getTimestamp("ZZZZZZZZZZ0000000000000000"),
        "getTimestamp allows getting timestamp that overflows MAX_TIME");
    long now = System.currentTimeMillis();
    long ts = 0x0000ffffffffffffL;
    Assertions.assertTrue(now < ts);
    String ulid = ULID.generate(ts, ZERO_ENTROPY);
    Assertions.assertEquals(ts, ULID.getTimestamp(ulid));
  }

  @Test
  public void testGetEntropyFixedValues() {
    for (TestParam params : TEST_PARAMETERS) {
      Assertions.assertArrayEquals(params.entropy, ULID.getEntropy(params.value),
          "ULID entropy is different");
    }
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
      Assertions.assertArrayEquals(bytes, ULID.toBinary(params.value), "ULID binary is different");
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
      Assertions.assertEquals(params.value, ULID.fromBinary(bytes), "ULID string is different");
    }
  }

  /**
   * This test increments entropy and checks the ULID value correctness. This takes approx 45mins on
   * a Intel® Core™ i7-7820X CPU @ 3.60GHz × 16, so only run this locally.
   */
  // @Test
  public void testGeneratorCorrectness() {
    long start = System.currentTimeMillis();
    String previousValue = null;
    final byte[] endEntropy = new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    };
    for (byte[] entropy = new byte[10]; !byteArrayEquals(endEntropy, entropy); entropy =
        TestUtils.incrementBytes(entropy)) {
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
      Assertions.assertArrayEquals(entropy, ULID.getEntropy(value),
          "ULID entropy is different for " + param.reproducer);
      Assertions.assertEquals(ULID.MIN_TIME, ULID.getTimestamp(value),
          "ULID timestamp is different for " + param.reproducer);
      previousValue = value;
    }
    long end = System.currentTimeMillis();
    System.out.println("testGeneratorCorrectness took " + (end - start) + " [ms]");
  }

  /**
   * Base32 characters mapping
   */
  private static final char[] C = new char[] { //
      0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, //
      0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, //
      0x47, 0x48, 0x4a, 0x4b, 0x4d, 0x4e, 0x50, 0x51, //
      0x52, 0x53, 0x54, 0x56, 0x57, 0x58, 0x59, 0x5a //
  };

  public static void assertEntropyIsIncremented(String prev, String next) {
    Assertions.assertEquals(prev.length(), next.length());
    boolean charMustbeSame = false;
    for (int i = next.length() - 1; i >= 0; i--) {
      if (!charMustbeSame) {
        // We assume only ASCII characters, so charAt is good enough here.
        int nextCharIndex = indexOfElement(C, next.charAt(i));
        int prevCharIndex = indexOfElement(C, prev.charAt(i));
        Assertions.assertTrue(nextCharIndex >= 0, "Next ULID value contains invalid character");
        Assertions.assertEquals(((nextCharIndex + C.length - 1) % C.length), prevCharIndex,
            "Next base64 char is wrong");
        charMustbeSame = nextCharIndex < C.length && nextCharIndex > 0;
      } else {
        Assertions.assertEquals(next.charAt(i), prev.charAt(i));
      }
    }
  }

  public static int indexOfElement(char[] arr, char e) {
    if (arr != null && arr.length > 0) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == e)
          return i;
      }
    }
    return -1;
  }

  public static boolean byteArrayEquals(byte[] l, byte[] r) {
    if (l != null && r != null && l.length == r.length) {
      for (int i = 0; i < l.length; i++) {
        if (l[i] != r[i])
          return false;
      }
      return true;
    } else if (l == null && r == null) {
      return true;
    }
    return false;
  }
}
