package org.gtug.lodz.guava.strings;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class JoinerTest {
	@Test
	public void testJoinerWithNull() {
		Joiner joiner = Joiner.on(" ").skipNulls();

		String joint = joiner
				.join("GTUG", "Łódź", "meets", "JUG", null, "Łódź");

		assertThat(joint).isEqualTo("GTUG Łódź meets JUG Łódź");
	}

	@Test
	public void testJoinerOnObject() {
		Joiner joiner = Joiner.on("\n").useForNull("null!");

		String joint = joiner.join(new Object(), new Object(), null,
				ImmutableList.of("Java", "meets", "Google"));

		assertThat(joint).contains("null!");
		assertThat(joint).contains("[Java, meets, Google]");
	}
}
