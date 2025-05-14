package io.azam.ulidj;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MonotonicULIDBenchmark {
  @State(Scope.Thread)
  public static class MonotonicULIDState {
    public MonotonicULID random;
    public MonotonicULID secureRandom;
    public MonotonicULID threadLocalRandom;

    @Setup(Level.Trial)
    public void doSetup() {
      random = new MonotonicULID(new Random());
      secureRandom = new MonotonicULID(new SecureRandom());
      threadLocalRandom = new MonotonicULID(ThreadLocalRandom.current());
    }
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
  public void generateSecureRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.secureRandom.generate());
  }

  @Benchmark
  public void generateBinarySecureRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.secureRandom.generateBinary());
  }

  @Benchmark
  public void generateThreadLocalRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.threadLocalRandom.generate());
  }

  @Benchmark
  public void generateBinaryThreadRandom(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.threadLocalRandom.generateBinary());
  }
}
