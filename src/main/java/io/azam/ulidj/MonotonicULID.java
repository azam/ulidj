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
import java.time.Clock;
import java.util.Random;

/**
 * Monotonic instance of ULID. ULID spec defines monotonicity behavior as if a ULID is to be
 * generated in the same millisecond, the entropy(random) component is to be incremented by 1-bit in
 * the least significant bit with carryover.<br>
 * <br>
 * In practice this behavior is however applicable to ULID's generated from the same source (in
 * Java, from the same instance), else an external synchronization is needed. Hence, instance of
 * this class will produce ULID in monotonic order only if called from the same instance.<br>
 * <br>
 * Usage example:<br>
 * <br>
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
 */
public class MonotonicULID {
  private final Random random;
  private final byte[] lastEntropy;
  private final Clock clock;
  private long lastTimestamp;

  /**
   * This allows lazy initialization of the default {@link java.util.Random} instance, backed by
   * {@link java.security.SecureRandom} instance.
   */
  private static class LazyDefaults {
    /**
     * Default {@link java.util.Random} instance.
     */
    static final Random random = new SecureRandom();
  }

  /**
   * Generate a monotonic ULID generator instance using default {@link java.util.Random} instance
   * backed by {@link java.security.SecureRandom}.
   */
  public MonotonicULID() {
    this(LazyDefaults.random, null);
  }

  /**
   * Generate a monotonic ULID generator instance using provided {@link java.util.Random} instance.
   *
   * @param random {@link java.util.Random} instance
   */
  public MonotonicULID(Random random) {
    this(random, null);
  }

  /**
   * Generate a monotonic ULID generator instance using default {@link java.util.Random} backed by
   * {@link java.security.SecureRandom}.instance with provided {@link java.time.Clock} instance as
   * timestamp provider.
   *
   * @param clock {@link java.time.Clock} instance
   * @since 2.0.0
   */
  public MonotonicULID(Clock clock) {
    this(null, clock);
  }

  /**
   * Generate a monotonic ULID generator instance using provided {@link java.util.Random} instance
   * with provided {@link java.time.Clock} instance as timestamp provider.
   *
   * @param random {@link java.util.Random} instance
   * @param clock {@link java.time.Clock} instance
   * @since 2.0.0
   */
  public MonotonicULID(Random random, Clock clock) {
    this.random = random == null ? LazyDefaults.random : random;
    this.clock = clock;
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
   */
  public synchronized String generate() {
    long now = this.clock == null ? System.currentTimeMillis() : this.clock.millis();
    if (now < ULID.MIN_TIME || now > ULID.MAX_TIME) {
      throw new IllegalStateException("Time is out of ULID specification");
    }
    if (now <= this.lastTimestamp) {
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
   */
  public synchronized byte[] generateBinary() {
    long now = this.clock == null ? System.currentTimeMillis() : this.clock.millis();
    if (now < ULID.MIN_TIME || now > ULID.MAX_TIME) {
      throw new IllegalStateException("Time is out of ULID specification");
    }
    if (now <= this.lastTimestamp) {
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
   * @since 2.0.0
   */
  public synchronized ULID generateULID() {
    return new ULID(generateBinary());
  }
}
