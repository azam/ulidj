window.BENCHMARK_DATA = {
  "lastUpdate": 1747266732272,
  "repoUrl": "https://github.com/azam/ulidj",
  "entries": {
    "develop-bench-temurin-jdk21-target-jdk11": [
      {
        "commit": {
          "author": {
            "email": "azamshul@gmail.com",
            "name": "azam",
            "username": "azam"
          },
          "committer": {
            "email": "azamshul@gmail.com",
            "name": "azam",
            "username": "azam"
          },
          "distinct": true,
          "id": "712dbcef33c58c47574ad5939f4bf22302a85e55",
          "message": "Fix Github action triggers for tag. Add github-action-benchmark action.",
          "timestamp": "2025-05-15T08:25:45+09:00",
          "tree_id": "063103ce11973d7612759b57c75bbc56cd6bc5de",
          "url": "https://github.com/azam/ulidj/commit/712dbcef33c58c47574ad5939f4bf22302a85e55"
        },
        "date": 1747266731962,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 44.116624345531655,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 44.21816760169429,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 44.11763003966542,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 58.38142791857015,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 58.70786153254535,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 58.5154644756072,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 28.516164208790013,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 13.351636715701721,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 3.1135744543307373,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 202.7056470026432,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 185.3032517175104,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryExternalRandom",
            "value": 44.608324310536325,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryExternalSecureRandom",
            "value": 185.13824379802514,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryExternalThreadLocalRandom",
            "value": 41.36830174412277,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryNewRandom",
            "value": 87.17834592270252,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryNewSecureRandom",
            "value": 264.00794724832275,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomExternalRandom",
            "value": 65.59793108101668,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomExternalSecureRandom",
            "value": 204.42395053161272,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomExternalThreadLocalRandom",
            "value": 62.93516844746553,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomNewRandom",
            "value": 104.24109759720088,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomNewSecureRandom",
            "value": 290.14007908396695,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 18.777113602978893,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.4300458980945185,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 212.95541211742548,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ]
  }
}