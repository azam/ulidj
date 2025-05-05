package io.azam.ulidj;

import java.util.UUID;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class UUIDBenchmark {
  public static final String TEST_VALUE = "00526084-9560-7fff-ffff-ffffffffffff";

  @Benchmark
  public void randomUUID(Blackhole blackhole) {
    blackhole.consume(UUID.randomUUID());
  }

  @Benchmark
  public void fromString(Blackhole blackhole) {
    blackhole.consume(UUID.fromString(TEST_VALUE));
  }
}
