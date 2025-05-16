/**
 * MIT License
 *
 * Copyright (c) 2016-2025 Azamshul Azizy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.azam.ulidj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUtils {
  @Test
  public void testIncrementBytes() {
    Assertions.assertArrayEquals(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01 //
    }, incrementBytes(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 //
    }));
    Assertions.assertArrayEquals(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff //
    }, incrementBytes(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfe //
    }));
    Assertions.assertArrayEquals(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00 //
    }, incrementBytes(new byte[] { //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff //
    }));
    Assertions.assertArrayEquals(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }, incrementBytes(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe //
    }));
    Assertions.assertArrayEquals(null, incrementBytes(new byte[] { //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
    }));
  }

  public static byte[] incrementBytes(byte[] bytes) {
    if (bytes == null || bytes.length != ULID.ENTROPY_LENGTH)
      return null;
    byte[] value = new byte[ULID.ENTROPY_LENGTH];

    boolean carry = true;
    for (int i = ULID.ENTROPY_LENGTH - 1; i >= 0; i--) {
      byte work = bytes[i];
      if (carry) {
        work = (byte) (work + 0x01);
        carry = bytes[i] == (byte) 0xff && carry;
      }
      value[i] = work;
    }
    // Last byte has carry over
    if (carry) {
      return null;
    }

    return value;
  }

  public static String bytesToInitializer(byte[] bytes) {
    if (bytes == null)
      return "null";
    StringBuilder sb = new StringBuilder();
    sb.append("new byte[] {");
    for (int i = 0; i < bytes.length; i++) {
      sb.append(String.format("(byte) 0x%02x", bytes[i]));
      if (i < bytes.length - 1)
        sb.append(",");
    }
    sb.append(" }");
    return sb.toString();
  }
}
