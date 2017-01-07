/**
 * MIT License
 * 
 * Copyright (c) 2016 Azamshul Azizy
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.azam.ulidj;

import java.util.Random;

/**
 * ULID string generator and parser class, using Crockford Base32 encoding. Only
 * upper case letters are used for generation. Parsing allows upper and lower
 * case letters, and i and l will be treated as 1 and o will be treated as 0.
 * <br>
 * <br>
 * ULID generation examples:<br>
 * 
 * <pre>
 * String ulid1 = ULID.random();
 * String ulid2 = ULID.random(ThreadLocalRandom.current());
 * String ulid3 = ULID.random(SecureRandom.newInstance("SHA1PRNG"));
 * byte[] entropy = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9 };
 * String ulid4 = ULID.generate(System.currentTimeMillis(), entropy);
 * </pre>
 * 
 * ULID parsing examples:<br>
 * 
 * <pre>
 * String ulid = "003JZ9J6G80123456789abcdef";
 * assert ULID.isValid(ulid);
 * long ts = ULID.getTimestamp(ulid);
 * assert ts == 123456789000L;
 * byte[] entropy = ULID.getEntropy(ulid);
 * </pre>
 * 
 * @author azam
 * @since 0.0.1
 * 
 * @see <a href="http://www.crockford.com/wrmg/base32.html">Base32 Encoding</a>
 * @see <a href="https://github.com/alizain/ulid">ULID</a>
 */
public class ULID {
	/**
	 * ULID string length.
	 */
	public static final int ULID_LENGTH = 26;

	/**
	 * Minimum allowed timestamp value.
	 */
	public static final long MIN_TIME = 0x0L;

	/**
	 * Maximum allowed timestamp value.
	 */
	public static final long MAX_TIME = 0x0000ffffffffffffL;

	/**
	 * Base32 characters mapping
	 */
	private static final char[] C = new char[] { //
			0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, //
			0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, //
			0x47, 0x48, 0x4a, 0x4b, 0x4d, 0x4e, 0x50, 0x51, //
			0x52, 0x53, 0x54, 0x56, 0x57, 0x58, 0x59, 0x5a };

	/**
	 * {@code char} to {@code byte} O(1) mapping with alternative chars mapping
	 */
	private static final byte[] V = new byte[] { //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, //
			(byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, //
			(byte) 0x08, (byte) 0x09, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, //
			(byte) 0x0d, (byte) 0x0e, (byte) 0x0f, (byte) 0x10, //
			(byte) 0x11, (byte) 0xff, (byte) 0x12, (byte) 0x13, //
			(byte) 0xff, (byte) 0x14, (byte) 0x15, (byte) 0xff, //
			(byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, //
			(byte) 0x1a, (byte) 0xff, (byte) 0x1b, (byte) 0x1c, //
			(byte) 0x1d, (byte) 0x1e, (byte) 0x1f, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0x0a, (byte) 0x0b, (byte) 0x0c, //
			(byte) 0x0d, (byte) 0x0e, (byte) 0x0f, (byte) 0x10, //
			(byte) 0x11, (byte) 0xff, (byte) 0x12, (byte) 0x13, //
			(byte) 0xff, (byte) 0x14, (byte) 0x15, (byte) 0xff, //
			(byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, //
			(byte) 0x1a, (byte) 0xff, (byte) 0x1b, (byte) 0x1c, //
			(byte) 0x1d, (byte) 0x1e, (byte) 0x1f, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, //
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff //
	};

	/**
	 * Generate random ULID string using {@link java.util.Random} instance.
	 * 
	 * @return ULID string
	 */
	public static String random() {
		byte[] entropy = new byte[10];
		Random random = new Random();
		random.nextBytes(entropy);
		return generate(System.currentTimeMillis(), entropy);
	}

	/**
	 * Generate random ULID string using provided {@link java.util.Random}
	 * instance.
	 * 
	 * @param random
	 *            {@link java.util.Random} instance
	 * @return ULID string
	 */
	public static String random(Random random) {
		byte[] entropy = new byte[10];
		random.nextBytes(entropy);
		return generate(System.currentTimeMillis(), entropy);
	}

	/**
	 * Generate ULID from Unix epoch timestamp in millisecond and entropy bytes.
	 * Throws {@link java.lang.IllegalArgumentException} if timestamp is less
	 * than {@value #MIN_TIME}, is more than {@value #MAX_TIME}, or entropy
	 * bytes is null or less than 10 bytes.
	 * 
	 * @param time
	 *            Unix epoch timestamp in millisecond
	 * @param entropy
	 *            Entropy bytes
	 * @return ULID string
	 */
	public static String generate(long time, byte[] entropy) {
		if (time < MIN_TIME || time > MAX_TIME || entropy == null || entropy.length < 10) {
			throw new IllegalArgumentException("Time is too long, or entropy is less than 10 bytes or null");
		}

		char[] chars = new char[26];

		// time
		chars[0] = C[((byte) (time >>> 45)) & 0x1f];
		chars[1] = C[((byte) (time >>> 40)) & 0x1f];
		chars[2] = C[((byte) (time >>> 35)) & 0x1f];
		chars[3] = C[((byte) (time >>> 30)) & 0x1f];
		chars[4] = C[((byte) (time >>> 25)) & 0x1f];
		chars[5] = C[((byte) (time >>> 20)) & 0x1f];
		chars[6] = C[((byte) (time >>> 15)) & 0x1f];
		chars[7] = C[((byte) (time >>> 10)) & 0x1f];
		chars[8] = C[((byte) (time >>> 5)) & 0x1f];
		chars[9] = C[((byte) (time)) & 0x1f];

		// entropy
		chars[10] = C[(byte) ((entropy[0] & 0xff) >>> 3)];
		chars[11] = C[(byte) (((entropy[0] << 2) | ((entropy[1] & 0xff) >>> 6)) & 0x1f)];
		chars[12] = C[(byte) (((entropy[1] & 0xff) >>> 1) & 0x1f)];
		chars[13] = C[(byte) (((entropy[1] << 4) | ((entropy[2] & 0xff) >>> 4)) & 0x1f)];
		chars[14] = C[(byte) (((entropy[2] << 5) | ((entropy[3] & 0xff) >>> 7)) & 0x1f)];
		chars[15] = C[(byte) (((entropy[3] & 0xff) >>> 2) & 0x1f)];
		chars[16] = C[(byte) (((entropy[3] << 3) | ((entropy[4] & 0xff) >>> 5)) & 0x1f)];
		chars[17] = C[(byte) (entropy[4] & 0x1f)];
		chars[18] = C[(byte) ((entropy[5] & 0xff) >>> 3)];
		chars[19] = C[(byte) (((entropy[5] << 2) | ((entropy[6] & 0xff) >>> 6)) & 0x1f)];
		chars[20] = C[(byte) (((entropy[6] & 0xff) >>> 1) & 0x1f)];
		chars[21] = C[(byte) (((entropy[6] << 4) | ((entropy[7] & 0xff) >>> 4)) & 0x1f)];
		chars[22] = C[(byte) (((entropy[7] << 5) | ((entropy[8] & 0xff) >>> 7)) & 0x1f)];
		chars[23] = C[(byte) (((entropy[8] & 0xff) >>> 2) & 0x1f)];
		chars[24] = C[(byte) (((entropy[8] << 3) | ((entropy[9] & 0xff) >>> 5)) & 0x1f)];
		chars[25] = C[(byte) (entropy[9] & 0x1f)];

		return new String(chars);
	}

	/**
	 * Checks ULID string validity.
	 * 
	 * @param ulid
	 *            ULID string
	 * @return true if ULID string is valid
	 */
	public static boolean isValid(CharSequence ulid) {
		if (ulid == null || ulid.length() != ULID_LENGTH) {
			return false;
		}
		for (int i = 0; i < ULID_LENGTH; i++) {
			/** We only care for chars between 0x00 and 0xff. */
			char c = ulid.charAt(i);
			if (c < 0 || c > V.length || V[c] == (byte) 0xff) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Extract and return the timestamp part from ULID. Expects a valid ULID
	 * string. Call {@link io.azam.ulidj.ULID#isValid(CharSequence)} and check
	 * validity before calling this method if you do not trust the origin of the
	 * ULID string.
	 * 
	 * @param ulid
	 *            ULID string
	 * @return Unix epoch timestamp in millisecond
	 */
	public static long getTimestamp(CharSequence ulid) {
		return (long) V[ulid.charAt(0)] << 45 //
				| (long) V[ulid.charAt(1)] << 40 //
				| (long) V[ulid.charAt(2)] << 35 //
				| (long) V[ulid.charAt(3)] << 30 //
				| (long) V[ulid.charAt(4)] << 25 //
				| (long) V[ulid.charAt(5)] << 20 //
				| (long) V[ulid.charAt(6)] << 15 //
				| (long) V[ulid.charAt(7)] << 10 //
				| (long) V[ulid.charAt(8)] << 5 //
				| (long) V[ulid.charAt(9)];
	}

	/**
	 * Extract and return the entropy part from ULID. Expects a valid ULID
	 * string. Call {@link io.azam.ulidj.ULID#isValid(CharSequence)} and check
	 * validity before calling this method if you do not trust the origin of the
	 * ULID string.
	 * 
	 * @param ulid
	 *            ULID string
	 * @return Entropy bytes
	 */
	public static byte[] getEntropy(CharSequence ulid) {
		byte[] bytes = new byte[10];
		bytes[0] = (byte) ((V[ulid.charAt(10)] << 3) //
				| (V[ulid.charAt(11)] & 0xff) >>> 2);
		bytes[1] = (byte) ((V[ulid.charAt(11)] << 6) //
				| V[ulid.charAt(12)] << 1 //
				| (V[ulid.charAt(13)] & 0xff) >>> 4);
		bytes[2] = (byte) ((V[ulid.charAt(13)] << 4) //
				| (V[ulid.charAt(14)] & 0xff) >>> 1);
		bytes[3] = (byte) ((V[ulid.charAt(14)] << 7) //
				| V[ulid.charAt(15)] << 2 //
				| (V[ulid.charAt(16)] & 0xff) >>> 3);
		bytes[4] = (byte) ((V[ulid.charAt(16)] << 5) //
				| V[ulid.charAt(17)]);
		bytes[5] = (byte) ((V[ulid.charAt(18)] << 3) //
				| (V[ulid.charAt(19)] & 0xff) >>> 2);
		bytes[6] = (byte) ((V[ulid.charAt(19)] << 6) //
				| V[ulid.charAt(20)] << 1 //
				| (V[ulid.charAt(21)] & 0xff) >>> 4);
		bytes[7] = (byte) ((V[ulid.charAt(21)] << 4) //
				| (V[ulid.charAt(22)] & 0xff) >>> 1);
		bytes[8] = (byte) ((V[ulid.charAt(22)] << 7) //
				| V[ulid.charAt(23)] << 2 //
				| (V[ulid.charAt(24)] & 0xff) >>> 3);
		bytes[9] = (byte) ((V[ulid.charAt(24)] << 5) //
				| V[ulid.charAt(25)]);
		return bytes;
	}
}
