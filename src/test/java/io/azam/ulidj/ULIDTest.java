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

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link io.azam.ulidj.ULID}
 * 
 * @author azam
 * @since 0.0.1
 */
public class ULIDTest {
	private static final byte[] ZERO_ENTROPY = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };

	private static class TestParam {
		public final long timestamp;
		public final byte[] entropy;
		public final String value;
		public final boolean isIllegalArgument;
		public final String reproducer;

		public TestParam(long timestamp, byte[] entropy, String value, boolean isIllegalArgument) {
			this.timestamp = timestamp;
			this.entropy = entropy;
			this.value = value;
			this.isIllegalArgument = isIllegalArgument;
			StringBuilder sb = new StringBuilder();
			sb.append("ULID.generate(").append(Long.toString(timestamp)).append("L,");
			if (entropy != null) {
				sb.append("new byte[]{");
				for (int i = 0; i < entropy.length; i++) {
					sb.append("0x").append(Integer.toHexString((entropy[i] & 0xFF) + 0x100).substring(1));
					if (i + 1 < entropy.length) {
						sb.append(",");
					}
				}
				sb.append("}");
			} else {
				sb.append("null");
			}
			sb.append(")");
			this.reproducer = sb.toString();
		}
	}

	private static final TestParam[] TEST_PARAMETERS = new TestParam[] { //
			new TestParam(ULID.MIN_TIME, ZERO_ENTROPY, "00000000000000000000000000", false), //
			new TestParam(ULID.MAX_TIME, ZERO_ENTROPY, "7ZZZZZZZZZ0000000000000000", false), //
			new TestParam(0x00000001L, ZERO_ENTROPY, "00000000010000000000000000", false), //
			new TestParam(0x0000000fL, ZERO_ENTROPY, "000000000F0000000000000000", false), //
			new TestParam(0x00000010L, ZERO_ENTROPY, "000000000G0000000000000000", false), //
			new TestParam(0x00000011L, ZERO_ENTROPY, "000000000H0000000000000000", false), //
			new TestParam(0x0000001fL, ZERO_ENTROPY, "000000000Z0000000000000000", false), //
			new TestParam(0x00000020L, ZERO_ENTROPY, "00000000100000000000000000", false), //
			new TestParam(0x00000021L, ZERO_ENTROPY, "00000000110000000000000000", false), //
			new TestParam(0x0000002fL, ZERO_ENTROPY, "000000001F0000000000000000", false), //
			new TestParam(0x00000030L, ZERO_ENTROPY, "000000001G0000000000000000", false), //
			new TestParam(0x00000031L, ZERO_ENTROPY, "000000001H0000000000000000", false), //
			new TestParam(0x0000003fL, ZERO_ENTROPY, "000000001Z0000000000000000", false), //
			new TestParam(0x00000040L, ZERO_ENTROPY, "00000000200000000000000000", false), //
			new TestParam(0x000000f0L, ZERO_ENTROPY, "000000007G0000000000000000", false), //
			new TestParam(0x000000ffL, ZERO_ENTROPY, "000000007Z0000000000000000", false), //
			new TestParam(0x00000100L, ZERO_ENTROPY, "00000000800000000000000000", false), //
			new TestParam(0x00000101L, ZERO_ENTROPY, "00000000810000000000000000", false), //
			new TestParam(0x000001ffL, ZERO_ENTROPY, "00000000FZ0000000000000000", false), //
			new TestParam(0x00000200L, ZERO_ENTROPY, "00000000G00000000000000000", false), //
			new TestParam(0x00000201L, ZERO_ENTROPY, "00000000G10000000000000000", false), //
			new TestParam(0x000002ffL, ZERO_ENTROPY, "00000000QZ0000000000000000", false), //
			new TestParam(0x00000300L, ZERO_ENTROPY, "00000000R00000000000000000", false), //
			new TestParam(0x00000301L, ZERO_ENTROPY, "00000000R10000000000000000", false), //
			new TestParam(0x000003ffL, ZERO_ENTROPY, "00000000ZZ0000000000000000", false), //
			new TestParam(0x00000400L, ZERO_ENTROPY, "00000001000000000000000000", false), //
			new TestParam(0x00000401L, ZERO_ENTROPY, "00000001010000000000000000", false), //
			new TestParam(0x000007ffL, ZERO_ENTROPY, "00000001ZZ0000000000000000", false), //
			new TestParam(0x00000800L, ZERO_ENTROPY, "00000002000000000000000000", false), //
			new TestParam(0x00007fffL, ZERO_ENTROPY, "0000000ZZZ0000000000000000", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x01 },
					"00000000000000000000000001", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0f },
					"0000000000000000000000000F", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x10 },
					"0000000000000000000000000G", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1f },
					"0000000000000000000000000Z", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x20 },
					"00000000000000000000000010", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x21 },
					"00000000000000000000000011", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x2f },
					"0000000000000000000000001F", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x30 },
					"0000000000000000000000001G", false), //
			new TestParam(ULID.MIN_TIME, new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3f },
					"0000000000000000000000001Z", false), //
	};

	@Test
	public void testRandom() {
		String value = ULID.random();
		Assert.assertNotNull("Generated ULID must not be null", value);
		Assert.assertEquals("Generated ULID length must be 26", 26, value.length());
		Assert.assertTrue("Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ]",
				value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
	}

	@Test
	public void testGenerateRandom() {
		byte[] entropy = new byte[10];
		Random random = new Random();
		random.nextBytes(entropy);
		String value = ULID.generate(System.currentTimeMillis(), entropy);
		Assert.assertNotNull("Generated ULID must not be null", value);
		Assert.assertEquals("Generated ULID length must be 26, but returned " + value.length() + " instead", 26,
				value.length());
		Assert.assertTrue(
				"Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned " + value
						+ " instead",
				value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
	}

	@Test
	public void testGenerateFixedValues() {
		for (TestParam params : TEST_PARAMETERS) {
			boolean hasIllegalArgumentException = false;
			try {
				String value = ULID.generate(params.timestamp, params.entropy);
				Assert.assertEquals("Generated ULID must be equal to \"" + params.value + "\" for " + params.reproducer
						+ " , but returned \"" + value + "\" instead", params.value, value);
				Assert.assertNotNull("Generated ULID must not be null", value);
				Assert.assertEquals("Generated ULID length must be 26, but returned " + value.length() + " instead", 26,
						value.length());
				Assert.assertTrue(
						"Generated ULID characters must only include [0123456789ABCDEFGHJKMNPQRSTVWXYZ], but returned "
								+ value + " instead",
						value.matches("[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}"));
			} catch (IllegalArgumentException e) {
				hasIllegalArgumentException = true;
			}
			if (params.isIllegalArgument) {
				Assert.assertTrue("IllegalArgumentException is expected for " + params.reproducer,
						hasIllegalArgumentException);
			} else {
				Assert.assertFalse("IllegalArgumentException is not expected for " + params.reproducer,
						hasIllegalArgumentException);
			}
		}
	}

	@Test
	public void testIsValidNegative() {
		String[] invalidUlids = new String[] { //
				null, //
				"", //
				"0", //
				"000000000000000000000000000", //
				"-0000000000000000000000000", //
				"0000000000000000000000000U", //
				"0000000000000000000000000/u3042", //
				"0000000000000000000000000#", //
		};
		for (String ulid : invalidUlids) {
			Assert.assertFalse("ULID \"" + ulid + "\" should be invalid", ULID.isValid(ulid));
		}
	}

	@Test
	public void testIsValidFixedValues() {
		for (TestParam params : TEST_PARAMETERS) {
			if (!params.isIllegalArgument) {
				Assert.assertTrue("ULID string is valid", ULID.isValid(params.value));
			}
		}
	}

	@Test
	public void testGetTimestampFixedValues() {
		for (TestParam params : TEST_PARAMETERS) {
			if (!params.isIllegalArgument) {
				Assert.assertEquals("ULID timestamp is different", params.timestamp, ULID.getTimestamp(params.value));
			}
		}
	}

	@Test
	public void testGetEntropyFixedValues() {
		for (TestParam params : TEST_PARAMETERS) {
			if (!params.isIllegalArgument) {
				Assert.assertArrayEquals("ULID entropy is different", params.entropy, ULID.getEntropy(params.value));
			}
		}
	}
}
