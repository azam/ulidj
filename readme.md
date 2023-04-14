# ulidj

[![MIT licensed](https://img.shields.io/badge/license-mit-blue.svg)](https://raw.githubusercontent.com/azam/ulidj/master/license)
[![Maven Central](https://img.shields.io/maven-central/v/io.azam.ulidj/ulidj)](https://central.sonatype.com/artifact/io.azam.ulidj/ulidj)
[![Build Status](https://github.com/azam/ulidj/actions/workflows/build.yml/badge.svg)](https://github.com/azam/ulidj/actions/workflows/build.yml)

ULID (Universally Unique Lexicographically Sortable Identifier) generator and parser for Java. Refer [ulid/spec](https://github.com/ulid/spec) for a more detailed ULID specification.

## Features

* Generates ULID to `String` (Crockford's base32) or `byte[]` (128-bit UUID compatible) objects
* Parses ULID from `String` (Crockford's base32) or `byte[]` (128-bit UUID compatible) objects
* Fast and simple static methods
* Includes ULID monotonic generator
* Zero runtime dependencies

## License

```
MIT License

Copyright (c) 2016 Azamshul Azizy

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
  <version>1.0.4</version>
</dependency>
```

## Usage

ULID generation examples:

```java
String ulid1 = ULID.random();
String ulid2 = ULID.random(ThreadLocalRandom.current());
String ulid3 = ULID.random(SecureRandom.newInstance("SHA1PRNG"));
byte[] entropy = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9 };
String ulid4 = ULID.generate(System.currentTimeMillis(), entropy); // Generate ULID in string representation
byte[] ulid5 = ULID.generateBinary(System.currentTimeMillis(), entropy); // Generate ULID in binary representation
```

ULID parsing examples:

```java
// ULID string parsing
String ulid = "003JZ9J6G80123456789abcdef";
assert ULID.isValid(ulid);
long ts = ULID.getTimestamp(ulid);
assert ts == 123456789000L;
byte[] entropy = ULID.getEntropy(ulid);

// ULID binary parsing
byte[] ulidBinary =  new byte[] { //
    // Timestamp part
    (byte) 0x01, (byte) 0x33, (byte) 0x7C, (byte) 0x0D, (byte) 0xEF, (byte) 0x00, //
    // Entropy part
    (byte) 0x10, (byte) 0x20, (byte) 0x30, (byte) 0x40, (byte) 0x50, (byte) 0x60, (byte) 0x70, (byte) 0x80, (byte) 0x90, (byte) 0x10 //
};
assert ULID.isValidBinary(ulidBinary);
long ts = ULID.getTimestampBinary(ulidBinary);
assert ts == 1320636247808L;
byte[] entropy = ULID.getEntropyBinary(ulidBinary);
```

Monotonic ULID generation example:

```java
MonotonicULID ulid = new MonotonicULID();
String ulidString = ulid.generate(); // Generate ULID in string representation
byte[] ulidBinary = ulid.generateBinary(); // Generate ULID in binary representation
```

## Develop

Please run the following before sending a PR:

* Make sure tests are passing and source is properly formatted by running ```mvn verify```
* You can format sources to match style with ```mvn formatter:format xml-format:xml-format```

## Prior Art

- [Lewiscowles1986/jULID](https://github.com/Lewiscowles1986/jULID)
- [ulid/spec](https://github.com/ulid/spec)
