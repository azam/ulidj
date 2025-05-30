# ulidj

[![MIT licensed](https://img.shields.io/badge/license-mit-blue.svg)](https://raw.githubusercontent.com/azam/ulidj/master/license)
[![Maven Central](https://img.shields.io/maven-central/v/io.azam.ulidj/ulidj)](https://central.sonatype.com/artifact/io.azam.ulidj/ulidj)
[![javadoc](https://javadoc.io/badge2/io.azam.ulidj/ulidj/javadoc.svg)](https://javadoc.io/doc/io.azam.ulidj/ulidj)
[![Build Status](https://github.com/azam/ulidj/actions/workflows/build.yml/badge.svg)](https://github.com/azam/ulidj/actions/workflows/build.yml)

ULID (Universally Unique Lexicographically Sortable Identifier) generator and parser for Java. Refer [ulid/spec](https://github.com/ulid/spec) for a more detailed ULID specification.

Version 2.x targets JDK 11, and peruse newer functions such as `java.time.Clock` and module declarations.

Version 1.x targets JDK 1.7, and is functionally equals to version 2.x without the extended support for `java.time.Clock` and module declarations.

## Features

* Generate, parse, convert ULID as either:
  * an immutable, sortable, valid `ULID` instance
  * a `String` in Crockford Base32 encoding as per ULID string representation 
  * a `byte[]` as per ULID binary specification
* Fast and simple static methods for non-monotonic ULID (monotonic ULID is stateful)
* Includes ULID monotonic generator
* Zero runtime dependencies
* Customizable by providing your own `java.util.Random` instance for entropy
* Full test coverage, performant, battle-tested, and production-ready

## License

```
MIT License

Copyright (c) 2016-2025 Azamshul Azizy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Maven

Add the following tag to `dependencies` tag in your `pom.xml` file. Change the version to the latest version accordingly. You can check the latest published version [here](https://central.sonatype.com/artifact/io.azam.ulidj/ulidj).

```xml
<dependency>
  <groupId>io.azam.ulidj</groupId>
  <artifactId>ulidj</artifactId>
  <version>2.0.0</version>
</dependency>
```

## Usage

ULID instance examples:

```java
 // Using default Random instance backed by SecureRandom
ULID ulid1 = ULID.randomULID();
// Using provided Random instance
ULID ulid2 = ULID.randomULID(ThreadLocalRandom.current());
// Using provided SecureRandom instance
ULID ulid3 = ULID.randomULID(SecureRandom.newInstance("SHA1PRNG"));
// Using provided Clock instance
ULID ulid4 = ULID.randomULID(Clock.systemUTC());
// Using provided Clock and Random instance
ULID ulid5 = ULID.randomULID(Clock.systemUTC(), SecureRandom.newInstance("SHA1PRNG"));
// Convert ULID string to ULID instance
ULID ulid6 = ULID.parseULID("003JZ9J6G80123456789abcdef");
// Convert ULID binary to ULID instance
ULID ulid7 = ULID.parseULID(
        new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf});
// Instantiate a ULID instance from current time and provided entropy bytes
ULID ulid8 = ULID.generateULID(System.currentTimeMillis(), entropy);
// Sort ULID instances lexicographically
List<ULID> ulids = Arrays.asList(ulid1, ulid2, ulid3, ulid4, ulid5, ulid6);
Collections.sort(ulids);
```

ULID string examples:

```java
 // Using default Random instance backed by SecureRandom
String ulid1 = ULID.random();
// Using provided Random instance
String ulid2 = ULID.random(ThreadLocalRandom.current());
// Using provided SecureRandom instance
String ulid3 = ULID.random(SecureRandom.newInstance("SHA1PRNG"));
// Using provided Clock instance
String ulid4 = ULID.random(Clock.systemUTC());
// Using provided Clock and Random instance
String ulid5 = ULID.random(Clock.systemUTC(), SecureRandom.newInstance("SHA1PRNG"));
// Generate ULID string from current time and provided entropy bytes
byte[] entropy = new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9};
String ulid6 = ULID.generate(System.currentTimeMillis(), entropy);
// Convert ULID binary to ULID string
String ulid7 = ULID.fromBinary(
        new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf});
```

ULID binary examples:

```java
// Using default Random instance backed by SecureRandom
byte[] ulid1 = ULID.randomBinary();
// Using provided Random instance
byte[] ulid2 = ULID.randomBinary(ThreadLocalRandom.current());
// Using provided SecureRandom instance
byte[] ulid3 = ULID.randomBinary(SecureRandom.newInstance("SHA1PRNG"));
// Using provided Clock instance
byte[] ulid4 = ULID.randomBinary(Clock.systemUTC());
// Using provided Clock and Random instance
byte[] ulid5 = ULID.randomBinary(Clock.systemUTC(), SecureRandom.newInstance("SHA1PRNG"));
// Generate ULID string from current time and provided entropy bytes
byte[] entropy = new byte[] {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9};
byte[] ulid6 = ULID.generateBinary(System.currentTimeMillis(), entropy);
// Convert ULID string to ULID binary
byte[] ulid7 = ULID.toBinary("003JZ9J6G80123456789abcdef");
```

ULID utilities examples:

```java
String ulid = "003JZ9J6G80123456789abcdef";
// Validate ULID string
assert ULID.isValid(ulid);
// Get timestamp from ULID string
long ts = ULID.getTimestamp(ulid);
assert ts == 123456789000L;
// Get entropy from ULID string
byte[] entropy = ULID.getEntropy(ulid);

byte[] ulidBinary =
        new byte[] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0xf, 0xf, 0xf, 0xf, 0xf, 0xf};
// Validate ULID binary
assert ULID.isValidBinary(ulidBinary);
// Get timestamp from ULID binary
long tsBinary = ULID.getTimestampBinary(ulidBinary);
assert tsBinary == 1L;
// Get entropy from ULID binary
byte[] entropyBinary = ULID.getEntropyBinary(ulidBinary);
```

Monotonic ULID examples:

```java
 // Using default MonotonicULID static instance backed by SecureRandom
String ulid1 = MonotonicULID.random();
// Subsequent calls generates monotonic ULID
String ulid2 = MonotonicULID.random();
byte[] ulid3 = MonotonicULID.randomBinary();
ULID ulid4 = MonotonicULID.randomULID();

// Create a new independent instance of MonotonicULID using default Random
// instance backed by SecureRandom
MonotonicULID monotonicUlid = new MonotonicULID();
String ulid5 = monotonicUlid.generate();
// Subsequent calls generates monotonic ULID
String ulid6 = monotonicUlid.generate();
byte[] ulid7 = monotonicUlid.generateBinary();
ULID ulid8 = monotonicUlid.generateULID();

// Create a new independent instance of MonotonicULID using provided Random instance
MonotonicULID monotonicUlid2 = new MonotonicULID(SecureRandom.newInstance("SHA1PRNG"));
String ulid9 = monotonicUlid2.generate();
// Subsequent calls generates monotonic ULID
String ulid10 = monotonicUlid2.generate();
byte[] ulid11 = monotonicUlid2.generateBinary();
ULID ulid12 = monotonicUlid2.generateULID();

// Create a new independent instance of MonotonicULID using provided Clock instance
MonotonicULID monotonicUlid3 = new MonotonicULID(Clock.systemUTC());
String ulid13 = monotonicUlid3.generate();
// Subsequent calls generates monotonic ULID
String ulid14 = monotonicUlid3.generate();
byte[] ulid15 = monotonicUlid3.generateBinary();
ULID ulid16 = monotonicUlid3.generateULID();

// Create a new independent instance of MonotonicULID using provided Clock and Random instance
MonotonicULID monotonicUlid4 =
        new MonotonicULID(Clock.systemUTC(), SecureRandom.newInstance("SHA1PRNG"));
String ulid17 = monotonicUlid4.generate();
// Subsequent calls generates monotonic ULID
String ulid18 = monotonicUlid4.generate();
byte[] ulid19 = monotonicUlid4.generateBinary();
ULID ulid20 = monotonicUlid4.generateULID();
```

## Develop

Please run the following before sending a PR:

* Make sure tests are passing and source is properly formatted by running ```mvn verify```
* You can format sources to match style with ```mvn formatter:format xml-format:xml-format```

## Prior Art

- [Lewiscowles1986/jULID](https://github.com/Lewiscowles1986/jULID)
- [ulid/spec](https://github.com/ulid/spec)
