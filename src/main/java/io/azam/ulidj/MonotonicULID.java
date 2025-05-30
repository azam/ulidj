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
import java.util.Random;

/**
 * Monotonic instance of ULID. ULID spec defines monotonicity behavior as if a ULID is to be
 * generated in the same millisecond, the entropy(random) component is to be incremented by 1-bit in
 * the least significant bit with carryover.<br>
 * <br>
 * In order to achieve monotonicity, the state for timestamp and its last generated entropy must be
 * persisted so that the next ULID generated within the same millisecond can generate an incremented
 * byte array for its entropy.<br>
 * <br>
 * This class achieves that via behaving as a factory class that generates ULID strings, binary and
 * ULID instances with the last timestamp and entropy persisted in the instance, and generates a new
 * ULID based on that. It guarantees the monotonicity of ULID (string/binary/ULID instance) if
 * called with the same {@link io.azam.ulidj.MonotonicULID} instance within the JVM.<br>
 * <br>
 * Normally you would want to create a singleton bean of this class in your application and use that
 * to generate ULIDs.<br>
 * <br>
 * Monotonic ULID examples:<br>
 *
 * <pre>
 * // Using default MonotonicULID static instance backed by SecureRandom
 * String ulid1 = MonotonicULID.random();
 * // Subsequent calls generates monotonic ULID
 * String ulid2 = MonotonicULID.random();
 * byte[] ulid3 = MonotonicULID.randomBinary();
 * ULID ulid4 = MonotonicULID.randomULID();
 *
 * // Create a new independent instance of MonotonicULID using default Random
 * // instance backed by SecureRandom
 * MonotonicULID monotonicUlid = new MonotonicULID();
 * String ulid5 = monotonicUlid.generate();
 * // Subsequent calls generates monotonic ULID
 * String ulid6 = monotonicUlid.generate();
 * byte[] ulid7 = monotonicUlid.generateBinary();
 * ULID ulid8 = monotonicUlid.generateULID();
 *
 * // Create a new independent instance of MonotonicULID using provided Random instance
 * MonotonicULID monotonicUlid2 = new MonotonicULID(SecureRandom.newInstance("SHA1PRNG"));
 * String ulid9 = monotonicUlid2.generate();
 * // Subsequent calls generates monotonic ULID
 * String ulid10 = monotonicUlid2.generate();
 * byte[] ulid11 = monotonicUlid2.generateBinary();
 * ULID ulid12 = monotonicUlid2.generateULID();
 * </pre>
 *
 * @author azam
 * @since 1.0.3
 *
 * @see <a href="https://github.com/azam">ulidj</a>
 * @see <a href="https://github.com/ulid/spec">ULID Specification</a>
 * @see <a href="https://www.crockford.com/wrmg/base32.html">Crockford Base32 Encoding</a>
 */
public final class MonotonicULID implements Serializable {
  private static final long serialVersionUID = 2L;

  /**
   * {@link java.time.Clock} instance used to generate timestamp (milliseconds since epoch) for
   * ULID.
   */
  private final Clock clock;

  /**
   * {@link java.util.Random} instance used to generate entropy for ULID.
   */
  private final Random random;

  /**
   * Last timestamp generated in the last call to {@link #generate()} or {@link #generateBinary()}
   * or {@link #generateULID()}.<br>
   * <br>
   * This is used to check if the current time is less than or equal to the last timestamp so that
   * the entropy can be incremented by 1-bit in the least significant bit with carryover.
   */
  private long lastTimestamp;

  /**
   * Last entropy generated in the last call to {@link #generate()} or {@link #generateBinary()} or
   * {@link #generateULID()}.<br>
   * <br>
   * This is used to increment the entropy in the least significant bit with carryover if this
   * method is called within the same millisecond.
   */
  private final byte[] lastEntropy;

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

    /**
     * Default {@link io.azam.ulidj.MonotonicULID} instance.
     *
     * @since 1.1.0
     */
    static final MonotonicULID singleton = new MonotonicULID(Clock.systemUTC(), random);
  }

  /**
   * Generate a monotonic ULID generator instance using default {@link java.util.Random} instance
   * backed by {@link java.security.SecureRandom} for entropy and {@link Clock#systemUTC()} for
   * timestamp.
   *
   * @since 1.0.3
   */
  public MonotonicULID() {
    this(Clock.systemUTC(), LazyDefaults.random);
  }

  /**
   * Generate a monotonic ULID generator instance using provided {@link java.util.Random} instance
   * for entropy and {@link Clock#systemUTC()} for timestamp.
   *
   * @param random {@link java.util.Random} instance
   * @since 1.0.3
   */
  public MonotonicULID(Random random) {
    this(Clock.systemUTC(), random);
  }

  /**
   * Generate a monotonic ULID generator instance using provided {@link java.time.Clock} instance
   * for timestamp and default {@link java.util.Random} instance backed by
   * {@link java.security.SecureRandom} for entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @since 2.0.0
   */
  public MonotonicULID(Clock clock) {
    this(clock, LazyDefaults.random);
  }

  /**
   * Generate a monotonic ULID generator instance using provided {@link java.time.Clock} instance
   * for timestamp and {@link java.util.Random} instance for entropy.
   *
   * @param clock {@link java.time.Clock} instance
   * @param random {@link java.util.Random} instance
   * @since 2.0.0
   */
  public MonotonicULID(Clock clock, Random random) {
    if (clock == null || random == null)
      throw new IllegalArgumentException("clock or random instance is null");
    this.clock = clock;
    this.random = random;
    this.lastTimestamp = 0L;
    this.lastEntropy = new byte[ULID.ENTROPY_LENGTH];
  }

  /**
   * Generate ULID string monotonically. If this method is called within the same millisecond, last
   * entropy will be incremented by 1 and the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes).
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
   * overflows entropy length (80-bits/10-bytes).
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
    long now = this.clock.millis();
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

  /**
   * Generate ULID string monotonically with {@link Clock#systemUTC()} for timestamp and default
   * {@link java.util.Random} instance backed by {@link java.security.SecureRandom} for entropy. If
   * this method is called within the same millisecond, last entropy will be incremented by 1 and
   * the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes).
   *
   * @return ULID string
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.0.3
   */
  public static String random() {
    return LazyDefaults.singleton.generate();
  }

  /**
   * Generate ULID binary monotonically with {@link Clock#systemUTC()} for timestamp and default
   * {@link java.util.Random} instance backed by {@link java.security.SecureRandom} for entropy. If
   * this method is called within the same millisecond, last entropy will be incremented by 1 and
   * the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes).
   *
   * @return ULID binary
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.0.4
   */
  public static byte[] randomBinary() {
    return LazyDefaults.singleton.generateBinary();
  }

  /**
   * Generate ULID instance monotonically with {@link Clock#systemUTC()} for timestamp and default
   * {@link java.util.Random} instance backed by {@link java.security.SecureRandom} for entropy. If
   * this method is called within the same millisecond, last entropy will be incremented by 1 and
   * the ULID string of incremented value is returned.<br>
   * <br>
   * This method will throw a {@link java.lang.IllegalStateException} exception if incremented value
   * overflows entropy length (80-bits/10-bytes).
   *
   * @return ULID instance
   * @throws IllegalStateException if time is out of ULID specification or entropy overflowed
   * @since 1.1.0
   */
  public static ULID randomULID() {
    return LazyDefaults.singleton.generateULID();
  }
}
