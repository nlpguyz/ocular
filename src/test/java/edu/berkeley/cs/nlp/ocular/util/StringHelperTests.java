package edu.berkeley.cs.nlp.ocular.util;

import static edu.berkeley.cs.nlp.ocular.util.StringHelper.join;
import static edu.berkeley.cs.nlp.ocular.util.StringHelper.last;
import static edu.berkeley.cs.nlp.ocular.util.StringHelper.longestCommonPrefix;
import static edu.berkeley.cs.nlp.ocular.util.StringHelper.take;
import static edu.berkeley.cs.nlp.ocular.util.StringHelper.toUnicode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import scala.actors.threadpool.Arrays;

/**
 * @author Dan Garrette (dhgarrette@gmail.com)
 */
public class StringHelperTests {

	@Test
	public void testToUnicode_string() {
		assertEquals("\\u0061", toUnicode("a"));
	}

	@Test
	public void testToUnicode_char() {
		assertEquals("\\u0061", toUnicode('a'));
	}

	@Test
	public void testTake() {
		assertEquals("", take("", 0));
		assertEquals("", take("", -2));
		assertEquals("", take("", 2));
		assertEquals("", take("abc", 0));
		assertEquals("", take("abc", -2));
		assertEquals("a", take("a", 1));
		assertEquals("a", take("a", 2));
		assertEquals("ab", take("abc", 2));
	}

	@Test
	public void testLast() {
		assertEquals("a", last("a"));
		assertEquals("c", last("abc"));
		try {
			assertEquals("a", last(""));
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testJoin_varargs() {
		assertEquals("abc", join("a", "", "b", "c"));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testJoin_list() {
		assertEquals("abc", join(Arrays.asList(new String[] { "a", "", "b", "c" })));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testJoin_list_sep() {
		assertEquals("a;;b;c", join(Arrays.asList(new String[] { "a", "", "b", "c" }), ";"));
	}

	@Test
	public void testEquals() {
		assertTrue(StringHelper.equals("", ""));
		assertFalse(StringHelper.equals("a", ""));
		assertFalse(StringHelper.equals("", "a"));
		assertFalse(StringHelper.equals(null, ""));
		assertFalse(StringHelper.equals("", null));
		assertFalse(StringHelper.equals(null, "a"));
		assertFalse(StringHelper.equals("a", null));
		assertTrue(StringHelper.equals(null, null));
	}

	@Test
	public void testLongestCommonPrefix() {
		assertEquals("".length(), longestCommonPrefix("", ""));
		assertEquals("".length(), longestCommonPrefix("abc", ""));
		assertEquals("".length(), longestCommonPrefix("", "abc"));
		assertEquals("ab".length(), longestCommonPrefix("abc", "ab"));
		assertEquals("ab".length(), longestCommonPrefix("ab", "abc"));
		assertEquals("abc".length(), longestCommonPrefix("abc", "abc"));
	}

}
