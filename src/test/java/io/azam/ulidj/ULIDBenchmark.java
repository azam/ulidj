package io.azam.ulidj;

import java.security.SecureRandom;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class ULIDBenchmark {
  private static final String TEST_VALUE = "00A9G895B0ZZZZZZZZZZZZZZZZ";
  private static final byte[] TEST_BINARY = new byte[] { //
      (byte) 0x00, (byte) 0x52, (byte) 0x60, (byte) 0x84, //
      (byte) 0x95, (byte) 0x60, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
      (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
  };

  @Benchmark
  public void generateRandom(Blackhole blackhole) {
    blackhole.consume(ULID.random(new Random()));
  }

  @Benchmark
  public void generateBinaryRandom(Blackhole blackhole) {
    blackhole.consume(ULID.randomBinary(new Random()));
  }

  @Benchmark
  public void generateSecureRandom(Blackhole blackhole) {
    blackhole.consume(ULID.random(new SecureRandom()));
  }

  @Benchmark
  public void generateBinarySecureRandom(Blackhole blackhole) {
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

  @Benchmark
  public void toBinary(Blackhole blackhole) {
    blackhole.consume(ULID.toBinary(TEST_VALUE));
  }

  @Benchmark
  public void fromBinary(Blackhole blackhole) {
    blackhole.consume(ULID.fromBinary(TEST_BINARY));
  }
}
