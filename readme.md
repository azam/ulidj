# ulidj

[![MIT licensed](https://img.shields.io/badge/license-mit-blue.svg)](https://raw.githubusercontent.com/azam/ulidj/master/license)
[![Maven Central](https://img.shields.io/maven-central/v/io.azam.ulidj/ulidj)](https://search.maven.org/artifact/io.azam.ulidj/ulidj)
[![Travis CI](https://api.travis-ci.org/azam/ulidj.svg?branch=master)](https://travis-ci.org/azam/ulidj)

ULID (Universally Unique Lexicographically Sortable Identifier) generator and parser for Java.

Refer [alizain/ulid](https://github.com/alizain/ulid) for a more detailed ULID specification.

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

Add the following tag to `dependencies` tag in your `pom.xml` file.

```xml
<dependency>
  <groupId>io.azam.ulidj</groupId>
  <artifactId>ulidj</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage

ULID generation examples:

```java
String ulid1 = ULID.random();
String ulid2 = ULID.random(ThreadLocalRandom.current());
String ulid3 = ULID.random(SecureRandom.newInstance("SHA1PRNG"));
byte[] entropy = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9 };
String ulid4 = ULID.generate(System.currentTimeMillis(), entropy);
```

ULID parsing examples:

```java
String ulid = "003JZ9J6G80123456789abcdef";
assert ULID.isValid(ulid);
long ts = ULID.getTimestamp(ulid);
assert ts == 123456789000L;
byte[] entropy = ULID.getEntropy(ulid);
```

## Prior Art

- [Lewiscowles1986/jULID](https://github.com/Lewiscowles1986/jULID)
- [alizain/ulid](https://github.com/alizain/ulid)
