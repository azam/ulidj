package io.azam.ulidj;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class MonotonicULIDBenchmark {
  @State(Scope.Thread)
  public static class MonotonicULIDState {
    public MonotonicULID instance;

    @Setup(Level.Trial)
    public void doSetup() {
      instance = new MonotonicULID();
    }
  }

  @Benchmark
  public void generate(Blackhole blackhole, MonotonicULIDState state) {
    blackhole.consume(state.instance.generate());
  }
}
