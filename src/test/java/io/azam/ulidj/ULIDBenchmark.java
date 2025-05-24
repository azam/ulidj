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
import java.util.concurrent.ThreadLocalRandom;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class ULIDBenchmark {
  private static final String TEST_VALUE = "00A9G895B0ZZZZZZZZZZZZZZZZ";
  private static final byte[] TEST_BINARY = new byte[] { //
      (byte) 0x00, (byte) 0x52, (byte) 0x60, (byte) 0x84, //
      (byte) 0x95, (byte) 0x60, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
  };

  @State(Scope.Thread)
  public static class ULIDState {
    public Random random;
    public SecureRandom secureRandom;
    public ThreadLocalRandom threadLocalRandom;

    @Setup(Level.Trial)
    public void doSetup() {
      this.random = new Random();
      this.secureRandom = new SecureRandom();
      this.threadLocalRandom = ThreadLocalRandom.current();
    }
  }

  @Benchmark
  public void random(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random());
  }

  @Benchmark
  public void randomBinary(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary());
  }

  @Benchmark
  public void randomULID(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomULID());
  }

  @Benchmark
  public void randomWithRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.random));
  }

  @Benchmark
  public void randomBinaryWithRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.random));
  }

  @Benchmark
  public void randomWithSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.secureRandom));
  }

  @Benchmark
  public void randomBinaryWithSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.secureRandom));
  }

  @Benchmark
  public void randomULIDWithSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomULID(state.secureRandom));
  }

  @Benchmark
  public void randomWithThreadLocalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.threadLocalRandom));
  }

  @Benchmark
  public void randomBinaryWithThreadLocalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.threadLocalRandom));
  }

  @Benchmark
  public void randomULIDWithThreadLocalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomULID(state.threadLocalRandom));
  }

  @Benchmark
  public void getTimestamp(Blackhole blackhole) {
    blackhole.consume(ULID.getTimestamp(TEST_VALUE));
  }

  @Benchmark
  public void getTimestampBinary(Blackhole blackhole) {
    blackhole.consume(ULID.getTimestampBinary(TEST_BINARY));
  }

  @Benchmark
  public void getEntropy(Blackhole blackhole) {
    blackhole.consume(ULID.getEntropy(TEST_VALUE));
  }

  @Benchmark
  public void getEntropyBinary(Blackhole blackhole) {
    blackhole.consume(ULID.getEntropyBinary(TEST_BINARY));
  }

  @Benchmark
  public void toBinary(Blackhole blackhole) {
    blackhole.consume(ULID.toBinary(TEST_VALUE));
  }

  @Benchmark
  public void fromBinary(Blackhole blackhole) {
    blackhole.consume(ULID.fromBinary(TEST_BINARY));
  }

  @Benchmark
  public void parseULIDString(Blackhole blackhole) {
    blackhole.consume(ULID.parseULID(TEST_VALUE));
  }

  @Benchmark
  public void parseULIDBinary(Blackhole blackhole) {
    blackhole.consume(ULID.parseULID(TEST_BINARY));
  }

  @Benchmark
  public void newRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(new Random());
  }

  @Benchmark
  public void newSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(new SecureRandom());
  }

  @Benchmark
  public void threadLocalRandomCurrent(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ThreadLocalRandom.current());
  }
}
