window.BENCHMARK_DATA = {
  "lastUpdate": 1748339587114,
  "repoUrl": "https://github.com/azam/ulidj",
  "entries": {
    "branch-main-temurin-jdk8-target-jdk8": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748122288898,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 48.85921159431698,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 48.24087055793085,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 48.16526885701281,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 48.12599530087429,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 61.85238850916541,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 65.94286500781034,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 62.25644511687228,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 62.19043740199527,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 56.143017453432144,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 56.07166508259284,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 56.16501250793199,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 56.03233255748875,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 62.06481904638365,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 48.4470653487059,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 55.686653190051025,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 35.67488503331655,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 31.487669658570315,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 6.134644282314645,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 26.1400108265514,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 3.1117319047961027,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 47.601143119079246,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 74.90767143412648,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 7.088092640170883,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 44.06868352600293,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 310.54648243586655,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 283.29011077675096,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 45.72177891867875,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 283.3788934327196,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 45.771312808299186,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 289.5641173146833,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 289.0386889533216,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 50.73005843244377,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 69.98615048759248,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 311.1946101732892,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 65.50899636192496,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 3.137721518444292,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 38.296066860019884,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 354.8287180709715,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 374.53075384861603,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-main-temurin-jdk8-target-jdk7": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748122289895,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 48.35734356363774,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 47.941891519568486,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 49.01443369010454,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 48.20872070098652,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 62.23670070858972,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 62.086941818665494,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 62.40134567537198,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 62.19794334376649,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 56.1904574291324,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 55.987795478700356,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 55.983882723531195,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 56.15154204545063,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 62.00369664822521,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 47.95213478968268,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 55.71338993605126,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 36.32418336397503,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 31.422278078913084,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 6.133196100609565,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 26.143768943922858,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 3.11318442125659,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 47.56707097704518,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 73.45443145628441,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 7.105301223066837,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 44.20036130490362,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 311.4841330183241,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 285.369510485029,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 45.69282928765004,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 281.8823456319062,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 45.72047741192851,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 290.10042558166236,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 289.91724589888383,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 50.778450369575225,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 68.78672662806709,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 313.0231733036483,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 65.36343889325744,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 3.1378612142764597,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 38.26413964597843,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 341.57534637816605,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 374.8744646037066,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748339585521,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 49.139425004234774,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 48.26645545096481,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 48.7102751805456,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 48.27415774796887,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 62.18372474841228,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 61.74474305548899,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 62.18663807208947,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 61.62277967503819,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 56.279918851796424,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 56.161742009588714,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 56.16400204595516,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 56.17819933412033,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 61.327392653686545,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 50.269134691307634,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 55.685582247773134,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 35.932289164446956,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 31.561787495228916,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 6.1532735332271695,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 26.133534233517135,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 3.110500922515267,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 47.6403279398483,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 75.33898655902824,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 7.11806461112533,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 44.155963108389564,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 310.5677982278439,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 282.345104982135,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 45.738989447021716,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 282.1205006017164,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 45.73756405288703,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 289.90556720006947,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 288.9973559554109,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 50.86011001706876,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 69.06442371353769,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 314.7432991648796,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 66.61386278082811,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 3.1431650063285437,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 38.35663039830143,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 331.6459353008657,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 375.1752128402412,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-main-temurin-jdk21-target-jdk17": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748122290481,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.18527030235403,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.06748550900338,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.13815618744505,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.09221308732574,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 60.645119169204385,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.33230003234315,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.778546938584974,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.765319452877996,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.37835088770405,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.488884476860754,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.35579195849461,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.37280002948342,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 65.71878951910814,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 45.792480630947246,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 53.63197421790921,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 29.371126054288446,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.87037661859421,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.126502984130999,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.4441275591869,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5554818287533947,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.56917511656797,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 75.93976869341472,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 5.8747012015869835,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 36.051525856074214,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 204.97759993131496,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 183.03274045018352,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 46.949584029411746,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 185.223199877905,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.278342384217574,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 186.07893841629175,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 188.94162357021398,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.5220241932303,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 66.13624812339441,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 203.63422080986376,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 62.548278677961534,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6225380742193454,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.57300434340556,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.424566656635875,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 211.71538474226406,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-main-temurin-jdk21-target-jdk8": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748122294829,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.183733664248,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.079680636907874,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.15880909437558,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.176366326109395,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 60.26185889859383,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.21349022251754,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.89984838471634,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.13189037569782,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.33405216952916,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.45572032648275,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.395931079714714,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.49480157812106,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 60.959313974070284,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 45.129325865026466,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 53.77773280922547,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 29.441867667174343,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.632158169935543,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.184192675893636,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.442295118187193,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5552574505515184,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.58058726304203,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 78.80102388043359,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 5.88286380980166,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 35.48617202336586,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 203.52635315530912,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 188.34823423658278,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.668425851568216,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 185.14039938624822,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.346464875681455,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 186.59722414706422,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 187.87419418330546,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.50558160316892,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 66.27150602014999,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 204.35380450543988,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 63.44324339768472,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.622689265178231,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.961354419044884,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.442562374620459,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 212.96078864519828,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-main-temurin-jdk21-target-jdk11": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748122296291,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.21135928096505,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.14209485580466,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.19599375277809,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.13196097338734,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 61.506105339443096,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.32997451138326,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.45790454060913,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.39839646601435,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.457442192125065,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 57.26669707602134,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.46757614224896,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.54902984133825,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 61.16746353519938,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 45.20721025380127,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 53.784030150580705,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 29.39472211806882,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.88191703699035,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.193111779905014,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.457650756001293,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5556197418866282,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.64130337959364,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 76.53862906492769,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 5.889687861252295,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 35.62149760216435,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 205.54421390459348,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 184.46649971275556,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.73886559693842,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 182.1728537591576,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.42612303907806,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 185.55162126699486,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 187.49670452342895,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.55212818387515,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 66.52466975413964,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 209.45772422338274,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 62.50770569944867,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6237074202578764,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.507098032630996,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.431979550579951,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 213.2233160919731,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748339584919,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.18642706015632,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.099565889830046,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.161063142110905,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.08627703567893,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 61.11201369821688,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.70200236095359,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.56212325412535,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 60.5853865398577,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.2746431044272,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.507111933809114,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 52.61429635431135,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 51.640358204983606,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 58.94619398021122,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 43.569734302737196,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 52.0053723682881,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 28.11972080529326,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 22.92330299934191,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 4.9817869519092275,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.335224389483697,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.549648976501643,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 38.33941492818046,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 74.70981106271992,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 5.84071152162441,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 35.54630529193256,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 197.8008029065382,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 179.25779103774647,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 43.18456362400082,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 179.5384886456753,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 39.819963987009956,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 180.3885499158336,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 180.96691984752198,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 49.42696065098407,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 62.990457982822996,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 200.5558690322205,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 60.778372657375584,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6017865219360272,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 28.60056812823785,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.195274573715914,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 206.1930051990663,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ],
    "branch-main-temurin-jdk21-target-jdk21": [
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748122297505,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.190864478912644,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.1030108462777,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.197162775064314,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.08744757750147,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 59.34776188377393,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.25531321174332,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 59.280101728244816,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 59.44255326266697,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.487954599826,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.43094961567143,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.38262969568806,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.48015918189633,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 60.89159595081962,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 45.12750981024249,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 53.611115574249226,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 30.125443108155256,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 23.829624318295206,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.182067588282729,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.469207073541353,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5580736942428355,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.54001879328265,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 75.48622996125651,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 5.861331151107589,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 35.59529369796521,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 205.06194347027494,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 184.43443845811788,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.70507269698448,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 182.86991509803184,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.309464183137784,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 184.907833715023,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 188.03814487540095,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.539755542822135,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 66.49777329052827,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 207.17094682621254,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 62.30940238028988,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6238096842951788,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.601788549000567,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.440672197997508,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 215.30391097851324,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      },
      {
        "commit": {
          "author": {
            "name": "azam",
            "username": "azam",
            "email": "azam@users.noreply.github.com"
          },
          "committer": {
            "name": "GitHub",
            "username": "web-flow",
            "email": "noreply@github.com"
          },
          "id": "345f9196fad777052978fc3857695c67f65520df",
          "message": "Remove dependabot (#23)\n\n* Bump version\n\n* Update GitHub Actions (#13)\n\n* Update Github Actions\r\n\r\n* Change compiler source and target on JDK higher than 17 for tests\r\n\r\n* Delegate source parameter for javadoc to maven.compiler.source\r\n\r\n* Add tests as JDK 11, 17 and 21\n\n* Update Maven plugins. Update junit version. Fix wrong <scm> values.\n\n* Add devcontainer\n\n* Update Github Actions\n\n* Remove jdk25-ea\n\n* Fix javadoc warnings\n\n* Revert \"Fix javadoc warnings\"\n\nThis reverts commit b20a5d9460e2e362a1644e9d966d078e04698631.\n\n* Add jmh benchmark\n\n* Set release version\n\n* Fix echo on bench.yml\n\n* Fix multiline commands on bench.yml\n\n* Reduce warmup time\n\n* Escape backticks on bench.yml\n\n* Bump version\n\n* Feature: Implement immutable sortable ULID instance.\nBreaking change: Random default instance use a lazily instantiated SecureRandom.\nBreaking change: generate, generateBinary, toBinary, fromBinary now checks for ULID validity.\nFix: Prevent monotonic ULID increment entropy on invalid timestamp.\nFix: First 8 bit of timestamp may be converted wrongly.\nFeature: Add JaCoCo report.\nFeature: More tests and benchmarks.\n\n* Fix: Fix surefire-report\n\n* Fix: Remove test that changes private constructor to public.\n\n* Fix: Wrong job name for site.yml\n\n* Fix: Remove mocks (JDK incompatibility, offline JDK instrumentation is brittle)\nFix: Remove private constructor for ULID.\nFix: Update Javadoc plugin.\nFeature: Add source cross reference plugin.\n\n* Update license\n\n* Feature: Add Monotonic.random static methods\nFix: Documentation\n\n* Update Github actions\n\n* Add parseULID benchmark\n\n* Update devcontainer.json\n\n* Set release version\n\n* Remove dependabot.yml",
          "timestamp": "2025-05-24T19:41:56Z",
          "url": "https://github.com/azam/ulidj/commit/345f9196fad777052978fc3857695c67f65520df"
        },
        "date": 1748339582254,
        "tool": "jmh",
        "benches": [
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryNoArgs",
            "value": 45.274305149821735,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryRandom",
            "value": 45.11971162203417,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinarySecureRandom",
            "value": 45.24412201308595,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateBinaryThreadLocalRandom",
            "value": 45.167202772329055,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateNoArgs",
            "value": 62.553065434673535,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateRandom",
            "value": 59.32869928495823,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateSecureRandom",
            "value": 60.334331270633655,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateThreadLocalRandom",
            "value": 60.83322341611538,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDNoArgs",
            "value": 53.46959893176889,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDRandom",
            "value": 53.54484074977714,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDSecureRandom",
            "value": 53.403229529050385,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.generateULIDThreadLocalRandom",
            "value": 53.52464115635985,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.random",
            "value": 60.64860371945451,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomBinary",
            "value": 45.20979859062125,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.MonotonicULIDBenchmark.randomULID",
            "value": 53.7354871251687,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.fromBinary",
            "value": 29.404366198114097,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropy",
            "value": 24.653758929134007,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getEntropyBinary",
            "value": 5.1806012954468645,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestamp",
            "value": 11.475716745238403,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.getTimestampBinary",
            "value": 1.5563244770285454,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newRandom",
            "value": 35.67720353181963,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.newSecureRandom",
            "value": 77.96452742515302,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDBinary",
            "value": 5.884686455239121,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.parseULIDString",
            "value": 35.66614931232771,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.random",
            "value": 204.10173598682752,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinary",
            "value": 185.36551655998136,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithRandom",
            "value": 44.62776595407506,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithSecureRandom",
            "value": 184.52387970051234,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomBinaryWithThreadLocalRandom",
            "value": 41.40560642374674,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULID",
            "value": 186.4864331267625,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithSecureRandom",
            "value": 188.97810273723795,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomULIDWithThreadLocalRandom",
            "value": 51.63232727414883,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithRandom",
            "value": 67.08866765528987,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithSecureRandom",
            "value": 204.10733239046237,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.randomWithThreadLocalRandom",
            "value": 62.387042530987806,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.threadLocalRandomCurrent",
            "value": 0.6224901905775524,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.ULIDBenchmark.toBinary",
            "value": 29.88968962617278,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.fromString",
            "value": 7.423210144609848,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          },
          {
            "name": "io.azam.ulidj.UUIDBenchmark.randomUUID",
            "value": 213.283418437666,
            "unit": "ns/op",
            "extra": "iterations: 5\nforks: 3\nthreads: 1"
          }
        ]
      }
    ]
  }
}