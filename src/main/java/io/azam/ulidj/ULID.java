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

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.Clock;
import java.util.Arrays;
import java.util.Random;

/**
 * ULID implementation in Java. This class implements an immutable, sortable (implements
 * {@link java.lang.Comparable}) ULID instance, convertors and helper methods for ULID string,
 * binary representations.<br>
 * <br>
 * ULID string representations are generated as upper case letters. Parsing of ULID strings allow
 * upper and lower case letters, where `i` and `l` will be treated as `1` and `o` will be treated as
 * `0`. <br>
 * <br>
 *
 * ULID instance generation examples:<br>
 *
 * <pre>
 * // Using default Random instance backed by SecureRandom
 * ULID ulid1 = ULID.randomULID();
 * // Using provided Random instance
 * ULID ulid2 = ULID.randomULID(ThreadLocalRandom.current());
 * // Using provided SecureRandom instance
 * ULID ulid3 = ULID.parseULID("003JZ9J6G80123456789abcdef");
 * // Convert ULID string to ULID instance
 * ULID ulid4 = ULID.parseULID("003JZ9J6G80123456789abcdef");
 * // Convert ULID binary to ULID instance
 * ULID ulid5 = ULID.parseULID(
 *     new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf});
 * // Instantiate a ULID instance from current time and provided entropy bytes
 * ULID ulid6 = ULID.generateULID(System.currentTimeMillis(), entropy);
 * // Sort ULID instances lexicographically
 * List&lt;ULID&gt; ulids = Arrays.asList(ulid1, ulid2, ulid3, ulid4, ulid5, ulid6);
 * Collections.sort(ulids);
 * </pre>
 *
 * ULID string generation examples:<br>
 *
 * <pre>
 * // Using default Random instance backed by SecureRandom
 * String ulid1 = ULID.random();
 * // Using provided Random instance
 * String ulid2 = ULID.random(ThreadLocalRandom.current());
 * // Using provided SecureRandom instance
 * String ulid3 = ULID.random(SecureRandom.newInstance("SHA1PRNG"));
 * // Generate ULID string from current time and provided entropy bytes
 * byte[] entropy = new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9};
 * String ulid4 = ULID.generate(System.currentTimeMillis(), entropy);
 * // Convert ULID binary to ULID string
 * String ulid5 = ULID.fromBinary(
 *     new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf});
 * </pre>
 *
 * ULID binary generation examples:<br>
 *
 * <pre>
 * // Using default Random instance backed by SecureRandom
 * byte[] ulid1 = ULID.randomBinary();
 * // Using provided Random instance
 * byte[] ulid2 = ULID.randomBinary(ThreadLocalRandom.current());
 * // Using provided SecureRandom instance
 * byte[] ulid3 = ULID.randomBinary(SecureRandom.newInstance("SHA1PRNG"));
 * // Generate ULID string from current time and provided entropy bytes
 * byte[] entropy = new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9};
 * byte[] ulid4 = ULID.generateBinary(System.currentTimeMillis(), entropy);
 * // Convert ULID string to ULID binary
 * byte[] ulid5 = ULID.toBinary("003JZ9J6G80123456789abcdef");
 * </pre>
 *
 * ULID utilities:<br>
 *
 * <pre>
 * String ulid = "003JZ9J6G80123456789abcdef";
 * // Validate ULID string
 * assert ULID.isValid(ulid);
 * // Get timestamp from ULID string
 * long ts = ULID.getTimestamp(ulid);
 * assert ts == 123456789000L;
 * // Get entropy from ULID string
 * byte[] entropy = ULID.getEntropy(ulid);
 *
 * byte[] ulidBinary =
 *     new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0xf, 0xf, 0xf, 0xf, 0xf, 0xf};
 * // Validate ULID binary
 * assert ULID.isValidBinary(ulidBinary);
 * // Get timestamp from ULID binary
 * long tsBinary = ULID.getTimestampBinary(ulidBinary);
 * assert tsBinary == 1L;
 * // Get entropy from ULID binary
 * byte[] entropyBinary = ULID.getEntropyBinary(ulidBinary);
 * </pre>
 *
 * ULID string representation:<br>
 *
 * <pre>
 *  01AN4Z07BY      79KA1307SR9X4MV3
 * |----------|    |----------------|
 *  Timestamp          Randomness
 *    48bits             80bits
 * </pre>
 *
 * ULID binary representation:<br>
 *
 * <pre>
 * |0                   1                   2                   3  |
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                      32_bit_uint_time_high                    |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |     16_bit_uint_time_low      |       16_bit_uint_random      |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                       32_bit_uint_random                      |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                       32_bit_uint_random                      |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * </pre>
 *
 * @author azam
 * @since 0.0.1
 *
 * @see <a href="https://github.com/azam">ulidj</a>
 * @see <a href="https://github.com/ulid/spec">ULID Specification</a>
 * @see <a href="https://www.crockford.com/wrmg/base32.html">Crockford Base32 Encoding</a>
 */
public final class ULID implements Serializable, Comparable<ULID> {
  private static final long serialVersionUID = 1L;

  /**
   * ULID string length.
   *
   * @since 0.0.1
   */
  public static final int ULID_LENGTH = 26;

  /**
   * ULID binary length.
   *
   * @since 1.0.4
   */
  public static final int ULID_BINARY_LENGTH = 16;

  /**
   * ULID entropy byte length.
   *
   * @since 1.0.3
   */
  public static final int ENTROPY_LENGTH = 10;

  /**
   * Minimum allowed timestamp value.
   *
   * @since 0.0.1
   */
  public static final long MIN_TIME = 0x0L;

  /**
   * Maximum allowed timestamp value. Encoded value can encode up to 0x0003ffffffffffffL but ULID
   * binary/byte representation states that timestamp will only be 48-bits.
   *
   * @since 0.0.1
   */
  public static final long MAX_TIME = 0x0000ffffffffffffL;

  /**
   * Base32 characters mapping
   *
   * @since 0.0.1
   */
  private static final char[] C = new char[] { //
      0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, //
      0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, //
      0x47, 0x48, 0x4a, 0x4b, 0x4d, 0x4e, 0x50, 0x51, //
      0x52, 0x53, 0x54, 0x56, 0x57, 0x58, 0x59, 0x5a //
  };

  /**
   * {@code char} to {@code byte} O(1) mapping with alternative chars mapping
   */
  private static final byte[] V = new byte[] { //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, //
      (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, //
      (byte) 0x08, (byte) 0x09, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, //
      (byte) 0x0d, (byte) 0x0e, (byte) 0x0f, (byte) 0x10, //
      (byte) 0x11, (byte) 0xff, (byte) 0x12, (byte) 0x13, //
      (byte) 0xff, (byte) 0x14, (byte) 0x15, (byte) 0xff, //
      (byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, //
      (byte) 0x1a, (byte) 0xff, (byte) 0x1b, (byte) 0x1c, //
      (byte) 0x1d, (byte) 0x1e, (byte) 0x1f, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, //
      (byte) 0x0d, (byte) 0x0e, (byte) 0x0f, (byte) 0x10, //
      (byte) 0x11, (byte) 0xff, (byte) 0x12, (byte) 0x13, //
      (byte) 0xff, (byte) 0x14, (byte) 0x15, (byte) 0xff, //
      (byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, //
      (byte) 0x1a, (byte) 0xff, (byte) 0x1b, (byte) 0x1c, //
      (byte) 0x1d, (byte) 0x1e, (byte) 0x1f, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
  };

  /**
   * This allows lazy initialization of the default {@link java.util.Random} instance, backed by
   * {@link java.security.SecureRandom} instance.
   *
   * @since 1.1.0
   */
  private static class LazyDefaults {
    /**
     * Default {@link java.util.Random} instance.
     *
     * @since 1.1.0
     */
    static final Random random = new SecureRandom();
  }

  private final byte[] binary;

  /**
   * Constructs an immutable ULID instance using provided ULID binary byte array.
   *
   * @param value ULID binary
   * @throws IllegalArgumentException if the ULID binary byte array is not valid
   * @since 1.1.0
   */
  private ULID(byte[] value) {
    this.binary = Arrays.copyOf(value, ULID_BINARY_LENGTH);
  }

  /**
   * Extract and return the timestamp part of this ULID instance.
   *
   * @return Unix epoch timestamp in millisecond
   * @since 1.1.0
   */
  public long getTimestamp() {
    return getTimestampBinary(this.binary);
  }

  /**
   * Extract and return the entropy part of this ULID instance.
   *
   * @return Entropy bytes
   * @since 1.1.0
   */
  public byte[] getEntropy() {
    return getEntropyBinary(this.binary);
  }

  /**
   * Convert this ULID instance to its ULID binary representation.
   *
   * @return ULID binary
   * @since 1.1.0
   */
  public byte[] toBinary() {
    return Arrays.copyOf(this.binary, ULID_BINARY_LENGTH);
  }

  /**
   * Convert this ULID instance to its ULID string representation.
   *
   * @return ULID string
   * @since 1.1.0
   */
  @Override
  public String toString() {
    return fromBinary(this.binary);
  }

  /**
   * Compares ULID instance lexicographically.<br>
   * <br>
   * If the two ULID instances share a common prefix, then the lexicographic comparison is the
   * result of comparing the rest of the value of the two ULID instances.<br>
   * <br>
   * A null ULID instance reference is considered lexicographically less than a non-null ULID
   * instance reference.<br>
   * <br>
   * Two null ULID instances references are considered equal.<br>
   * <br>
   * The comparison is consistent with equals, where the ULID instances are equal if and only if
   * they are lexicographically equal.
   *
   * @param obj the ULID instance to compare
   * @return the value 0 if the ULID instance are equal and contain the same value; a value less
   *         than 0 if this ULID instance is lexicographically less than the ULID instance; and a
   *         value greater than 0 if this ULID instance is lexicographically greater than the ULID
   *         instance
   * @since 1.1.0
   */
  @Override
  public int compareTo(ULID obj) {
    return compare(this, obj);
  }

  /**
   * Compares this object to the specified object. The result is true if and only if the argument is
   * not null, is a ULID instance and has the same timestamp and entropy.
   *
   * @param obj the ULID instance to compare with
   * @return true if the objects are the same; false otherwise
   * @since 1.1.0
   */
  @Override
  public boolean equals(Object obj) {
    if ((null == obj) || (obj.getClass() != ULID.class))
      return false;
    return Arrays.equals(this.binary, ((ULID) obj).binary);
  }

  /**
   * Returns a hash code for this ULID instance.
   *
   * @return A hash code value for this ULID instance
   * @since 1.1.0
   */
  @Override
  public int hashCode() {
    return 67 * Arrays.hashCode(this.binary);
  }

  /**
   * Compares two ULID instances lexicographically.<br>
   * <br>
   * If the two ULID instances share a common prefix, then the lexicographic comparison is the
   * result of comparing the rest of the value of the two ULID instances.<br>
   * <br>
   * A null ULID instance reference is considered lexicographically less than a non-null ULID
   * instance reference.<br>
   * <br>
   * The comparison is consistent with equals, where the ULID instances are equal if and only if
   * they are lexicographically equal.
   *
   * @param a the first ULID instance to compare
   * @param b the second ULID instance to compare
   * @return the value 0 if the first and second ULID instance are equal and contain the same value;
   *         a value less than 0 if the first ULID instance is lexicographically less than the
   *         second ULID instance; and a value greater than 0 if the first ULID instance is
   *         lexicographically greater than the second ULID instance
   * @since 1.1.0
   */
  public static int compare(ULID a, ULID b) {
    if (a == b)
      return 0;
    if (a == null || b == null)
      return a == null ? -1 : 1;
    // {@link Arrays.compareUnsigned(byte[], byte[])} is not available in Java 7
    int len = Math.min(a.binary.length, b.binary.length);
    for (int i = 0; i < len; i++) {
      int aInt = a.binary[i] & 0xff;
      int bInt = b.binary[i] & 0xff;
      int cmp = Integer.compare(aInt, bInt);
      if (cmp != 0)
        return cmp;
    }
    return a.binary.length - b.binary.length;
  }

  /**
   * Generate random ULID string using default {@link java.util.Random} instance backed by
   * {@link java.security.SecureRandom} for entropy and {@link System#currentTimeMillis()} for
   * timestamp.
   *
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 0.0.1
   */
  public static String random() {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    LazyDefaults.random.nextBytes(entropy);
    return generate(System.currentTimeMillis(), entropy);
  }

  /**
   * Generate random ULID binary using default {@link java.util.Random} instance backed by
   * {@link java.security.SecureRandom} for entropy and {@link System#currentTimeMillis()} for
   * timestamp.
   *
   * @return ULID binary
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 1.0.4
   */
  public static byte[] randomBinary() {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    LazyDefaults.random.nextBytes(entropy);
    return generateBinary(System.currentTimeMillis(), entropy);
  }

  /**
   * Generate random ULID instance using default {@link java.util.Random} instance backed by
   * {@link java.security.SecureRandom} for entropy and {@link System#currentTimeMillis()} for
   * timestamp.
   *
   * @return ULID instance
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 1.1.0
   */
  public static ULID randomULID() {
    return new ULID(randomBinary());
  }

  /**
   * Generate random ULID string using provided {@link java.util.Random} instance for entropy and
   * {@link System#currentTimeMillis()} for timestamp.
   *
   * @param random {@link java.util.Random} instance
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 0.0.1
   */
  public static String random(Random random) {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    random.nextBytes(entropy);
    return generate(System.currentTimeMillis(), entropy);
  }

  /**
   * Generate random ULID binary using provided {@link java.util.Random} instance for entropy and
   * {@link System#currentTimeMillis()} for timestamp.
   *
   * @param random {@link java.util.Random} instance
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 1.0.4
   */
  public static byte[] randomBinary(Random random) {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    random.nextBytes(entropy);
    return generateBinary(System.currentTimeMillis(), entropy);
  }

  /**
   * Generate random ULID instance using provided {@link java.util.Random} instance for entropy and
   * {@link System#currentTimeMillis()} for timestamp.
   *
   * @param random {@link java.util.Random} instance
   * @return ULID instance
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 1.1.0
   */
  public static ULID randomULID(Random random) {
    return new ULID(randomBinary(random));
  }

  /**
   * Generate random ULID string using provided {@link java.time.Clock} instance for timestamp and
   * default {@link java.util.Random} instance backed by {@link java.security.SecureRandom} for
   * entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 2.0.0
   */
  public static String random(Clock clock) {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    LazyDefaults.random.nextBytes(entropy);
    return generate(clock.millis(), entropy);
  }

  /**
   * Generate random ULID binary using provided {@link java.time.Clock} instance for timestamp and
   * default {@link java.util.Random} instance backed by {@link java.security.SecureRandom} for
   * entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 2.0.0
   */
  public static byte[] randomBinary(Clock clock) {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    LazyDefaults.random.nextBytes(entropy);
    return generateBinary(clock.millis(), entropy);
  }

  /**
   * Generate random ULID instance using provided {@link java.time.Clock} instance for timestamp and
   * default {@link java.util.Random} instance backed by {@link java.security.SecureRandom} for
   * entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @return ULID instance
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 2.0.0
   */
  public static ULID randomULID(Clock clock) {
    return new ULID(randomBinary(clock));
  }

  /**
   * Generate random ULID string using provided {@link java.time.Clock} instance for timestamp and
   * {@link java.util.Random} instance for entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @param random {@link java.util.Random} instance
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 2.0.0
   */
  public static String random(Clock clock, Random random) {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    random.nextBytes(entropy);
    return generate(clock.millis(), entropy);
  }

  /**
   * Generate random ULID binary using provided {@link java.time.Clock} instance for timestamp and
   * {@link java.util.Random} instance for entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @param random {@link java.util.Random} instance
   * @return ULID string
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 2.0.0
   */
  public static byte[] randomBinary(Clock clock, Random random) {
    byte[] entropy = new byte[ENTROPY_LENGTH];
    random.nextBytes(entropy);
    return generateBinary(clock.millis(), entropy);
  }

  /**
   * Generate random ULID instance using provided {@link java.time.Clock} instance for timestamp and
   * {@link java.util.Random} instance for entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @param random {@link java.util.Random} instance
   * @return ULID instance
   * @throws IllegalArgumentException if current time is out of ULID specification
   * @since 2.0.0
   */
  public static ULID randomULID(Clock clock, Random random) {
    return new ULID(randomBinary(clock, random));
  }

  /**
   * Generate ULID string from Unix epoch timestamp in millisecond and entropy bytes. Throws
   * {@link java.lang.IllegalArgumentException} if timestamp is less than {@value #MIN_TIME}, is
   * more than {@value #MAX_TIME}, or entropy bytes is not 10 bytes.
   *
   * @param time Unix epoch timestamp in millisecond
   * @param entropy Entropy bytes
   * @return ULID string
   * @throws IllegalArgumentException if time is out of ULID specification, or entropy is less than
   *         10
   * @since 0.0.1
   */
  public static String generate(long time, byte[] entropy) {
    if (time < MIN_TIME || time > MAX_TIME || entropy == null || entropy.length != ENTROPY_LENGTH)
      throw new IllegalArgumentException(
          "Time is out of ULID specification, or entropy is less than 10 bytes or null");

    char[] chars = new char[ULID_LENGTH];

    // time
    chars[0] = C[((byte) (time >>> 45)) & 0x1f];
    chars[1] = C[((byte) (time >>> 40)) & 0x1f];
    chars[2] = C[((byte) (time >>> 35)) & 0x1f];
    chars[3] = C[((byte) (time >>> 30)) & 0x1f];
    chars[4] = C[((byte) (time >>> 25)) & 0x1f];
    chars[5] = C[((byte) (time >>> 20)) & 0x1f];
    chars[6] = C[((byte) (time >>> 15)) & 0x1f];
    chars[7] = C[((byte) (time >>> 10)) & 0x1f];
    chars[8] = C[((byte) (time >>> 5)) & 0x1f];
    chars[9] = C[((byte) (time)) & 0x1f];

    // entropy
    chars[10] = C[(byte) ((entropy[0] & 0xff) >>> 3)];
    chars[11] = C[(byte) (((entropy[0] << 2) | ((entropy[1] & 0xff) >>> 6)) & 0x1f)];
    chars[12] = C[(byte) (((entropy[1] & 0xff) >>> 1) & 0x1f)];
    chars[13] = C[(byte) (((entropy[1] << 4) | ((entropy[2] & 0xff) >>> 4)) & 0x1f)];
    chars[14] = C[(byte) (((entropy[2] << 1) | ((entropy[3] & 0xff) >>> 7)) & 0x1f)];
    chars[15] = C[(byte) (((entropy[3] & 0xff) >>> 2) & 0x1f)];
    chars[16] = C[(byte) (((entropy[3] << 3) | ((entropy[4] & 0xff) >>> 5)) & 0x1f)];
    chars[17] = C[(byte) (entropy[4] & 0x1f)];
    chars[18] = C[(byte) ((entropy[5] & 0xff) >>> 3)];
    chars[19] = C[(byte) (((entropy[5] << 2) | ((entropy[6] & 0xff) >>> 6)) & 0x1f)];
    chars[20] = C[(byte) (((entropy[6] & 0xff) >>> 1) & 0x1f)];
    chars[21] = C[(byte) (((entropy[6] << 4) | ((entropy[7] & 0xff) >>> 4)) & 0x1f)];
    chars[22] = C[(byte) (((entropy[7] << 1) | ((entropy[8] & 0xff) >>> 7)) & 0x1f)];
    chars[23] = C[(byte) (((entropy[8] & 0xff) >>> 2) & 0x1f)];
    chars[24] = C[(byte) (((entropy[8] << 3) | ((entropy[9] & 0xff) >>> 5)) & 0x1f)];
    chars[25] = C[(byte) (entropy[9] & 0x1f)];

    return new String(chars);
  }

  /**
   * Generate ULID binary from Unix epoch timestamp in millisecond and entropy bytes. Throws
   * {@link java.lang.IllegalArgumentException} if timestamp is less than {@value #MIN_TIME}, is
   * more than {@value #MAX_TIME}, or entropy bytes not 10 bytes.
   *
   * @param time Unix epoch timestamp in millisecond
   * @param entropy Entropy bytes
   * @return ULID string
   * @throws IllegalArgumentException if time is out of ULID specification, or entropy is less than
   *         10
   * @since 1.0.4
   */
  public static byte[] generateBinary(long time, byte[] entropy) {
    if (time < MIN_TIME || time > MAX_TIME || entropy == null || entropy.length != ENTROPY_LENGTH)
      throw new IllegalArgumentException(
          "Time is out of ULID specification, or entropy is less than 10 bytes or null");

    byte[] bytes = new byte[ULID_BINARY_LENGTH];

    // Long to big endian byte array up to 6 bytes
    long ts = time;
    for (int i = 5; i >= 0; i--) {
      bytes[i] = (byte) (ts & 0xff);
      ts = ts >>> 8;
    }

    // Copy over bytes from entropy
    System.arraycopy(entropy, 0, bytes, 6, 10);

    return bytes;
  }

  /**
   * Generate ULID instance from Unix epoch timestamp in millisecond and entropy bytes. Throws
   * {@link java.lang.IllegalArgumentException} if timestamp is less than {@value #MIN_TIME}, is
   * more than {@value #MAX_TIME}, or entropy bytes is null or less than 10 bytes.
   *
   * @param time Unix epoch timestamp in millisecond
   * @param entropy Entropy bytes
   * @return ULID instance
   * @throws IllegalArgumentException if time is out of ULID specification, or entropy is less than
   *         10
   * @since 1.1.0
   */
  public static ULID generateULID(long time, byte[] entropy) {
    return new ULID(generateBinary(time, entropy));

  }

  /**
   * Checks ULID string validity.
   *
   * @param ulid ULID string
   * @return true if ULID string is valid
   * @since 0.0.1
   */
  public static boolean isValid(CharSequence ulid) {
    if (ulid == null || ulid.length() != ULID_LENGTH)
      return false;
    // Maximum encodable timestamp is 50-bit, but ULID limits to 48-bit.
    // {@link ULID.MAX_TIME}
    char char0 = ulid.charAt(0);
    if (char0 > V.length || V[char0] == (byte) 0xff || V[char0] > 0x07)
      return false;
    for (int i = 1; i < ULID_LENGTH; i++) {
      // We only care for chars between 0x00 and 0xff.
      char c = ulid.charAt(i);
      if (c > V.length || V[c] == (byte) 0xff) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks ULID binary validity.
   *
   * @param ulid ULID binary
   * @return true if ULID binary is valid
   * @since 1.0.4
   */
  public static boolean isValidBinary(byte[] ulid) {
    return ulid != null && ulid.length == ULID_BINARY_LENGTH;
  }

  /**
   * Extract and return the timestamp part from ULID string. Expects a valid ULID string.
   *
   * @param ulid ULID string
   * @return Unix epoch timestamp in millisecond
   * @throws IllegalArgumentException if the ULID string is not valid
   * @since 0.0.1
   */
  public static long getTimestamp(CharSequence ulid) {
    if (!isValid(ulid))
      throw new IllegalArgumentException("Invalid ULID string");
    return (long) V[ulid.charAt(0)] << 45 //
        | (long) V[ulid.charAt(1)] << 40 //
        | (long) V[ulid.charAt(2)] << 35 //
        | (long) V[ulid.charAt(3)] << 30 //
        | (long) V[ulid.charAt(4)] << 25 //
        | (long) V[ulid.charAt(5)] << 20 //
        | (long) V[ulid.charAt(6)] << 15 //
        | (long) V[ulid.charAt(7)] << 10 //
        | (long) V[ulid.charAt(8)] << 5 //
        | (long) V[ulid.charAt(9)];
  }

  /**
   * Extract and return the timestamp part from ULID binary.
   *
   * @param ulid ULID string
   * @return Unix epoch timestamp in millisecond
   * @throws IllegalArgumentException if the ULID binary is not valid
   * @since 1.0.4
   */
  public static long getTimestampBinary(byte[] ulid) {
    if (!isValidBinary(ulid))
      throw new IllegalArgumentException("Invalid ULID binary");
    long timestamp = (long) ulid[0] & 0xff;
    timestamp = (timestamp << 8) | ulid[1] & 0xff;
    timestamp = (timestamp << 8) | ulid[2] & 0xff;
    timestamp = (timestamp << 8) | ulid[3] & 0xff;
    timestamp = (timestamp << 8) | ulid[4] & 0xff;
    timestamp = (timestamp << 8) | ulid[5] & 0xff;
    return timestamp;
  }

  /**
   * Extract and return the entropy part from ULID string.
   *
   * @param ulid ULID string
   * @return Entropy bytes
   * @throws IllegalArgumentException if the ULID string is not valid
   * @since 0.0.1
   */
  public static byte[] getEntropy(CharSequence ulid) {
    if (!isValid(ulid))
      throw new IllegalArgumentException("Invalid ULID string");
    byte[] bytes = new byte[ENTROPY_LENGTH];
    bytes[0] = (byte) ((V[ulid.charAt(10)] << 3) //
        | (V[ulid.charAt(11)] & 0xff) >>> 2);
    bytes[1] = (byte) ((V[ulid.charAt(11)] << 6) //
        | V[ulid.charAt(12)] << 1 //
        | (V[ulid.charAt(13)] & 0xff) >>> 4);
    bytes[2] = (byte) ((V[ulid.charAt(13)] << 4) //
        | (V[ulid.charAt(14)] & 0xff) >>> 1);
    bytes[3] = (byte) ((V[ulid.charAt(14)] << 7) //
        | V[ulid.charAt(15)] << 2 //
        | (V[ulid.charAt(16)] & 0xff) >>> 3);
    bytes[4] = (byte) ((V[ulid.charAt(16)] << 5) //
        | V[ulid.charAt(17)]);
    bytes[5] = (byte) ((V[ulid.charAt(18)] << 3) //
        | (V[ulid.charAt(19)] & 0xff) >>> 2);
    bytes[6] = (byte) ((V[ulid.charAt(19)] << 6) //
        | V[ulid.charAt(20)] << 1 //
        | (V[ulid.charAt(21)] & 0xff) >>> 4);
    bytes[7] = (byte) ((V[ulid.charAt(21)] << 4) //
        | (V[ulid.charAt(22)] & 0xff) >>> 1);
    bytes[8] = (byte) ((V[ulid.charAt(22)] << 7) //
        | V[ulid.charAt(23)] << 2 //
        | (V[ulid.charAt(24)] & 0xff) >>> 3);
    bytes[9] = (byte) ((V[ulid.charAt(24)] << 5) //
        | V[ulid.charAt(25)]);
    return bytes;
  }

  /**
   * Extract and return the entropy part from ULID binary.
   *
   * @param ulid ULID binary
   * @return Entropy bytes
   * @throws IllegalArgumentException if the ULID binary is not valid
   * @since 1.0.4
   */
  public static byte[] getEntropyBinary(byte[] ulid) {
    if (!isValidBinary(ulid))
      throw new IllegalArgumentException("Invalid ULID binary");
    byte[] bytes = new byte[ENTROPY_LENGTH];
    System.arraycopy(ulid, 6, bytes, 0, 10);
    return bytes;
  }

  /**
   * Convert a valid ULID string representation to its binary representation.
   *
   * @param ulid ULID string
   * @return ULID binary
   * @throws IllegalArgumentException if the ULID string is not valid
   * @since 1.0.4
   */
  public static byte[] toBinary(CharSequence ulid) {
    if (!isValid(ulid))
      throw new IllegalArgumentException("Invalid ULID string");
    byte[] bytes = new byte[ULID_BINARY_LENGTH];
    // Timestamp
    bytes[0] = (byte) ((V[ulid.charAt(0)] << 5) //
        | V[ulid.charAt(1)]);
    bytes[1] = (byte) ((V[ulid.charAt(2)] << 3) //
        | (V[ulid.charAt(3)] & 0xff) >>> 2);
    bytes[2] = (byte) ((V[ulid.charAt(3)] << 6) //
        | V[ulid.charAt(4)] << 1 //
        | (V[ulid.charAt(5)] & 0xff) >>> 4);
    bytes[3] = (byte) ((V[ulid.charAt(5)] << 4) //
        | (V[ulid.charAt(6)] & 0xff) >>> 1);
    bytes[4] = (byte) ((V[ulid.charAt(6)] << 7) //
        | V[ulid.charAt(7)] << 2 //
        | (V[ulid.charAt(8)] & 0xff) >>> 3);
    bytes[5] = (byte) ((V[ulid.charAt(8)] << 5) //
        | V[ulid.charAt(9)]);
    // Entropy
    bytes[6] = (byte) ((V[ulid.charAt(10)] << 3) //
        | (V[ulid.charAt(11)] & 0xff) >>> 2);
    bytes[7] = (byte) ((V[ulid.charAt(11)] << 6) //
        | V[ulid.charAt(12)] << 1 //
        | (V[ulid.charAt(13)] & 0xff) >>> 4);
    bytes[8] = (byte) ((V[ulid.charAt(13)] << 4) //
        | (V[ulid.charAt(14)] & 0xff) >>> 1);
    bytes[9] = (byte) ((V[ulid.charAt(14)] << 7) //
        | V[ulid.charAt(15)] << 2 //
        | (V[ulid.charAt(16)] & 0xff) >>> 3);
    bytes[10] = (byte) ((V[ulid.charAt(16)] << 5) //
        | V[ulid.charAt(17)]);
    bytes[11] = (byte) ((V[ulid.charAt(18)] << 3) //
        | (V[ulid.charAt(19)] & 0xff) >>> 2);
    bytes[12] = (byte) ((V[ulid.charAt(19)] << 6) //
        | V[ulid.charAt(20)] << 1 //
        | (V[ulid.charAt(21)] & 0xff) >>> 4);
    bytes[13] = (byte) ((V[ulid.charAt(21)] << 4) //
        | (V[ulid.charAt(22)] & 0xff) >>> 1);
    bytes[14] = (byte) ((V[ulid.charAt(22)] << 7) //
        | V[ulid.charAt(23)] << 2 //
        | (V[ulid.charAt(24)] & 0xff) >>> 3);
    bytes[15] = (byte) ((V[ulid.charAt(24)] << 5) //
        | V[ulid.charAt(25)]);
    return bytes;
  }

  /**
   * Convert a valid ULID binary representation to its string representation.
   *
   * @param ulid ULID binary
   * @return ULID string
   * @throws IllegalArgumentException if the ULID binary is not valid
   * @since 1.0.4
   */
  public static String fromBinary(byte[] ulid) {
    if (!isValidBinary(ulid))
      throw new IllegalArgumentException("Invalid ULID binary");
    char[] chars = new char[ULID_LENGTH];

    // time
    chars[0] = C[(byte) (((ulid[0] & 0xff) >>> 5) & 0x1f)];
    chars[1] = C[(byte) (ulid[0] & 0x1f)];
    chars[2] = C[(byte) ((ulid[1] & 0xff) >>> 3)];
    chars[3] = C[(byte) (((ulid[1] << 2) | ((ulid[2] & 0xff) >>> 6)) & 0x1f)];
    chars[4] = C[(byte) (((ulid[2] & 0xff) >>> 1) & 0x1f)];
    chars[5] = C[(byte) (((ulid[2] << 4) | ((ulid[3] & 0xff) >>> 4)) & 0x1f)];
    chars[6] = C[(byte) (((ulid[3] << 1) | ((ulid[4] & 0xff) >>> 7)) & 0x1f)];
    chars[7] = C[(byte) (((ulid[4] & 0xff) >>> 2) & 0x1f)];
    chars[8] = C[(byte) (((ulid[4] << 3) | ((ulid[5] & 0xff) >>> 5)) & 0x1f)];
    chars[9] = C[(byte) (ulid[5] & 0x1f)];

    // entropy
    chars[10] = C[(byte) ((ulid[6] & 0xff) >>> 3)];
    chars[11] = C[(byte) (((ulid[6] << 2) | ((ulid[7] & 0xff) >>> 6)) & 0x1f)];
    chars[12] = C[(byte) (((ulid[7] & 0xff) >>> 1) & 0x1f)];
    chars[13] = C[(byte) (((ulid[7] << 4) | ((ulid[8] & 0xff) >>> 4)) & 0x1f)];
    chars[14] = C[(byte) (((ulid[8] << 1) | ((ulid[9] & 0xff) >>> 7)) & 0x1f)];
    chars[15] = C[(byte) (((ulid[9] & 0xff) >>> 2) & 0x1f)];
    chars[16] = C[(byte) (((ulid[9] << 3) | ((ulid[10] & 0xff) >>> 5)) & 0x1f)];
    chars[17] = C[(byte) (ulid[10] & 0x1f)];
    chars[18] = C[(byte) ((ulid[11] & 0xff) >>> 3)];
    chars[19] = C[(byte) (((ulid[11] << 2) | ((ulid[12] & 0xff) >>> 6)) & 0x1f)];
    chars[20] = C[(byte) (((ulid[12] & 0xff) >>> 1) & 0x1f)];
    chars[21] = C[(byte) (((ulid[12] << 4) | ((ulid[13] & 0xff) >>> 4)) & 0x1f)];
    chars[22] = C[(byte) (((ulid[13] << 1) | ((ulid[14] & 0xff) >>> 7)) & 0x1f)];
    chars[23] = C[(byte) (((ulid[14] & 0xff) >>> 2) & 0x1f)];
    chars[24] = C[(byte) (((ulid[14] << 3) | ((ulid[15] & 0xff) >>> 5)) & 0x1f)];
    chars[25] = C[(byte) (ulid[15] & 0x1f)];

    return new String(chars);
  }

  /**
   * Constructs an immutable ULID instance using provided ULID string.
   *
   * @param ulid ULID string
   * @return ULID instance
   * @throws IllegalArgumentException if the ULID string is not valid
   * @since 1.1.0
   */
  public static ULID parseULID(CharSequence ulid) {
    return new ULID(toBinary(ulid));
  }

  /**
   * Constructs an immutable ULID instance using provided ULID binary byte array.
   *
   * @param ulid ULID binary
   * @return ULID instance
   * @throws IllegalArgumentException if the ULID binary byte array is not valid
   * @since 1.1.0
   */
  public static ULID parseULID(byte[] ulid) {
    if (isValidBinary(ulid))
      return new ULID(ulid);
    throw new IllegalArgumentException("Invalid ULID binary");
  }
}
