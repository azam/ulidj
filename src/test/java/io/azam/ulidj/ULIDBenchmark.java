package io.azam.ulidj;

import java.security.SecureRandom;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class ULIDBenchmark {
  private static final String TEST_VALUE = "00A9G895B0ZZZZZZZZZZZZZZZZ";

  @Benchmark
  public void random(Blackhole blackhole) {
    blackhole.consume(ULID.random(new Random()));
  }

  @Benchmark
  public void secureRandom(Blackhole blackhole) {
    blackhole.consume(ULID.random(new SecureRandom()));
  }

  @Benchmark
  public void getTimestamp(Blackhole blackhole) {
    blackhole.consume(ULID.getTimestamp(TEST_VALUE));
  }

  @Benchmark
  public void getEntropy(Blackhole blackhole) {
    blackhole.consume(ULID.getEntropy(TEST_VALUE));
  }
}
