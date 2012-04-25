package org.gtug.lodz.guava.strings;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.google.common.base.CharMatcher;

public class CharMatcherTest {
	@Test
	public void testRetain() {
		// given
		String phone = "517-725-068";
		// when
		phone = CharMatcher.DIGIT.retainFrom(phone);
		// then
		assertThat(phone).isEqualTo("517725068");
	}

	@Test
	public void testOrRemove() {
		// given
		String label = "Marek Defeciński 32";
		// when
		label = CharMatcher.DIGIT.or(CharMatcher.WHITESPACE).removeFrom(label);
		// then
		assertThat(label).isEqualTo("MarekDefeciński");
	}

	@Test
	public void testRangeNegatedMatcher() {
		// given
		String label = "Marek Defeciński 32";
		// when
		label = CharMatcher.inRange('A', 'Z').negate().removeFrom(label);
		// then
		assertThat(label).isEqualTo("MD");
	}

	@Test
	public void testAnyOfMatcher() {
		// given
		String phone = "+48 517 725-068";
		// when
		phone = CharMatcher.anyOf("-+").removeFrom(phone);
		// then
		assertThat(phone).isEqualTo("48 517 725 068");
	}

	@Test
	public void testTrim() {
		// given
		String phone = "    +48 517 725 068 ";
		// when
		phone = CharMatcher.WHITESPACE.trimFrom(phone);
		// then
		assertThat(phone).isEqualTo("+48 517 725 068");
	}

}
