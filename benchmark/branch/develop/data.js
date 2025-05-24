window.BENCHMARK_DATA = {
  "lastUpdate": 1748099801544,
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
    ],
    "branch-develop-temurin-jdk21-target-jdk11": [
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
        "date": 1748099797570,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.193783382448224,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.13148921606091,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.18686466847372,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.14142615877882,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 59.40998432932636,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 60.06252505205457,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.40525977202011,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.55169700934856,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.46669795263344,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.5323022154551,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.46999834993399,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.52442994392125,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 37.719049476306324,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.608035929517666,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.1800231887512505,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.454390559778119,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5556340292485304,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.65048198054027,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 77.1539020550538,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 202.85755431404726,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 184.7167177597608,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.70518199487373,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 185.0856562354812,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.35761299995206,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 186.69158686746246,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 191.29914584818886,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.69325280750508,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 65.37164795017216,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 206.6534529326761,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 62.632238457545206,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6227491417330224,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.56919943826616,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.441052094293428,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 215.37065611679847,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-develop-temurin-jdk21-target-jdk8": [
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
        "date": 1748099798349,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.22874110865658,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.169707981546466,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.85506770957919,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.177803694882456,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 60.0741195693219,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.25497771353358,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 60.10559349494635,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.1263748669749,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.36943412537266,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.51584790271565,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.33811863058499,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.33794875676635,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 29.40169435935927,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.635474878919513,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.1564778113286005,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.449055490582149,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5551510222181395,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.58429292202543,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 75.35964783981711,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 202.0409706197129,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 184.2260375840013,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.63420997982089,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 183.33534120460232,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.25900915022797,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 184.38402776352274,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 188.46096346968451,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.3838294320307,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 67.13045855139634,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 204.7912155035311,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 63.67492483175839,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6231238725846002,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.691694599298227,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.449929685538563,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 212.6822070896621,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-develop-temurin-jdk8-target-jdk7": [
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
        "date": 1748099798025,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 48.90304261959643,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 47.99415296859423,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 48.63402154452816,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 48.25738223799416,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 62.381809975601755,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 62.260443078856454,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 62.59836996665043,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 61.98307566915198,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 56.161042987726695,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 56.04453303631428,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 56.17621446592913,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 56.30623776699379,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 35.660350049804,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 31.430243132817047,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 6.149519219289691,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 26.15800233485281,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 3.1128165531963234,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 47.60092008850682,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 78.96351024750587,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 311.02227190505147,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 283.9188143106133,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 45.821853626819795,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 285.75436122277875,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 45.75063713968225,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 290.08071641576583,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 289.8382403548647,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 50.83068122106362,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 68.902563617216,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 311.7622741674113,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 65.44491829518402,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 3.13776717328668,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 38.330511815128034,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 367.7681678266564,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 379.5748329493872,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-develop-temurin-jdk8-target-jdk8": [
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
        "date": 1748099795754,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 48.8824636374638,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 48.16034869772035,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 48.729885081744314,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 48.15218738291466,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 62.74324230328605,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 62.63101191274282,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 62.18602750256796,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 62.34099274845078,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 56.16914699765689,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 56.31473741596013,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 56.11862257387806,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 56.150058792596354,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 35.73196380767858,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 31.415296267152872,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 6.15924218125333,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 26.135964433707702,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 3.114848423726725,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 47.52925445403891,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 73.3322056133818,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 310.64999999344377,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 282.77612236990433,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 48.69064362326014,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 283.1040259099962,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 45.80979116217244,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 289.82629292786237,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 290.36304689257395,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 50.89291157168086,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 68.8551387857812,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 312.8449472844813,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 65.72322211670316,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 3.139708378303326,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 38.47447146627265,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 336.2287261813238,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 374.5802790880726,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ]
  }
}