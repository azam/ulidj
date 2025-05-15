/**
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
    public Clock systemUTC;

    @Setup(Level.Trial)
    public void doSetup() {
      this.random = new Random();
      this.secureRandom = new SecureRandom();
      this.threadLocalRandom = ThreadLocalRandom.current();
      this.systemUTC = Clock.systemUTC();
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
  public void randomExternalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.random));
  }

  @Benchmark
  public void randomBinaryExternalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.random));
  }

  @Benchmark
  public void randomExternalSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.secureRandom));
  }

  @Benchmark
  public void randomBinaryExternalSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.secureRandom));
  }

  @Benchmark
  public void randomExternalThreadLocalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.threadLocalRandom));
  }

  @Benchmark
  public void randomBinaryExternalThreadLocalRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.threadLocalRandom));
  }

  @Benchmark
  public void randomNewRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(new Random()));
  }

  @Benchmark
  public void randomBinaryNewRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(new Random()));
  }

  @Benchmark
  public void randomNewSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(new SecureRandom()));
  }

  @Benchmark
  public void randomBinaryNewSecureRandom(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(new SecureRandom()));
  }

  @Benchmark
  public void randomSystemUTC(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(state.systemUTC));
  }

  @Benchmark
  public void randomBinarySystemUTC(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(state.systemUTC));
  }

  @Benchmark
  public void randomNewSystemUTC(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.random(Clock.systemUTC()));
  }

  @Benchmark
  public void randomBinaryNewSystemUTC(Blackhole blackhole, ULIDState state) {
    blackhole.consume(ULID.randomBinary(Clock.systemUTC()));
  }

  @Benchmark
  public void getTimestamp(Blackhole blackhole) {
    blackhole.consume(ULID.getTimestamp(TEST_VALUE));
  }

  @Benchmark
  public void getEntropy(Blackhole blackhole) {
    blackhole.consume(ULID.getEntropy(TEST_VALUE));
  }

  @Benchmark
  public void toBinary(Blackhole blackhole) {
    blackhole.consume(ULID.toBinary(TEST_VALUE));
  }

  @Benchmark
  public void fromBinary(Blackhole blackhole) {
    blackhole.consume(ULID.fromBinary(TEST_BINARY));
  }
}
