window.BENCHMARK_DATA = {
  "lastUpdate": 1748099796878,
  "repoUrl": "https://github.com/azam/ulidj",
  "entries": {
    "branch-develop-temurin-jdk21-target-jdk17": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azamshul@gmail.com"
          },
          "committer": {
            "name": "azam",
            "username": "azam",
            "email": "azamshul@gmail.com"
          },
          "id": "1731f9648e1dde3c6f1e05046f078a0b4df3305e",
          "message": "Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.",
          "timestamp": "2025-05-24T14:34:08Z",
          "url": "https://github.com/azam/ulidj/commit/1731f9648e1dde3c6f1e05046f078a0b4df3305e"
        },
        "date": 1748099796433,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.17381406507812,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.10204401246983,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.17880446858691,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.16558636365698,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 60.36791465529028,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.40539592639246,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.888141482063446,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.72651550427407,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.3797263399072,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.42437778179628,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.33041729227355,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.48250795105867,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 29.603466002899367,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.6707251083139,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.168089631568936,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.462263795455039,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5554209706026723,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.56612115270386,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 78.90902409793894,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 203.49729466195774,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 184.35833242273804,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.64869894352541,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 186.09146389294997,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.23271464735093,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 187.6567359557737,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 186.3126648189094,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.49603918165489,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 65.3771024368136,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 205.75379145625536,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 62.29815919098758,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6240861667598121,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.586706060638168,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.452909189589269,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 214.95912873024503,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ]
  }
}