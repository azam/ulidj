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

import java.security.SecureRandom;
import java.util.Random;

/**
 * Monotonic instance of ULID. ULID spec defines monotonicity behavior as if a ULID is to be
 * generated in the same millisecond, the entropy(random) component is to be incremented by 1-bit in
 * the least significant bit with carryover.<br>
 *
 * In practice this behavior is however applicable to ULID's generated from the same source (in
 * Java, from the same instance), else an external synchronization is needed. Hence, instance of
 * this class will produce ULID in monotonic order only if called from the same instance, e.g. from
 * a singleton Bean.<br>
 *
 * Usage example:<br>
 *
 * <pre>
 * MonotonicULID ulid = new MonotonicULID();
 * String ulid1 = ulid.generate();
 * String ulid2 = ulid.generate();
 * String ulid3 = ulid.generate();
 * byte[] ulid4 = ulid.generateBinary();
 * ULID ulid5 = ulid.generateULID();
 * </pre>
 *
 * @see <a href="https://github.com/ulid/spec">ULID</a>
 *
 * @author azam
 * @since 1.0.3
 *
 * @see <a href="https://github.com/azam">ulidj</a>
 * @see <a href="https://github.com/ulid/spec">ULID Specification</a>
 * @see <a href="https://www.crockford.com/wrmg/base32.html">Crockford Base32 Encoding</a>
 */
public class MonotonicULID {
  private final Random random;
  private final byte[] lastEntropy;
  private long lastTimestamp;

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

  /**
   * Generate a monotonic ULID generator instance using default {@link java.util.Random} instance
   * backed by {@link java.security.SecureRandom}.
   *
   * @since 1.0.3
   */
  public MonotonicULID() {
    this(LazyDefaults.random);
  }

  /**
   * Generate a monotonic ULID generator instance.
   *
   * @param random {@link java.util.Random} instance
   * @since 1.0.3
   */
  public MonotonicULID(Random random) {
    this.random = random == null ? LazyDefaults.random : random;
    this.lastEntropy = new byte[ULID.ENTROPY_LENGTH];
    this.lastTimestamp = 0L;
  }

  /**
   * Generate ULID string monotonically. If this method is called within the same millisecond, last
   * entropy will be incremented by 1 and the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes)
   *
   * @return ULID string
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.0.3
   */
  public synchronized String generate() {
    mutate();
    return ULID.generate(this.lastTimestamp, this.lastEntropy);
  }

  /**
   * Generate ULID binary monotonically. If this method is called within the same millisecond, last
   * entropy will be incremented by 1 and the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes)
   *
   * @return ULID binary
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.0.4
   */
  public synchronized byte[] generateBinary() {
    mutate();
    return ULID.generateBinary(this.lastTimestamp, this.lastEntropy);
  }

  /**
   * Generate ULID instance monotonically. If this method is called within the same millisecond,
   * last entropy will be incremented by 1 and the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes)
   *
   * @return ULID instance
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.1.0
   */
  public synchronized ULID generateULID() {
    mutate();
    return ULID.generateULID(this.lastTimestamp, this.lastEntropy);
  }

  /**
   * Check if the last timestamp is less than the current time. If so, generate new entropy
   * otherwise increment the last entropy by 1-bit in the least significant bit with carryover. If
   * the last entropy overflows, revert to the previous value and throw an exception.
   *
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.1.0
   */
  private void mutate() {
    long now = System.currentTimeMillis();
    if (now < ULID.MIN_TIME || now > ULID.MAX_TIME)
      throw new IllegalStateException("Time is out of ULID specification");
    if (now <= this.lastTimestamp) {
      // Keep last timestamp and increment last entropy
      // Save a copy of last entropy in case of entropy overflow
      byte[] previousEntropy = new byte[ULID.ENTROPY_LENGTH];
      System.arraycopy(this.lastEntropy, 0, previousEntropy, 0, ULID.ENTROPY_LENGTH);
      // Entropy is big-endian (network byte order) per ULID spec
      // Increment last entropy by 1
      boolean carry = true;
      for (int i = ULID.ENTROPY_LENGTH - 1; i >= 0; i--) {
        if (carry) {
          byte work = this.lastEntropy[i];
          work = (byte) (work + 0x01);
          carry = this.lastEntropy[i] == (byte) 0xff;
          this.lastEntropy[i] = work;
        }
      }
      // Last byte has carry over
      if (carry) {
        // Revert last entropy to previous value
        System.arraycopy(previousEntropy, 0, this.lastEntropy, 0, ULID.ENTROPY_LENGTH);
        // Throw error if entropy overflows in same millisecond per ULID spec
        throw new IllegalStateException("ULID entropy overflowed for same millisecond");
      }
    } else {
      // Generate new entropy
      this.lastTimestamp = now;
      this.random.nextBytes(this.lastEntropy);
    }
  }
}
