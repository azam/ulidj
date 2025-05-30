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

public class MonotonicULIDBenchmark {
  @State(Scope.Thread)
  public static class MonotonicULIDState {
    public MonotonicULID noArgs;
    public MonotonicULID random;
    public MonotonicULID secureRandom;
    public MonotonicULID threadLocalRandom;
    public MonotonicULID systemDefaultZone;
    public MonotonicULID systemUTC;

    @Setup(Level.Trial)
    public void doSetup() {
      this.noArgs = new MonotonicULID();
      this.random = new MonotonicULID(new Random());
      this.secureRandom = new MonotonicULID(new SecureRandom());
      this.threadLocalRandom = new MonotonicULID(ThreadLocalRandom.current());
      this.systemDefaultZone = new MonotonicULID(MutableFixedClock.systemDefaultZone());
      this.systemUTC = new MonotonicULID(MutableFixedClock.systemUTC());
    }
  }

  @Benchmark
  public void generateNoArgs(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.noArgs.generate());
  }

  @Benchmark
  public void generateBinaryNoArgs(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.noArgs.generateBinary());
  }

  @Benchmark
  public void generateULIDNoArgs(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.noArgs.generateULID());
  }

  @Benchmark
  public void generateRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.random.generate());
  }

  @Benchmark
  public void generateBinaryRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.random.generateBinary());
  }

  @Benchmark
  public void generateULIDRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.random.generateULID());
  }

  @Benchmark
  public void generateSecureRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.secureRandom.generate());
  }

  @Benchmark
  public void generateBinarySecureRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.secureRandom.generateBinary());
  }

  @Benchmark
  public void generateULIDSecureRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.secureRandom.generateULID());
  }

  @Benchmark
  public void generateThreadLocalRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.threadLocalRandom.generate());
  }

  @Benchmark
  public void generateBinaryThreadLocalRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.threadLocalRandom.generateBinary());
  }

  @Benchmark
  public void generateULIDThreadLocalRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.threadLocalRandom.generateULID());
  }

  @Benchmark
  public void generateSystemDefaultZone(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.systemDefaultZone.generate());
  }

  @Benchmark
  public void generateBinarySystemDefaultZone(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.systemDefaultZone.generateBinary());
  }

  @Benchmark
  public void generateULIDSystemDefaultZone(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.systemDefaultZone.generateULID());
  }

  @Benchmark
  public void generateSystemUTC(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.systemUTC.generate());
  }

  @Benchmark
  public void generateBinarySystemUTC(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.systemUTC.generateBinary());
  }

  @Benchmark
  public void generateULIDSystemUTC(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.systemUTC.generateULID());
  }

  @Benchmark
  public void random(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(MonotonicULID.random());
  }

  @Benchmark
  public void randomBinary(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(MonotonicULID.randomBinary());
  }

  @Benchmark
  public void randomULID(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(MonotonicULID.randomULID());
  }
}
